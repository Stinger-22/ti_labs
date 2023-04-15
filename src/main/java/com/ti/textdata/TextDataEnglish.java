package com.ti.textdata;

import com.ti.data.CharacterData;

import java.util.TreeMap;

public class TextDataEnglish extends TextData {
    public static final int ALPHABET_SIZE = 52;

    public TextDataEnglish() {
        characterDataMap = new TreeMap<>();
        for (int i = 65; i <= 90; i++) {
            characterDataMap.put((char) i, new CharacterData((char) i, 0));
            characterDataMap.put((char) (i ^ 32), new CharacterData((char) (i ^ 32), 0));
        }
    }

    @Override
    public int getAlphabetSize() {
        return ALPHABET_SIZE;
    }
}
