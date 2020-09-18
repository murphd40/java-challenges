package challenges;

import java.io.IOException;
import java.util.List;

public class Application {

  public static void main(String[] args) throws IOException {
//    GenerateValues.generateValues(50);

    List<Item> items = LoadValues.loadValues();

    long start = System.currentTimeMillis();

    List<Item> solution = KnapsackSolver.memoizedRecursive(items, 30);

    System.out.println(solution);

    System.out.println(String.format("Time taken: %s seconds", (System.currentTimeMillis() - start) / 1000.0));

  }
}
