package com.espimx.algo.map;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 使用链地址法实现的hashMap
 * 链地址法将具有同样的散列索引的条目都放在一个位置，而不是寻找一个新的位置。
 * 链地址法的每个位置使用一个桶（本例中使用了LinkedList）来放置多个条目。
 */
public class IHashMap<K, V> implements IMap<K, V> {
    /**
     * 哈希表的默认大小，必须是2的幂次方
     */
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    /**
     * 哈希表的最大容量，1<<30 == 2^30
     */
    private static int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 映射表的容量
     */
    private int capacity;

    /**
     * 默认的装填因子阈值
     * 装填因子（load factor)衡量一个散列表有多满。
     * 如果装填因子溢出，则增加散列表的大小，并重新装载条目到一个新的更大的散列表中，这成为在散列
     */
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75F;

    /**
     * 自定义的装填因子阈值
     */
    private float loadFactorThreshold;

    /**
     * 映射表的条目数
     */
    private int size = 0;

    /**
     * 使用LinkedList来存放同一个位置的条目
     */
    private LinkedList<Entry<K, V>>[] table;

    public IHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public IHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public IHashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPowerOf2(initialCapacity);
        }

        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }


    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                set.addAll(bucket);
            }
        }
        return set;
    }

    @Override
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    set.add(entry.getKey());
                }
            }
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        //如果key已经存在映射表中，则更新该key所映射的value
        if (get(key) != null) {
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.value = value;
                    return oldValue;
                }
            }
        }

        //如果key不存在映射表中，则添加一个新的映射条目到映射表中
        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("映射表已达最大容量，无法添加新的映射");
            }
            rehash();
        }

        int bucketIndex = hash(key.hashCode());
        System.out.println("index: " + bucketIndex);
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<>();
        }
        table[bucketIndex].add(new Entry<>(key, value));

        size++;
        return value;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {
        Set<V> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    set.add(entry.getValue());
                }
            }
        }
        return set;
    }

    /**
     * 哈希函数
     */
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * 如果指定的容量不为2的幂次方，则进行改变
     */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity = capacity << 1;
        }
        return capacity;
    }

    /**
     * 移除所有的映射条目
     */
    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    /**
     * 哈希表达到装填因子阈值后进行扩容并重新hash
     */
    private void rehash() {
        Set<Entry<K, V>> set = entrySet();
        capacity = capacity << 1;
        table = new LinkedList[capacity];
        size = 0;

        for (Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        LinkedList<Entry<K, V>> bucket;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                bucket = table[i];
                for (Entry<K, V> entry : bucket) {
                    stringBuilder.append(entry);
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
