package util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Shuffler
{
    public static void shuffle(int[] arr)
    { // Implementing Fisher–Yates shuffle
      // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
}
