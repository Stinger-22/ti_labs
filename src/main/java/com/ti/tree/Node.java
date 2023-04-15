package com.ti.tree;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Node {
    private String value;
    private double probability;
    private Node left;
    private Node right;

    public Node(String value, double probability) {
        this.value = value;
        this.probability = probability;
    }

    public Node(String value) {
        this.value = value;
    }

    public Node(char value) {
        this.value = String.valueOf(value);
    }

    public Node(char value, double probability) {
        this.value = String.valueOf(value);
        this.probability = probability;
    }

    public Node() {
        this.value = "";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
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

    public Node add(Node other) {
        return new Node(
                this.value + other.value,
                this.probability + other.probability
        );
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", probability=" + probability +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
