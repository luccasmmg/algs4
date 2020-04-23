import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() { }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    private void swap(int index) {
        Item lastItem = a[N-1];
        Item item = a[index];
        a[index] = lastItem;
        a[N-1] = item;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }


    // remove and return a random item
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, N);
        swap(index);
        Item item = a[--N];
        a[N] = null;
        if (N < 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, N);
        return a[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }
    private class RandomArrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {return size() > 0; }
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            return dequeue();
        }
        public void remove() { throw new UnsupportedOperationException(); }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        r.enqueue("01");
        r.enqueue("02");
        r.enqueue("03");
        r.enqueue("04");
        r.enqueue("05");
        r.enqueue("06");
        r.enqueue("07");
        r.enqueue("08");
        r.enqueue("09");
        r.enqueue("10");
        r.enqueue("11");
        r.enqueue("12");
        r.dequeue();
        for (String s: r)
            StdOut.println(s);
    }

}
