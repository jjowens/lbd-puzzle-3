package services;

import models.PyramidCell;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<String> readFile(String resourceFilename) throws IOException {
        List<String> lines = new ArrayList<>();

        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourceFilename);
            if (inputStream != null) {
                lines = new BufferedReader(new InputStreamReader(inputStream)).lines().toList();
                inputStream.close();
            }
        } catch (IOException ioEx) {
            throw ioEx;
        }

        return lines;
    }

    public String exportPyramidCellsToTable(List<PyramidCell> pyramidCells) {
        StringBuilder sb = new StringBuilder();

        String pipelineSeparator = "|";
        String separator = "-";
        String rowHeaderText = "Row";
        String columnHeaderText = "Column";
        String originalHeaderText = "Original Value";
        String actualHeaderText = "Actual Value";

        sb.append(String.format("%s%s", pipelineSeparator, rowHeaderText));
        sb.append(String.format("%s%s", pipelineSeparator, columnHeaderText));
        sb.append(String.format("%s%s", pipelineSeparator, originalHeaderText));
        sb.append(String.format("%s%s", pipelineSeparator, actualHeaderText));
        sb.append(String.format("%s", pipelineSeparator));
        sb.append("\n");

        for (PyramidCell pyramidCell : pyramidCells) {
            sb.append(String.format("%s%s",
                    pipelineSeparator,
                    StringUtils.leftPad(String.valueOf(pyramidCell.getRow()), rowHeaderText.length())));
            sb.append(String.format("%s%s",
                    pipelineSeparator,
                    StringUtils.leftPad(String.valueOf(pyramidCell.getCol()), columnHeaderText.length())));
            sb.append(String.format("%s%s",
                    pipelineSeparator,
                    StringUtils.leftPad(String.valueOf(pyramidCell.getOriginalValue()), originalHeaderText.length())));
            sb.append(String.format("%s%s",
                    pipelineSeparator,
                    StringUtils.leftPad(String.valueOf(pyramidCell.getActualValue()), actualHeaderText.length())));


            sb.append(String.format("%s", pipelineSeparator));

            sb.append("\n");
        }

        sb.append(pipelineSeparator);
        sb.append(String.format("%s", separator.repeat(rowHeaderText.length())));
        sb.append(String.format("%s", separator.repeat(columnHeaderText.length())));
        sb.append(String.format("%s", separator.repeat(originalHeaderText.length())));
        sb.append(String.format("%s", separator.repeat(actualHeaderText.length())));
        sb.append(String.format("%s", separator.repeat(3)));
        sb.append(pipelineSeparator);

        return sb.toString();
    }

}
