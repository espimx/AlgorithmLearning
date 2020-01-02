package com.espimx.algo.test;

import com.espimx.algo.map.IHashMap;
import com.espimx.algo.map.IMap;

public class HashMapTest {
    public static void main(String[] args) {
        IMap<String, Integer> map = new IHashMap<>();
        map.put("a", 10);
        map.put("b", 10);
        map.put("c", 10);
        map.put("d", 10);
        map.put("e", 10);
        map.put("f", 10);
        map.put("g", 10);
        System.out.println(map.toString());
        System.out.println("f: " + map.get("f"));
        map.remove("g");
        System.out.println("contains c: " + map.containsKey("c"));
        //map.put("c", 20);
        System.out.println("contains 20: " + map.containsValue(20));
        System.out.println(map.toString());
    }
}
