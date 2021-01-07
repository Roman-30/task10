package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Набор функций для работы с массивами
 *
 * @author Дмитрий Соломатин (кафедра ПиИТ ФКН ВГУ)
 */

public class ArrayUtils {

    public static double[] toPrimitive(Double[] arr) {
        if (arr == null) {
            return null;
        }
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая распаковка из объекта
            result[i] = arr[i];
        }
        return result;
    }

    public static double[] toDoubleArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Double> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextDouble());
        }
        // из List<Double> можно получить Double[]
        Double[] arr = list.toArray(new Double[0]);
        // Double[] -> double[]
        return ArrayUtils.toPrimitive(arr);
    }
}
