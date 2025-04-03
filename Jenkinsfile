pipeline{
    agent any
       environment {
            CLIENT_ID = credentials('CLIENT_ID')
            CLIENT_SECRET = credentials('CLIENT_SECRET')
       }

    stages{
        stage('build'){
            steps{
                bat 'mvn clean'
            }
        }
        stage('test'){
            steps   {
                bat 'mvn test'
            }
        }
    }
}