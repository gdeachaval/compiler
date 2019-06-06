package lexer;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestConstants {

    @Test
    public void testSeparators() {
        assertTrue(";".matches(Constants.SEPARATOR));
        assertTrue(":".matches(Constants.SEPARATOR));
        assertTrue("=".matches(Constants.SEPARATOR));
        assertTrue("(".matches(Constants.SEPARATOR));
        assertTrue(")".matches(Constants.SEPARATOR));
    }

    @Test
    public void testSpaces() {
        assertTrue("\n".matches(Constants.SPACE));
        assertTrue(" ".matches(Constants.SPACE));
        assertTrue("\t".matches(Constants.SPACE));
    }

    @Test
    public void testAlphanumeric() {
        assertTrue("a".matches(Constants.ALPHANUMERIC));
        assertTrue("A".matches(Constants.ALPHANUMERIC));
        assertTrue("0".matches(Constants.ALPHANUMERIC));
    }

    @Test
    public void testLetter() {
        assertTrue("a".matches(Constants.LETTER));
        assertTrue("A".matches(Constants.LETTER));
    }

    @Test
    public void testOperator() {
        assertTrue("+".matches(Constants.OPERATOR));
        assertTrue("-".matches(Constants.OPERATOR));
        assertTrue("*".matches(Constants.OPERATOR));
        assertTrue("/".matches(Constants.OPERATOR));
    }
}
