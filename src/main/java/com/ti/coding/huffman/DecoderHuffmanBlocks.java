package com.ti.coding.huffman;

import com.ti.TextAnalyzer;
import com.ti.data.StringData;
import com.ti.textdata.TextData;
import com.ti.tree.BinaryTree;
import com.ti.tree.Node;

import java.util.List;

public class DecoderHuffmanBlocks {
    private List<StringData> stringDataList;
    private BinaryTree codingTree;
    private double Lmin = 0.0;
    private double Lavg = 0.0;


    public DecoderHuffmanBlocks(BinaryTree codingTree, List<StringData> stringDataList) {
        this.codingTree = codingTree;
        this.stringDataList = stringDataList;
    }

    public void printDecodingTable() {
        print(codingTree.getRoot(), "");
        Lmin = 0.0;
        for (int i = 0; i < stringDataList.size(); i++) {
            double probability = stringDataList.get(i).getProbability();
            Lmin += probability * (Math.log(probability) / Math.log(2));
        }
        Lmin = -Lmin;
        System.out.println("Lmin: " + Lmin);
    }

    private void print(Node node, String code) {
        if (node.getLeft() == null && node.getRight() == null)  {
            System.out.println(node.getValue() + " - " + code);
//            Lavg += textData.getCharacterProbability(node.getValue().charAt(0)) * code.length();
            return;
        }
        print(node.getLeft(), code + "0");
        print(node.getRight(), code + "1");
    }
}