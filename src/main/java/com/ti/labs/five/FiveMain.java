package com.ti.labs.five;

import com.ti.coding.hamming.HammingCoder;
import com.ti.coding.hamming.HammingDecoder;

import java.util.Arrays;

public class FiveMain {
    public static void main(String[] args) {
        int[] data = {0, 1, 0};
        System.out.println("Data: " + Arrays.toString(data));

        HammingCoder coder = new HammingCoder(data);
        int[] encodedData = coder.code();
        System.out.println("Sent data: " + Arrays.toString(encodedData));


        int[] receivedData = {1, 0, 0, 1, 1, 0};
        System.out.println("Received data: " + Arrays.toString(receivedData));

        HammingDecoder decoder = new HammingDecoder(receivedData);
        int[] decodedData = decoder.decode();
        System.out.println("Data: " + Arrays.toString(decodedData));
    }
}
