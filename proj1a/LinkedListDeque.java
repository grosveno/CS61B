import java.util.List;

public class LinkedListDeque<T> {
    private class ListNode {
        public T item;
        public ListNode prev;
        public ListNode next;
        public ListNode() {
            this.prev = null;
            this.next = null;
        }
        public ListNode(T item, ListNode prev, ListNode next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }
    private ListNode sentinel;
    public int size;
    public LinkedListDeque() {
        sentinel = new ListNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        ListNode first = sentinel.next;
        ListNode newNode = new ListNode(item, sentinel, first);
        sentinel.next = newNode;
        first.prev = newNode;
        size += 1;
    }
    public void addLast(T item) {
        ListNode last = sentinel.prev;
        ListNode newNode = new ListNode(item, last, sentinel);
        sentinel.prev = newNode;
        last.next = newNode;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        ListNode ptr = sentinel.next;
        while(ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            ListNode ptr1 = sentinel.next;
            ListNode ptr2 = ptr1.next;
            ptr2.prev = sentinel;
            sentinel.next = ptr2;
            size -= 1;
            return ptr1.item;
        }
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            ListNode ptr1 = sentinel.prev;
            ListNode ptr2 = ptr1.prev;
            ptr2.next = sentinel;
            sentinel.prev = ptr2;
            size -= 1;
            return ptr1.item;
        }
    }
    public T get(int index) {
        ListNode ptr = sentinel.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }
    public T getRecursive(ListNode L, int index) {
        if (index == 0) {
            return L.item;
        }
        return getRecursive(L.next, index - 1);
    }
    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }
}
