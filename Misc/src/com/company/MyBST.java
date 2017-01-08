package com.company;

/**
 * Created by bicsi on 23.10.2016.
 */
public class MyBST<K extends Comparable<K>> {
    private class Node {
        K key;
        Node left, right;
        Node(K key) {
            this.key = key;
            left = right = null;
        }
    }

    private int size;
    private Node root;

    private Node _insert(Node root, K key) {
        if(root == null) {
            root = new Node(key);
            ++size;
        }
        switch(root.key.compareTo(key)) {
            case 1: root.left = _insert(root.left, key); break;
            case -1: root.right = _insert(root.right, key); break;
        }
        return root;
    }
    public boolean add(K key) {
        int oldSize = size;
        root = _insert(root, key);
        return oldSize != size;
    }

    public boolean _equals(Node rootA, Node rootB) {
        if(rootA == null && rootB == null)
            return true;
        if(rootA == null || rootB == null)
            return false;

        if(!rootA.key.equals(rootB.key)) return false;
        return _equals(rootA.left, rootB.left) && _equals(rootA.right, rootB.right);
    }

    @Override
    public boolean equals(Object oth) {
        if(oth.getClass() != this.getClass())
            return false;
        return _equals(root, ((MyBST)oth).root);
    }

    public int _hashCode(Node root) {
        if(root == null) return 0;

        return (_hashCode(root.left) * 312317) + root.key.hashCode() * 381762 + (_hashCode(root.right) * 37126113);
    }
    @Override
    public int hashCode() {
        return _hashCode(root);
    }

    private String _traverse(Node node) {
        if(node == null) return "";
        return _traverse(node.left) + " " + node.key + _traverse(node.right);
    }

    @Override
    public String toString() {
        return _traverse(root).substring(1);
    }
}
