package com.rk.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copy pasted from (https://stackoverflow.com/questions/1657066/java-regular-expression-finding-comments-in-code)
 * as time limits to 2 hr
 */
public class CommentsFun {
    static List<Match> commentMatches = new ArrayList<Match>();

    public static String removeCommentsFromText(String inputText) {
        Pattern commentsPattern = Pattern.compile("(//.*?$)|(/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);
        Pattern stringsPattern = Pattern.compile("(\".*?(?<!\\\\)\")");

        Matcher commentsMatcher = commentsPattern.matcher(inputText);
        while (commentsMatcher.find()) {
            Match match = new Match();
            match.start = commentsMatcher.start();
            match.text = commentsMatcher.group();
            commentMatches.add(match);
        }

        List<Match> commentsToRemove = new ArrayList<Match>();

        Matcher stringsMatcher = stringsPattern.matcher(inputText);
        while (stringsMatcher.find()) {
            for (Match comment : commentMatches) {
                if (comment.start > stringsMatcher.start() && comment.start < stringsMatcher.end())
                    commentsToRemove.add(comment);
            }
        }
        for (Match comment : commentsToRemove)
            commentMatches.remove(comment);

        for (Match comment : commentMatches)
            inputText = inputText.replace(comment.text, " ");

       return inputText;
    }

    static class Match {
        int start;
        String text;
    }
}
