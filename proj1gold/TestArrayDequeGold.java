import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void TestArrayDeque() {
        StudentArrayDeque<Integer> lst1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> lst2 = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 50; i++) {
            double rate = StdRandom.uniform();

            if (rate < 0.25) {
                lst1.addFirst(i);
                lst2.addFirst(i);
                message = String.format("%s addFirst(%d)\n", message, i);
            } else if (rate < 0.5){
                lst1.addLast(i);
                lst2.addLast(i);
                message = String.format("%s addLast(%d)\n", message, i);
            } else if (rate < 0.75 && !lst1.isEmpty()&& !lst2.isEmpty()) {
                Integer a = lst1.removeFirst();
                Integer b = lst2.removeFirst();
                message = String.format("%s removeFirst()\n", message);
                assertEquals(message, a, b);
            } else if (!lst1.isEmpty() && !lst2.isEmpty()) {
                Integer a = lst1.removeLast();
                Integer b = lst2.removeLast();
                message = String.format("%s removeLast()\n", message);
                assertEquals(message, a, b);
            }
        }
    }
}
