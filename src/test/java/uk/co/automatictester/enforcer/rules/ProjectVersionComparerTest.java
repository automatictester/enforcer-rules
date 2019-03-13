package uk.co.automatictester.enforcer.rules;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectVersionComparerTest {

    private static final String X_Y_Z_WITH_OPTIONAL_SNAPSHOT_PATTERN = "^\\d+\\.\\d+\\.\\d+(?:-SNAPSHOT|$)$";
    private static final String X_Y_Z_NON_SNAPSHOT_PATTERN = "^\\d+\\.\\d+\\.\\d+$";

    @DataProvider(name = "matchingXyzWithOptionalSnapshot")
    public Object[][] matchingXyzWithOptionalSnapshot() {
        return new String[][]{
                {"1.0.0"},
                {"1.2.3"},
                {"11.20.31"},
                {"1.0.0-SNAPSHOT"},
                {"10.20.30-SNAPSHOT"},
        };
    }

    @Test(dataProvider = "matchingXyzWithOptionalSnapshot")
    public void testVersionMatchingOptionalSnapshot(String version) {
        assertTrue(ProjectVersionComparer.isMatching(version, X_Y_Z_WITH_OPTIONAL_SNAPSHOT_PATTERN));
    }

    @DataProvider(name = "notMatchingXyzWithOptionalSnapshot")
    public Object[][] notMatchingXyzWithOptionalSnapshot() {
        return new String[][]{
                {"1.0"},
                {"1.2-SNAPSHOT"},
                {"1.2.3-SNAPSHOTT"},
                {"0.1.0-alpha-SNAPSHOT"},
                {"alpha-0.2.3"},
                {"a.1.0"},
        };
    }

    @Test(dataProvider = "notMatchingXyzWithOptionalSnapshot")
    public void testVersionNotMatchingOptionalSnapshot(String version) {
        assertFalse(ProjectVersionComparer.isMatching(version, X_Y_Z_WITH_OPTIONAL_SNAPSHOT_PATTERN));
    }

    @DataProvider(name = "matchingXyzWithoutSnapshot")
    public Object[][] matchingXyzWithoutSnapshot() {
        return new String[][]{
                {"1.0.0"},
                {"1.2.3"},
                {"11.20.31"},
        };
    }

    @Test(dataProvider = "matchingXyzWithoutSnapshot")
    public void testVersionMatchingNonSnapshot(String version) {
        assertTrue(ProjectVersionComparer.isMatching(version, X_Y_Z_NON_SNAPSHOT_PATTERN));
    }

    @DataProvider(name = "notMatchingXyzWithoutSnapshot")
    public Object[][] notMatchingXyzWithoutSnapshot() {
        return new String[][]{
                {"1.0"},
                {"1.2-SNAPSHOT"},
                {"1.2.3-SNAPSHOT"},
                {"0.1.0-alpha-SNAPSHOT"},
                {"alpha-0.2.3"},
                {"a.1.0"},
        };
    }

    @Test(dataProvider = "notMatchingXyzWithoutSnapshot")
    public void testVersionNotMatchingNonSnapshot(String version) {
        assertFalse(ProjectVersionComparer.isMatching(version, X_Y_Z_NON_SNAPSHOT_PATTERN));
    }
}
