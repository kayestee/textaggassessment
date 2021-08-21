package com.textagg.utility;

import org.apache.commons.math3.stat.StatUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Counting
{

    public void countUniqueWords(List<String> inputList){
        final String outputFile = "1.out";
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        try{
            for (String token: inputList){
                countMap.merge(token, 1,(x, y) -> Math.addExact(x, 1));
            }
            CountUtility utilObj = new CountUtility();
            utilObj.parseOutput(outputFile,countMap);
        }catch (Exception ce){
            ce.getStackTrace();
        }


    }

    // This will result in 3 or more means substring of
    public void countUniquePhrases(List<String> inputList) {
        final String outputFile = "2.out";
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        Set<String> strSet = new HashSet<>();
        int cnt = 2;
        while (cnt < inputList.size()) {
            for (int i = cnt; i < inputList.size(); i++) {
                String phrase = String.join(" ", inputList.subList(i-cnt, i+1));
                countMap.merge(phrase, 1, (x,y)-> Math.addExact(x,1));
            }
            cnt++;
        }
        Map<String, Integer> filteredCountMap = countMap.entrySet().stream().filter(val -> val.getValue() > 1).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        CountUtility utilObj = new CountUtility();
        utilObj.parseOutput(outputFile,filteredCountMap);
    }


    public void countUniqueNumbers(List<String> inputList){
        final String outputFile = "3.out";
        HashMap<String, String> countMap = new HashMap<String, String>();
        for (String token: inputList){
            if (token.strip().matches("^[\\d]+$")){
                HashMap<Character, Integer> tempMap = new HashMap<Character, Integer>();
                token.chars().mapToObj(e -> (char)e)
                        .forEach(e ->  tempMap.merge(e, 1,(x, y) -> Math.addExact(x, 1)));
                countMap.put(token, tempMap.entrySet().stream()
                        .map(e -> String.valueOf(e.getKey()).concat("->").concat(String.valueOf(e.getValue())))
                        .collect(Collectors.joining(",")));
            }
            }
        CountUtility utilObj = new CountUtility();
        utilObj.parseOutputSec(outputFile,countMap);
    }
}
