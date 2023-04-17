package com.ti.coding.huffman.adaptive;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoderAdaptiveHuffman {
    private Node root;
    private Node NYT;

    private HashMap<Integer, Node> seenCharacters = new HashMap<>();
    private List<Node> order = new ArrayList<>();

    private PrintWriter writer = new PrintWriter(System.out, false, Charset.forName("windows-1251"));

    public CoderAdaptiveHuffman() {
        this.root = new Node('\0', 0);
        this.NYT = this.root;
        order.add(this.root);
    }

    public void code(String input) {
        char character;
        readFirstCharacter(input.charAt(0));
        print();
        for (int i = 1; i < input.length(); i++) {
            character = input.charAt(i);

            if (!isCharacterSeen(character)) {
                Node addedNode;
                addedNode = addNewCharacter(character);
                write(NYT);
                writer.print(' ');
                increaseOccurrence(addedNode.getParent());
                seenCharacters.put((int) character, addedNode);
                writeNewCharacter(character); // internal code
            }
            else {
                write(findNodeByCharacter(character)); // BITS
                increaseOccurrence(character);
            }
            writer.print(' ');

            refillOrder();
            Integer misplacedNodeIndex;
            while ((misplacedNodeIndex = findMisplacedNodeIndex()) != null) {
                Integer nodeToSwap = findNodeIndexToSwap(misplacedNodeIndex);
                swapNodes(misplacedNodeIndex, nodeToSwap);
                if (doOccurrencesBroke(order.get(misplacedNodeIndex)) || doOccurrencesBroke(order.get(nodeToSwap))) {
                    recalculateOccurrences(root);
                }
                refillOrder();
            }
            print();
        }
        writer.flush();
    }

    private void readFirstCharacter(char character) {
        Node addedNode = addNewCharacter(character);
        increaseOccurrence(addedNode.getParent());
        seenCharacters.put((int) character, addedNode);
        writer.print(Integer.toString(character, 2));
        writer.print(' ');
    }

    private int recalculateOccurrences(Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return node.getOccurrence();
        }
        node.setOccurrence(recalculateOccurrences(node.getLeft()) + recalculateOccurrences(node.getRight()));
        return node.getLeft().getOccurrence() + node.getRight().getOccurrence();
    }

    private boolean doOccurrencesBroke(Node node) {
        Node parent = node.getParent();
        return parent.getOccurrence() != parent.getLeft().getOccurrence() + parent.getRight().getOccurrence();
    }

    private void writeNewCharacter(char character) {
        writer.print(Integer.toString(character, 2));
    }

    private void write(Node node) {
        StringBuilder sb = new StringBuilder();
        Node parent;
        while (node != root) {
            parent = node.getParent();
            if (parent.getLeft() == node) {
                sb.append('0');
            }
            else {
                sb.append('1');
            }
            node = parent;
        }
        writer.print(sb.reverse());
    }



    private Integer findNodeIndexToSwap(Integer misplacedNodeIndex) {
        int misplacedNodeOccurrence = order.get(misplacedNodeIndex).getOccurrence();
        for (int i = 0; i < misplacedNodeIndex; i++) {
            if (order.get(i).getOccurrence() < misplacedNodeOccurrence) {
                return i;
            }
        }
        throw new IllegalStateException("Order is not sorted but and this method didn't find cause.");
    }

    private void swapNodes(int firstNodeIndex, int secondNodeIndex) {
        Node firstNode = order.get(firstNodeIndex);
        Node secondNode = order.get(secondNodeIndex);

        order.set(firstNodeIndex, secondNode);
        order.set(secondNodeIndex, firstNode);

        Node firstNodeParent = firstNode.getParent();
        Node secondNodeParent = secondNode.getParent();

        if (firstNodeParent == secondNodeParent) {
            firstNodeParent.setLeft(secondNode);
            firstNodeParent.setRight(firstNode);
        }
        else {
            if (firstNodeParent.getLeft() == firstNode) {
                firstNodeParent.setLeft(secondNode);
            }
            else {
                firstNodeParent.setRight(secondNode);
            }

            if (secondNodeParent.getLeft() == secondNode) {
                secondNodeParent.setLeft(firstNode);
            }
            else {
                secondNodeParent.setRight(firstNode);
            }
        }

        firstNode.setParent(secondNodeParent);
        secondNode.setParent(firstNodeParent);

    }

    private boolean isCharacterSeen(char character) {
        return seenCharacters.containsKey((int) character);
    }

    private Node addNewCharacter(char character) {
        Node newCharacter = new Node(character, 1, NYT);
        Node newNYT = new Node('\0', 0, NYT);

        NYT.setLeft(newNYT);
        NYT.setRight(newCharacter);

        NYT = newNYT;

        order.add(newCharacter);
        order.add(newNYT);
        return newCharacter;
    }

    private void increaseOccurrence(char character) {
        Node node = seenCharacters.get((int) character);
        while (node != root) {
            node.setOccurrence(node.getOccurrence() + 1);
            node = node.getParent();
        }
        node.setOccurrence(node.getOccurrence() + 1);
    }

    private void increaseOccurrence(Node node) {
        while (node != root) {
            node.setOccurrence(node.getOccurrence() + 1);
            node = node.getParent();
        }
        node.setOccurrence(node.getOccurrence() + 1);
    }

    private Node findNodeByCharacter(char character) {
        return seenCharacters.get((int) character);
    }

    private Integer findMisplacedNodeIndex() {
        int size = order.size() - 1;
        for (int i = 0; i < size; i++) {
            if (order.get(i).getOccurrence() < order.get(i + 1).getOccurrence()) {
                return i + 1;
            }
        }
        return null;
    }

    private void refillOrder() {
        order.clear();
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            fillOrder(root, i);
        }
    }

    private void fillOrder(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            order.add(node);
        }
        else if (level > 1) {
            fillOrder(node.getRight(), level - 1);
            fillOrder(node.getLeft(), level - 1);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)  {
            return 0;
        }
        return size(node.getLeft()) + size(node.getRight()) + 1;
    }

    public void print() {
        System.out.println(toString(root, new StringBuilder(), true, new StringBuilder()));
    }

    private StringBuilder toString(Node node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.getRight() !=null) {
            toString(node.getRight(), new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.getValue()).append(node.getOccurrence()).append("\n");
        if (node.getLeft() != null) {
            toString(node.getLeft(), new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }
}
