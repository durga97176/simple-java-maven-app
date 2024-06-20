pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.0', '1.1', '1.2'])
        booleanParam(name: 'durga', defaultValue: true)
    }

    stages {
        stage('Clone application code') {
            steps {
                git 'https://github.com/durga97176/simple-java-maven-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn package -f pom.xml'
            }
        }

        stage('Upload to Nexus') {
            steps {
                nexusArtifactUploader credentialsId: 'nexuskey', 
                                     groupId: 'com.mycompany.app', 
                                     nexusUrl: 'http://54.167.126.21:8081/nexus', 
                                     nexusVersion: 'nexus2', 
                                     protocol: 'http', 
                                     repository: 'releases', 
                                     version: '1.0-SNAPSHOT'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deployed the application code'
            }
        }
    }
}
