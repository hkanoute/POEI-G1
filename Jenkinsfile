pipeline{
    agent any
       environment {
            CLIENT_ID = credentials('CLIENT_ID_CREDENTIAL_ID')
            CLIENT_SECRET = credentials('CLIENT_SECRET_CREDENTIAL_ID')
        }
    stages{
        stage('Verify'){
            steps{
                bat 'mvn verify'
            }
        }
    }
}