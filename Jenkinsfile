pipeline {
    agent any

    parameters {
        string(name: 'KEYS', defaultValue: '', description: 'Clés de tests à exécuter (séparées par ;)')
    }

    environment {
        JIRA_ID = credentials('JIRA_IDS')
        KEYS = "${params.KEYS}"
        DISCORD_WEBHOOK = "https://discord.com/api/webhooks/1359154405147934992/2RwoZD57gNSStkB8yxAUT4O7jAe7OOAECZTCuMj9tDW6RBHYUaCjgon1E05MoTjsaQlg"
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
