pipeline {
    agent any

    parameters {
        string(name: 'KEYS', defaultValue: '', description: 'Clés de tests à exécuter (séparées par ;)')
    }

    environment {
        JIRA_ID = credentials('JIRA_IDS')
        KEYS = "${params.KEYS}"
        DISCORD_WEBHOOK = "https://discord.com/api/webhooks/1363533361230119103/RfREgncYSYbTKzNOisEMoz_BHpF3tLIu3Ab7Drt4HN0UAAUo20WKuqz2nSDtSnFqGsnp"
    }

    stages {
        stage('Build & Test') {
            steps {
                script {
                    sh '''
                        chmod +x ./run-tests.sh
                        ./run-tests.sh "${JIRA_ID_USR}" "${JIRA_ID_PSW}" "${KEYS}"
                    '''
                }
            }
            post {
                always {
                    sh '''
                        chmod +x send-discord-report.sh
                        ./send-discord-report.sh "${DISCORD_WEBHOOK}" "$JOB_NAME" "$BUILD_URL"
                    '''
                    cucumber fileIncludePattern: 'target/cucumber.json'
                    cleanWs()
                }
            }
        }
    }
}
