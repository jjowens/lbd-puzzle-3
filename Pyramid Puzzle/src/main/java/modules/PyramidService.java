package modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PyramidService {

    private final String fileName;
    private final HexCalculator calculator;

    public PyramidService(String fileName) {
        this.fileName = fileName;
        this.calculator = new HexCalculator();
    }

    public List<String> readFile() throws IOException, URISyntaxException {
        List<String> lines = new ArrayList<>();

        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
            if (inputStream != null) {
                lines = new BufferedReader(new InputStreamReader(inputStream)).lines().toList();
                inputStream.close();
            }
        } catch (IOException ioEx) {
            throw ioEx;
        }

        return lines;
    }

    public Long[] parseLinesOfText(List<String> linesOfText) {
        List<Long> longlist = new ArrayList<>();

        for(int idx=0; idx<linesOfText.size(); idx++) {
            longlist.add(calculator.findMaxValue(linesOfText.get(idx)));
        }

        Long[] list = new Long[longlist.size()];
        longlist.toArray(list);

        return list;
    }

}
