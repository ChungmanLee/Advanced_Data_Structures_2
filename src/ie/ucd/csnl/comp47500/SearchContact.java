package ie.ucd.csnl.comp47500;

import java.util.ArrayList;
import java.util.List;

import ie.ucd.csnl.comp47500.api.Contact;
import ie.ucd.csnl.comp47500.impl.AVLTreeImpl;
import ie.ucd.csnl.comp47500.impl.Node;

public class SearchContact {
	
	public static List<Contact> searchContactByName(AVLTreeImpl<Contact> tree, String name) {
	    List<Contact> contacts = new ArrayList<>();
	    searchByName(tree.getRoot(), name, contacts, false);
	    return contacts;
	}

	private static void searchByName(Node<Contact> node, String name, List<Contact> contacts, boolean isVisited) {
	    if (node != null) {
	        int compareResult = name.compareTo(node.data.getName());
	        
	        // If target name is smaller, search in the left subtree
	        if (compareResult < 0 && !isVisited) {
	            searchByName(node.left, name, contacts, isVisited);
	        }
	        
	        // If target name matches, add the contact to the result list and search in both subtrees
	        if (compareResult == 0) {
	            contacts.add(node.data);
	            searchByName(node.left, name, contacts, true);
	            searchByName(node.right, name, contacts, true);
	        }

	        // If target name is larger, search in the right subtree
	        if (compareResult >= 0 && !isVisited) {
	            searchByName(node.right, name, contacts, isVisited);
	        }
	    }
	}


}
