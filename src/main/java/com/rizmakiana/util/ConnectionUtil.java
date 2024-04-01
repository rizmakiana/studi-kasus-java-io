package com.rizmakiana.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConnectionUtil {

    private static File file = new File("src\\main\\java\\com\\rizmakiana\\database\\database.txt");
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;


    public static BufferedReader getConnectionShow() throws IOException {
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);

        return bufferedReader;
    }

    public static BufferedWriter getConnectionAdd() throws IOException  {
        fileWriter = new FileWriter(file,true);
        bufferedWriter = new BufferedWriter(fileWriter);

        return bufferedWriter;
    }

    public static File getConnectionFile() throws IOException {
        return file;
    }

    public static FileWriter getConnectionToDeleteWithReset() throws IOException {
        return new FileWriter(file, false);
    }
    

}
