package csvInput;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CSVLoader {
    public static int[] loadCSVInput() {
        try (InputStream is = CSVLoader.class.getResourceAsStream("/data.csv")) {
            assert is != null;
            try (CSVParser parser = new CSVParser(new InputStreamReader(is),
                    CSVFormat.DEFAULT.withIgnoreSurroundingSpaces())) {

                List<Integer> integerList = new ArrayList<>();

                for (CSVRecord record : parser) {
                    integerList.add(Integer.parseInt(record.get(0)));
                }

                return integerList.stream().mapToInt(Integer::intValue).toArray();
            }
        } catch (IOException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        return null;
    }
}
