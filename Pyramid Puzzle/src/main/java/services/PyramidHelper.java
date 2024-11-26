package services;

import models.PyramidCell;
import models.PyramidCellEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PyramidHelper {

    public Long parseHexValue(String val) {
        return Long.parseLong(val, 16);
    }

    public String[] splitColumns(String lineOfText) {
        return lineOfText.split(" ");
    }

    public Long[] splitColumnsIntoValues(String lineOfText) {
        String[] strArray = lineOfText.split(" ");
        Long[] lArray = new Long[strArray.length];

        for(int idx = 0; idx < strArray.length; idx++) {
            lArray[idx] = parseHexValue(strArray[idx]);
        }

        Arrays.sort(lArray);

        return lArray;
    }

    public Long findMaxValue(String lineOfText) {
        Long[] lArray = splitColumnsIntoValues(lineOfText);
        return lArray[lArray.length - 1];
    }

    public List<PyramidCell> splitColumnsIntoPyramidCells(String lineOfText, int rowNumber) {
        String[] strArray = lineOfText.split(" ");
        List<PyramidCell> list = new ArrayList<PyramidCell>();

        int lastCol = strArray.length - 1;
        for(int col = 0; col < strArray.length; col++) {
            PyramidCellEnum currentEnum = PyramidCellEnum.NEITHER;

            if (col == 0) {
                currentEnum = PyramidCellEnum.FIRST_CELL;
            } else if (col == lastCol) {
                currentEnum = PyramidCellEnum.LAST_CELL;
            }

            list.add(new PyramidCell(strArray[col], rowNumber, col, currentEnum));
        }
        return list;
    }

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

    public Long getTotalFromPyramidCells(List<PyramidCell> pyramidCells) {
        return pyramidCells.stream().filter(item -> item.getActualValue() > 0).mapToLong(PyramidCell::getActualValue).sum();
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

        // ## HEADER ROW
        sb.append(String.format("%s%s", pipelineSeparator, rowHeaderText));
        sb.append(String.format("%s%s", pipelineSeparator, columnHeaderText));
        sb.append(String.format("%s%s", pipelineSeparator, originalHeaderText));
        sb.append(String.format("%s%s", pipelineSeparator, actualHeaderText));
        sb.append(String.format("%s", pipelineSeparator));
        sb.append("\n");

        // ## DATA ROWS
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
        // # END ROW
        int extraPadding = 3;
        int totalSeparators = rowHeaderText.length() +
                columnHeaderText.length() +
                originalHeaderText.length() +
                actualHeaderText.length() +
                extraPadding;

        // # TOTAL ROW
        Long totalAmount = getTotalFromPyramidCells(pyramidCells);
        String totalText = String.format("Total = %s", totalAmount);
        String totalTextPadding = " ";
        Integer totalTextPaddingAmount = totalSeparators - totalText.length();
        String completeTotalText = String.format("%s" + totalText, totalTextPadding.repeat(totalTextPaddingAmount));

        sb.append(pipelineSeparator);
        sb.append(completeTotalText);
        sb.append(pipelineSeparator);
        sb.append("\n");

        // # END ROW
        sb.append(pipelineSeparator);
        sb.append(String.format("%s", separator.repeat(totalSeparators)));
        sb.append(pipelineSeparator);

        return sb.toString();
    }

}
