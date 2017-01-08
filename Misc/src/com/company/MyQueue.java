package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Queue;

/**
 * Created by bicsi on 23.10.2016.
 */
public class MyQueue<T> implements Queue<T> {

    private class Node<T> {
        T info;
        Node next;

        public Node(T arg) {
            info = arg;
            next = null;
        }
    }
    private class Iterator<T> implements java.util.Iterator<T> {
        public Node<T> at;

        public Iterator(Node at) {
            this.at = at;
        }

        @Override
        public boolean hasNext() {
            return at != null;
        }

        @Override
        public T next() {
            T ret = at.info;
            at = at.next;
            return ret;
        }
    }

    private Node<T> head, tail;
    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new NotImplementedException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(head);
    }

    @Override
    public Object[] toArray() {
        Object[] ret = new Object[size];
        int i = 0;
        for (T elem : this) {
            ret[i++] = elem;
        }
        return ret;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new NotImplementedException();
    }

    @Override
    public boolean add(T t) {
        if(size == 0) {
            head = tail = new Node<>(t);
            size = 1;
        } else {
            tail.next = new Node<>(t);
            tail = tail.next;
            size += 1;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean offer(T t) {
        throw new NotImplementedException();
    }

    @Override
    public T remove() {
        throw new NotImplementedException();
    }

    @Override
    public T poll() {
        T ret = peek();
        head = head.next;
        size -= 1;
        if(size == 0)
            head = tail = null;
        return ret;
    }

    @Override
    public T element() {
        throw new NotImplementedException();
    }

    @Override
    public T peek() {
        return head.info;
    }
}
