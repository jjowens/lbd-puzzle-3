package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PyramidService {

    private final String fileName;
    private final PyramidHelper pyramidHelper;

    public PyramidService(String fileName) {
        this.fileName = fileName;
        this.pyramidHelper = new PyramidHelper();
    }

    public List<String> readFile() throws IOException {
        List<String> lines = new ArrayList<>();

        lines = pyramidHelper.readFile(fileName);

        return lines;
    }

    public Long[] getMaximumValuesFromLinesOfText(List<String> linesOfText) {
        List<Long> longlist = new ArrayList<>();

        for(int idx=0; idx<linesOfText.size(); idx++) {
            longlist.add(pyramidHelper.findMaxValue(linesOfText.get(idx)));
        }

        Long[] list = new Long[longlist.size()];
        longlist.toArray(list);

        return list;
    }

    public String getOptimalPath() {
        String results = "";
        List<String> lines;
        Long[] optimalPath;

        try {
            lines = readFile();
            optimalPath = getMaximumValuesFromLinesOfText(lines);
            results = pyramidHelper.exportLongArrayTotal(optimalPath, " + ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

}
