pipeline {
	agent any

	environment {
		mavenHome = tool 'MAVEN_HOME'
	}

	tools {
		jdk 'JDK17'
	}

	stages {

		stage('Build'){
			steps {
				bat "mvn clean"
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