pipeline 
{
    agent any
    
    tools{
        maven 'Maven'
        }

    stages 
    {
        stage("Deploy to QA"){
            steps{
                echo("deploy to QA")
            }
        }
        
        stage('Clean Workspace') {
            steps {
                script {
                    // Clean the workspace before running the job
                    cleanWs()
                }
            }
        }
                
        stage('Run Tests On Chrome QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/AshwiniBhawar/ParaBankApplication.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/chrometestng.xml -Denv=qa"
                    
                }
            }
        }

        stage('QA Chrome Cucumber HTML Report'){
                  steps{
                         publishHTML([allowMissing: false,
                                      alwaysLinkToLastBuild: true,
                                      keepAll: true,
                                      reportDir: 'target/cucumber-reports/',
                                      reportFiles: 'cucumber-report-chrome.html',
                                      reportName: 'QA Chrome Cucumber HTML Report',
                                      reportTitles: ''])
                 }
        }

        stage('Run Tests On Firefox QA') {
               steps {
                   catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        git 'https://github.com/AshwiniBhawar/ParaBankApplication.git'
                        bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/firefoxtestng.xml -Denv=qa"
                   }
               }
         }
        
        stage('QA Firefox Cucumber HTML Report'){
                 steps{
                         publishHTML([allowMissing: false,
                                     alwaysLinkToLastBuild: true,
                                     keepAll: true,
                                     reportDir: 'target/cucumber-reports/',
                                     reportFiles: 'cucumber-report-firefox.html',
                                     reportName: 'QA Firefox Cucumber HTML Report',
                                     reportTitles: ''])
                 }
        }

         stage("Deploy to UAT"){
                  steps{
                         echo("deploy to UAT")
                  }
         }
    }

    post {
            success {
                testng '**/target/surefire-reports/testng-results.xml'
                archiveArtifacts 'target/*.jar'
                archiveArtifacts 'target/logs/*.log'
                archiveArtifacts 'target/cucumber-reports/*.html'
            }
        }
}