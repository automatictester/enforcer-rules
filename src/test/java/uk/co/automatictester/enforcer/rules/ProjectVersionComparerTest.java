package uk.co.automatictester.enforcer.rules;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectVersionComparerTest {

    @Test
    public void testVersionMatching()  {
        assertTrue(ProjectVersionComparer.isMatching("1.0.0", "\\d\\.\\d\\.\\d(?:-SNAPSHOT|$)"));
    }

    @Test
    public void testVersionNotMatching()  {
        assertFalse(ProjectVersionComparer.isMatching("1.0", "\\d\\.\\d\\.\\d(?:-SNAPSHOT|$)"));
    }
}
