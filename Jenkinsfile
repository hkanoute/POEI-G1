pipeline{
    agent any
       environment {
            CLIENT_ID = credentials('CLIENT_ID')
            CLIENT_SECRET = credentials('CLIENT_SECRET')
        }
    stages{
        stage('Verify'){
            steps{
                bat 'mvn verify'
            }
        }
    }
}