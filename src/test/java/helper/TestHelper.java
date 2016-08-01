package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TestHelper {
    public TestHelper() {
    }

    public String getStringFromFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path))
                .stream()
                .collect(Collectors.joining());
    }
}