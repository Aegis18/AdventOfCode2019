import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static Integer getFuelNeededPart1(String inputFileName) {
        return getFuelNeeded(inputFileName, fuelCalculator);
    }

    public static Integer getFuelNeededPart2(String inputFileName) {
        return getFuelNeeded(inputFileName, fuelCalculator2);
    }

    private static int getFuelNeeded(String inputFileName, IntUnaryOperator function) {
        int result = 0;
        try (final BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(FuelCalculator.class.getResourceAsStream(inputFileName)))) {
            result = bufferedReader.lines().parallel().mapToInt(Integer::parseInt).map(function).sum();
        } catch (final IOException e) {
            e.printStackTrace(System.err);
        }
        return result;
    }

}
