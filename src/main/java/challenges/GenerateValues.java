package challenges;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomUtils;

public class GenerateValues {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static int randomValue() {
    return RandomUtils.nextInt(1, 10);
  }

  private static Item generateValue(int i) {
    return new Item(i, randomValue(), randomValue());
  }

  public static void generateValues(int numValues) throws IOException {
    final List<Item> items = IntStream.range(0, numValues).mapToObj(GenerateValues::generateValue)
        .collect(Collectors.toList());

    String json = OBJECT_MAPPER.writeValueAsString(items);

    Files.write(Paths.get("values.json"), json.getBytes());
  }

}
