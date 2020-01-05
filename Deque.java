import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int count = 0;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.first == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item");
        }

        if (size() > 1) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.previous = first;
            first.previous = null;
            count++;
        } else if (size() == 1) {
            first = new Node();
            first.item = item;
            first.previous = null;
            first.next = last;
            last.previous = first;
            count++;
        } else {
            first = new Node();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
            count++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item");
        }

        if (size() > 1) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = oldLast;
            oldLast.next = last;
            count++;
        } else if (size() == 1) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = first;
            first.next = last;
            count++;
        } else {
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = null;
            first = last;
            count++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("Empty deque");
        } else if (size() == 1) {
            Item firstItem = first.item;
            first = null;
            last = null;
            count--;
            return firstItem;
        } else {
            Item firstItem = first.item;
            first = first.next;
            first.previous = null;
            count--;
            return firstItem;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException("Empty deque");
        } else if (size() == 1) {
            Item lastItem = last.item;
            first = null;
            last = null;
            count--;
            return lastItem;
        } else {
            Item lastItem = last.item;
            Node previousNode = last.previous;
            previousNode.next = null;
            last = previousNode;
            count--;
            return lastItem;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
       return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty deque");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> testDeque = new Deque<String>();
        StdOut.println(testDeque.isEmpty());

        for (int i = 0; i < 3; i++) {
            testDeque.addFirst(StdIn.readString());
            testDeque.addLast(StdIn.readString());
        }

        for (String s : testDeque) {
            StdOut.println(s);
        }
        StdOut.println("" + testDeque.size());

        testDeque.removeFirst();

        for (String s : testDeque) {
            StdOut.println(s);
        }
        StdOut.println("" + testDeque.size());

        testDeque.removeLast();

        for (String s : testDeque) {
            StdOut.println(s);
        }
        StdOut.println("" + testDeque.size());
    }
}
