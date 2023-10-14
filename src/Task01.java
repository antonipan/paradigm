import java.util.ArrayList;
import java.util.Arrays;

public class Task01 {

    public static void main(String[] args) {
        // Императивный стиль - быстрая сортировка
        int [] array = new int [] {3, 6, 2, 1};
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
        // Декларативный стиль - быстрая сортировка
        int [] array2 = new int [] {3, 7, 8, 1, 4};
        Arrays.sort(array2);
        System.out.println(Arrays.toString(array2));
    }

    public static void quickSort (int [] array, int begin, int end) {
        if (begin < end) {
            int pivotIndex = findPivot(array, begin, end);
            quickSort(array, begin, pivotIndex-1);
            quickSort(array, pivotIndex+1, end);
        }
    }

    private static int findPivot(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                int swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }

        int swapTemp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;

        return i+1;
    }

}
