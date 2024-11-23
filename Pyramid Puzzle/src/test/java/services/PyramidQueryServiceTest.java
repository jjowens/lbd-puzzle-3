package services;

import models.PyramidCell;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PyramidQueryServiceTest {

    PyramidQueryService pyramidQueryService;
    PyramidHelper pyramidHelper = new PyramidHelper();

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

    public void service_getOptimalPath(String filename) {
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

    public void service_getOptimalPathInReverse(String filename) {
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