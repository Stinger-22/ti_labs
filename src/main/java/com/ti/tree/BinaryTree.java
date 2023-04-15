package com.ti.tree;

public class BinaryTree {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
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
        if (node.getValue().length() == 1)  {
            return 1;
        }
        return size(node.getLeft()) + size(node.getRight());
    }

    public void print() {
        System.out.println(toString(root, new StringBuilder(), true, new StringBuilder()).toString());
    }

    private StringBuilder toString(Node node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.getRight() !=null) {
            toString(node.getRight(), new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.getValue()).append("\n");
        if (node.getLeft() != null) {
            toString(node.getLeft(), new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }
}