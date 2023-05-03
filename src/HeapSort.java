public class HeapSort {
    public static void sort(int[] arr) {
        int length = arr.length;
        // Build max heap
        BuildMaxHeap(arr);
        // One by one extract an element from heap
        for (int i = length - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // call max heapify on the reduced heap
            MaxHeapify(arr, i, 0);
        }
    }

    // To max-heapify a subtree rooted with node i
    private static void MaxHeapify(int[] arr, int length, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < length && arr[left] > arr[largest])
            largest = left;

        if (right < length && arr[right] > arr[largest])
            largest = right;

        // If largest is not root
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected subtree
            MaxHeapify(arr, length, largest);
        }
    }

    // Build max heap
    private static void BuildMaxHeap(int[] arr) {
        int length = arr.length;
        for (int i = length / 2 - 1; i >= 0; i--)
            MaxHeapify(arr, length, i);
    }
}
