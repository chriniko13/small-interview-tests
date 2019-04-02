package com.chriniko.interview_tests;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Fifth {

    public static void main(String[] args) {
        int[] ints = IntStream
                .rangeClosed(1, 100)
                .toArray();
        System.out.println("before shuffling: " + Arrays.toString(ints));


        // Note: create a random shuffle method.
        Random r = new Random();
        for (int i = 0; i < ints.length; i++) {
            int randomIdx = r.nextInt(ints.length);

            int temp = ints[i];
            ints[i] = ints[randomIdx];
            ints[randomIdx] = temp;
        }
        System.out.println("after shuffling: " + Arrays.toString(ints));
    }

}
