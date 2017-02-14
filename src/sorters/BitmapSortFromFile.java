package sorters;

import java.io.IOException;

import bitvector.BitVector;
import util.ReadUtil;
import util.StopwatchCPU;
import util.WriteUtil;

public class BitmapSortFromFile {
	public static void sort(int[] arr) {
		BitVector bv = new BitVector();
		for (int i : arr) {
			bv.set(i);
		}
		int j = 0;
		for (long i = 0; i < bv.length(); i++)
			if (bv.isSet(i))
				arr[j++] = (int) i;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		int[] arr = ReadUtil.read(1000000, "nums.txt");
		StopwatchCPU sw = new StopwatchCPU();
		sort(arr);
		System.out.println("Bitmap sort:" + sw.elapsedTime() + " ms");
		WriteUtil.write("bitmapsorted.txt", arr);
	}
}
