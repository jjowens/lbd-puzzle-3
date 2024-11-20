package services;

import java.util.Arrays;

public class PyramidHelper {

    public String exportLongArray(Long[] array, String separator) {
        if (array == null || array.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (long l : array) {
            sb.append(l);
            sb.append(separator);
        }

        return sb.substring(0, sb.length() - separator.length());
    }

    public String exportLongArrayTotal(Long[] array, String separator) {
        if (array == null || array.length == 0) {
            return "0";
        }

        String lineOfText = exportLongArray(array, separator);
        Long sum = Arrays.stream(array).reduce(0L, Long::sum);

        return String.format("%s = %s", lineOfText, sum);
    }

}
