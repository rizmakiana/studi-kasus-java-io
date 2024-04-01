package com.rizmakiana.util;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String name(String message) {
        System.out.print(message);
        String word = scanner.nextLine();
        return word;
    }

    public static String string(String message) {
        System.out.print(message);
        String word = scanner.next();
        return word;
    }

    public static int number(String message) {
        System.out.print(message);
        int x = scanner.nextInt();
        return x;
    }

    public static boolean getYesOrNo(String message) {
        System.out.print(message);
        String word = scanner.next();
        
        while (!word.equalsIgnoreCase("y") && !word.equalsIgnoreCase("n")) {
            System.out.println("Pilihan salah");
            System.out.print(message);
            word = scanner.next();
        }

        return word.equalsIgnoreCase("y");
    }
}
