pipeline {
    agent any

    parameters {
        string(name: 'KEYS', defaultValue: '', description: 'Veuillez renseigner les clés de test à exécuter, séparées par un ";"')
        string(name: 'TEST_EXECUTION_KEY', defaultValue: '', description: 'Clé d\'exécution de test Xray')
    }

    environment {
        JIRA_ID = credentials('JIRA_IDS') // This should be a Jenkins credential of type 'Username and Password'
        KEYS    = "${params.KEYS}"
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Curl') {
            steps {
                script {
                    bat '''
                        curl -H "Content-Type: application/json" -X POST --data "{ \\"client_id\\": \\"%JIRA_ID_USR%\\", \\"client_secret\\": \\"%JIRA_ID_PSW%\\" }" ^
                        https://xray.cloud.getxray.app/api/v2/authenticate > token.txt
                    '''

                    bat """
                        set /p TOKEN=<token.txt
                        curl -X GET "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=${KEYS}" ^
                        -H "Authorization: Bearer %TOKEN%" ^
                        -o features.zip
                    """

                    bat 'tar -xf features.zip -C src/test/resources/features/'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }

            post {
                always {
                    // Generate xray-info.json with test execution metadata
                    bat """
                        echo {
                            \\"testExecutionKey\\": \\"${params.TEST_EXECUTION_KEY}\\",
                            \\"summary\\": \\"Execution triggered from Jenkins\\",
                            \\"description\\": \\"Automated run from Jenkins pipeline\\"
                        } > xray-info.json
                    """

                    // Send multipart request to Xray
                    bat """
                        set /p TOKEN=<token.txt
                        curl -X POST https://xray.cloud.getxray.app/api/v2/import/execution/cucumber/multipart ^
                        -H "Authorization: Bearer %TOKEN%" ^
                        -F "results=@target/cucumber.json;type=application/json" ^
                        -F "info=@xray-info.json;type=application/json"
                    """

                    junit 'target/surefire-reports/*.xml'
                    cucumber fileIncludePattern: 'target/cucumber.json'
                    cleanWs()
                }
            }
        }
    }
}
