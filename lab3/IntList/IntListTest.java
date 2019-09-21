import static org.junit.Assert.*;

import org.junit.Test;

public class IntListTest {

    /**
     * Example test that verifies correctness of the IntList.list static
     * method. The main point of this is to convince you that
     * assertEquals knows how to handle IntLists just fine.
     */

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.of(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testdSquareList() {
        IntList L = IntList.of(1, 2, 3);
        IntList.dSquareList(L);
        assertEquals(IntList.of(1, 4, 9), L);
    }

    /**
     * Do not use the new keyword in your tests. You can create
     * lists using the handy IntList.list method.
     * <p>
     * Make sure to include test cases involving lists of various sizes
     * on both sides of the operation. That includes the empty list, which
     * can be instantiated, for example, with
     * IntList empty = IntList.of().
     * <p>
     * Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     * Anything can happen to A.
     */

    @Test
    public void testSquareListRecursive() {
        IntList L = IntList.of(1, 2, 3);
        IntList res = IntList.squareListRecursive(L);
        assertEquals(IntList.of(1, 2, 3), L);
        assertEquals(IntList.of(1, 4, 9), res);
    }

    @Test
    public void testDcatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.dcatenate(A, B));
        assertEquals(IntList.of(1, 2, 3, 4, 5, 6), A);
    }

    @Test
    public void testCatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.catenate(A, B));
        assertEquals(IntList.of(1, 2, 3), A);
    }

    @Test(timeout = 1000)
    public void testReverse(){
        /*
        * That the function returns a reversed list. 检测reverse: 用俩list!!
        * That the function is destructive, 理解上的难点：即引用一样，但值已经改了
        * i.e. when it is done running, the list pointed to by A has been tampered with. You can use assertNotEquals. This is sort of a silly test.
        * That the method handles a null input properly.
        */

        // 1. 检测reverse: 用俩list
//        IntList input  = IntList.of(1, 2, 3);
//        IntList expected = IntList.of(3, 2, 1);
//        IntList actual = IntList.reverse(input);
//        assertEquals(expected, actual);

        // 2*. 检测引用不变值变了：用1个list!!!
        IntList input2 = IntList.of(1, 2, 3);
        assertNotEquals(input2, IntList.reverse(input2));

        // 3. null
        assertEquals(null, IntList.of());

    }

    /** If you're running this from the command line, you'll need
      * to add a main method. See ArithmeticTest.java for an
      * example. */

}
