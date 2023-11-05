import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task06 {

    public static void main(String[] args) {
        // Решил задачу в функциональной парадигме программирования.
        int [] array1 = new int [] {3, 1, 0, 6, 10, 4, 11, 2, 17, 8, 9, 13};
        int [] array2 = new int [] {1, 4, 5, 6, 7, 5, 10, 0, 13, 1, 9, 13};
        double result = meanSquareError(array1, array2);
        System.out.println("result = " + result);

        // Решил задачу в императивной парадигме
        System.out.println(Arrays.toString(sortMerge(array1)));
        // Бинарный поиск - решил в императивном стиле
        int [] array = createdArray(19);
        System.out.println(Arrays.toString(array));

        binaryFind(array, 30, 0, array.length);

    }


    /**
     *
     * @param array1 - входящий массив наблюдений
     * @param array2 - входящий массив прогнозов
     * @return - возвращает ошибку отклонения
     * @throws RuntimeException - если оба входящих массива по длине не равны друг другу
     */
    static double meanSquareError (int [] array1, int [] array2) throws RuntimeException {
        if (array1.length != array2.length) {
            throw new RuntimeException("Arrays is'n different.");
        }
        return IntStream.range(0, array1.length)
                .mapToDouble(i -> Math.pow(array1[i] - array2[i], 2)).sum()/ array1.length;
    }

    /**
     *
     * @param array - принимает массив несортированных целых чисел
     * @return - возвращает отсартированный массив
     */
    static int [] sortMerge(int [] array) {
        if (array.length < 2) {
            return array;
        }
        int [] left = new int [array.length/2];
        System.arraycopy(array, 0, left, 0, array.length/2);

        int [] right = new int [array.length - array.length/2];
        System.arraycopy(array, array.length/2, right, 0, array.length - array.length/2);

        left = sortMerge(left);
        right = sortMerge(right);

        return merging(left, right);
    }

    /**
     * Принимает два массива
     * @param left - левый массив
     * @param right - правый массив
     * @return - возвращает упорядоченный целый массив из двух массивов.
     */
    static int [] merging (int [] left, int [] right) {
        int posLeft = 0, posRight = 0;
        int [] merged = new int [left.length + right.length];
        while (posRight + posLeft < merged.length){
            if (posLeft == left.length) {
                merged[posLeft + posRight] = right[posRight];
                posRight++;
            } else if (posRight == right.length) {
                merged[posLeft + posRight] = left[posLeft];
                posLeft++;
            } else if (left[posLeft] < right[posRight]) {
                merged[posLeft + posRight] = left[posLeft];
                posLeft++;
            } else {
                merged[posLeft + posRight] = right[posRight];
                posRight++;
            }
        }
        return merged;
    }
    static int [] createdArray(int length) {
        int count = 10;
        int [] array = new int [length];
        for (int i = 0; i < length; i++) {
            array[i] = count;
            count +=10;
        }
        return array;
    }

    static void binaryFind (int [] array, int numSearch, int min, int max) {
        int bound = (max - min)/2;
        if (min >= max || bound < 0) {
            System.out.println("not found!! ");
            return;
        }
        if (array[min + bound] == numSearch) {
            System.out.println("find!!! ");;
        } else {
            if (numSearch < array[min + bound]) {
                if (bound == 0) {
                    System.out.println("not found!! ");
                    return;
                }
                binaryFind(array, numSearch, min, max - bound);
            } else if (numSearch > array[min + bound]) {
                if (bound == 0) {
                    System.out.println("not found!! ");
                    return;
                }
                binaryFind(array, numSearch, min + bound, max);
            }
        }
    }
}
