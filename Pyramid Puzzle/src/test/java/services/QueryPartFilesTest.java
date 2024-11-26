package services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class QueryPartFilesTest {

    PyramidQueryServiceTest pqs = new PyramidQueryServiceTest();
    static final String[] myFiles =  {"Part1.txt", "Part2.txt", "Part3.txt"};

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


}