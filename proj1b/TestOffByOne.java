import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('x', 'y'));
        assertTrue(offByOne.equalChars('y', 'x'));
        assertTrue(offByOne.equalChars('&', '%'));

        assertFalse(offByOne.equalChars('x', 'x'));
        assertFalse(offByOne.equalChars('x', 'a'));

    }


}
