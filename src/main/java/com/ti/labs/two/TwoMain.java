package com.ti.labs.two;

import com.ti.TextAnalyzer;
import com.ti.coding.shannoonfano.DecoderShannonFano;
import com.ti.textdata.*;
import com.ti.coding.shannoonfano.CoderShannonFano;
import com.ti.tree.BinaryTree;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TwoMain {
    public static void main(String[] args) throws IOException {

        test();
//        System.out.println("Task 2:");
//        task2();
//        System.out.println("Task 5:");
//        task5();
    }

    public static void task2() {
        List<Map.Entry<Character, Double>> characterProbabilitiesMapping = new ArrayList<>(8);
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('а', 0.39));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('б', 0.2));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('в', 0.1));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('г', 0.05));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('д', 0.09));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('е', 0.08));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('є', 0.08));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('ж', 0.01));

        TextData customData = new TextDataCustom(characterProbabilitiesMapping);
        customData.print();

        CoderShannonFano coder = new CoderShannonFano();
        coder.setTextData(customData);
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(customData);
        System.out.println("H(X) = " + analyzer.calculateEntropy());

        BinaryTree binaryTree = coder.code();
        binaryTree.print();
        System.out.println("Size: " + binaryTree.size());
        DecoderShannonFano decoder = new DecoderShannonFano(binaryTree, customData);
        System.out.println("Shannon-Fano code:");
        decoder.printDecodingTable();
    }

    public static void task5() throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream("maska.txt")));
        TextData bookMaska = new UkrainianWithPunctuation();
        bookMaska.read(reader);
        reader.close();
        bookMaska.print();

        CoderShannonFano coder = new CoderShannonFano();
        coder.setTextData(bookMaska);

        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(bookMaska);
        System.out.println("Entropy: " + analyzer.calculateEntropy());
        BinaryTree binaryTree = coder.code();
        System.out.println("Size: " + binaryTree.size());
        DecoderShannonFano decoder = new DecoderShannonFano(binaryTree, bookMaska);
        decoder.printDecodingTable();
    }

    public static void test() throws IOException {
        List<Map.Entry<Character, Double>> characterProbabilitiesMapping = new ArrayList<>(8);
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('м', 0.2));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('а', 0.3));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('т', 0.2));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('е', 0.1));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('и', 0.1));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('к', 0.1));

        TextData customData = new TextDataCustom(characterProbabilitiesMapping);
        customData.print();

        CoderShannonFano coder = new CoderShannonFano();
        coder.setTextData(customData);
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(customData);
        System.out.println("H(X) = " + analyzer.calculateEntropy());

        BinaryTree binaryTree = coder.code();
        binaryTree.print();
        System.out.println("Size: " + binaryTree.size());
        DecoderShannonFano decoder = new DecoderShannonFano(binaryTree, customData);
        System.out.println("Shannon-Fano code:");
        decoder.printDecodingTable();
    }
}
