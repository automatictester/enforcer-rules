#!groovy

multibranchPipelineJob('enforcer-rules') {
    branchSources {
        git {
            id('160F213D-6B5A-4997-A88C-8CEB1A7C4E60')
            remote('git@github.com:automatictester/enforcer-rules.git')
            credentialsId('github-creds')
        }
    }
}
