pipeline
{
    agent any
    
    stages{
        stage ('Build'){
            steps{
                echo "Hello Building"
            }
        }
        stage ('Test'){
            steps{
                echo "Hello testing"
            }
        }
        stage ('Deploy'){
            steps{
                echo "Hello deploying"
            }
        }
        stage ('Post'){
            steps{
               emailext body: 'Pipeline passed', subject: 'Jenkins test', to: 'rajan.parani@gmail.com'
            }
        }
    }
}
