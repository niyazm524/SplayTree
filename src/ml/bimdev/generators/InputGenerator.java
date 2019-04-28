package ml.bimdev.generators;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InputGenerator {
    public static void main(String[] args) {
        createData("assets/input.txt", 100, 20);
    }

    private static void createData(String filename, int amount, int step) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();

        try (FileWriter writer = new FileWriter(filename, false)) {
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < i * step; j++) {
                    text.append(random.nextInt(1000)).append(" ");
                }
                writer.write(text.toString());
                writer.append('\n');
                text = new StringBuilder();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
