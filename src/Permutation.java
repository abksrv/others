import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	private RandomizedQueue<String> q = new RandomizedQueue<String>();

	public static void main(String[] args) {
		Permutation permutation = new Permutation();
		int k = Integer.parseInt(args[0]);
		for (int i = 0; i < k; i++) {
			while (!StdIn.isEmpty()) {
				String string = StdIn.readString();
				permutation.q.enqueue(string);
				
			}
		}
		Iterator<String> iterator = permutation.q.iterator();
		int i = 0;
		while (iterator.hasNext() && i++ < k) {
			StdOut.println(iterator.next());
		}
	}
}
