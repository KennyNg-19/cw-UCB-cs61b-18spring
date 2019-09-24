

public class ArrayDequeTest {

    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
//		System.out.println("PASS checkEmpty");
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
//		System.out.println("PASS checkSize");
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }
    
    public static void addRemoveTest() {

        System.out.print("Running add/remove test: ");

//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> ADeque = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, ADeque.isEmpty());

        ADeque.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, ADeque.isEmpty()) && passed;

        ADeque.addLast(20);
        passed = checkSize(2, ADeque.size()) && passed;

//        ADeque.removeFirst();
//        // should be empty
//        passed = checkEmpty(true, ADeque.isEmpty()) && passed;

        ADeque.addLast(30);
        ADeque.addLast(40);
        ADeque.addLast(50);
        ADeque.addLast(60);
        ADeque.addLast(70);
        ADeque.addFirst(5);
        ADeque.addLast(80);
        ADeque.printDeque();
//        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
//        addIsEmptySizeTest();
        addRemoveTest();
//		getTest();
    }
}
