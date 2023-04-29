package com.ti.labs.four;

import com.ti.coding.lzw.Alphabet;
import com.ti.coding.lzw.LZWCoder;
import com.ti.coding.lzw.LZWDecoder;

import java.util.List;
import java.util.Map;

public class FourMain {
    public static void main(String[] args) {
        Map<Integer, String> integerStringMap = Alphabet.getUADictionaryIntegerString();
        Map<String, Integer> stringIntegerMap = Alphabet.getUADictionaryStringInteger();

        String string = "аааббабабаб";
        LZWCoder coder = new LZWCoder(string, stringIntegerMap);
        List<Integer> data = coder.code();

        System.out.println("String: \"" + string + "\"");
        System.out.println("Encoded string: " + data);

        LZWDecoder decoder = new LZWDecoder(data, integerStringMap);
        String decodedData = decoder.decode();
        System.out.println("Decoded string: " + decodedData);
    }
}
