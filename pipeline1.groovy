pipeline {
    agent any

    stages {
        stage('Clone application code') {
            steps {
                git 'https://github.com/durga97176/simple-java-maven-app.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn package -f pox.xml'
            }
        }
        stage('Deploy') {
            steps {
                echo 'deployed the application code'
            }
        }
    }
}
