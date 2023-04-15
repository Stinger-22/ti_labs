package com.ti.data;

import java.util.ArrayList;
import java.util.List;

public class CharacterData implements Data<Character> {
    private final Character data;
    private int occurrence;
    private double probability;
    private double information;

    public CharacterData(char data) {
        this.data = data;
    }

    public CharacterData(char data, int occurrence) {
        this.data = data;
        this.occurrence = occurrence;
    }

    public CharacterData(char data, int occurrence, double probability) {
        this.data = data;
        this.occurrence = occurrence;
        this.probability = probability;
    }

    public CharacterData(CharacterData characterData) {
        this.data = characterData.data;
        this.occurrence = characterData.occurrence;
        this.probability = characterData.probability;
        this.information = characterData.information;
    }

    public Character getData() {
        return data;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("Probability not in range [0, 1]");
        }
        else {
            this.probability = probability;
        }
    }

    public double getInformation() {
        return information;
    }

    public void setInformation(double information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "CharacterData{" +
                "data=" + data +
                ", occurrence=" + occurrence +
                ", probability=" + probability +
                ", information=" + information +
                '}';
    }
}
