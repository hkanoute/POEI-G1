#!/bin/bash

# Read inputs from Jenkins pipeline
CLIENT_ID="$1"
CLIENT_SECRET="$2"
TEST_KEYS="$3"

# Step 1: Authenticate
echo "🔐 Authenticating with Xray..."
curl -H "Content-Type: application/json" \
  -X POST \
  --data "{ \"client_id\": \"${CLIENT_ID}\", \"client_secret\": \"${CLIENT_SECRET}\" }" \
  https://xray.cloud.getxray.app/api/v2/authenticate > token.txt

# Check if the token was successfully obtained
if [ $? -ne 0 ]; then
  echo "❌ Failed to obtain token. Please check your credentials."
  exit 1
fi

TOKEN=$(cat token.txt | tr -d '"')
if [ -z "$TOKEN" ]; then
  echo "❌ Token is empty. Please check your credentials."
  exit 1
fi

echo "✅ Token obtained: $TOKEN"

# Step 2: Download Cucumber features
echo "📥 Downloading feature files for keys: $TEST_KEYS"
curl -X GET "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=${TEST_KEYS}" \
  -H "Authorization: Bearer $TOKEN" \
  -o features.zip

# Extract features
mkdir -p src/test/resources/features
unzip -o features.zip -d src/test/resources/features/

# Step 3: Run tests
echo "🚀 Running tests..."
mvn clean test

# Step 4: Upload test results
echo "📤 Uploading test results to Xray..."
curl -H "Content-Type: application/json" \
  -X POST \
  -H "Authorization: Bearer $TOKEN" \
  --data @"target/cucumber.json" \
  https://xray.cloud.getxray.app/api/v2/import/execution/cucumber

echo "✅ Test results uploaded!"
