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
                        bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/firefoxtestng.xml -Denv=qa"
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
                echo 'Archiving cucumber reports logs jars and testng xml results'
                testNG reportFilenamePattern: '**/target/surefire-reports/testng-results.xml'
                archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                archiveArtifacts artifacts: 'target/logs/*.log', followSymlinks: false
                cucumber buildStatus: 'UNCHANGED', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/cucumber-report-chrome.json', jsonReportDirectory: 'target/cucumber-reports/', pendingStepsNumber: -1, reportTitle: 'Cucumber Chrome Json Report', skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
                cucumber buildStatus: 'UNCHANGED', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/cucumber-report-firefox.json', jsonReportDirectory: 'target/cucumber-reports/', pendingStepsNumber: -1, reportTitle: 'Cucumber Firefox Json Report', skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
            }
        }
}