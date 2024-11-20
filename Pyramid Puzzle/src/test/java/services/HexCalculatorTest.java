package services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(3, result.length);
    }

    @DisplayName("split line into 12 columns")
    @Test
    public void splitLineInto12Columns() {
        String[] result = hexCalculator.splitColumns(longLineOfText);
        assertEquals(12, result.length);
    }

    @DisplayName("parse a list of hexadecimals")
    @ParameterizedTest
    @CsvSource(value = {"7c:124", "9a:154"}, delimiter = ':')
    public void parse_hexadecimals(String input, String expected) {
        setup();
        Long result = hexCalculator.parseHexValue(input);
        int expectedInt = Integer.parseInt(expected);
        assertEquals(expectedInt, result);
    }

    @DisplayName("find max value from short line of text")
    @Test
    public void findMaxValueFrom_ShortLineOfText() {
        Long result = hexCalculator.findMaxValue(shortLineOfText);

        assertEquals(169, result);
    }

    @DisplayName("find max value from long line of text")
    @Test
    public void findMaxValueFrom_LongLineOfText() {
        Long result = hexCalculator.findMaxValue(longLineOfText);

        assertEquals(193, result);
    }

}