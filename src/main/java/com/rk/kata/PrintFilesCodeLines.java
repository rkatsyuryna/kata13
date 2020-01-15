package com.rk.kata;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

class PrintFilesCodeLines extends SimpleFileVisitor<Path> {

    private static final String TAB_CHAR = " ";

    final Supplier<String> rootFolderNamingFunction;
    private int counter = 0;

    PrintFilesCodeLines(Supplier<String> rootFolderNamingFunction) {
        this.rootFolderNamingFunction = rootFolderNamingFunction;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) throws IOException {
        String dirName = dir.getFileName().toString();
        if(counter == 0) {
            dirName = rootFolderNamingFunction.get();
        }
        System.out.format(TAB_CHAR.repeat(counter) + "%s : %d\n", dirName, getDirSize(dir));
        counter ++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        counter --;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        System.out.format(TAB_CHAR.repeat(counter+1) + "%s : %d\n", file.getFileName(), FileUtils.codeLinesPerFile(file));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    /**
     * Walks through directory path and sums up all files' lines with code count.
     *
     * @param dirPath Path to directory.
     * @return Total size of all files lines with code included in dirPath.
     * @throws IOException
     */
    private long getDirSize(Path dirPath) throws IOException {
        final AtomicLong size = new AtomicLong(0L);

        Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                size.addAndGet(FileUtils.codeLinesPerFile(file));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                //just skip
                return FileVisitResult.CONTINUE;
            }
        });

        return size.get();
    }
}

