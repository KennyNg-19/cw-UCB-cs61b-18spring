package synthesizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        int expected_fillCount = 3;
        assertEquals(expected_fillCount, arb.fillCount());
        arb.enqueue(4);
        arb.enqueue(5);


        int expected_remove = 1;
        int actual_remove = arb.dequeue();
        assertEquals(expected_remove, actual_remove);

        expected_fillCount = 4;
        assertEquals(expected_fillCount, arb.fillCount());

        arb.enqueue(6);
        int i = 1;
        for (int x : arb) {

            System.out.println(i + "th: " + x + " ");
        }

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
