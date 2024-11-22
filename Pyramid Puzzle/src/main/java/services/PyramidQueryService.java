package services;

import models.PyramidCell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PyramidQueryService {

    private final String fileName;
    private final PyramidHelper pyramidHelper;

    public PyramidQueryService(String fileName) {
        this.fileName = fileName;
        this.pyramidHelper = new PyramidHelper();
    }

    public List<PyramidCell> getOptimalPath() throws IOException {
        List<PyramidCell> pyramidCellList = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        lines = pyramidHelper.readFile(fileName);

        int rowNumber = 1;
        for (String line : lines) {
            pyramidCellList.addAll(pyramidHelper.splitColumnsIntoPyramidCells(line, rowNumber));
            rowNumber++;
        }

        return pyramidCellList;
    }
}
