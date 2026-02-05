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
                    bat "mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/chrometestng.xml -Denv=qa"
                }
            }
        }

        stage('QA Chrome Cucumber HTML Report'){
                  steps{
                         publishHTML([allowMissing: false,
                                      alwaysLinkToLastBuild: false,
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
                        bat "mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/firefoxtestng.xml -Denv=qa"
					}
               }
         }

        stage('QA Firefox Cucumber HTML Report'){
                 steps{
                         publishHTML([allowMissing: false,
                                     alwaysLinkToLastBuild: false,
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
            always {
				cucumber jsonReportDirectory: 'target/cucumber-reports/',
                fileIncludePattern: 'cucumber-report-chrome.json',
                reportTitle: 'QA Chrome Cucumber JSON Report'

				cucumber jsonReportDirectory: 'target/cucumber-reports/',
                fileIncludePattern: 'cucumber-report-firefox.json',
                reportTitle: 'QA Firefox Cucumber JSON Report'
        }
    }
}