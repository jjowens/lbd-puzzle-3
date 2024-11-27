package services;

import models.PyramidCell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileWriter;
import java.io.IOException;
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

    @DisplayName("Render results to HTML")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_exportToHTML(String filename) {
        pyramidQueryService = new PyramidQueryService(filename);
        List<PyramidCell> pyramidCells;

        try {
            List<PyramidCell> temp = pyramidQueryService.getOptimalPath();
            pyramidCells = pyramidQueryService.getAllCells();

            String headerText = String.format("%s", filename);
            String htmlOutput = pyramidHelper.addPyramidCellsToTemplate("template.html", pyramidCells, headerText);

            String htmlFileName = filename.replaceAll(".txt", ".html");
            String fullPath = "./output/" + htmlFileName;
            writeToFile(fullPath, htmlOutput);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @DisplayName("Render results, in reverse, to HTML")
    @ParameterizedTest
    @MethodSource("myFiles")
    public void service_exportReverseToHTML(String filename) {
        pyramidQueryService = new PyramidQueryService(filename);
        List<PyramidCell> pyramidCells;

        try {
            List<PyramidCell> temp = pyramidQueryService.getOptimalPathInReverse();
            pyramidCells = pyramidQueryService.getAllCells();

            String headerText = String.format("%s in reverse", filename);
            String htmlOutput = pyramidHelper.addPyramidCellsToTemplate("template.html", pyramidCells, headerText);

            String htmlFileName = filename.replaceAll(".txt", "-reverse.html");
            String fullPath = "./output/" + htmlFileName;
            writeToFile(fullPath, htmlOutput);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    private void writeToFile(String filename, String content) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
