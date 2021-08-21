package com.textagg.controller;

import com.textagg.utility.CountUtility;
import com.textagg.utility.Counting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TextAggregator {

    public static void main(String[] args){
        CountUtility utilObj = new CountUtility();
        String[] inputArgs = utilObj.parseArgs(args);

        Counting doCount = new Counting();
        if (!inputArgs[1].isEmpty()){
            List<String> inputList = utilObj.parseInput(inputArgs);
            Map<String, Integer> countWordsMap = doCount.countUniqueWords(inputList);
            utilObj.parseOutput("1.out",countWordsMap);

            Map<String, String> countNumbersMap =  doCount.countUniqueNumbers(inputList);
            utilObj.parseOutputStr("2.out",countNumbersMap);

            Map<String, Integer> countUniquePhrasesMap = doCount.countUniquePhrases(inputList);
            utilObj.parseOutput("3.out",countUniquePhrasesMap);

        }
    }

}
