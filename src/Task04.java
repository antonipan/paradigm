import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Task04 {

    public static void main(String[] args) {

        double [] array1 = new double [] {1, 3, 4, 5, 6};
        double [] array2 = new double [] {2, 4, 5, 6, 7};

        // Первый вариант решения
        Function <Double> subtract = (x, y) -> x - y;
        Function <Double> multiply = (x, y) -> x*y;
        Degree <Double> degree = x -> x*x;

        double middle1 = Arrays.stream(array1).average().getAsDouble();
        double middle2 = Arrays.stream(array2).average().getAsDouble();
        double correlate = 0;
        double sum1 =0;
        double sum2 =0;
        for (int i = 0; i < array1.length; i++) {
            sum1 += multiply.apply(
                    subtract.apply(array1[i], middle1),
                    subtract.apply(array2[i], middle2));
            sum2 += multiply.apply(
                    degree.degree(subtract.apply(array1[i], middle1)),
                    degree.degree(subtract.apply(array2[i], middle2)));
        }
        correlate = sum1 / Math.sqrt(sum2);
        System.out.printf("Корреляция равна: %.3f\n", correlate);
//
//        // Второй вариант решени

        System.out.printf("Корреляция равна: %.3f", correlate(array1, array2));
    }

    public static double correlate (double [] array1, double [] array2) throws RuntimeException {
        if (array1.length != array2.length) {
            throw new RuntimeException("Массивы не равны.. ");
        }
        double middle1 = Arrays.stream(array1).average().getAsDouble();
        double middle2 = Arrays.stream(array2).average().getAsDouble();
        double sumUp = IntStream.range(0, array1.length)
                .mapToDouble(i -> (array1[i] - middle1)*(array2[i]-middle2)).sum();
        double sumDown = IntStream.range(0, array1.length)
                .mapToDouble(i -> Math.pow((array1[i] - middle1), 2)
                        *Math.pow((array2[i]-middle2), 2)).sum();
        return sumUp / Math.sqrt(sumDown);
    }
}
