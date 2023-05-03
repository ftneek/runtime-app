import java.util.Arrays;

public class RadixSort {
    // Main function to sort
    public static void sort(int[] arr)
    {
        int length = arr.length;
        // Find maximum number
        int max = getMax(arr, length);

        // Do counting sort for every digit
        // exp is 10^i where i is current digit number
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, length, exp);
    }
    // Function to find the maximum number in the array
    private static int getMax(int[] arr, int length)
    {
        int max = arr[0];
        for (int i = 1; i < length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }

    // Arrange numbers according to their digits
    // to sort
    private static void countSort(int[] arr, int length, int exp)
    {
        // Output
        int[] output = new int[length];

        int i;
        // Initialize an array of 0-10 to count
        // the number according to their digits
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences
        // for each digit
        for (i = 0; i < length; i++)
            count[(arr[i] / exp) % 10]++;

        // Let count[i] now contains actual
        // position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[] in order to store the
        // numbers according to current digit
        for (i = 0; i < length; i++)
            arr[i] = output[i];
    }
}
