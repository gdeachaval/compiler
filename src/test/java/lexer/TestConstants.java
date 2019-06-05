package lexer;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TestConstants {

    @Test
    public void testSeparators() {
        assertTrue("\n".matches(Constants.SEPARATOR));
        assertTrue(" ".matches(Constants.SEPARATOR));
        assertTrue("\t".matches(Constants.SEPARATOR));
        assertTrue(";".matches(Constants.SEPARATOR));
        assertTrue(":".matches(Constants.SEPARATOR));
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
    public void testNotANumber() {
        assertTrue("+".matches(Constants.NOT_A_NUMBER));
    }

    @Test
    public void testNotAlphanumeric() {
        assertTrue("+".matches(Constants.NOT_ALPHANUMERIC));
        assertTrue(" ".matches(Constants.NOT_ALPHANUMERIC));
        assertFalse("a".matches(Constants.NOT_ALPHANUMERIC));
        assertFalse("A".matches(Constants.NOT_ALPHANUMERIC));
        assertFalse("0".matches(Constants.NOT_ALPHANUMERIC));
    }

    @Test
    public void testOperator() {
        assertTrue("+".matches(Constants.OPERATOR));
        assertTrue("-".matches(Constants.OPERATOR));
        assertTrue("*".matches(Constants.OPERATOR));
        assertTrue("/".matches(Constants.OPERATOR));
    }

    @Test
    public void testNotOperator() {
        assertFalse("+".matches(Constants.NOT_AN_OPERATOR));
        assertFalse("-".matches(Constants.NOT_AN_OPERATOR));
        assertFalse("*".matches(Constants.NOT_AN_OPERATOR));
        assertFalse("/".matches(Constants.NOT_AN_OPERATOR));
        assertTrue("a".matches(Constants.NOT_AN_OPERATOR));
    }
}
