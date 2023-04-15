package com.ti.tree;

import java.util.Comparator;

public class NodeComparatorProbability implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return Double.compare(o1.getProbability(), o2.getProbability());
    }
}
