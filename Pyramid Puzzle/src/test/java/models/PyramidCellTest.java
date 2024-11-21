package models;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PyramidCellTest {

    @DisplayName("Check basic model for pyramid cell")
    @Test
    public void basic_PyramidCell() {
        PyramidCell cell = new PyramidCell("1", 0,0);
        assertEquals("1", cell.getOriginalValue());
        assertEquals(1, cell.getActualValue());

        assertEquals(0, cell.getCol());
        assertEquals(0, cell.getRow());

    }

    @DisplayName("Check complex model for pyramid cell")
    @Test
    public void complex_PyramidCell() {
        PyramidCell cell = new PyramidCell("d", 0,0);
        assertEquals("d", cell.getOriginalValue());
        assertEquals(13, cell.getActualValue());

        assertEquals(0, cell.getCol());
        assertEquals(0, cell.getRow());

    }

}