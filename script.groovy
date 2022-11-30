def buildImage(){
    echo "Building image with ${BUILD_NUMBER} ..."

    withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PSW', usernameVariable: 'USER')]) {

        sh "docker build -t 167.71.231.153:8082/sanskriti-portfolio:${BUILD_NUMBER} ."
        sh "echo ${PSW} | docker login -u ${USER} --password-stdin 167.71.231.153:8082"
        sh "docker push 167.71.231.153:8082/sanskriti-portfolio:${BUILD_NUMBER}" 

    }

}

def testApp(){
    echo "Testing application ..."
}

def deployApp(){
    echo "Deploying application ..."
} 

def updateVersion(){
    sh '''
        sed -i 's/Version:.*/Version: '"${BUILD_NUMBER}"'/g' index.html
    '''
}

def commitChanges(){
    echo "Update version in the application"

    withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'PSW', usernameVariable: 'USER')]) {

        sh 'git config --global user.name "jenkins"'
        sh 'git config --global user.email "my.jenkins.server@gmail.com"'
        sh "git remote set-url origin https://${USER}:${PSW}@github.com/BhairaviSanskriti/nexus-jenkins-CI-pipeline.git"
        
        sh "git add ."
        sh 'git commit -m "updated version"'
        sh "git push origin HEAD:main"
        
  }

}

return this