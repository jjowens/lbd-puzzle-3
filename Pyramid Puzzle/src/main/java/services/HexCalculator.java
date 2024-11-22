package services;

import models.PyramidCell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexCalculator {

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

        for(int col = 0; col < strArray.length; col++) {
            list.add(new PyramidCell(strArray[col], rowNumber, col));
        }
        return list;
    }

}
