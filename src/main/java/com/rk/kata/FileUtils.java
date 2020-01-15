package com.rk.kata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileUtils {

    private static final Pattern STRING_PATTERN = Pattern.compile("(\".*?(?<!\\\\)\")");
    private static final Pattern COMMENT_PATTERN = Pattern.compile("(//.*?$)|(/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);

    private FileUtils(){};

    public static long codeLinesPerFile(final Path path) {
        String content = null;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //yes we could read this line by line and skip line if no text except comments, but KISS for this time
        final String commentsFreeText = CommentsFun.removeCommentsFromText(content);

        return commentsFreeText.lines().filter(Predicate.not(String::isBlank)).count();
    }

}
