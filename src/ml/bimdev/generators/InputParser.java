package ml.bimdev.generators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    public static List<Integer[]> parseInputFile(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename))
                .stream()
                .map(line -> Arrays.stream(
                        line.split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new)
                )
                .collect(Collectors.toList());
    }
}
