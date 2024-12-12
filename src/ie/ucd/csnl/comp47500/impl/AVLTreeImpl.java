package ie.ucd.csnl.comp47500.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ie.ucd.csnl.comp47500.api.AVLTree;

public class AVLTreeImpl<T extends Comparable<T>> implements AVLTree<T> {
	

	private Node<T> root;
	private int size = 0;


	 public Node<T> getRoot() {
		return root;
	}

	public void printBinaryTree()
    {
        LinkedList<Node<T>> treeLevel = new LinkedList<Node<T>>();
        treeLevel.add(root);
        LinkedList<Node<T>> temp = new LinkedList<Node<T>>();
        int counter = 0;
		int height = (root != null) ? root.height - 1 : 0;
		// System.out.println(height);
        double numberOfElements
            = (Math.pow(2, (height + 1)) - 1);
        // System.out.println(numberOfElements);
        while (counter <= height) {
            Node<T> removed = treeLevel.removeFirst();
            if (temp.isEmpty()) {
                printSpace(numberOfElements
                               / Math.pow(2, counter + 1),
                           removed);
            }
            else {
                printSpace(numberOfElements
                               / Math.pow(2, counter),
                           removed);
            }
            if (removed == null) {
                temp.add(null);
                temp.add(null);
            }
            else {
                temp.add(removed.left);
                temp.add(removed.right);
            }
 
            if (treeLevel.isEmpty()) {
                System.out.println("");
                System.out.println("");
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }
        }
    }
 
    public  void printSpace(double n, Node<T> removed)
    {
        for (; n > 0; n--) {
            System.out.print("\t");
        }
        if (removed == null) {
            System.out.print(" ");
        }
        else {
            System.out.print(removed.data);
        }
    }
 
    public int heightOfTree(Node<T> root)
    {
        if (root == null) {
            return 0;
        }
        return 1
            + Math.max(heightOfTree(root.left),
                       heightOfTree(root.right));
    }
	@Override
	public void insert(T data) {
	    root = insert(data, root);
		this.size++;
	}

	private Node<T> insert(T data, Node<T> node) {
	    if (node == null) {
	        root=new Node<T>(data);
	        return root;
	    }

	    int compare = data.compareTo(node.data);
	    if (compare < 0) {
	        node.left = insert(data, node.left);
	    } else if (compare > 0) {
	        node.right = insert(data, node.right);
	    } else {
	        // Duplicate data
	        return node;
	    }

	    updateHeight(node);
	    return rebalance(node);
	}
	
	private void updateHeight(Node<T> node) {
		  if (node == null) return; // Empty node has height -1
		  int leftHeight = (node.left == null) ? -1 : node.left.height;
		  int rightHeight = (node.right == null) ? -1 : node.right.height;
		  node.height = 1 + Math.max(leftHeight, rightHeight);
		}
	
	private Node<T> rebalance(Node<T> node) {
		  int balance = getBalance(node);

		  // Left Left Case (Single Right Rotation)
		  if (balance > 1 && getBalance(node.left) >= 0) {
		    return rightRotate(node);
		  }

		  // Right Right Case (Single Left Rotation)
		  if (balance < -1 && getBalance(node.right) <= 0) {
		    return leftRotate(node);
		  }

		  // Left Right Case (Double Rotation)
		  if (balance > 1 && getBalance(node.left) < 0) {
		    node.left = leftRotate(node.left);
		    return rightRotate(node);
		  }

		  // Right Left Case (Double Rotation)
		  if (balance < -1 && getBalance(node.right) > 0) {
		    node.right = rightRotate(node.right);
		    return leftRotate(node);
		  }

		  return node; // Node is already balanced
		}
	
	private int getBalance(Node<T> node) {
		  if (node == null) return 0; // Empty node has balance factor 0
		  int leftHeight = (node.left == null) ? -1 : node.left.height;
		  int rightHeight = (node.right == null) ? -1 : node.right.height;
		  return leftHeight - rightHeight;
		}
	
