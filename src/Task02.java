public class Task02 {

    public static void main(String[] args) {
//        // Example 1
//        int [][] array = new int [][] {
//                        {3, 1, 4},
//                        {4, 5, 1},
//                        {3, 6, 2}};
//        System.out.println(sumDiagonalMatrix(array));

        // Example 2. Я использовал процедурную парадигму, так как даёт больше универсальности
        // Я могу подставлять любое число. Если бы я использовал структурный подход, то мне бы пришлось для каждого нового
        // числа копировать последовательность команд.
        multiply(10);


    }

    /**
     * Метод с семинара
     * @param array
     * @return
     */
    public static int sumDiagonalMatrix (int [][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != array.length) {
                throw new ArrayIndexOutOfBoundsException("Матрица не квадратная. ");
            } else {
                for (int j = 0; j < array[i].length; j++) {
                    if (i == j) {
                        sum += array[i][j];
                    }
                }
            }
        }
        return sum;
    }

    public static void multiply (int n) {
        for (int i = 1; i <= n; i++) {
            System.out.printf("%d: ", i);
            for (int j = 1; j <= n; j++) {
                System.out.printf("%d*%d=%d || ", i, j, i*j);
            }
            System.out.println();
        }
    }
}
