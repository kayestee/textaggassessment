package com.textagg.utility;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountUtility {


    public String[] parseArgs(String[] args) {
        String[] operFileName = new String[2];
        if (args.length == 0 || args.length > 2) {
            System.out.println("Specify input as '-f <file name>' or pass input string as argument");
            System.exit(-1);
        } else if (args.length == 1) {
            operFileName[0] = "";
            operFileName[1] = args[0];
            if (args[0].equals("-f")) {
                System.out.println("Specify input as '-f <file name>' or pass input string as argument");
                System.exit(-1);
            }
        } else {
            operFileName[0] = args[0];
            operFileName[1] = args[1];
            if (args[0].equals("-f") && args[1].equals("")) {
                System.out.println("Specify input as '-f <file name>' or pass input string as argument");
                System.exit(-1);
            }else if (!Files.exists(Path.of(args[1]))){
                System.out.printf("No file found with name %s \n", args[1]);
                System.exit(-1);
            }
        }
        return operFileName;
    }

    public List<String> parseInput(String[] args) {
        String oper = args[0];
        String fileName = args[1];
        List<String> inputList = new ArrayList<>();
        if (oper.equals("-f") && !fileName.equals("")) {
            try (Stream<String> inputStream = Files.lines(Path.of(fileName))) {
                inputList = inputStream.flatMap(line -> Stream.of(line.split("\\s+")))
                        .collect(Collectors.toList());
            } catch (IOException fnfe) {
                System.out.printf("Error Reading the file --> %s", fileName);
                fnfe.getStackTrace();
            }
        } else {
            inputList = Stream.of(fileName.split("\\s+")).collect(Collectors.toList());
        }
        return inputList;
    }

    public Boolean parseOutput(String filename, Map<String, Integer> outputHash){
        try(BufferedWriter bufWriter = Files.newBufferedWriter(Path.of(filename) , StandardOpenOption.CREATE)){
            outputHash.forEach((k,v) -> {
                try {
                    bufWriter.write(k.concat(",").concat(String.valueOf(v)));
                    bufWriter.write("\n");
                } catch (IOException e) {
                    System.out.println("Unable to write to output file, check permissions");
                    e.getStackTrace();
                }
            });
            System.out.printf("Output written to file -> %s \n ", filename);

        } catch (IOException ioe){
            System.out.println("Unable to create output file, check permissions");
            return false;
        }
        return true;
    }

    public Boolean parseOutputStr(String filename, Map<String, String> outputHash){
        try(BufferedWriter bufWriter = Files.newBufferedWriter(Path.of(filename) , StandardOpenOption.CREATE)){
            outputHash.forEach((k,v) -> {
                try {
                    bufWriter.write(k.concat(",").concat(v));
                    bufWriter.write("\n");
                } catch (IOException e) {
                    System.out.println("Unable to write to output file, check permissions");
                }
            });
            System.out.printf("Output written to file -> %s \n ", filename);

        } catch (IOException ioe){
            System.out.println("Unable to create output file, check permissions");
            ioe.getStackTrace();
            return false;
        }
        return true;
    }
}
