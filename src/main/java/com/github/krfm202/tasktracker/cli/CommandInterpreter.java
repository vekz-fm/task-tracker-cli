package com.github.krfm202.tasktracker.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandInterpreter {
    private static final Map<String, Consumer<String[]>> commands = new HashMap<>();

    public static void main(String[] args) {


    }

    public static String ConsoleReader() {
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = "";
        try {
            System.out.println("Escribir:");
            inputLine = inputBuffer.readLine();
            //System.out.println(inputLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return inputLine;
    }

}
