import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.ToIntFunction;

public class FuelCalculator {

    private static ToIntFunction<String> fuelCalculator = n -> {
        int calculated = Integer.valueOf(n);
        calculated = Math.floorDiv(calculated, 3);
        calculated -= 2;
        return calculated;
    };

    public static Integer getFuelNeeded(String inputFileName) throws URISyntaxException, IOException {
        List<String> fileContent;
        Path path = Paths.get(FuelCalculator.class.getResource(inputFileName).toURI());
        fileContent = Files.readAllLines(path);
        return fileContent.parallelStream().mapToInt(fuelCalculator).sum();
    }

}
