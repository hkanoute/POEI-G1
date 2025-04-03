pipeline{
  agent any
  parameters {
    choice(name: 'ENV', choices: ['DEV','PROD'], description: 'Veuillez choisir un environnement de production')
    string(name: 'KEYS', defaultValue: '', description: 'Veuillez renseigner les clés de test à exécuter séparées par un ;')
  }
  environnement{
    API_URL = "${params.ENV == 'DEV' ? 'https://xray.cloud.getxray.app/api/v2' : 'https://xray.cloud.getxray.app/api/v2'}"
    CLIENT_ID = credentials('CLIENT_ID')
    CLIENT_SECRET = credentials('CLIENT_SECRET')
  }
  stages {
    stage('Afficher la configuration'){
      steps {
        script {echo "Exécution des test sur : ${API_URL}"}
      }
    }

    stage('Authenticate and Get Token') {
        steps {
            script {
                def authResponse = sh(
                    script: """
                    curl -s -X POST '${API_URL}/authenticate' \\
                    -H 'Content-Type: application/json' \\
                    -d '{ "client_id": "${CLIENT_ID}", "client_secret": "${CLIENT_SECRET}" }'
                    """,
                    returnStdout: true
                ).trim()

                env.AUTH_TOKEN = authResponse.replaceAll('"', '')
            }
        }
    }

    stage('Export Cucumber Features') {
        steps {
            script {
                sh """
                curl -s -X GET '${API_URL}/export/cucumber?keys=${KEYS}' \\
                -H 'Authorization: Bearer ${env.AUTH_TOKEN}' \\
                -H 'Content-Type: application/json' \\
                --output features.zip
                """

                sh "unzip -o features.zip -d src/test/resources/features"
            }
        }
    }

    stage('Build & Test'){
        steps{
                bat 'mvn clean verify'
            }
        }
    }

    stage('Upload Test Execution Report') {
        steps {
            script {
                def response = sh(
                    script: """
                    curl -s -X POST 'https://xray.cloud.getxray.app/api/v2/import/execution/cucumber' \\
                    -H 'Authorization: Bearer ${env.AUTH_TOKEN}' \\
                    -H 'Content-Type: application/json' \\
                    -d @target/cucumber.json
                    """,
                    returnStdout: true
                ).trim()

                echo "Xray Response: ${response}"
            }
        }
    }
  }
}