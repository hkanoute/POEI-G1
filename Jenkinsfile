pipeline{
    agent any
       environment {
            CLIENT_ID = credentials('CLIENT_ID')
            CLIENT_SECRET = credentials('CLIENT_SECRET')
       }

    stages{
        stage('checkout') {
            steps {
               git branch: 'main',
               url: 'https://github.com/hkanoute/selenium-jenkins',
               credentialsId: 'git_credentials'
            }
        }
        stage('Build'){
            steps{
                bat 'mvn clean'
            }
        }
        stage('Test'){
            steps   {
                bat 'mvn verify'
            }
        }
        stage('Generate Report'){
            steps   {
                bat 'mvn surefire-report:report'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
             }
        }
    }
}