package day7;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static class Folder {
        private final String name;
        Folder parent;
        Map<String, Integer> fileSizes = new HashMap<>();
        Map<String, Folder> folders = new HashMap<>();
        private int size = -1;

        Folder(Folder parent, String name) {
            this.parent = parent;
            this.name = name;
        }

        public int size() {
            if (this.size == -1) {
                size = 0;
                for (Folder subfolder : folders.values()) {
                    size += subfolder.size();
                }
                for (Integer fileSize : fileSizes.values()) {
                    size += fileSize;
                }
            }
            return this.size;
        }

        public List<Folder> foldersSmallerThan(int maxSize) {
            List<Folder> smallFolders = new ArrayList<>();
            for (Folder subfolder : folders.values()) {
                if (subfolder.size() <= maxSize) {
                    smallFolders.add(subfolder);
                }
                smallFolders.addAll(subfolder.foldersSmallerThan(maxSize));
            }
            return smallFolders;
        }

        public List<Folder> foldersLargerThan(int minSize) {
            List<Folder> largeFolders = new ArrayList<>();
            for (Folder subfolder : folders.values()) {
                if (subfolder.size() >= minSize) {
                    largeFolders.add(subfolder);
                }
                largeFolders.addAll(subfolder.foldersLargerThan(minSize));
            }
            return largeFolders;
        }

        public String path() {
            if (parent != null) {
                return parent.path() + name + "/";
            } else {
                return "/";
            }
        }
    }

    private static Folder readInput() throws IOException {
        List<String> inputData = Files.readAllLines(Path.of("src/day7/input"));

        Folder rootFolder = new Folder(null, "/");
        Folder currentFolder = rootFolder;

        for (String line : inputData) {
            String[] parts = line.split(" ");
            if (parts[0].equals("$")) {
                switch(parts[1]) {
                    case "cd":
                        if (parts[2].equals("/")) {
                            currentFolder = rootFolder;
                        } else if (parts[2].equals("..")) {
                            if (currentFolder.parent == null) {
                                throw new IOException("Attempted to traverse up from root");
                            }
                            currentFolder = currentFolder.parent;
                        } else {
                            if (!currentFolder.folders.containsKey(parts[2])) {
                                currentFolder.folders.put(parts[2], new Folder(currentFolder, parts[2]));
                            }
                            currentFolder = currentFolder.folders.get(parts[2]);
                        }
                        break;
                    case "ls":
                        break;
                    default:
                        throw new IOException("Unexpected command");
                }
            } else if (parts[0].equals("dir")) {
                if (!currentFolder.folders.containsKey(parts[1])) {
                    currentFolder.folders.put(parts[1], new Folder(currentFolder, parts[1]));
                }
            } else {
                currentFolder.fileSizes.put(parts[1], Integer.valueOf(parts[0]));
            }
        }
        return rootFolder;
    }

    public static void partOne() throws IOException {
        Folder rootFolder = readInput();
        int totalSize = 0;
        List<Folder> smallFolders = rootFolder.foldersSmallerThan(100000);
        for (Folder smallFolder : smallFolders) {
            totalSize += smallFolder.size();
        }
        System.out.println("Total size of small folders: " + totalSize);
    }

    public static void partTwo() throws IOException {
        int unused = 30000000;
        int total = 70000000;
        int maxSpace = total - unused;
        Folder rootFolder = readInput();
        int usedSpace = rootFolder.size();
        System.out.println("Used: "+ usedSpace);
        int toDelete = usedSpace - maxSpace;
        System.out.println("To Delete: "+ toDelete);

        List<Folder> options = rootFolder.foldersLargerThan(toDelete);
        Folder smallest = options.get(0);
        for (Folder option : options) {
            if (option.size() < smallest.size()) {
                smallest = option;
            }
        }
        System.out.println("Size of smallest sufficiently big folder: "+ smallest.size());
    }

    public static void main(String[] args) throws IOException {
        partTwo();
    }
}
