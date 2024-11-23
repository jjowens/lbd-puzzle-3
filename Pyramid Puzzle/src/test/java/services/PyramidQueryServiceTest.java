package services;

import models.PyramidCell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

    @DisplayName("Check basic model for pyramid cell")
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

    @DisplayName("Get Max Values from all rows")
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
}