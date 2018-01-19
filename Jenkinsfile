node {

    String mvntool = tool name: "3.5.0", type: 'hudson.tasks.Maven$MavenInstallation'
    String jdktool = tool name: "JDK8", type: 'hudson.model.JDK'
    String imageName = "akros/devopsjcc"
    String containerName = "DevOpsJCC"
    def image

    stage('Checkout') {
        checkout scm
    }

    stage('Initialize Tooling') {
        echo("Used JDK: ${jdktool}")
        echo("Used Maven: ${mvntool}")
    }

    stage('Build Application') {
        withEnv(["PATH+MVN=${mvntool}/bin", "PATH+JDK=${jdktool}/bin", "JAVA_HOME=${jdktool}", "MAVEN_HOME=${mvntool}"]) {
            sh 'mvn clean package'
        }
    }

    stage('Build Docker Image') {
        image = docker.build("${imageName}")
    }

	stage('Cleanup Docker Container') {
        sh "docker stop ${containerName} || true"
        sh "docker rm ${containerName} || true"
    }

    stage('Start Application') {
        image.run("-d -p 666:666 --name ${containerName} ${imageName}")
    }
    
    stage('Test Application') {
        sleep 5
        def status = sh(script: "docker ps -a -f 'name=${containerName}' --format '{{.Status}}'", returnStdout: true).trim()
        echo("${status}")
        script {
            if ("${status}".contains('Exited')) {
                error("Container is not started correctly.")
            }
        }
    }

    stage('UI-Test') {
    	withEnv(["PATH+MVN=${mvntool}/bin", "PATH+JDK=${jdktool}/bin", "JAVA_HOME=${jdktool}", "MAVEN_HOME=${mvntool}"]) {
            sh 'mvn verify -P IT-Test'
        }
        sh "docker stop ${containerName} || true"
        sh "docker rm ${containerName} || true"
    }
    
    stage('Publish to Docker Registry') {
    	docker.withRegistry('http://localhost:5000') {
            image.push("${env.BUILD_NUMBER}")
            image.push("latest")
        }
    }
    
    stage('Publish Test-Results') {
    	junit 'target/surefire-reports/*.xml,target/failsafe-reports/*.xml'
    }
}