package com.ti.textdata;

import com.ti.data.CharacterData;

public class UkrainianWithPunctuation extends TextDataUkrainian {
    public UkrainianWithPunctuation() {
        super();
        characterDataMap.put('.', new CharacterData('.', 0));
        characterDataMap.put('!', new CharacterData('!', 0));
        characterDataMap.put('?', new CharacterData('?', 0));
        characterDataMap.put(',', new CharacterData(',', 0));
        characterDataMap.put(':', new CharacterData(':', 0));
        characterDataMap.put('-', new CharacterData('-', 0));
        characterDataMap.put('—', new CharacterData('—', 0));
        characterDataMap.put(';', new CharacterData(';', 0));
        characterDataMap.put('«', new CharacterData('«', 0));
        characterDataMap.put('»', new CharacterData('»', 0));
        characterDataMap.put('\'', new CharacterData('\'', 0));
        characterDataMap.put('’', new CharacterData('’', 0));
        characterDataMap.put('…', new CharacterData('…', 0));
        characterDataMap.put('\"', new CharacterData('\"', 0));
    }
}
