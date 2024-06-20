pipeline {
    agent any
    parameters{
        choice{name:'VERSION',choice:['1.0','1.1','1.2']}
        boolenparam(name: 'durga',defaultValue: true)
    }

    stages {
        stage('Clone application code') {
            steps {
                git 'https://github.com/durga97176/simple-java-maven-app.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn package -f pom.xml'
                    } else {
                        bat 'mvn package -f pom.xml'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'deployed the application code'
            }
        }
    }
}
