import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int count = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] resizedArray = (Item[]) new Object[capacity * 2];
        for (int i = 0; i < count; i++) {
            resizedArray[i] = items[i];
        }
        items = resizedArray;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count <= 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item");
        }
        if (count == items.length) {
            resize(2 * items.length);
            items[count++] = item;
        } else {
            items[count++] = item;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (count <= 0) {
            throw new NoSuchElementException("Empty queue");
        } else if (count == items.length / 4) {
            int randomIndex = StdRandom.uniform(count);
            Item returnItem = items[randomIndex];
            items[randomIndex] = items[--count];
            items[count] = null;
            resize(items.length / 2);
            return returnItem;
        } else {
            int randomIndex = StdRandom.uniform(count);
            Item returnItem = items[randomIndex];
            items[randomIndex] = items[--count];
            items[count] = null;
            return returnItem;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (count <= 0) {
            throw new NoSuchElementException("Empty queue");
        }
        return items[StdRandom.uniform(count)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        StdRandom.shuffle(items, 0, count);
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = count;

        public boolean hasNext() {
            return i >= 1;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty queue");
            }
            return items[--i];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> testRandomizedQueue = new RandomizedQueue<String>();

        StdOut.println(testRandomizedQueue.isEmpty());
        StdOut.println("" + testRandomizedQueue.size());

        for (int i = 0; i < 6; i++) {
            testRandomizedQueue.enqueue(StdIn.readString());
        }

        StdOut.println(testRandomizedQueue.isEmpty());
        StdOut.println("" + testRandomizedQueue.size());

        for (int i = 0; i < 3; i++) {
            StdOut.println("Test number " + (i + 1));
            for (String s : testRandomizedQueue) {
                StdOut.println(s);
            }
        }

        StdOut.println("Dequeue and sample test");
        StdOut.println(testRandomizedQueue.dequeue());
        StdOut.println(testRandomizedQueue.dequeue());
        StdOut.println(testRandomizedQueue.size());
        StdOut.println(testRandomizedQueue.sample());
        StdOut.println(testRandomizedQueue.size());
    }

}