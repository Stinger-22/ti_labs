package com.ti.data;

import java.util.Comparator;

public class DataComparatorProbability implements Comparator<Data<?>> {
    @Override
    public int compare(Data o1, Data o2) {
        return Double.compare(o1.getProbability(), o2.getProbability());
    }
}
