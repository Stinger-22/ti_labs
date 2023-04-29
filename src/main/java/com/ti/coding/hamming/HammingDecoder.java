package com.ti.coding.hamming;

import com.ti.util.Utilities;

public class HammingDecoder {
    int[] data;

    public HammingDecoder(int[] data) {
        this.data = data;
    }

    public int[] decode() {
        int[] symptom = getSymptom(data);
        if (isSymptomGood(symptom)) {
            return removeRedundantBits();
        }
        else {
            fixData(symptom);
            int errorBit = findErrorBit();
            System.out.println(errorBit);
            if (errorBit == 0) {
                return removeRedundantBits();
            }
            else {
                throw new IllegalArgumentException("2 or more errors");
            }
        }
    }

    private int findNumberOfRedundantBits() {
        int redunantBits = 1;

        while (Math.pow(2, redunantBits) < (data.length)) {
            redunantBits++;
        }

        return redunantBits;
    }

    private int[] getSymptom(int[] decoding) {
        int n = findNumberOfRedundantBits();
        int[] symptom = new int[n];

        for (int i = 0; i < n; i++) {
            for (int k = i; k <= decoding.length; k++) {
                if (((k >> i) & 1) == 1) {
                    symptom[i] ^= decoding[k - 1];
                }
            }
        }
        return symptom;
    }

    private boolean isSymptomGood(int[] symptom) {
        for (int i = 0; i < symptom.length; i++) {
            if (symptom[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private int[] removeRedundantBits() {
        int[] decoding = new int[data.length - findNumberOfRedundantBits()];
        int j = 0;
        for (int i = 1; i <= data.length; i++) {
            if (!Utilities.isPowerOfTwo(i)) {
                decoding[j] = data[i - 1];
                j++;
            }
        }
        return decoding;
    }

    private void fixData(int[] symptom) {
        int n = getWrongBitNumber(symptom);
        if (Utilities.isPowerOfTwo(n)) {
            throw new IllegalArgumentException("Error in redundant bit");
        }
        data[n - 1] ^= 1;
    }

    private int getWrongBitNumber(int[] symptom) {
        int n = symptom[symptom.length - 1];
        for (int i = symptom.length - 2; i >= 0; i--) {
            n = (n << 1) | symptom[i];
        }
        return n;
    }

    private int findErrorBit() {
        int errorBit = 0;
        for (int i = 0; i < data.length; i++) {
            if (!Utilities.isPowerOfTwo(i + 1)) {
                errorBit ^= data[i];
            }
        }
        return errorBit;
    }
}
