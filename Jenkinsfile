pipeline {
	agent any

	tools {
		maven 'MAVEN_HOME'
	}

	stages {

		stage('Build'){
			steps {
				bat "mvn clean package"
			}
		}

// 		stage('Test'){
// 			steps{
// 				bat "mvn test"
// 			}
// 		}

		stage('Deploy') {
			steps {
			    bat "mvn jar:jar deploy:deploy"
			}
		}
	}
}