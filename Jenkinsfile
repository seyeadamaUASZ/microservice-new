node("master") {
  stage("Clone the project") {
    git branch: 'master', url: 'https://github.com/seyeadamaUASZ/microservice-new.git'
  }

  stage("build") {
    bat "mvn clean install -DskipTests"
  }
node("master") {
  stage("Clone the project") {
    git branch: 'master', url: 'https://github.com/seyeadamaUASZ/microservice-new.git'
  }

  stage("build") {
    bat "mvn clean install -DskipTests"
  }

  stage("run test") {
      bat "mvn test"
   }

   stage("package") {
         bat "mvn clean package"
   }

    stage('Scan Quality code'){
         withSonarQubeEnv(installationName:'sonar'){
              bat 'mvn clean install -DskipTests org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=target/classes'
         }
     }

     stage('Docker Build & push') {
        bat 'mvn clean compile jib:build'
     }

}node("master") {
   stage("Clone the project") {
     git branch: 'master', url: 'https://github.com/seyeadamaUASZ/microservice-new.git'
   }

   stage("build") {
     bat "mvn clean install -DskipTests"
   }

   stage("run test") {
       bat "mvn test"
    }

    stage("package") {
          bat "mvn clean package"
    }

     stage('Scan Quality code'){
          withSonarQubeEnv(installationName:'sonar'){
               bat 'mvn clean install -DskipTests org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=target/classes'
          }
      }

      stage('Docker Build & push') {
         bat 'mvn clean compile jib:build'
      }

 }
  stage("run test") {
      bat "mvn test"
   }

   stage("package") {
         bat "mvn clean package"
   }

    stage('Scan Quality code'){
         withSonarQubeEnv(installationName:'sonar'){
              bat 'mvn clean install -DskipTests org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=target/classes'
         }
     }

     stage('Docker Build & push') {
        bat 'mvn clean compile jib:build'
     }

}