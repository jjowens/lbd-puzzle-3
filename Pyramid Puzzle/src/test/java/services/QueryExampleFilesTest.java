package services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class QueryExampleFilesTest {

    PyramidQueryServiceTest pqs = new PyramidQueryServiceTest();
    static final String[] myFiles =  {"example1.txt", "example2.txt"};

    static List<String> myFiles() {
        return Arrays.asList(myFiles);
    }

    @DisplayName("Show details from all cells")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_getAllPyramidCells(String filename) {
        pqs.service_getAllPyramidCells(filename);
    }

    @DisplayName("Get optimal path")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_getOptimalPath(String filename) {
        pqs.service_getOptimalPath(filename);
    }

    @DisplayName("Get optimal path in reverse")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_getOptimalPathInReverse(String filename) {
        pqs.service_getOptimalPathInReverse(filename);
    }

    @DisplayName("First file only. Get optimal path in reverse")
    @ParameterizedTest
    @ValueSource(strings = {"example1.txt"})
    public void service_getFirstFileOptimalPathInReverse(String filename) {
        pqs.service_getOptimalPathInReverse(filename);
    }

    @DisplayName("Specific file only. Get optimal path in reverse based on last line and line above it")
    @ParameterizedTest
    @ValueSource(strings = {"example2.txt"})
    public void service_getSpecificFileOptimalPathInReverseAndLineAbove(String filename) {
        pqs.service_getSpecificFileOptimalPathInReverseAndLineAbove(filename);
    }
}