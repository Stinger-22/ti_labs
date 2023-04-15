package com.ti.textdata;

import com.ti.data.CharacterData;
import com.ti.data.Data;
import com.ti.data.StringData;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class TextData {

    protected Map<Character, CharacterData> characterDataMap;
    private long numberOfCharacters = 0;

    public abstract int getAlphabetSize();

    public void read(Reader reader) throws IOException {
        int characterCode = 0;
        char character;
        while (characterCode != -1) {
            characterCode = reader.read();
            character = (char) characterCode;
            if (characterDataMap.containsKey(character)) {
                int occurrence = characterDataMap.get(character).getOccurrence();
                characterDataMap.get(character).setOccurrence(occurrence + 1);
            }
        }
        calculateNumberOfCharacters();
        calculateProbability();
        calculateCharacterInformation();
    }

    private void calculateNumberOfCharacters() {
        Set<Character> mapKeys = characterDataMap.keySet();
        for (Character character : mapKeys) {
            numberOfCharacters += characterDataMap.get(character).getOccurrence();
        }
    }

    private void calculateProbability() {
        Set<Character> mapKeys = characterDataMap.keySet();
        CharacterData characterData;
        for (Character character : mapKeys) {
            characterData = characterDataMap.get(character);
            characterData.setProbability((double) characterDataMap.get(character).getOccurrence() / numberOfCharacters);
        }
    }

    private void calculateCharacterInformation() {
        Set<Character> mapKeys = characterDataMap.keySet();
        CharacterData characterData;
        for (Character character : mapKeys) {
            characterData = characterDataMap.get(character);
            if (characterData.getProbability() == 0.0) {
                characterData.setInformation(0.0);
            }
            else {
                characterData.setInformation( -(Math.log(characterData.getProbability()) / Math.log(2)) );
            }
        }
    }

    public long getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public Set<Character> getCharacters() {
        return characterDataMap.keySet();
    }

    public int getCharacterOccurrence(char character) {
        return characterDataMap.get(character).getOccurrence();
    }

    public double getCharacterProbability(char character) {
        return characterDataMap.get(character).getProbability();
    }

    public double getCharacterInformation(char character) {
        return characterDataMap.get(character).getInformation();
    }

    public List<CharacterData> getCharacterData() {
        List<CharacterData> characterDataList = new ArrayList<>(getAlphabetSize());
        for (CharacterData characterData : characterDataMap.values()) {
            characterDataList.add(new CharacterData((characterData)));
        }
        return characterDataList;
    }

    public void print() {
        System.out.println("Total number of characters: " + numberOfCharacters);
        Set<Character> mapKeys = characterDataMap.keySet();
        System.out.printf("%1c | %11s | %11s | %11s\n", 'S', "Occurrence", "Probability", "Info");
        for (Character character : mapKeys) {
            System.out.printf("%1c | %11d | %11f | %11f\n", character, getCharacterOccurrence(character), getCharacterProbability(character), getCharacterInformation(character));
        }
    }

    public List<StringData> buildBlocks(int sizeOfBlock) {
        List<CharacterData> characterDataList = getCharacterData();
        int size = (int) Math.pow(characterDataList.size(), sizeOfBlock);
        List<StringData> blocks = new ArrayList<>(size);
        for (CharacterData data : characterDataList) {
            blocks.add(new StringData(String.valueOf(data.getData()), data.getOccurrence(), data.getProbability()));
        }

        for (int addChar = 1; addChar < sizeOfBlock; addChar++) {
            List<StringData> newBlocks = new ArrayList<>(size);
            for (StringData block : blocks) {
                for (Data<Character> characterData : characterDataList) {
                    newBlocks.add(new StringData(
                            block.getData() + characterData.getData(),
                            0,
                            block.getProbability() * characterData.getProbability()
                    ));
                }
            }

            blocks = newBlocks;
        }

        return blocks;
    }
}
