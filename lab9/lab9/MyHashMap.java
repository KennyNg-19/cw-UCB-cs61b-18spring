package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    public MyHashMap(int initialSize) {
        buckets = new ArrayMap[initialSize];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) return null;

        int index = hash(key);
        return buckets[index].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) return;

        int index = hash(key);
        ArrayMap<K, V> bucket = buckets[index];
        //找到bucket
        // if(buckets[index].containsKey(key)){ 不需要确认在否，怎样都必然会加入，
        // 且ArrayMap的put方法已经有这个功能了+ 自带resize(单个bucket)
        // 但是update时，size不变！
        if (!bucket.containsKey(key)) size += 1;

        bucket.put(key, value);
        // add new + resize if necessary
        if (loadFactor() > MAX_LF) {
            // resize(DEFAULT_SIZE*2);//错了 不能写DEFAULT_SIZE*2,
            /* 一个是常量式地翻倍，buckets.length是变量！当test case的数量很大时——指数级的增加！*/
            resize(buckets.length * 2);

        }
    }

    private void resize(int capacity) {
        MyHashMap<K, V> newBuckets = new MyHashMap<>(capacity);

        for (int i = 0; i < buckets.length; i += 1) {
            for (K key : buckets[i]) {
                /* 重新计算index？
                MyHashMap的put,封装好了，自带计算hash！！*/
                newBuckets.put(key, buckets[i].get(key));
            }
        }
        this.buckets = newBuckets.buckets;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            keyset.addAll(buckets[i].keySet()); //ArrayMap的keyset，实现和这个keySet()一模一样！
        }
        return keyset;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("calls remove() with a null key");

        V value = get(key);
        // key不存在！
        if (value == null) return null;

        //key存在！
        //用ArrayMap的封装好的remove方法
        ArrayMap<K, V> bucket = buckets[hash(key)];
        size--;
        return bucket.remove(key);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls remove() with a null key");
        if (value == null) return null;

        V actualV = get(key);
        // 确认key 的V 和value一致
        if (actualV == value) {
            ArrayMap<K, V> bucket = buckets[hash(key)];
            size--;
            return bucket.remove(key, value); //用ArrayMap的封装好的remove方法
        }
        //else
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        //偷懒，直接用set自带的
        return keySet().iterator();
    }
}
