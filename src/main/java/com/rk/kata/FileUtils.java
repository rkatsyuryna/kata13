package com.rk.kata;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    private FileUtils(){};

    public static boolean isValidFolder(final String folderName) {
        final Path folderPath = new File(folderName).toPath();
        return Files.exists(folderPath) ? Files.isDirectory(folderPath) : false;
    }

    public static boolean isValidFile(final String fileName) {
        final Path filePath = new File(fileName).toPath();
        return Files.exists(filePath) ? Files.isRegularFile(filePath) : false;
    }
}
