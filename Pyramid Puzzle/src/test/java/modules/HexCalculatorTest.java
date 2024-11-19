package modules;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

public class HexCalculatorTest {
    HexCalculator hexCalculator;
    String shortLineOfText = "6a a9 04";
    String longLineOfText = "34 9f 01 7e 57 9b c1 9f 51 20 23 0f";

    @Before
    public void setup() {
        hexCalculator = new HexCalculator();
    }

    @DisplayName("split line into 3 columns")
    @Test
    public void splitLineInto3Columns() {
        String[] result = hexCalculator.splitColumns(shortLineOfText);
        assertEquals(result.length, 3);
    }

    @DisplayName("split line into 12 columns")
    @Test
    public void splitLineInto12Columns() {
        String[] result = hexCalculator.splitColumns(longLineOfText);
        assertEquals(result.length, 12);
    }

    @DisplayName("parse 7c as 124")
    @Test
    public void parse7c() {
        Long result = hexCalculator.parseHexValue("7c");
        assertEquals(result, 124);
    }

    @DisplayName("parse 9a as 154")
    @Test
    public void parse9a() {
        Long result = hexCalculator.parseHexValue("9a");
        assertEquals(result, 154);
    }

    @DisplayName("find max value from short line of text")
    @Test
    public void findMaxValueFrom_ShortLineOfText() {
        Long result = hexCalculator.findMaxValue(shortLineOfText);

        assertEquals(result, 169);
    }

    @DisplayName("find max value from long line of text")
    @Test
    public void findMaxValueFrom_LongLineOfText() {
        Long result = hexCalculator.findMaxValue(longLineOfText);

        assertEquals(result, 193);
    }

}