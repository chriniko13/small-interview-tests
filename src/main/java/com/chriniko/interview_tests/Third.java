package com.chriniko.interview_tests;

import java.util.*;

public class Third {

    public static void main(String[] args) {

         /*
            expected result:
                a\n
                b
         */
        String result = Third.decode(
                Arrays.asList("a\t100100", "b\t100101", "[newline]\t111111"),
                "100100111111100101"
        );
        System.out.println("RESULT: " + result);


        /*
            expected result:
                pqa\n
                bcp
         */
        result = Third.decode(
                Arrays.asList("a\t100100", "b\t100101", "c\t110001", "d\t100000", "[newline]\t111111", "p\t111110", "q\t000001"),
                "111110000001100100111111100101110001111110"
        );
        System.out.println("RESULT: " + result);

        /*
            expected result:
                a\n
                bcd
         */
        result = Third.decode(
                Arrays.asList("a\t100100", "b\t100101", "c\t110001", "d\t100000", "[newline]\t111111"),
                "100100111111100101110001100000"
        );
        System.out.println("RESULT: " + result);

    }

    /*
        Huffman coding.

        Example:

        codes:
            a\t100100, b\t100101, [newline]\t111111

        encoded:
            100100111111100101

        decoded:
           a\nb

     */
    private static String decode(List<String> codes, String encoded) {
        System.out.println("\n~~~decode called~~~");
        Map<String, String> mappings = new LinkedHashMap<>();

        for (String code : codes) {
            String[] splited = code.split("\t");
            mappings.put(splited[1], splited[0]);
        }

        int codingLength = mappings.entrySet().iterator().next().getKey().length();

        System.out.println("mappings: " + mappings);
        System.out.println("codingLength: " + codingLength);
        System.out.println();

        List<String> intermediateStep = new ArrayList<>(encoded.length() / codingLength);

        for (int i = 0; i < encoded.length(); i += codingLength) {
            String substring = encoded.substring(i, codingLength + i);

            intermediateStep.add(substring);
        }

        System.out.println("intermediateStep: " + intermediateStep);
        System.out.println();

        StringBuilder result = new StringBuilder();

        for (String s : intermediateStep) {
            String decodedChar = mappings.get(s);
            if ("[newline]".equals(decodedChar)) {
                decodedChar = "\n";
            }
            result.append(decodedChar);
        }

        return result.toString();
    }


}
