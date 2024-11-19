package modules;

import java.util.Arrays;

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

}
