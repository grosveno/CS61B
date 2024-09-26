package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
    }

    private void updateLast() {
        this.last = (this.last + 1) % this.capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Queue is full.");
        }
        this.rb[last] = x;
        this.updateLast();
        this.fillCount += 1;
    }

    private void updateFirst() {
        this.first = (this.first + 1) % this.capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        T front = this.peek();
        this.updateFirst();
        this.fillCount -= 1;
        return front;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        return this.rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;

        public ArrayRingBufferIterator() {
            wizPos = first;
        }

        public boolean hasNext() {
            return (wizPos - first + 1) < fillCount;
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos = (wizPos + 1) % capacity;
            return returnItem;
        }
    }
}
