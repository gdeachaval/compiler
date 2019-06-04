package lexer;

import org.junit.Test;

import java.util.function.Supplier;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestCharacterSupplier {

    @Test
    public void test001() {
        Supplier<Character> characterSupplier = new CharacterSupplier("test");
        assertThat(characterSupplier.get(), is(equalTo('t')));
        assertThat(characterSupplier.get(), is(equalTo('e')));
        assertThat(characterSupplier.get(), is(equalTo('s')));
        assertThat(characterSupplier.get(), is(equalTo('t')));
        assertNull(characterSupplier.get());
    }
}
