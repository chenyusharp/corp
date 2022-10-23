package com.xiazhenyu.cucumber.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Date: 2022/10/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FixedLinkedHashMap<K, V> extends LinkedHashMap<K, V> {


    private static final long serialVersionUID = 6725303442217277310L;

    private int capacity;

    public FixedLinkedHashMap(int capacity) {
        super(capacity + 1, 1.0f, true);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > this.capacity;
    }
}