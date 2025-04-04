pipeline{
    agent any
    parameters {
        string(name: 'KEYS', defaultValue: '', description: 'Veuillez renseigner les cles de test a executer separees par un ;')
    }
    environment {
        CLIENT_ID = credentials('CLIENT_ID')
        CLIENT_SECRET = credentials('CLIENT_SECRET')
        KEYS = "${params.KEYS}"
    }

    stages{
        stage('Build & Test'){
            steps{
                bat 'mvn clean verify'
            }
        }
    }
     post {
        always {
            cucumber fileIncludePattern: 'target/cucumber.json'
        }
     }
}