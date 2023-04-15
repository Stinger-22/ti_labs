package com.ti.coding.huffman.adaptive;

public class Node {
    private char value;
    private int occurrence;

    private Node parent;
    private Node left;
    private Node right;

    public Node(char value, int occurrence, Node parent) {
        this.value = value;
        this.occurrence = occurrence;
        this.parent = parent;
    }

    public Node(char value, int occurrence) {
        this.value = value;
        this.occurrence = occurrence;
    }

    public Node(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", occurrence=" + occurrence +
//                ", parent=" + parent +
//                ", left=" + left +
//                ", right=" + right +
                '}';
    }
}
