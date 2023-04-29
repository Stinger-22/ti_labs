package com.ti.util;

public class Utilities {
    public static boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0;
    }
}
