package com.ti.textdata;

import com.ti.data.CharacterData;

import java.util.TreeMap;

public class TextDataUkrainian extends TextData {
    public static final int ALPHABET_SIZE = 66;

    public TextDataUkrainian() {
        characterDataMap = new TreeMap<>();
        for (int i = 1040; i <= 1065; i++) {
            characterDataMap.put((char) i, new CharacterData((char) i, 0));
            characterDataMap.put((char) (i + 32), new CharacterData((char) (i + 32), 0));
        }
        characterDataMap.put((char) 1068, new CharacterData((char) 1068, 0)); // 'Ь'
        characterDataMap.put((char) 1070, new CharacterData((char) 1070, 0)); // 'Ю'
        characterDataMap.put((char) 1071, new CharacterData((char) 1071, 0)); // 'Я'
        characterDataMap.put((char) 1100, new CharacterData((char) 1100, 0)); // 'ь'
        characterDataMap.put((char) 1102, new CharacterData((char) 1102, 0)); // 'ю'
        characterDataMap.put((char) 1103, new CharacterData((char) 1103, 0)); // 'я'
        characterDataMap.put((char) 1168, new CharacterData((char) 1168, 0)); // 'Ґ'
        characterDataMap.put((char) 1169, new CharacterData((char) 1169, 0)); // 'ґ'
        characterDataMap.put((char) 1108, new CharacterData((char) 1108, 0)); // 'є'
        characterDataMap.put((char) 1028, new CharacterData((char) 1028, 0)); // 'Є'
        characterDataMap.put((char) 1110, new CharacterData((char) 1110, 0)); // 'і'
        characterDataMap.put((char) 1030, new CharacterData((char) 1030, 0)); // 'І'
        characterDataMap.put((char) 1111, new CharacterData((char) 1111, 0)); // 'ї'
        characterDataMap.put((char) 1031, new CharacterData((char) 1031, 0)); // 'Ї'
    }

    @Override
    public int getAlphabetSize() {
        return ALPHABET_SIZE;
    }
}
