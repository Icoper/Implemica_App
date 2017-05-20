package com.wbapps;

import java.math.BigInteger;

/**
 * Created by samik on 20.05.2017.
 * <p>
 * <p>
 * Find the sum of the digits in the number 100!.
 */
public class TaskThree {
    private static final int COUNT_NUMBER = 100;
    private static final String SUM_NUMBER = "Sum is - ";


    public void start() {
        int sum = 0;

        // This variable will store the factorial 100
        BigInteger factorial = BigInteger.ONE;

        // Consider factorial 100
        for (long i = 2; i <= COUNT_NUMBER; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        // The resulting factorial is divided into an array of characters,
        // in each of the elements of the array will be stored 1 digit.
        String s = String.valueOf(factorial);
        String[] sList = s.split("");

        // Fold all the numbers together, output the result to the console
        for (int i = 0; i < sList.length; i++) {
            sum += Integer.parseInt(sList[i]);
        }

        printL(SUM_NUMBER + sum);
    }


    // Output the result on the screen
    private void printL(String line) {
        System.out.println(line);
    }
}
