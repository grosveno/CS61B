public class ArrayDeque<T> {
    private T[] arr;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int capacity;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        nextFirst = 3;
        nextLast = 4;
    }

    private int getFirst() {
        int first;
        if (nextFirst == capacity - 1) {
            first = 0;
        } else {
            first = nextFirst + 1;
        }
        return first;
    }

    private int getLast() {
        int last;
        if (nextLast == 0) {
            last = capacity - 1;
        } else {
            last = nextLast - 1;
        }
        return last;
    }

    private void resize(int n) {
        T[] a = (T[]) new Object[n];
        int first = getFirst(), last = getLast();
        if (first > last) {
            System.arraycopy(arr, 0, a, 0, last + 1);
            System.arraycopy(arr, first, a, n - (capacity - first), capacity - first);
            nextFirst = n - (capacity - first) - 1;
        } else {
            System.arraycopy(arr, first, a, n / 4, size);
            nextFirst = n / 4 - 1;
            nextLast = nextFirst + size + 1;
        }
        arr = a;
        capacity = n;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        arr[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = capacity - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        arr[nextLast] = item;
        if (nextLast == capacity - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int first = getFirst(), last = getLast();
        if (first < last) {
            for (int i = first; i < last + 1; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = first; i < capacity - 1; i++) {
                System.out.print(arr[i] + " ");
            }
            for (int i = 0; i < last + 1; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    public T removeFirst() {
        if (size >= 16 && (float) capacity / size > 2.5) {
            resize(capacity / 2);
        }
        if (size == 0) {
            return null;
        } else {
            int first = getFirst();
            T firstElement = arr[first];
            arr[first] = null;
            size -= 1;
            nextFirst = first;
            return firstElement;
        }
    }

    public T removeLast() {
        if (size >= 16 && (float) capacity / size > 2.5) {
            resize(capacity / 2);
        }
        if (size == 0) {
            return null;
        } else {
            int last = getLast();
            T lastElement = arr[last];
            arr[last] = null;
            size -= 1;
            nextLast = last;
            return lastElement;
        }
    }

    public T get(int index) {
        int first = getFirst();
        index = (first + index) % capacity;
        return arr[index];
    }
}
