package com.ti.coding.shannoonfano;

import com.ti.TextAnalyzer;
import com.ti.textdata.TextData;
import com.ti.tree.BinaryTree;
import com.ti.tree.Node;

public class DecoderShannonFano {
    private TextData textData;
    private BinaryTree codingTree;
    private double Lmin = 0.0;
    private double Lavg = 0.0;


    public DecoderShannonFano(BinaryTree codingTree, TextData textData) {
        this.codingTree = codingTree;
        this.textData = textData;
    }

    public void printDecodingTable() {
        print(codingTree.getRoot(), "");
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.setTextData(textData);
        Lmin = analyzer.calculateEntropy();
        System.out.println("Lmin: " + Lmin);
        System.out.println("Lavg: " + Lavg);
    }

    private void print(Node node, String code) {
        if (node.getValue().length() == 1)  {
            System.out.println(node.getValue() + " - " + code);
            Lavg += textData.getCharacterProbability(node.getValue().charAt(0)) * code.length();
            return;
        }
        print(node.getLeft(), code + "0");
        print(node.getRight(), code + "1");
    }
}
