package com.textagg.utility;


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

    public Map<String, Integer> countUniqueWords(List<String> inputList){
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        try{
            for (String token: inputList){
                countMap.merge(token, 1,(x, y) -> Math.addExact(x, 1));
            }
        }catch (Exception ce){
            ce.getStackTrace();
        }
        return countMap;

    }

    // This will result in 3 or more means substring of
    public Map<String, Integer> countUniquePhrases(List<String> inputList) {
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        int cnt = 2;
        while (cnt < inputList.size()) {
            for (int i = cnt; i < inputList.size(); i++) {
                String phrase = String.join(" ", inputList.subList(i-cnt, i+1));
                countMap.merge(phrase, 1, (x,y)-> Math.addExact(x,1));
            }
            cnt++;
        }
        Map<String, Integer> filteredCountMap = countMap.entrySet().stream()
                                                    .filter(val -> val.getValue() > 1).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
       return filteredCountMap;
    }


    public Map<String, String>  countUniqueNumbers(List<String> inputList){
        Map<String, String> countMap = new HashMap<String, String>();
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
        return countMap;

    }
}
