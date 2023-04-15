package com.ti.labs.one;

import com.ti.CanalMatrix;
import com.ti.TextAnalyzer;
import com.ti.textdata.TextDataEnglish;

import java.io.*;

public class OneMain {
    public static void main(String[] args) throws IOException {
//        for (char i = 0; i < Character.MAX_VALUE; i++) {
//            System.out.printf("|%c|:|%d| ", i, (int) i);
//            Character c;
//        }

        System.out.println("Task 4:");
        task4();
        System.out.println();

        System.out.println("Task 7:");
        task7();
        System.out.println();
    }

    public static void task4() throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream("sherlockholmes.txt")));
        TextDataEnglish textDataEnglish = new TextDataEnglish();
        textDataEnglish.read(reader);
        reader.close();
        textDataEnglish.print();
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(textDataEnglish);
        System.out.println("Entropy: " + analyzer.calculateEntropy());
        System.out.println("Information of \"MaksymSobol\": " + analyzer.calculateInformationOfString("MaksymSobol"));
    }

    public static void task7() throws IOException {
        System.out.println("do task 7");
        TextDataLabTask systemX = new TextDataLabTask();
        TextDataLabTask systemY = new TextDataLabTask();
        System.out.println("System X");
        systemX.print();
        System.out.println("System Y");
        systemY.print();
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(systemX);
        System.out.println("Entropy of system X: " + analyzer.calculateEntropy());
        analyzer.setTextData(systemY);
        System.out.println("Entropy of system Y: " + analyzer.calculateEntropy());

        double[][] matrix = {
                {0.5, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                {0.5, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0},
                {0.0, 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0},
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.5},
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.5}
        };
        CanalMatrix canalMatrix = new CanalMatrix(systemX, systemY, matrix);
        System.out.println("partialConditionalEntropy x2 = " + canalMatrix.partialConditionalEntropy(1));
        System.out.println("ConditionalEntropy = " + canalMatrix.conditionalEntropy());
        System.out.println("mutualEntropy = " + canalMatrix.mutualEntropy());
        System.out.println("jointEntropy = " + canalMatrix.jointEntropy());
        System.out.println("jointEntropy = " + canalMatrix.jointEntropy1());
        System.out.println("jointEntropy = " + canalMatrix.jointEntropy2());
    }
}
