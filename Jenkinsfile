pipeline {
  environment {
    // registry = ${docker_registry_name}/${app_name}
    registryCredential = "9b9c07c5-860c-444c-9b70-e86db78cf28a"
    app = ''
  }
  agent { label 'slave_01' }
  tools {
      gradle "Gradle-build"
  }
  stages {
    stage('Cloning Git') {
      steps {
        sh 'gradle --version'
        // git(
        //    url: 'git@github.com:digitalventures/lbc.git',
        //    branch: branch,
        // )
      }
    }
    // stage('Install dependencies') {
    //   steps{
    //     script {
    //       sh 'npm install'
    //       sh 'npm run prebuild'
    //       sh 'npm run build'
    //     }
    //   }
    // }
    // stage('Building image') {
    //   when {
    //     allOf {
    //       expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
    //     }
    //   }
    //   steps{
    //     script {
    //         sh 'docker stop ${app_name} || true && docker rm ${app_name} || true && docker image rm ${docker_registry_name}/${app_name} || true'
    //         sh 'docker build -t ${docker_registry_name}/${app_name} .'
    //         sh 'docker run -p 1333:3000 -d -e --network="host" --name ${app_name} ${docker_registry_name}/${app_name}'
    //     }
    //   }
    // }
  }
}