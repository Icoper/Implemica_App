package com.wbapps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by samik on 20.05.2017.
 * <p>
 * Execute the task of finding the correct bracket sequence. I used the Catalan method
 */

public class TaskOne {
    private static final String TASK_START_TEXT = "Enter N : ";
    private static final String INPUT_ERROR = "Incorrect number.";
    private static final String RESULT_TEXT = "Result is: ";

    private int enterNumber; // User-entering number of bracket
    private int result; // Our target number

    private BufferedReader reader;

    public void start() {
        reader = new BufferedReader(new InputStreamReader(System.in));

        printL(TASK_START_TEXT);

        try {
            String input = reader.readLine();

            //I check the entered number by the user for validity. In case of an error, please re-enter.
            if (checkInputData(input) && Integer.parseInt(input) > 0) {
                enterNumber = Integer.parseInt(input);
            } else {
                printL(INPUT_ERROR);
                start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate the result and output it to the console
        result = getResult(enterNumber);
        printL(RESULT_TEXT + result);

    }

    /**
     * This method counts our number by the Catalan method
     *
     * @param n it's our enterNumber;
     * @return result number
     */
    private int getResult(int n) {
        if (n <= 0) {
            return 1;
        }

        result = 0;

        for (int i = 0; i < n; i++) {
            result += getResult(i) * getResult((n - 1) - i);
        }

        return result;

    }

    /**
     * This method is called when we need to check the string for the correctness.
     *
     * @param s User-entered string
     * @return If the string contains characters other than numbers, it returns false
     */
    private boolean checkInputData(String s) {
        return s.matches("-?[\\d]+");
    }

    private void printL(String line) {
        System.out.println(line);
    }

}