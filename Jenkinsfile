def buildNumber = env.BUILD_NUMBER as int
if (buildNumber > 1) milestone(buildNumber - 1)
milestone(buildNumber)

pipeline {
	agent any

	tools {
		maven 'MAVEN_HOME'
		jdk 'JDK17'
	}

	stages {

		stage('Build'){
			steps {
				bat "mvn clean install"
			}
		}

// 		stage('Test'){
// 			steps{
// 				bat "mvn test"
// 			}
// 		}

		stage('Deploy') {
// 			steps {
// 			    bat "mvn deploy -Dmaven.install.skip=true -DskipTests"
// 			}
			steps {
            	bat "mvn spring-boot:run"
            }
		}
	}
}