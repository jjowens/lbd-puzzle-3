package modules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PyramidServiceTest {
    PyramidService pyramidService;

    @DisplayName("Read Example files")
    @ParameterizedTest
    @ValueSource(strings = {"example1.txt", "example2.txt"})
    void readFile_ExampleFiles(String fileName) {
        pyramidService = new PyramidService(fileName);
        List<String> lines;

        try {
            lines = pyramidService.readFile();
            assertTrue(!lines.isEmpty());
            System.out.println("No. of Lines = " + lines.size());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            fail();

        }

    }
}