node("master") {
  stage("Clone the project") {
    git branch: 'master', url: 'https://github.com/seyeadamaUASZ/microservice-new.git'
  }

  stage("build") {
    bat "mvn clean install -DskipTests"
  }


   stage("package") {
         bat "mvn clean package"
   }

    stage('Scan'){
         withSonarQubeEnv(installationName:'sonar'){
              bat 'mvn clean install -DskipTests org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=target/classes'
         }
     }

}