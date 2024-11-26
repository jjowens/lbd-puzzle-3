package services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class PyramidServiceTest {
    PyramidService pyramidService;
    PyramidHelper pyramidHelper = new PyramidHelper();

    static final String[] myFiles =  {"example1.txt", "example2.txt"};

    static List<String> myFiles() {
        return Arrays.asList(myFiles);
    }

    @DisplayName("Read Example files")
    @ParameterizedTest
    @MethodSource("myFiles")
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
    @MethodSource("myFiles")
    void parse_ExampleFiles(String fileName) {
        pyramidService = new PyramidService(fileName);
        List<String> lines;
        Long[] optimalPath;

        try {
            lines = pyramidService.readFile();
            optimalPath = pyramidService.getMaximumValuesFromLinesOfText(lines);

            assertTrue(optimalPath.length > 0);
            System.out.println(pyramidHelper.exportLongArrayTotal(optimalPath, " + "));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            fail();

        }
    }
}