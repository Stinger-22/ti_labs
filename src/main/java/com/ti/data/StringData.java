package com.ti.data;

public class StringData implements Data<String> {
    private final String data;
    private int occurrence;
    private double probability;
    private double information;

    public StringData(String data) {
        this.data = data;
    }

    public StringData(String data, int occurrence) {
        this.data = data;
        this.occurrence = occurrence;
    }

    public StringData(String data, int occurrence, double probability) {
        this.data = data;
        this.occurrence = occurrence;
        this.probability = probability;
    }

    public StringData(StringData stringData) {
        this.data = stringData.data;
        this.occurrence = stringData.occurrence;
        this.probability = stringData.probability;
        this.information = stringData.information;
    }

    public String getData() {
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
        } else {
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
        return "StringData{" +
                "data='" + data + '\'' +
                ", occurrence=" + occurrence +
                ", probability=" + probability +
                ", information=" + information +
                '}';
    }
}
