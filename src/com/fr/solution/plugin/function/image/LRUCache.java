package com.fr.solution.plugin.function.image;

/**
 * Created by richie on 16/5/4.
 */
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public LRUCache(int maxSize) {
        super(maxSize);
        this.maxElements = maxSize;
    }

    protected boolean removeEldestEntry(Entry eldest) {
        return this.size() > this.maxElements;
    }
}
