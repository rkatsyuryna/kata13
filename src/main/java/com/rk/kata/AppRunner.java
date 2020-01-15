package com.rk.kata;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class AppRunner {

    /**
     * Main execution point. Provide path to file or folder
     * @param args
     */
    public static void main(String [] args) {
        System.out.println("Start console App");
        Path p = Paths.get("arg[0]");
        try {
            //wish we think of JVP -Xss as not to get StackOverflowException
            Files.walkFileTree(p, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE,
                    new PrintFilesCodeLines(() -> "root"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
