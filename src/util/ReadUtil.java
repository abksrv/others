package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadUtil {
	public static int[] read(int size, String filename)
			throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String s;
		int[] arr = new int[size];
		int i = 0;
		while ((s = br.readLine()) != null) {
			arr[i++] = Integer.parseInt(s);
		}
		return arr;
	}
}
