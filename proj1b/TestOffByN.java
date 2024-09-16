import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        CharacterComparator comparator = new OffByN(5);
        assertTrue(comparator.equalChars('a', 'f'));
        assertTrue(comparator.equalChars('f', 'a'));
        assertFalse(comparator.equalChars('f', 'h'));

    }
}
