package com.ti.coding.lzw;

import java.util.List;
import java.util.Map;

public class LZWDecoder {
    private List<Integer> data;
    private Map<Integer, String> dictionary;
    private int dictionarySize;

    public LZWDecoder(List<Integer> data, Map<Integer, String> dictionary) {
        this.data = data;
        this.dictionary = dictionary;
        this.dictionarySize = dictionary.size() + 1;
    }

    public String decode() {

        String reading = "" + dictionary.get(data.remove(0));
        StringBuilder decodedString = new StringBuilder(reading);

        for (int i : data) {
            String decodingEntry;

            if (dictionary.containsKey(i)) {
                decodingEntry = dictionary.get(i);
            }
            else if (i == dictionarySize) {
                decodingEntry = reading + reading.charAt(0);
            }
            else {
                throw new IllegalArgumentException("Bad data");
            }

            decodedString.append(decodingEntry);
            dictionary.put(dictionarySize++, reading + decodingEntry.charAt(0));

            reading = decodingEntry;
        }
        return decodedString.toString();
    }
}
