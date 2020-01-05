import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = StdIn.readInt();
        RandomizedQueue<String> testRandomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty() && testRandomizedQueue.size() < k) {
            String value = StdIn.readString();
            testRandomizedQueue.enqueue(value);
        }
        for (String s : testRandomizedQueue) {
            StdOut.println(s);
        }
    }
}
