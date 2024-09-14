package com.github.krfm202.tasktracker.cli;

import com.github.krfm202.tasktracker.exceptions.CommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandInterpreter {
    public static void run() {
        try {
            while (true) {
                List<String> lines = splitLine(readConsole());
                if (lines.isEmpty()) continue;
                String command = lines.remove(0);
                CommandProcessor executor = new CommandProcessor(lines);
                findMatchingCommand(command, executor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findMatchingCommand(String command, CommandProcessor processor) {
        switch (command) {
            case "add" -> processor.processAdd();
            case "delete" -> processor.processDelete();
            case "list" -> processor.processListAll();
            case "exit" -> processor.processExit();
            default -> {
                throw new CommandException("Unknown command: " + command);
            }
        }
    }

    public static String readConsole() {
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String inputLine = "";
        try {
            System.out.println("Escribir:");
            inputLine = inputBuffer.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
        return inputLine;
    }

    public static ArrayList<String> splitLine(String text) {
        ArrayList<String> splitText = new ArrayList<>();
        String regex = "\\b\\w+\\b|\"(?:[^\"\\\\]|\\\\.)*\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            splitText.add(matcher.group());
        }
        return splitText;
    }
}
