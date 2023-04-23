package com.ti.coding.lzw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LZWCoder {
    private String string;
    private Map<String, Integer> dictionary;
    private int dictionarySize;

    public LZWCoder(String string, Map<String, Integer> dictionary) {
        this.string = string;
        this.dictionary = dictionary;
        this.dictionarySize = dictionary.size() + 1;
    }

    public List<Integer> code() {
        List<Integer> data = new ArrayList<>();

        String readingChar = "";
        char[] input = string.toCharArray();
        for (char c : input) {
            String nextChar = readingChar + c;

            if (dictionary.containsKey(nextChar)) {
                readingChar = nextChar;
            } else {
                data.add(dictionary.get(readingChar));
                dictionary.put(nextChar, dictionarySize);
                dictionarySize++;
                readingChar = "" + c;
            }
        }

        if (!readingChar.equals("")) {
            data.add(dictionary.get(readingChar));
        }

        return data;
    }
}
