package services;

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

        int rowNumber = totalLines;
        Optional<PyramidCell> previousPyramidCell = Optional.empty();

        while(rowNumber > 0) {
            int tempRowNo = rowNumber;

            Optional<PyramidCell> pyramidCell = Optional.empty();

            // GET MAX VALUE FROM BOTTOM ROW
            if (tempRowNo == totalLines) {
                pyramidCell = pyramidCellList.stream()
                        .filter(item -> tempRowNo == item.getRow())
                        .max(Comparator.comparingLong(item -> item.getActualValue()));
            } else {
                // SEARCH IN LINE ABOVE. LOOK FOR MAX VALUES WITHIN TOUCHING DISTANCE
                if (previousPyramidCell.isPresent()) {
                    Optional<PyramidCell> finalPreviousPyramidCell = previousPyramidCell;

                    // SEARCH RANGE FOR LINE ABOVE
                    List<Integer> colIndexRange = getPyramidCellRange(finalPreviousPyramidCell);

                    List<PyramidCell> testList = pyramidCellList.stream()
                            .filter(item -> tempRowNo == item.getRow()
                                            && Arrays.asList(colIndexRange).contains(item.getCol())).toList();

                    pyramidCell = pyramidCellList.stream()
                            .filter(item -> tempRowNo == item.getRow())
                            .max(Comparator.comparingLong(item -> item.getActualValue()));

                  /*  pyramidCell = pyramidCellList.stream()
                            .filter(item -> tempRowNo == item.getRow()
                                    && item.getCol() == finalPreviousPyramidCell.get().getCol())
                            .max(Comparator.comparingLong(item -> item.getActualValue()));
                   */
                }
            }

            if (pyramidCell.isPresent()) {
                optimalPath.add(0, pyramidCell.get());
                previousPyramidCell = pyramidCell;
            }

            rowNumber--;
        }

        return optimalPath;
    }

    private List<Integer> getPyramidCellRange(Optional<PyramidCell> pyramidCell) {
        List<Integer> cellRange = new ArrayList<>();

        // RETURN DEFAULT
        if (pyramidCell.isEmpty()) {
            cellRange.add(-1);
            cellRange.add(0);
            cellRange.add(1);
            return cellRange;
        }

        if (pyramidCell.get().getPyramidCellEnum() == PyramidCellEnum.NEITHER) {
            cellRange.add(pyramidCell.get().getCol() - 1);
            cellRange.add(pyramidCell.get().getCol());
            cellRange.add(pyramidCell.get().getCol() + 1);
        }

        if (pyramidCell.get().getPyramidCellEnum() == PyramidCellEnum.FIRST_CELL) {
            cellRange.add(pyramidCell.get().getCol());
            cellRange.add(pyramidCell.get().getCol() + 1);
        }

        if (pyramidCell.get().getPyramidCellEnum() == PyramidCellEnum.LAST_CELL) {
            cellRange.add(pyramidCell.get().getCol() - 1);
            cellRange.add(pyramidCell.get().getCol() - 2);
        }

        return cellRange;
    }

}
