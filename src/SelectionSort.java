public class SelectionSort {
    public static void sort(int[] arr)
    {
        int length = arr.length;
        // Finding the smallest element and move it
        // to the front
        for (int i = 0; i < length-1; i++)
        {
            // Find the minimum
            int index_min = i;
            for (int j = i+1; j < length; j++)
                if (arr[j] < arr[index_min])
                    index_min = j;

            // Swap it with first element
            int temp_value = arr[index_min];
            arr[index_min] = arr[i];
            arr[i] = temp_value;
        }
    }
}
