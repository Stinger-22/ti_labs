package com.ti.coding.lzw;

import java.util.Map;
import java.util.TreeMap;

public class Alphabet {
    public static Map<Integer, String> getUADictionaryIntegerString() {
        int n = 1;
        Map<Integer, String> map = new TreeMap<>();
        for (int i = 1072; i <= 1097; i++) {
            map.put(n++, String.valueOf((char) i));
        }
        map.put(n++, String.valueOf((char) 1100)); // 'ь'
        map.put(n++, String.valueOf((char) 1102)); // 'ю'
        map.put(n++, String.valueOf((char) 1103)); // 'я'
        map.put(n++, String.valueOf((char) 1169)); // 'ґ'
        map.put(n++, String.valueOf((char) 1108)); // 'є'
        map.put(n++, String.valueOf((char) 1110)); // 'і'
        map.put(n++, String.valueOf((char) 1111)); // 'ї'
        return map;
    }

    public static Map<String, Integer> getUADictionaryStringInteger() {
        int n = 1;
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 1072; i <= 1097; i++) {
            map.put(String.valueOf((char) i), n++);
        }
        map.put(String.valueOf((char) 1100), n++); // 'ь'
        map.put(String.valueOf((char) 1102), n++); // 'ю'
        map.put(String.valueOf((char) 1103), n++); // 'я'
        map.put(String.valueOf((char) 1169), n++); // 'ґ'
        map.put(String.valueOf((char) 1108), n++); // 'є'
        map.put(String.valueOf((char) 1110), n++); // 'і'
        map.put(String.valueOf((char) 1111), n++); // 'ї'
        return map;
    }
}
