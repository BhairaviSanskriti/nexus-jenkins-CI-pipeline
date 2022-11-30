pipeline{
    agent any

    stages{

        stage("Init"){
            steps{
                script{
                    gv_script = load "script.groovy"
                }
            }
        }

        stage("Build"){
            steps{
                script{
                    gv_script.buildImage()
                }   
            }
        }
    
        stage("Test"){
            steps{
                script{
                    gv_script.testApp()
                }
            }
        }

        stage("Deploy"){
            steps{
                script{
                    gv_script.deployApp()
                }
            }
        }
        stage("Update Version"){
            steps{
                script{
                    gv_script.updateVersion()
                }
            }
            post {
              success {
                    sh "Version updated"
                }

            }
        }

        stage("Commit changes"){
            steps{
                script{
                    gv_script.commitChanges()
                }
            }
            post {
              success {
                    sh "Changes committed"
                }

            }
        }
    }

    post {
        success {
            echo 'Build was successful.'
        }
    }

}