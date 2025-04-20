pipeline {
    agent any

    parameters {
        string(name: 'KEYS', defaultValue: '', description: 'Veuillez renseigner les cles de test à executer separees par un ;')
    }

    environment {
        JIRA_ID    = credentials('JIRA_IDS')
        KEYS = "${params.KEYS}"
    }

    stages {
        stage('Build'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('Curl') {
            steps {
                script{
                    sh 'curl -H "Content-Type: application/json" -X POST --data "{ \\"client_id\\": \\"%JIRA_ID_USR%\\",\\"client_secret\\": \\"%JIRA_ID_PSW%\\" }"  https://xray.cloud.getxray.app/api/v2/authenticate >token.txt'
                    sh """
                                        set /p TOKEN=<token.txt
                                        curl -X GET "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=${KEYS}" ^
                                        -H "Authorization: Bearer %TOKEN%" ^
                                        -o features.zip
                                    """
                    sh 'tar -xf features.zip -C src/test/resources/features/'
                }
            }
        }
        stage('Test'){
            steps{
                sh 'mvn test'
            }
            post {
                always {
                    sh """
                        set /p TOKEN=<token.txt
                        curl -H "Content-Type: application/json" -X POST -H "Authorization: Bearer %TOKEN%" ^
                        --data @"target/cucumber.json" https://xray.cloud.getxray.app/api/v2/import/execution/cucumber
                    """
                    junit 'target/surefire-reports/*.xml'
                    cucumber fileIncludePattern: 'target/cucumber.json'
                    cleanWs()
                }
            }
        }
    }

}
