node {
   stage 'Checkout'

   checkout scm

   stage 'Build'

   sh "rm -rf build/libs/"
   sh "chmod +x gradlew"
   sh "./gradlew build -Pbuildnumber=${env.BUILD_NUMBER}"

   stage "Archive artifacts"

   archive 'build/libs/*'
}