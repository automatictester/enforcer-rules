package uk.co.automatictester.enforcer.rules;

class ProjectVersionComparer {

    private ProjectVersionComparer() {}

    static boolean isMatching(String projectVersion, String versionPattern) {
        return projectVersion.matches(versionPattern);
    }
}
