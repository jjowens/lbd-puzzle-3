package services;

import models.PyramidCell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class SolutionTest {
    PyramidQueryService pyramidQueryService;
    PyramidHelper pyramidHelper = new PyramidHelper();

    static final String[] myFiles =  {"example1.txt", "example2.txt", "Part1.txt", "Part2.txt", "Part3.txt"};

    static List<String> myFiles() {
        return Arrays.asList(myFiles);
    }

    @DisplayName("Get optimal path")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_getOptimalPath(String filename) {
        pyramidQueryService = new PyramidQueryService(filename);
        List<PyramidCell> pyramidCells = new ArrayList<>();

        try {
            pyramidCells = pyramidQueryService.getOptimalPath();

            assertFalse(pyramidCells.isEmpty());

            System.out.println("Optimal Path");
            System.out.println(pyramidHelper.exportOptimalPath(pyramidCells));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @DisplayName("Get optimal path in reverse")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_getOptimalPathInReverse(String filename) {
        pyramidQueryService = new PyramidQueryService(filename);
        List<PyramidCell> pyramidCells = new ArrayList<>();

        try {
            pyramidCells = pyramidQueryService.getOptimalPathInReverse();

            assertFalse(pyramidCells.isEmpty());

            System.out.println("Optimal Path in reverse. Search from bottom");
            System.out.println(pyramidHelper.exportOptimalPath(pyramidCells));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }


}
