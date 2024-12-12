package ie.ucd.csnl.comp47500.impl;



public class Node<T> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    public int height;

    public Node(T data) {
        this.data = data;
        this.height = 1; // New nodes are initially added at leaf level
    }
}
