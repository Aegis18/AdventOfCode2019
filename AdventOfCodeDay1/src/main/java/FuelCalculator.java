import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class FuelCalculator {

    private static final IntUnaryOperator fuelCalculator = n -> {
        return Math.floorDiv(n, 3) - 2;
    };

    private static final IntUnaryOperator fuelCalculator2 = n -> {
        int sum = 0;
        int calculated = n;
        while (calculated > 2) {
            calculated = Math.floorDiv(calculated, 3) - 2;
            sum += Math.max(calculated, 0);
        }
        return sum;
    };

    public static Integer getFuelNeededPart1(String inputFileName) throws URISyntaxException, IOException {
        return getFuelNeeded(inputFileName, fuelCalculator);
    }

    public static Integer getFuelNeededPart2(String inputFileName) throws URISyntaxException, IOException {
        return getFuelNeeded(inputFileName, fuelCalculator2);
    }

    private static Integer getFuelNeeded(String inputFileName, IntUnaryOperator function) throws IOException, URISyntaxException {
        List<String> fileContent;
        Path path = Paths.get(FuelCalculator.class.getResource(inputFileName).toURI());
        fileContent = Files.readAllLines(path);
        return fileContent.parallelStream().mapToInt(Integer::parseInt).map(function).sum();
    }

}
