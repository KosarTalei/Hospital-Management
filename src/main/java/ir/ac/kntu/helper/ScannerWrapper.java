package ir.ac.kntu.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScannerWrapper {

    private static ScannerWrapper instance = new ScannerWrapper();
    private BufferedReader line;
    private Scanner scanner;

    private ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public static ScannerWrapper getInstance() {
        return instance;
    }

    public Integer nextInt() {
        return scanner.nextInt();
    }

    public void close() {
        if (line!=null) {
            try {
                line.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    public String getInput(String prompt) {
        String input = null;
        System.out.print(prompt + " ");
        try {
            line = new BufferedReader(new InputStreamReader(System.in));
            input = line.readLine();
            if (input.length() == 0){
                return null;
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return input;
    }
}
