package com.ti.coding.shannoonfano;

import com.ti.data.CharacterData;
import com.ti.data.Data;
import com.ti.data.DataComparatorProbability;
import com.ti.textdata.TextData;
import com.ti.tree.BinaryTree;
import com.ti.tree.Node;

import java.util.*;

public class CoderShannonFano {
    private TextData textData;
    private BinaryTree codingTree;

    public CoderShannonFano() {

    }

    public void setTextData(TextData textData) {
        this.textData = textData;
    }

    public BinaryTree code() {
        List<CharacterData> dataList = textData.getCharacterData();
        Comparator<Data<?>> comparatorProbability = new DataComparatorProbability();
        dataList.sort(comparatorProbability.reversed());

        StringBuilder stringBuilder = new StringBuilder();
        for (Data<Character> data : dataList) {
            stringBuilder.append(data.getData());
        }

        codingTree = new BinaryTree(new Node(stringBuilder.toString()));
        coderOnTree(codingTree.getRoot());

        return codingTree;
    }

    private void coderOnTree(Node node) {
        if (node == null || node.getValue().length() == 1)  {
            return;
        }

        divide(node);

        coderOnTree(node.getLeft());
        coderOnTree(node.getRight());
    }

    private void divide(Node node) {
        double probabilitySum;
        double nextSum;
        int length = node.getValue().length();
        String string = node.getValue();

        int l = 0;
        double d;
        double dnext = 1.0;

        do {
            l++;
            d = dnext;
            probabilitySum = calculateProbabilitiesSum(string, 0, l);
            nextSum = calculateProbabilitiesSum(string, l, length);
            dnext = Math.abs(probabilitySum - nextSum);
        } while (dnext < d);
        l--;

        node.setLeft(new Node(string.substring(0, l)));
        node.setRight(new Node(string.substring(l, length)));
    }

    private double calculateProbabilitiesSum(String string, int start, int end) {
        double sum = 0.0;
        for (int i = start; i < end; i++) {
            sum += textData.getCharacterProbability(string.charAt(i));
        }
        return sum;
    }
}
