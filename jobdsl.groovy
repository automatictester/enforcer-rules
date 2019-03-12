#!groovy

multibranchPipelineJob('enforcer-rules') {
    branchSources {
        git {
            remote('git@github.com:automatictester/enforcer-rules.git')
            credentialsId('github-creds')
        }
    }
}
