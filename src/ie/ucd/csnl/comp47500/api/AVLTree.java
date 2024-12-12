package ie.ucd.csnl.comp47500.api;

import java.util.List;

public interface AVLTree<T extends Comparable<T>> {

    /**
     * Inserts a new element into the AVL tree.
     *
     * @param data the data to be inserted
     */

    
    void insert(T data);

    /**
     * Prints the AVL tree in 2d order
     *
     */
    void printBinaryTree();

    /**
     * Removes an element from the AVL tree.
     *
     * @param data the data to be removed
     */
    void delete(T data);

    /**
     * Checks if the AVL tree contains a specific element.
     *
     * @param data the data to be checked
     * @return true if the element is present, false otherwise
     */
    boolean contains(T data);

    /**
     * Gets the height of the AVL tree.
     *
     * @return the height of the tree
     */
    int getHeight();

    /**
     * Performs an in-order traversal of the AVL tree and returns the elements in sorted order.
     *
     * @return a List containing the elements in sorted order
     */
    List<T> inOrderTraversal();

    /**
     * Clears all elements from the AVL tree.
     */
    void clear();

    /**
     * Gets the size (number of elements) of the AVL tree.
     *
     * @return the size of the tree
     */
    int size();
    
    boolean isBalanced();
}

