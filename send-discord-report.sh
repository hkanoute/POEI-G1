#!/bin/bash

WEBHOOK_URL="$1"
JOB_NAME="$2"
BUILD_URL="$3"
JSON_FILE="target/cucumber.json"

if [ ! -f "$JSON_FILE" ]; then
  echo "❌ Fichier cucumber.json introuvable"
  exit 1
fi

# Utilise ton jq complet pour extraire les stats
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

# Format embed Discord
EMBED=$(cat <<EOF
{
  "embeds": [
    {
      "title": "🧪 Rapport Jenkins - ${JOB_NAME}",
      "color": 3447003,
      "fields": [
        { "name": "✅ Réussis", "value": "$PASSED", "inline": true },
        { "name": "❌ Échecs", "value": "$FAILED", "inline": true },
        { "name": "📊 Total", "value": "$TOTAL", "inline": true }
      ],
      "footer": {
        "text": "Voir le job Jenkins",
        "icon_url": "https://www.jenkins.io/images/logos/jenkins/jenkins.png"
      },
      "url": "${BUILD_URL}",
      "timestamp": "$(date --iso-8601=seconds)"
    }
  ]
}
EOF
)

curl -H "Content-Type: application/json" \
     -X POST \
     -d "$EMBED" \
     "$WEBHOOK_URL"
