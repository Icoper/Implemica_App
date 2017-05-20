package com.wbapps;

import com.wbapps.TaskTwo.GetTaskData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is used to select a task by the user.
 */

public class Main {
    private static final String TEXT_ERROR = "ERROR! Re-enter psl!";

    public static void main(String[] args) {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        /**
         * This is the task selection menu.
         * After entering a certain number,
         * the method will send a request to start() the desired task
         */
        System.out.println("Task 1 - enter '1'" +
                "\nTask 2 - enter '2'" +
                "\nTask 3 - enter '3'" +
                "\nChose test task : ");

        String s = null;
        try {
            s = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check the entered data
        if (!checkInputData(s)) {

            // if false - stop app
            System.out.println(TEXT_ERROR);
            return;
        }
        byte result = Byte.parseByte(s);

        switch (result) {
            case 1:
                new TaskOne().start();
                break;
            case 2:
                new GetTaskData().start();
                break;
            case 3:
                new TaskThree().start();
                break;
        }
    }

    /**
     * This method is called when we need to check the string for the correctness.
     *
     * @param s User-entered string
     * @return If the string contains characters other than numbers, it returns false
     */
    private static boolean checkInputData(String s) {
        return s.matches("-?[\\d]+");
    }
}
