package modules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PyramidServiceTest {
    PyramidService pyramidService;
    PyramidHelper pyramidHelper = new PyramidHelper();

    public static final String[] EXAMPLE_FILES = new String[]{"example1.txt", "example2.txt"};

    @DisplayName("Read Example files")
    @ParameterizedTest
    @ValueSource(strings = {"example1.txt", "example2.txt"})
    void read_ExampleFiles(String fileName) {
        pyramidService = new PyramidService(fileName);
        List<String> lines;

        try {
            lines = pyramidService.readFile();
            assertTrue(!lines.isEmpty());
            System.out.println("No. of Lines = " + lines.size());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            fail();

        }

    }

    @DisplayName("Parse Example files")
    @ParameterizedTest
    @ValueSource(strings = {"example1.txt", "example2.txt"})
    void parse_ExampleFiles(String fileName) {
        pyramidService = new PyramidService(fileName);
        List<String> lines;
        Long[] optimalPath;

        try {
            lines = pyramidService.readFile();
            optimalPath = pyramidService.parseLinesOfText(lines);

            assertTrue(optimalPath.length > 0);
            System.out.println(pyramidHelper.exportLongArrayTotal(optimalPath, " + "));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            fail();

        }

    }


}