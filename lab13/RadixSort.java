import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int maxLen = 0;
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            String s = asciis[i];
            maxLen = Math.max(maxLen, s.length());
            sorted[i] = s;
        }
        for (int i = maxLen - 1; i >= 0; i--) {
            sorted = sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */

    private static String[] sortHelperLSD(String[] asciis, int index) {
        int[] counts = new int[256];
        for (String s : asciis) {
            int ascii;
            if (index >= s.length()) {
                ascii = 0;
            } else {
                ascii = (int) s.charAt(index);
            }
            counts[ascii] += 1;
        }

        int[] start = new int[256];
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            start[i] = pos;
            pos += counts[i];
        }
        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            char c;
            if (index >= s.length()) {
                c = (char) 0;
            } else {
                c = s.charAt(index);
            }
            int p = start[(int) c];
            sorted[p] = s;
            start[(int) c] += 1;
        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] array = {"abc", "z", "hello", "world"};
        String[] sorted = sort(array);
        System.out.println(Arrays.toString(sorted));
    }
}
