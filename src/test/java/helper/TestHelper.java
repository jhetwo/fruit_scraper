package helper;

import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestHelper {
    public TestHelper() {
    }

    public String getStringFromFile(String path) throws IOException {
        List<String> strings = Files.readAllLines(Paths.get(path));
        return StringUtils.join(strings, "");
    }
}