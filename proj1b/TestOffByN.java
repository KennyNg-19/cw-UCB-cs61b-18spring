import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {

    static OffByN offBy5 = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('f', 'h'));
    }
}
