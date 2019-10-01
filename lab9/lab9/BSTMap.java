package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        /* 有recursion，所以用helper */
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");

        if (p == null) return null;// 便利到头，leaf 之下
        //else
        int cmp = key.compareTo(p.key); // 未知key K具体类型！所以才实现Comparable, 用compareTo
        if (cmp < 0) return getHelper(key, p.right);
        else if (cmp > 0) return getHelper(key, p.left);
        else return p.value;
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    /* 有recursion，所以用helper */
    @Override
    public V get(K key) {
        // 因为this.root是node类型 —— left right都连在root上
        return getHelper(key, this.root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        /* 有recursion，所以用helper */

        //p is null, it returns a one node BSTMap containing (KEY, VALUE)
        // 方法终结点 1.
        if (p == null) {
            // size的update
            size += 1; /// 0 -> 1
            return new Node(key, value);// 便利到头，leaf 之下
        }

        // return a BSTMap rooted in p
        int cmp = key.compareTo(p.key); // 未知key K具体类型！所以才实现Comparable, 用compareTo
        /* 都是*/
        if (cmp < 0) p.right = putHelper(key, value, p.right);
        else if (cmp > 0) p.left = putHelper(key, value, p.left);
        else p.value = value; // update

        //别忘了无node加入，size 不变!!
        return p; // 方法终结点 2

    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        // this.root是node类型 —— 接受helper的返回值，生成新数！
        this.root = putHelper(key, value, this.root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        //直接用get！
        // 且要给Node类实现remove，保证只remove该key;但之后要涉及旋转 较复杂!
        throw new UnsupportedOperationException();
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();

    }

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
            /* left right = null, by default*/
        }
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
