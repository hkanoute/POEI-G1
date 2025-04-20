#!/bin/bash

WEBHOOK_URL="$1"
JOB_NAME="$2"
BUILD_URL="$3"
JSON_FILE="target/cucumber.json"

if [ ! -f "$JSON_FILE" ]; then
  echo "❌ Fichier cucumber.json introuvable"
  exit 1
fi

# Parsing structuré via jq
STATS=$(jq '
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
  .failed = (.total - .passed)
' "$JSON_FILE")

TOTAL=$(echo "$STATS" | jq -r '.total')
PASSED=$(echo "$STATS" | jq -r '.passed')
FAILED=$(echo "$STATS" | jq -r '.failed')

# Vérification
if [ -z "$TOTAL" ] || [ -z "$PASSED" ] || [ -z "$FAILED" ]; then
  echo "❌ Parsing invalide du fichier cucumber.json"
  exit 1
fi

# Construction de l'embed Discord
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

# Debug (optionnel)
echo "📦 JSON envoyé à Discord :"
echo "$EMBED"

# Envoi vers Discord
curl -H "Content-Type: application/json" \
     -X POST \
     -d "$EMBED" \
     "$WEBHOOK_URL"
