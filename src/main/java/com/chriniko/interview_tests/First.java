package com.chriniko.interview_tests;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class First {

    // Structural design pattern ---> Composite
    public static void main(String[] args) {

        File file1 = new File("file1", "txt");
        File file2 = new File("file2", "tar");
        File file3 = new File("file3", "zip");

        Folder folder1 = new Folder("folder1");
        folder1.getContents().add(file1);
        folder1.getContents().add(file2);
        folder1.getContents().add(file3);

        System.out.println("folder1 size: " + folder1.size());

        Folder folder2 = new Folder("folder2");

        List<File> files = IntStream.rangeClosed(4, 6)
                .boxed()
                .map(idx -> new File(
                                "file" + idx,
                                Arrays.asList("txt", "tar", "zip").get(new Random().nextInt(3))
                        )
                )
                .collect(Collectors.toList());
        files.forEach(f -> folder2.getContents().add(f));

        folder2.getContents().add(folder1);

        System.out.println("folder2 size: " + folder2.size());
    }


    public abstract static class Traversable {
        protected String name;

        public Traversable(String name) {
            this.name = name;
        }

        public String name() {
            return name;
        }

        public abstract int size();
    }

    public static class Folder extends Traversable {
        private String name;
        private List<Traversable> contents;

        public Folder(String name) {
            super(name);
            this.contents = new LinkedList<>();
        }

        @Override
        public int size() {
            int sum = 0;
            for (Traversable content : contents) {
                sum += content.size();
            }
            return sum;
        }

        public List<Traversable> getContents() {
            return contents;
        }
    }

    public static class File extends Traversable {
        private String type;

        public File(String name, String type) {
            super(name);
            this.type = type;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public int size() {
            return 1;
        }

        public String getType() {
            return type;
        }
    }


}
