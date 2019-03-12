package uk.co.automatictester.enforcer.rules;

import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;

public class RequireVersionRule implements EnforcerRule {

    private String versionPattern;

    public void execute(EnforcerRuleHelper helper) throws EnforcerRuleException {
        String projectVersion = getProjectVersion(helper);
        if (!ProjectVersionComparer.isMatching(projectVersion, versionPattern)) {
            throw new EnforcerRuleException("Project version doesn't match the pattern");
        }
    }

    private String getProjectVersion(EnforcerRuleHelper helper) throws EnforcerRuleException {
        String projectVersion;
        try {
            projectVersion = (String) helper.evaluate("${project.version}");
        } catch (ExpressionEvaluationException e) {
            throw new EnforcerRuleException("Unable to lookup an expression " + e.getMessage(), e);
        }
        helper.getLog().info("Project version: " + projectVersion);
        return projectVersion;
    }

    public String getCacheId() {
        return "" + versionPattern.hashCode();
    }

    public boolean isCacheable() {
        return false;
    }

    public boolean isResultValid(EnforcerRule rule) {
        return false;
    }
}
