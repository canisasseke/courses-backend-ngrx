pipeline{
  agent any
  stages {
    stage('Compile-package') {
      steps {
        sh 'mvn install'
        }
      }
    stage('build docker') {
      steps {
        sh 'docker build -t kaskinas/courses-backend-ngrx:${BUILD_NUMBER} .'
        sh 'docker push kaskinas/courses-backend-ngrx:${BUILD_NUMBER}'
    }
    }

 }
}
