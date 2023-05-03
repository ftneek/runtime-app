// Take input as length of an array of integers and return the record times
// of different sorting algorithms
// "BubbleSort","SelectionSort","QuickSort","RadixSort","MergeSort","HeapSort"
import java.util.HashMap;

public class RecordTimesOfSorts {
    public static HashMap<String, Long> returnTimesRecorded(int[] intArr) {
        HashMap<String, Long> timePerSorting = new HashMap<>();

        int[] arr = intArr.clone();
        long startTime = System.currentTimeMillis();
        SelectionSort.sort(arr);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        timePerSorting.put("SelectionSort", executionTime);

        int[] arr1 = intArr.clone();
        startTime = System.currentTimeMillis();
        MergeSort.sort(arr1);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        timePerSorting.put("MergeSort", executionTime);

        int[] arr2 = intArr.clone();
        startTime = System.currentTimeMillis();
        QuickSort.sort(arr2);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        timePerSorting.put("QuickSort", executionTime);

        int[] arr3 = intArr.clone();
        startTime = System.currentTimeMillis();
        HeapSort.sort(arr3);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        timePerSorting.put("HeapSort", executionTime);

        int[] arr4 = intArr.clone();
        startTime = System.currentTimeMillis();
        RadixSort.sort(arr4);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        timePerSorting.put("RadixSort", executionTime);

        int[] arr5 = intArr.clone();
        startTime = System.currentTimeMillis();
        BubbleSort.sort(arr5);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        timePerSorting.put("BubbleSort", executionTime);

        HeapSort.sort(arr);

        return timePerSorting;
    }
}
