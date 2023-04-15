package com.ti.textdata;

import com.ti.data.CharacterData;
import com.ti.data.Data;
import com.ti.data.StringData;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TextDataCustom extends TextData {
    public final int ALPHABET_SIZE;

    public TextDataCustom(List<Map.Entry<Character, Double>> characterProbabilitiesMapping) {
        characterDataMap = new TreeMap<>();
        for (Map.Entry<Character, Double> entry : characterProbabilitiesMapping) {
            characterDataMap.put(entry.getKey(), new CharacterData(entry.getKey(), 0, entry.getValue()));
        }
        ALPHABET_SIZE = characterDataMap.size();
    }

    @Override
    public int getAlphabetSize() {
        return ALPHABET_SIZE;
    }
}
