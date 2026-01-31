package csvInput;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CSVLoader {
    public static int[] loadCSVInput() {
        try (Reader reader = Files.newBufferedReader(Path.of("data.csv"));
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces())) {

            List<Integer> integerList = new ArrayList<>();

            for (CSVRecord record : parser) {
                integerList.add(Integer.parseInt(record.get(0)));
            }

            return integerList.stream().mapToInt(Integer::intValue).toArray();
        } catch (IOException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        return null;
    }
}
