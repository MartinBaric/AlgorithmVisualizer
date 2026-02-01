import csvInput.CSVLoader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CSVLoaderTest {
    @Test
    void testLoadCSVInput() {
        // Act
        int[] numbers = CSVLoader.loadCSVInputAsArray();

        assertNotNull(numbers, "CSVLoader returned null");

        assertTrue(numbers.length > 0, "CSV file is empty");
    }
}
