#!/bin/bash

WEBHOOK_URL="$1"
JOB_NAME="$2"
BUILD_URL="$3"
JSON_FILE="target/cucumber.json"

if [ ! -f "$JSON_FILE" ]; then
  echo "❌ Fichier cucumber.json introuvable"
  exit 1
fi

read -r TOTAL PASSED FAILED <<< $(jq '
  reduce .[].elements[] as $e (
    {"total": 0, "passed": 0, "failed": 0};
    if $e.type == "scenario" then 
      .total += 1 |
      if ($e.steps[].result.status == "passed") then 
        .passed += 1 
      else 
        .failed += 1 
      end 
    else . end
  ) | 
  .failed = (.total - .passed) | 
  "\(.total) \(.passed) \(.failed)"
' "$JSON_FILE")

EMBED=$(jq -n   --arg job "$JOB_NAME"   --arg url "$BUILD_URL"   --arg total "$TOTAL"   --arg passed "$PASSED"   --arg failed "$FAILED"   --arg timestamp "$(date --iso-8601=seconds)"   '{
    embeds: [
      {
        title: "🧪 Rapport Jenkins - \($job)",
        color: 3447003,
        fields: [
          { name: "✅ Réussis", value: $passed, inline: true },
          { name: "❌ Échecs", value: $failed, inline: true },
          { name: "📊 Total", value: $total, inline: true }
        ],
        footer: {
          text: "Voir le job Jenkins",
          icon_url: "https://www.jenkins.io/images/logos/jenkins/jenkins.png"
        },
        url: $url,
        timestamp: $timestamp
      }
    ]
  }')

  if [ -z "$TOTAL" ] || [ -z "$PASSED" ] || [ -z "$FAILED" ]; then
  echo "❌ Parsing invalide du fichier cucumber.json"
  exit 1
fi

# debug
echo "JSON envoyé à Discord :"
echo "$EMBED"

curl -H "Content-Type: application/json" \
     -X POST \
     -d "$EMBED" \
     "$WEBHOOK_URL"

# debug
echo "Réponse de Discord :"
curl -H "Content-Type: application/json" \
     -X POST \
     -d "$EMBED" \
     "$WEBHOOK_URL" | jq
echo "✅ Rapport envoyé à Discord avec succès"
