/* *****************************************************************************
 *  Name:              Vinicius Corbellini
 *  Last modified:     3/7/2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> perm = new RandomizedQueue<String>();

        if (k > 0) {
            for (int i = 0; i < k; i++) {
                if (StdIn.isEmpty()) break;
                perm.enqueue(StdIn.readString());
            }

            for (int i = 0; !StdIn.isEmpty(); i++) {
                String str = StdIn.readString();
                int rand = StdRandom.uniform(k + i + 1);
                if (rand < k) {
                    perm.dequeue();
                    perm.enqueue(str);
                }
            }
        }
        for (String st : perm)
            StdOut.println(st);
    }
}
