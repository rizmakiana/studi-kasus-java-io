package com.rizmakiana.util;

public class Clear {

    public static void Screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
