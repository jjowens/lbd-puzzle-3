package services;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicTest {

    @DisplayName("Check if value is a hexadecimal type")
    @Test
    public void printHexValue() {
      String value = String.format("0x%08X", 23);
      assertEquals("0x00000017",value);
    }

}
