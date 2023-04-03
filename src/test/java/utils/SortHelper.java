package utils;

import java.util.Arrays;

public class SortHelper {
    public static String[] sortByAsc(String[] array) {
        return Arrays.stream(array)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toArray(String[]::new);
    }

    public static String[] sortByDesc(String[] array) {
        return Arrays.stream(array)
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .toArray(String[]::new);
    }
}
