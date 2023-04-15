package com.ti;

import com.ti.textdata.TextData;

public class CanalMatrix {
    private TextData source;
    private TextData receiver;
    private double[][] canalMatrix;
    private double[][] mutualMatrix;

    public CanalMatrix(TextData source, TextData receiver, double[][] canalMatrix) {
        this.source = source;
        this.receiver = receiver;
        this.canalMatrix = canalMatrix;
        buildMutualMatrix();
    }

    private void buildMutualMatrix() {
        this.mutualMatrix = new double[source.getAlphabetSize()][receiver.getAlphabetSize()];
        for (int i = 0; i < mutualMatrix.length; i++) {
            for (int j = 0; j < mutualMatrix[i].length; j++) {
                mutualMatrix[i][j] = source.getCharacterProbability( (char) (97 + i) ) * canalMatrix[i][j];
                System.out.println(mutualMatrix[i][j]);
            }
        }
    }

    public double partialConditionalEntropy(int signalRow) {
        double entropy = 0.0;
        double probability;
        for (int i = 0; i < canalMatrix[signalRow].length; i++) {
            probability = canalMatrix[signalRow][i];
            if (probability != 0.0) {
                entropy += probability * (Math.log(probability) / Math.log(2));
            }
        }
        return -entropy;
    }

    public double conditionalEntropy() {
        double entropy = 0.0;
        for (int i = 0; i < mutualMatrix.length; i++) {
            for (int j = 0; j < mutualMatrix[i].length; j++) {
                if (canalMatrix[i][j] != 0.0) {
                    entropy += mutualMatrix[i][j] * (Math.log(canalMatrix[i][j]) / Math.log(2));
                }
            }
        }
        return -entropy;
    }

    public double mutualEntropy() {
        double entropy = 0.0;
        double probability;
        for (int i = 0; i < mutualMatrix.length; i++) {
            for (int j = 0; j < mutualMatrix[i].length; j++) {
                probability = mutualMatrix[i][j];
                if (probability != 0.0) {
                    entropy += probability * (Math.log(probability) / Math.log(2));
                }
            }
        }
        return -entropy;
    }

    public double jointEntropy() {
        double entropy = 0.0;
        double probability;
        for (int i = 0; i < mutualMatrix.length; i++) {
            for (int j = 0; j < mutualMatrix[i].length; j++) {
                probability = mutualMatrix[i][j];
                if (probability != 0.0) {
                    entropy += probability * (Math.log( probability / (source.getCharacterProbability( (char) (97 + i) ) * receiver.getCharacterProbability( (char) (97 + i) )) ) / Math.log(2));
                }
            }
        }
        return entropy;
    }

    public double jointEntropy1() {
        double entropy = 0.0;
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(source);
        entropy += analyzer.calculateEntropy();
        analyzer.setTextData(receiver);
        entropy += analyzer.calculateEntropy();
        entropy -= mutualEntropy();
        return entropy;
    }

    public double jointEntropy2() {
        double entropy = 0.0;
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(receiver);
        entropy += analyzer.calculateEntropy();
        entropy -= conditionalEntropy();
        return entropy;
    }
}
