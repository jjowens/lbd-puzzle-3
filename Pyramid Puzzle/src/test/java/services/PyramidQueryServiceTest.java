package services;

import models.PyramidCell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PyramidQueryServiceTest {

    PyramidQueryService pyramidQueryService;
    PyramidHelper pyramidHelper = new PyramidHelper();
    static final String[] myFiles =  {"example1.txt", "example2.txt", "Part1.txt", "Part2.txt", "Part3.txt"};

    static List<String> myFiles() {
        return Arrays.asList(myFiles);
    }

    @DisplayName("Show details from all cells")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_getAllPyramidCells(String filename) {
        pyramidQueryService = new PyramidQueryService(filename);
        List<PyramidCell> pyramidCells = new ArrayList<>();

        try {
            pyramidCells = pyramidQueryService.getAllCells();

            assertFalse(pyramidCells.isEmpty());

            System.out.println("All Cells");
            System.out.println(pyramidHelper.exportPyramidCellsToTable(pyramidCells));

        } catch (Exception ex) {
            fail();
        }
    }

    @DisplayName("Get optimal path")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_getMaxValuesFromAllRows(String filename) {
        pyramidQueryService = new PyramidQueryService(filename);
        List<PyramidCell> pyramidCells = new ArrayList<>();

        try {
            pyramidCells = pyramidQueryService.getOptimalPath();

            assertFalse(pyramidCells.isEmpty());

            System.out.println("Max Values");
            System.out.println(pyramidHelper.exportPyramidCellsToTable(pyramidCells));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @DisplayName("Get optimal path. Example 1")
    @ParameterizedTest
    @ValueSource(strings = {"example1.txt"})
    public void service_getMaxValuesFromAllRows_Example1(String filename) {
        pyramidQueryService = new PyramidQueryService(filename);
        List<PyramidCell> pyramidCells = new ArrayList<>();

        try {
            pyramidCells = pyramidQueryService.getOptimalPathInReverse();

            assertFalse(pyramidCells.isEmpty());

            System.out.println("Max Values");
            System.out.println(pyramidHelper.exportPyramidCellsToTable(pyramidCells));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }
}