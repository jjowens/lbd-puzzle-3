package services;

import models.PyramidCell;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PyramidQueryServiceTest {

    PyramidQueryService pyramidQueryService;
    PyramidHelper pyramidHelper = new PyramidHelper();

    @DisplayName("Check basic model for pyramid cell")
    @Test
    public void service_getAllPyramidCells_FromExample1() {
        pyramidQueryService = new PyramidQueryService("example1.txt");
        List<PyramidCell> pyramidCells = new ArrayList<>();

        try {
            pyramidCells = pyramidQueryService.getOptimalPath();

            assertTrue(!pyramidCells.isEmpty());

            System.out.println(pyramidHelper.exportPyramidCellsToTable(pyramidCells));

        } catch (Exception ex) {
            fail();
        }

    }

    @DisplayName("Get Max Values from all rows")
    @Test
    public void service_getMaxValuesFromAllRows_FromExample1() {
        pyramidQueryService = new PyramidQueryService("example1.txt");
        List<PyramidCell> pyramidCells = new ArrayList<>();

        try {
            pyramidCells = pyramidQueryService.getOptimalPath();

            assertTrue(!pyramidCells.isEmpty());

            System.out.println(pyramidHelper.exportPyramidCellsToTable(pyramidCells));

        } catch (Exception ex) {
            fail();
        }

    }


}