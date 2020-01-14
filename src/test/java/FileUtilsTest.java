import com.rk.kata.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUtilsTest {

    private final static String EMPTY_PATH = "NON EXISTING PATH";

    @Test
    public void testIsValidFileForExistingFile() {
        String path = getAbsolutePathTestResources("testFile.txt");

        boolean validFile = FileUtils.isValidFile(path);

        assertTrue(validFile);
    }

    @Test
    public void testIsValidFileForNonExistingFile() {
        String path = getAbsolutePathTestResources("fake.txt");

        boolean validFile = FileUtils.isValidFile(path);

        assertFalse(validFile);
    }

    @Test
    public void testIsValidFileForExistingFolder() {
        String path = getAbsolutePathTestResources("testFolder");

        boolean validFile = FileUtils.isValidFile(path);

        assertFalse(validFile);
    }

    @Test
    public void testIsValidFileForNonExistingFolder() {
        String path = getAbsolutePathTestResources("fakeFolder");

        boolean validFile = FileUtils.isValidFile(path);

        assertFalse(validFile);
    }

    @Test
    public void testIsValidFolderForExistingFolder() {
        String path = getAbsolutePathTestResources("testFolder");

        boolean validFolder = FileUtils.isValidFolder(path);

        assertTrue(validFolder);
    }

    @Test
    public void testIsValidFolderForNonExistingFolder() {
        String path = getAbsolutePathTestResources("fakeFolder");

        boolean validFolder = FileUtils.isValidFolder(path);

        assertFalse(validFolder);
    }

    @Test
    public void testIsValidFolderForExistingFile() {
        String path = getAbsolutePathTestResources("testFile.txt");

        boolean validFolder = FileUtils.isValidFolder(path);

        assertFalse(validFolder);
    }

    @Test
    public void testIsValidFolderForNonExistingFile() {
        String path = getAbsolutePathTestResources("fileFile.txt");

        boolean validFolder = FileUtils.isValidFolder(path);

        assertFalse(validFolder);
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
