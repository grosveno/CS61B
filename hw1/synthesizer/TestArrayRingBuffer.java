package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        int[] test = {6, 4, 8, 3, 9, 10, 309, 38, 78, 90};
        for (int i = 0; i < test.length; i++) {
            arb.enqueue(test[i]);
        }
        assertEquals(arb.peek(), 6);
        assertTrue(arb.isFull());
        for (int i = 0; i < test.length; i++) {
            assertEquals(arb.dequeue(), test[i]);
        }
        assertTrue(arb.isEmpty());

        for (int i = 0; i < 6; i++) {
            arb.enqueue(test[i]);
        }
        assertEquals(arb.dequeue(), 6);
        for (int i = 6; i < test.length; i++) {
            arb.enqueue(test[i]);
        }
        arb.enqueue(67);
        for (int i = 1; i < 10; i++) {
            assertEquals(arb.dequeue(), test[i]);
        }
        assertEquals(arb.dequeue(), 67);

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
