package com.ti.labs.one;

import com.ti.data.CharacterData;
import com.ti.textdata.TextData;

import java.util.TreeMap;

public class TextDataLabTask extends TextData {
    public static final int ALPHABET_SIZE = 8;

    public TextDataLabTask() {
        characterDataMap = new TreeMap<>();
        for (int i = 97; i < 105; i++) {
            characterDataMap.put((char) i, new CharacterData((char) i, 0, 0.125));
        }
    }

    @Override
    public int getAlphabetSize() {
        return ALPHABET_SIZE;
    }
}