	private Node<T> leftRotate(Node<T> node) {
		  Node<T> rightChild = node.right;
		  Node<T> rightLeftChild = rightChild.left;

		  // Perform rotation
		  rightChild.left = node;
		  node.right = rightLeftChild;

		  // Update heights
		  updateHeight(node);
		  updateHeight(rightChild);

		  return rightChild;
		}

	private Node<T> rightRotate(Node<T> node) {
		  Node<T> leftChild = node.left;
		  Node<T> leftRightChild = leftChild.right;

		  // Perform rotation
		  leftChild.right = node;
		  node.left = leftRightChild;

		  // Update heights
		  updateHeight(node);
		  updateHeight(leftChild);

		  return leftChild;
		}


	@Override
	public void delete(T data) {
	    root = delete(data, root);
		this.size--;
	}
	

	private Node<T> delete(T data, Node<T> node) {
	    // Base case: If the tree is empty or the node is not found
	    if (node == null) {
	        return node;
	    }

	    // Compare the data with the current node's data
	    int compare = data.compareTo(node.data);

	    // Recur down the tree
	    if (compare < 0) {
	        node.left = delete(data, node.left);
	    } else if (compare > 0) {
	        node.right = delete(data, node.right);
	    } else {
	        // Node with data to be deleted found

	        // Case 1: Node with only one child or no child
	        if (node.left == null || node.right == null) {
	            Node<T> temp = (node.left != null) ? node.left : node.right;

	            // No child case
	            if (temp == null) {
	                temp = node;
	                node = null;
	            } else {
	                // One child case
	                node = temp;
	            }
	        } else {
	            // Case 2: Node with two children
	            Node<T> temp = findMin(node.right); // Find the inorder successor
	            node.data = temp.data; // Copy the inorder successor's data to this node
	            node.right = delete(temp.data, node.right); // Delete the inorder successor
	        }
	    }

	    // If the tree had only one node, then return
	    if (node == null) {
	        return node;
	    }

	    // Update height of the current node
	    updateHeight(node);

	    // Rebalance the tree
	    return rebalance(node);
	}

	private Node<T> findMin(Node<T> node) {
	    Node<T> current = node;

	    // Find the leftmost leaf
	    while (current.left != null) {
	        current = current.left;
	    }

	    return current;
	}


	@Override
	public boolean contains(T data) {
		return contains(data, root);
	}

	private boolean contains(T data, Node<T> node) {
		if (node == null) {
			return false;
		}
		int compare = data.compareTo(node.data);
		if (compare < 0) {
			return contains(data, node.left);
		} else if (compare > 0) {
			return contains(data, node.right);
		} else {
			return true;
		}
	}

	// test purpose
	@Override
	public int getHeight() {
		//return heightOfTree(root) -1; test purpose
		return (root == null) ? -1 : this.root.height;
	}

	@Override
	public List<T> inOrderTraversal() {
	    List<T> result = new ArrayList<>();
	    inOrderTraversal(root, result);
	    return result;
	}

	private void inOrderTraversal(Node<T> node, List<T> result) {
	    if (node != null) {
	        inOrderTraversal(node.left, result);
	        result.add(node.data);
	        inOrderTraversal(node.right, result);
	    }
	}


	@Override
    public void clear() {
        root = null;
		size = 0;
    }

	 @Override
	    public int size() {
		 	return this.size;
	        //return countNodes(root); test purpose
	    }

//	    private int countNodes(Node<T> node) {
//	        if (node == null) {
//	            return 0;
//	        }
//	        return 1 + countNodes(node.left) + countNodes(node.right);
//	    } test purpose
	  
	 @Override
	 public boolean isBalanced() {
		 // it's always true but for test purpose
		 return isBalanced(root);
	 }
	 
	 private boolean isBalanced(Node<T> node) {
		 if (node == null) {
	            return true; // An empty tree is always balanced
	        }

	        int balance = this.getBalance(node);

	        // Check the balance factor of the current node
	        if (Math.abs(balance) > 1) {
	            return false;
	        }

	        // Recursively check the balance of left and right subtrees
	        return isBalanced(node.left) && isBalanced(node.right);
	 }
}
