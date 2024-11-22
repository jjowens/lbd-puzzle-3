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
    private List<PyramidCell> pyramidCellList = new ArrayList<>();
    private int totalLines = 0;

    public PyramidQueryService(String fileName) {
        this.fileName = fileName;
        this.pyramidHelper = new PyramidHelper();
        try {
            readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFile() throws IOException {
        List<String> lines = pyramidHelper.readFile(fileName);
        totalLines = lines.size();

        int rowNumber = 1;
        for (String line : lines) {
            pyramidCellList.addAll(pyramidHelper.splitColumnsIntoPyramidCells(line, rowNumber));
            rowNumber++;
        }
    }

    public List<PyramidCell> getAllCells() {
        return pyramidCellList;
    }

    public List<PyramidCell> getOptimalPath() throws IOException {
        List<PyramidCell> optimalPath = new ArrayList<>();

        for(int idx = 0; idx < totalLines; idx++) {
            Long val = pyramidCellList.stream().filter(item -> item.getActualValue() && idx == item.getRow()).findFirst().get().getActualValue();
            optimalPath.add()
        }

        optimalPath = pyramidCellList.stream().filter(item -> item.)

        return optimalPath;
    }
}
