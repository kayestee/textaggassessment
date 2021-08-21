package com.textagg.controller;

import com.textagg.utility.CountUtility;
import com.textagg.utility.Counting;

import java.util.List;


public class TextAggregator {

    public static void main(String[] args){
        CountUtility utilObj = new CountUtility();
        String[] inputArgs = utilObj.parseArgs(args);

        Counting doCount = new Counting();
        if (!inputArgs[1].isEmpty()){
            List<String> inputList = utilObj.parseInput(inputArgs);
            doCount.countUniqueWords(inputList);
            doCount.countUniqueNumbers(inputList);
            doCount.countUniquePhrases(inputList);
        }


    }

}
