package ml.bimdev.charts;

import ml.bimdev.generators.InputParser;
import ml.bimdev.presentation.TestRunner;
import ml.bimdev.tree.SplayTree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringJoiner;


public class Main {
    private static final int TIMES = 5;
    private static List<Integer[]> input;
    private static PrintWriter printWriter;

    public static void main(String[] args) throws IOException {
        input = InputParser.parseInputFile("assets/input.txt");
        printWriter = new PrintWriter(new FileOutputStream(new File("assets/table.csv")));
        printWriter.println("N,insert(N),search(N),remove(N)");

        for(Integer[] dataset : input) {
            SplayTree<Integer, Void> tree = new SplayTree<>();
            int insertTime = bench(dataset, key -> tree.insert(key, null));
            int size = tree.size();
            int searchTime = bench(dataset, key -> tree.search(key));
            SplayTree<Integer, Void> removeTree = new SplayTree<>();
            bench(dataset, key -> removeTree.insert(key, null));
            int removeTime = bench(dataset, key -> removeTree.remove(key));
            print(size, insertTime, searchTime, removeTime);
        }
        printWriter.flush();
        printWriter.close();
    }

    private static void print(Number ...numbers) {
        StringJoiner joiner = new StringJoiner(",");
        for (Number number : numbers)
            joiner.add(number.toString());
        printWriter.println(joiner.toString());
    }

    private static int bench(Integer[] dataset, OperationRunner runner) {
        long startTime = System.nanoTime();
        for (int i = 0; i < TIMES; i++) {
            for (Integer number : dataset) {
                runner.run(number);
            }
        }
        long stopTime = System.nanoTime();
        return Math.round((stopTime - startTime) / (TIMES * 1000f));
    }
}

interface OperationRunner {
    void run(Integer key);
}
