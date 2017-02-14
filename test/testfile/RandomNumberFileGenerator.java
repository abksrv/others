package testfile;

import java.io.IOException;

import util.WriteUtil;
import edu.princeton.cs.algs4.StdRandom;

public class RandomNumberFileGenerator {

	public static int[] generate(int k, int n) {
		int[] arr = new int[k];
		for (int i = n - k, j = 0; i < n; i++, j++) {
			arr[j] = i;
		}
		StdRandom.shuffle(arr);
		return arr;
	}

	public static void writeRandomNums(int k, int n) throws IOException {
		int[] arr = generate(k, n);
		WriteUtil.write("nums.txt", arr);
	}

	public static void main(String[] args) throws IOException {
		writeRandomNums(1000000, 10000000);
	}
}
