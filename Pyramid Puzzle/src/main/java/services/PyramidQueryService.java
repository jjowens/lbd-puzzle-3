package services;

import models.ColumnRange;
import models.PyramidCell;
import models.PyramidCellEnum;

import java.io.IOException;
import java.util.*;

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

        int rowNumber = 0;
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

        Optional<PyramidCell> previousPyramidCell = Optional.empty();

        for(int idx = 0; idx < totalLines; idx++) {
            int tempIdx = idx;

            ColumnRange colIndexRange = getPyramidCellRange(previousPyramidCell);

            Optional<PyramidCell> pyramidCell = pyramidCellList.stream()
                    .filter(item -> tempIdx == item.getRow()
                            && (item.getCol() >= colIndexRange.getMin() && item.getCol() <= colIndexRange.getMax()))
                    .max(Comparator.comparingLong(item -> item.getActualValue()));

            if (pyramidCell.isPresent()) {
                optimalPath.add(pyramidCell.get());
                previousPyramidCell = Optional.of(pyramidCell.get());
            }
        }

        return optimalPath;
    }

    public List<PyramidCell> getOptimalPathInReverse() throws IOException {
        List<PyramidCell> optimalPath = new ArrayList<>();

        int rowNumber = totalLines;

        while(rowNumber > 0) {
            int tempIdx = rowNumber;
            Optional<PyramidCell> pyramidCell = pyramidCellList.stream()
                    .filter(item -> tempIdx == item.getRow())
                    .max(Comparator.comparingLong(item -> item.getActualValue()));

            if (pyramidCell.isPresent()) {
                optimalPath.add(pyramidCell.get());
            }

            rowNumber--;
        }

        return optimalPath;
    }

    public List<PyramidCell> getOptimalPathInReverseAndLineAbove() throws IOException {
        List<PyramidCell> optimalPath = new ArrayList<>();

        int lastRowIndex = totalLines - 1;
        int rowIndex = totalLines - 1;
        Optional<PyramidCell> previousPyramidCell = Optional.empty();

        while(rowIndex != -1) {
            int tempRowNo = rowIndex;

            Optional<PyramidCell> pyramidCell = Optional.empty();

            // GET MAX VALUE FROM BOTTOM ROW
            if (tempRowNo == lastRowIndex) {
                pyramidCell = pyramidCellList.stream()
                        .filter(item -> tempRowNo == item.getRow())
                        .max(Comparator.comparingLong(item -> item.getActualValue()));
            } else {
                // SEARCH IN LINE ABOVE. LOOK FOR MAX VALUES WITHIN TOUCHING DISTANCE
                if (previousPyramidCell.isPresent()) {
                    Optional<PyramidCell> finalPreviousPyramidCell = previousPyramidCell;

                    // SEARCH RANGE BASED ON PREVIOUS LINE. MUST BE WITHIN TOUCHING DISTANCE
                    ColumnRange colIndexRange = getPyramidCellRange(finalPreviousPyramidCell);

                    pyramidCell = pyramidCellList.stream()
                            .filter(item -> tempRowNo == item.getRow()
                            && (item.getCol() >= colIndexRange.getMin() && item.getCol() <= colIndexRange.getMax()))
                            .max(Comparator.comparingLong(item -> item.getActualValue()));
                }
            }

            if (pyramidCell.isPresent()) {
                optimalPath.add(0, pyramidCell.get());
                previousPyramidCell = pyramidCell;
            }

            rowIndex--;
        }

        return optimalPath;
    }

    private ColumnRange getPyramidCellRange(Optional<PyramidCell> pyramidCell) {
        ColumnRange columnRange = null;

        int min = 0;
        int max = 0;

        // RETURN DEFAULT
        if (pyramidCell.isEmpty()) {
            min = -1;
            max = 1;
        } else {
            if (pyramidCell.get().getPyramidCellEnum() == PyramidCellEnum.NEITHER) {
                min = pyramidCell.get().getCol() - 1;
                max = pyramidCell.get().getCol() + 1;
            }

            if (pyramidCell.get().getPyramidCellEnum() == PyramidCellEnum.FIRST_CELL) {
                min = pyramidCell.get().getCol();
                max = pyramidCell.get().getCol() + 1;
            }

            if (pyramidCell.get().getPyramidCellEnum() == PyramidCellEnum.LAST_CELL) {
                min = pyramidCell.get().getCol() - 2;
                max = pyramidCell.get().getCol() - 1;
            }
        }

        return new ColumnRange(min, max);
    }

}
