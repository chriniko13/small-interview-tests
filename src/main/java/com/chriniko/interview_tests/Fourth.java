package com.chriniko.interview_tests;

import java.util.LinkedHashSet;
import java.util.Set;

public class Fourth {

    private static boolean isDuplicate_1(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char selectedChar = chars[i];

            for (int k = 0; k < chars.length; k++) {
                if (i == k) continue;

                if (selectedChar == chars[k]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isDuplicate_2(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int charsLength = input.toCharArray().length;

        Set<Character> s = new LinkedHashSet<>();
        for (char c : input.toCharArray()) {
            s.add(c);
        }

        return s.size() < charsLength;
    }

    private static boolean isDuplicate_3(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        char[] chars = input.toCharArray();
        Set<Character> scanned = new LinkedHashSet<>();

        for (int i = 0; i < chars.length; i++) {
            char selectedChar = chars[i];

            for (int k = 0; k < chars.length; k++) {
                if (i == k) continue;
                if (scanned.contains(chars[k])) continue;

                if (selectedChar == chars[k]) {
                    return true;
                }
            }

            scanned.add(selectedChar);
        }
        return false;
    }

    public static void main(String[] args) {

        // first approach
        long startTime = System.nanoTime();
        System.out.println(isDuplicate_1("abcdec"));
        System.out.println(isDuplicate_1("abbcdec"));
        System.out.println(isDuplicate_1("abcdef"));
        System.out.println(isDuplicate_1("abcdefghh"));
        try {
            System.out.println(isDuplicate_1(""));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        try {
            System.out.println(isDuplicate_1(null));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        long totalTime = System.nanoTime() - startTime;
        System.out.println(totalTime);
        System.out.println("\n\n");


        // second approach
        startTime = System.nanoTime();
        System.out.println(isDuplicate_2("abcdec"));
        System.out.println(isDuplicate_2("abbcdec"));
        System.out.println(isDuplicate_2("abcdef"));
        System.out.println(isDuplicate_2("abcdefghh"));
        try {
            System.out.println(isDuplicate_2(""));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        try {
            System.out.println(isDuplicate_2(null));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println(totalTime);
        System.out.println("\n\n");


        // third approach
        startTime = System.nanoTime();
        System.out.println(isDuplicate_3("abcdec"));
        System.out.println(isDuplicate_3("abbcdec"));
        System.out.println(isDuplicate_3("abcdef"));
        System.out.println(isDuplicate_3("abcdefghh"));
        try {
            System.out.println(isDuplicate_3(""));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        try {
            System.out.println(isDuplicate_3(null));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println(totalTime);
        System.out.println("\n\n");

    }

}
