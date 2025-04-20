pipeline {
    agent any

    parameters {
        string(name: 'KEYS', defaultValue: '', description: 'Clés de tests à exécuter (séparées par ;)')
    }

    environment {
        JIRA_ID = credentials('JIRA_IDS')
        KEYS = "${params.KEYS}"
    }

    stages {
        stage('Build & Test') {
            steps {
                script {
                    sh '''
                        chmod +x ./xray.sh
                        ./xray.sh "${JIRA_ID_USR}" "${JIRA_ID_PSW}" "${KEYS}"
                    '''
                }
            }
        }
    }
}
