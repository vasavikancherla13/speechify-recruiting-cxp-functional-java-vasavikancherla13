package com.speechify;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<>(maxSize, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.maxSize;
            }
        };
    }

    public V get(K key) {
        return cache.getOrDefault(key, null);
    }

    public void set(K key, V value) {
        cache.put(key, value);
    }
}

