package com.espimx.algo.map;

import java.util.Set;

public interface IMap<K, V> {
    /**
     * 删除映射表中的所有条目
     */
    void clear();

    /**
     * 判断映射表中是否包含指定键的条目
     */
    boolean containsKey(K key);

    /**
     * 判断映射表中是否将一个或多个键映射到指定的值
     */
    boolean containsValue(V value);

    /**
     * 返回一个包含该映射表中所有条目的集合
     */
    Set<Entry<K, V>> entrySet();

    /**
     * 返回指定键对应的值
     */
    V get(K key);

    /**
     * 判断映射表是否为空
     */
    boolean isEmpty();

    /**
     * 返回该映射表中所有键的集合
     */
    Set<K> keySet();

    /**
     * 将一个映射添加到映射表中
     */
    V put(K key, V value);

    /**
     * 移除指定键的映射表
     */
    void remove(K key);

    /**
     * 返回映射表中的映射数目
     */
    int size();

    /**
     * 返回一个包含映射表中值的集合
     */
    Set<V> values();

    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }
}
