package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteUtil {
	public static void write(String filename, int[] arr) throws IOException {
		PrintWriter f0 = new PrintWriter(new FileWriter(filename));
		for (int i = 0; i < arr.length; i++) {
			f0.println(arr[i]);
		}
		f0.close();
	}
}
