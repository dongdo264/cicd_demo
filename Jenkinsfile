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
			steps {
			    bat "mvn deploy -Dmaven.install.skip=true -DskipTests"
			}
		}
	}
}