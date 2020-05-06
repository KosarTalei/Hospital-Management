package ir.ac.kntu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScannerWrapper {

    private static ScannerWrapper instance = new ScannerWrapper();
    private Scanner scanner;

    private ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public static ScannerWrapper getInstance() {
        return instance;
    }

    public String next() {
        return scanner.next();
    }

    public Double nextDouble() {
        return scanner.nextDouble();
    }

    public Integer nextInt() {
        return scanner.nextInt();
    }

    public void close() {
        scanner.close();
    }

    public static String getInput(String prompt) {

        String input = null;
        System.out.print(prompt + " ");
        BufferedReader line =null;
        try {
            line = new BufferedReader(new InputStreamReader(System.in));
            input = line.readLine();
            if (input.length() == 0){
                return null;
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }finally {
            System.out.println("");
        }
        return input;
    }
}
