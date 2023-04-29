package com.ti.coding.hamming;

import com.ti.util.Utilities;

public class HammingCoder {
    int[] data;

    public HammingCoder(int[] data) {
        this.data = data;
    }

    public int[] code() {
        int[] encoding = setupRedundantBits();
        fillRedundantBits(encoding);
        return encoding;
    }

    private int findNumberOfRedundantBits() {
        int redundantBits = 1;

        while (Math.pow(2, redundantBits) < (data.length + redundantBits)) {
            redundantBits++;
        }

        return redundantBits;
    }

    private int[] setupRedundantBits() {
        int[] encoding = new int[findNumberOfRedundantBits() + data.length];
        int j = 0;
        for (int i = 1; i <= encoding.length; i++) {
            if (Utilities.isPowerOfTwo(i)) {
                encoding[i - 1] = 0;
            }
            else {
                encoding[i - 1] = data[j];
                j++;
            }
        }

        return encoding;
    }

    private void fillRedundantBits(int[] encoding) {
        int r;
        int n = findNumberOfRedundantBits();
        for (int i = 0; i < n; i++) {
            r = 0;
            for (int k = i; k <= encoding.length; k++) {
                if (((k >> i) & 1) == 1) {
                    r ^= encoding[k - 1];
                }
            }
          encoding[(int) Math.pow(2, i) - 1] = r;
        }
    }
}
