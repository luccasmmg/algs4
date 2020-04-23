import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
       RandomizedQueue<String> r = new RandomizedQueue<String>();
       int k = Integer.parseInt(args[0]);
       for (int i = 0; i < k; i++) {
           String item = StdIn.readString();
           r.enqueue(item);
       }
       for (String s: r)
           StdOut.println(s);
   }
}
