package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static final Path PATH = Path.of("data/day7.txt");
    public static long spaceToDelete = Long.MAX_VALUE;
    public static long smallestDirectory = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        var data = Files.readAllLines(PATH);

        //Load Data
        var currentDirectory = new Directory("disk", null, new ArrayList<>(), null);
        var root = new Directory("/", new ArrayList<>(), new ArrayList<>(), currentDirectory);
        currentDirectory.directories().add(root);

        for (var line : data) {
            var lineData = line.split(" ");
            switch (lineData[0]) {
                case "$" -> {
                    if (lineData[1].equals("cd")) {
                        if (lineData[2].equals("..")) {
                            currentDirectory = currentDirectory.parent();
                        } else {
                            var dir = currentDirectory.directories()
                                    .stream()
                                    .filter(directory -> directory.name().equals(lineData[2]))
                                    .findFirst();
                            if (dir.isPresent()) {
                                currentDirectory = dir.get();
                            }
                        }
                    }
                }
                case "dir" -> {
                    var dir = new Directory(lineData[1], new ArrayList<>(), new ArrayList<>(), currentDirectory);
                    currentDirectory.directories().add(dir);
                }
                default -> {
                    var size = Long.parseLong(lineData[0]);
                    var file = new File(lineData[1], size);
                    currentDirectory.files().add(file);
                }
            }
        }

        //find all of the directories with a total size of at most 100000
        var usedSpace = traverse(root);
        var freeSpace = 70_000_000 - usedSpace;
        spaceToDelete = 30_000_000 - freeSpace;
        //System.out.println(spaceToDelete);
        //System.out.println(smallestDirectory);

        traverse(root);
        System.out.println(smallestDirectory);

    }

    private static long traverse(Directory directory) {
        long sum = 0;
        long size = directory.totalSizeOfCurrentFiles();

        for (var child : directory.directories()) {
            sum += traverse(child);
        }

        if (sum + size >= spaceToDelete) {
            if (sum + size < smallestDirectory) {
                smallestDirectory = sum + size;
            }
        }
        return size + sum;
    }
}

record File(String name, long size) {
}

record Directory(String name, List<File> files, List<Directory> directories, Directory parent) {
    public long totalSizeOfCurrentFiles() {
        return files.stream().mapToLong(File::size).sum();
    }
}