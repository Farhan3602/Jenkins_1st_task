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
                sshagent(['SecondServer']) {
                sh 'ssh -o StrictHostKeyChecking=no ubuntu@65.2.3.16 "sudo rm -r /var/www/html/Netflix/" '
                sh 'scp -o StrictHostKeyChecking=no -r * ubuntu@65.2.3.16:/home/ubuntu'
                sh 'ssh -o StrictHostKeyChecking=no ubuntu@65.2.3.16 "pwd"'
                }
            }        
        }
        stage('Moving'){
            steps{
                sh 'scp -o StrictHostKeyChecking=no ubuntu@65.2.3.16 "sudo mv /home/ubuntu /var/www/html/Netflix/" '
            }
        }
    }
}