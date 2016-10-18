node {
   stage 'Checkout'

   checkout scm

   stage 'Build'

   sh "rm -rf build/libs/"
   sh "chmod +x gradlew"
   sh "./gradlew build -Pbuildnumber=BRANCH-${env.BUILD_NUMBER}".replace('BRANCH', "${env.JOB_NAME}".replace('/', '_').replace('%2F', '_').replace('TheManiacs_ManiacCore_', ''))

   stage "Archive artifacts"

   archive 'build/libs/*'
}