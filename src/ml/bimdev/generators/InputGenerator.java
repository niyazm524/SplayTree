package ml.bimdev.generators;

import java.io.*;
import java.util.Random;

public class InputGenerator {
    public static void main(String[] args) {
        createData("assets/input.txt", 100, 20);
    }

    private static void createData(String filename, int amount, int step) {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 1; i <= amount; i++) {
                    random.ints(1, 10000)
                            .distinct()
                            .limit(i*step)
                            .forEach(num -> {
                                try {
                                    writer.append(Integer.toString(num)).append(" ");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                writer.append('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
