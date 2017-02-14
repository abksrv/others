package sorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.StopwatchCPU;
import util.ReadUtil;
import util.WriteUtil;

public class SystemSortFromFile {
	public static void sort(int[] arr)
	{
		ArrayList<Integer> list = new ArrayList<>();
		for(int a : arr)
			list.add(a);
		Collections.sort(list);
		int i = 0;
		for(Integer e : list)
			arr[i++] = e;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] arr = ReadUtil.read(1000000, "nums.txt");
		StopwatchCPU sw = new StopwatchCPU();
		sort(arr);
		System.out.println("System sort:"+sw.elapsedTime()+" s");
		WriteUtil.write("syssorted.txt", arr);
	}

	
}
