import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
   
public class Deque<Item> implements Iterable<Item> {
    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int N; // number of itens

    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You cant add a null element");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    // add the item to the back
    public void addFirst(Item item) {
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.next = oldfirst;
       first.prev = null;
       if (isEmpty()) {
           last = first;
       } else {
           oldfirst.prev = first;
       }
       N++;
    }

    // remove and return the item from the front
    public Item removeLast() {
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Node oldlast = last;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        N--;
        return oldlast.item;
    }

    // remove and return the item from the back
    public Item removeFirst() {
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Node oldFirst = first;
        first = first.next;
        if(first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        N--;
        return oldFirst.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator(); }
    private class ListIterator implements Iterator<Item> {
        private Node current = last;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() { throw new UnsupportedOperationException("Dont use remove"); }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no element next");
            }
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Add first in initialy empty deque and then add last
        Deque<String> d = new Deque<String>();
        d.addLast("1");
        d.removeFirst();
        d.addLast("3");
        d.removeLast();
        for (String s: d)
            StdOut.println(s);
    }

}
