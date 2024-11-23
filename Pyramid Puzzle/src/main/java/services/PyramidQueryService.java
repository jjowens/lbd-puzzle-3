package services;

import models.PyramidCell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

        for(int idx = 1; idx <= totalLines; idx++) {
            int tempIdx = idx;

            Optional<PyramidCell> pyramidCell = pyramidCellList.stream()
                    .filter(item -> tempIdx == item.getRow())
                    .max(Comparator.comparingLong(item -> item.getActualValue()));

            if (pyramidCell.isPresent()) {
                optimalPath.add(pyramidCell.get());
            }
        }

        return optimalPath;
    }
}
