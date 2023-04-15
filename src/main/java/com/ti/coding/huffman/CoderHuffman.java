package com.ti.coding.huffman;

import com.ti.data.CharacterData;
import com.ti.data.Data;
import com.ti.textdata.TextData;
import com.ti.tree.BinaryTree;
import com.ti.tree.Node;
import com.ti.tree.NodeComparatorProbability;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CoderHuffman {
    private TextData textData;
    private BinaryTree codingTree;

    public CoderHuffman() {

    }

    public void setTextData(TextData textData) {
        this.textData = textData;
    }

    public BinaryTree code() {
        PriorityQueue<Node> nodeSorter = setupNodeSorter();
        this.codingTree = buildTree(nodeSorter);
        return codingTree;
    }

    private PriorityQueue<Node> setupNodeSorter() {
        List<CharacterData> characterDataList = textData.getCharacterData();
        Comparator<Node> comparatorProbability = new NodeComparatorProbability();

        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>(characterDataList.size(), comparatorProbability);
        for (CharacterData data : characterDataList) {
            nodePriorityQueue.add(new Node(data.getData(), data.getProbability()));
        }

        return nodePriorityQueue;
    }

    private BinaryTree buildTree(PriorityQueue<Node> nodeSorter) {
        Node root = null;

        while (nodeSorter.size() > 1) {
            Node firstMinimal = nodeSorter.poll();
            Node secondMinimal = nodeSorter.poll();

            Node mergedNodes = secondMinimal.add(firstMinimal);
            mergedNodes.setLeft(firstMinimal);
            mergedNodes.setRight(secondMinimal);

            root = mergedNodes;
            nodeSorter.add(mergedNodes);
        }

        return new BinaryTree(root);
    }
}
