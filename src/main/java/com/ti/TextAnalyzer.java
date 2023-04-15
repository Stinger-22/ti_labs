package com.ti;

import com.ti.textdata.TextData;

import java.util.Set;

public class TextAnalyzer {
    private TextData textData;

    public void setTextData(TextData textData) {
        this.textData = textData;
    }

    public double calculateInformationOfString(String string) {
        double info = 0.0;
        for (int i = 0; i < string.length(); i++) {
            info += textData.getCharacterInformation(string.charAt(i));
        }
        return info;
    }

    public double calculateEntropy() {
        double entropy = 0.0;
        double probability;
        Set<Character> mapKeys = textData.getCharacters();
        for (Character character : mapKeys) {
            probability = textData.getCharacterProbability(character);
            if (probability != 0.0) {
                entropy += probability * (Math.log(probability) / Math.log(2));
            }
        }
        return -entropy;
    }
}
