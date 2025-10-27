pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Fetch the repository using SSH credentials
                git branch: 'main', url: 'https://github.com/Farhan3602/Netflix_clone_lite'
            }
        }
        stage('Testing') {
            steps {
                sh 'scp -o StrictHostKeyChecking=no -r * ubuntu@65.2.3.16:/home/ubuntu' 
                sshagent(['SecondServer']) {
                sh 'ssh -o StrictHostKeyChecking=no ubuntu@65.2.3.16 "ls"'
                }
            }        
        }
    }
}