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
        sh 'docker build -t kaskinas/courses-backend-ngrx:1.0.0 .'
        sh 'docker push kaskinas/courses-backend-ngrx:1.0.0'
    }
    }

 }
}
