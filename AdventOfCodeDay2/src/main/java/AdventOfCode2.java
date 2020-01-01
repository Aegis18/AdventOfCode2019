import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class AdventOfCode2 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Path.of(AdventOfCode2.class.getResource("input.txt").toURI());
        int[] nums = List.of(Files.readString(path).split(",")).stream().mapToInt(Integer::parseInt).toArray();
        intcode1(nums);
        nums = List.of(Files.readString(path).split(",")).stream().mapToInt(Integer::parseInt).toArray();
        intcode2(nums);
        System.out.println("Arrays.toString(intcode(nums)) = " + Arrays.toString(nums));
        System.out.println("Arrays.toString(nums2) = " + (100*nums[1]+nums[2]));
    }

    public static void intcode1(int[] optcode){
        intcodeHelper(optcode, 12, 2);
    }

    public static void intcode2(int[] optcode){
        for(int i = 0; i < 100; i++){
            for(int j=0; j<100; j++){
                int[] newOptCode = Arrays.copyOf(optcode, optcode.length);
                intcodeHelper(newOptCode, i, j);
                if(newOptCode[0] == 19690720){
                    for(int k = 0; k < optcode.length; k++){
                        optcode[k] = newOptCode[k];
                    }
                    return;
                }
            }
        }
    }

    private static void intcodeHelper(int[] optcode, int noun, int verb){
        optcode[1] = noun;
        optcode[2] = verb;
        for(int i = 0; optcode[i] != 99; i = i +4){
            switch (optcode[i]){
                case 1:
                    optcode[optcode[i+3]] = optcode[optcode[i+1]] + optcode[optcode[i+2]];
                    break;

                case 2:
                    optcode[optcode[i+3]] = optcode[optcode[i+1]] * optcode[optcode[i+2]];
                    break;
                default:
                    throw new IllegalArgumentException("Error in array at index: " + i + " optcode: " + optcode[i]);
            }
        }
    }
}
