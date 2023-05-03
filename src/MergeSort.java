public class MergeSort {
    // Main function to sort
    public static void sort(int[] arr)
    {
        sort(arr,0, arr.length - 1);
    }
    private static void sort(int[] arr, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    // Merges two sub-arrays of arr[].
    private static void merge(int[] arr, int l, int m, int r)
    {
        // Find sizes of two sub-arrays to be merged
        int size1 = m - l + 1;
        int size2 = r - m;

        int[] left = new int[size1];
        int[] right = new int[size2];

        // Create temp arrays and copy data
        System.arraycopy(arr, l, left, 0, size1);
        for (int j = 0; j < size2; ++j)
            right[j] = arr[m + 1 + j];

        // Initial indexes of first and second sub-arrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < size1 && j < size2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < size1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < size2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
