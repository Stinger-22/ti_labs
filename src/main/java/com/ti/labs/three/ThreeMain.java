package com.ti.labs.three;

import com.ti.TextAnalyzer;
import com.ti.coding.huffman.CoderHuffman;
import com.ti.coding.huffman.CoderHuffmanBlocks;
import com.ti.coding.huffman.DecoderHuffmanBlocks;
import com.ti.coding.huffman.adaptive.CoderAdaptiveHuffman;
import com.ti.coding.shannoonfano.DecoderShannonFano;
import com.ti.data.StringData;
import com.ti.textdata.TextData;
import com.ti.textdata.TextDataCustom;
import com.ti.tree.BinaryTree;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThreeMain {
    public static void main(String[] args) throws IOException {
//        System.out.println("Task 3:");
//        task3();
//        System.out.println("Task 4:");
//        task4();
        System.out.println((int) 'с');
        System.out.println("Task 6:");
        task6();
    }
    public static void task3() {
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

        CoderHuffman coder = new CoderHuffman();
        coder.setTextData(customData);
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(customData);
        System.out.println("H(X) = " + analyzer.calculateEntropy());

        BinaryTree binaryTree = coder.code();
        binaryTree.print();
        System.out.println("Size: " + binaryTree.size());
        DecoderShannonFano decoder = new DecoderShannonFano(binaryTree, customData);
        System.out.println("Huffman code:");
        decoder.printDecodingTable();
    }

    public static void task4() {
        List<Map.Entry<Character, Double>> characterProbabilitiesMapping = new ArrayList<>(3);

        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('a', 0.2));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('b', 0.56));
        characterProbabilitiesMapping.add(new AbstractMap.SimpleEntry<>('c', 0.24));

        TextData customData = new TextDataCustom(characterProbabilitiesMapping);
        CoderHuffmanBlocks huffmanBlocks = new CoderHuffmanBlocks();
        List<StringData> stringDataList = customData.buildBlocks(5);
        huffmanBlocks.setStringData(stringDataList);

        BinaryTree binaryTree = huffmanBlocks.code();
        DecoderHuffmanBlocks decoder = new DecoderHuffmanBlocks(binaryTree, stringDataList);
        System.out.println("Blocks Huffman code:");
        decoder.printDecodingTable();
    }

    public static void task6() {
        String string = "катерина білик";
        CoderAdaptiveHuffman coder = new CoderAdaptiveHuffman();
        coder.code(string);
    }
}
