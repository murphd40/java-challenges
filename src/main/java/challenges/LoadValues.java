package challenges;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LoadValues {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static List<Item> loadValues() throws IOException {
    Path path = Paths.get("values.json");

    try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
      String content = bufferedReader.lines().reduce("", String::concat);
      return OBJECT_MAPPER.readValue(content,
          OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Item.class));
    }
  }

}
