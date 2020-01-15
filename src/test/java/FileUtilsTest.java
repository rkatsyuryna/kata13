import com.rk.kata.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

    private final static String EMPTY_PATH = "NON EXISTING PATH";

    @Test
    public void testCodeLinesPerFile() throws IOException {
        String path = getAbsolutePathTestResources("testFile.txt");

        long expectedCount = FileUtils.codeLinesPerFile(Path.of(path));

        assertEquals(expectedCount, 2);
    }

    private String getAbsolutePathTestResources(String resourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceURL = classLoader.getResource(resourceName);
        if(resourceURL == null) {
            return EMPTY_PATH;
        }

        File file = new File(resourceURL.getFile());

        return file.getAbsolutePath();
    }
}
