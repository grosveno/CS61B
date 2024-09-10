public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
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

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<String> lld2 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld2.isEmpty());

        lld2.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld2.size()) && passed;
        passed = checkEmpty(false, lld2.isEmpty()) && passed;

        lld2.addLast("middle");
        passed = checkSize(2, lld2.size()) && passed;

        lld2.addLast("back");
        passed = checkSize(3, lld2.size()) && passed;

        System.out.println("Printing out deque: ");
        lld2.printDeque();

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld2.isEmpty());

        lld2.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld2.isEmpty()) && passed;

        lld2.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld2.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
    }
}
