package ie.ucd.csnl.comp47500;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Random;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ie.ucd.csnl.comp47500.api.AVLTree;
import ie.ucd.csnl.comp47500.api.Contact;
import ie.ucd.csnl.comp47500.impl.AVLTreeImpl;
import ie.ucd.csnl.comp47500.constant.Message;
import ie.ucd.csnl.comp47500.impl.ContactImpl;

public class Main {

    private AVLTree<Contact> tree;

    public Main() {
        this.tree = new AVLTreeImpl<>();
    }

    public AVLTree<Contact> getTree() {
        return tree;
    }

    private static void insertContactsFromFile(String filePath, Main ob) {
        try (BufferedReader br = new BufferedReader(new FileReader(Message.RESOURCE_PATH + filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] contactData = line.split(",");
                if (contactData.length == 5) {
                    String id = contactData[0].trim();
                    String name = contactData[1].trim();
                    String phone = contactData[4].trim();
                    String organization = contactData[3].trim();
                    String email = contactData[2].trim();

                    Contact contact = new ContactImpl(id, name, phone, organization, email);
                    ob.getTree().insert(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertContactsFromRandomFile(String filePath, Main ob) {
        try (BufferedReader br = new BufferedReader(new FileReader(Message.RESOURCE_PATH + filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] contactData = line.split(",");
                if (contactData.length == 5) {
                    String id = contactData[0].trim();
                    String name = contactData[1].trim();
                    String phone = contactData[2].trim();
                    String organization = contactData[3].trim();
                    String email = contactData[4].trim();

                    Contact contact = new ContactImpl(id, name, phone, organization, email);
                    ob.getTree().insert(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContactsFromFile(String filePath, Main ob) { 
        try (BufferedReader br = new BufferedReader(new FileReader(Message.RESOURCE_PATH + Message.RANDOM_INPUT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] contactData = line.split(",");
                if (contactData.length == 5) {
                    String id = contactData[0].trim();
                    String name = contactData[1].trim();
                    String phone = contactData[2].trim();
                    String organization = contactData[3].trim();
                    String email = contactData[4].trim();

                    Contact contact = new ContactImpl(id, name, phone, organization, email);
                    ob.getTree().delete(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private static void searchConatactsFromRandomFile(String filePath, Main ob) {
            try (BufferedReader br = new BufferedReader(new FileReader(Message.RESOURCE_PATH +  Message.RANDOM_INPUT_FILE_PATH))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] contactData = line.split(",");
                    if (contactData.length == 5) {
                        String name = contactData[1].trim();
                        List<Contact> contacts = testSearch(ob, name);
                        contacts.stream().forEach(contact -> System.out.print(contact.toString() + " "));
                    }
                }
                System.out.println();
                List<Contact> randomSearch = testSearch(ob, "tst name");
                if(randomSearch.size() == 0) {
                    System.out.println("No contact found with the name tst name");
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    public static void main(String[] args) {
        Main ob = new Main();
        //String[] files = { "order_input_1.txt", "order_input_2.txt"};
        String[] files = { "order_input_1.txt", "order_input_2.txt", "order_input_3.txt", "order_input_4.txt",
        "order_input_5.txt", "order_input_6.txt" };
        for (int i = 0; i < files.length; i++) {
            String filePath = files[i];

            // insert all contacts from the file
            long startTimeForIteration = System.currentTimeMillis();
            insertContactsFromFile(filePath, ob);
            System.out.println("Is the tree Balanced? The answer is : "+ob.getTree().isBalanced());
            System.out.println("Tree size :"+ ob.getTree().size() + " height of tree is " + ob.getTree().getHeight());
            /* code to print the Binary tree
            This part is kept commented as we are addinf lot of nodes in a tree hence the tree will be very big to print
            */
            //ob.getTree().printBinaryTree();
            long endTimeForIteration = System.currentTimeMillis();
            System.out.println("insertion time taken of all element for size " + ob.getTree().size() + "," + (endTimeForIteration - startTimeForIteration) + "ms.");

            // insert next 25 contacts from the file
            long startTimeForinsertingelement = System.currentTimeMillis();
            insertContactsFromRandomFile(Message.RANDOM_INPUT_FILE_PATH, ob);
            long endTimeForinsertingelement = System.currentTimeMillis();
            long differenceForinsertion = endTimeForinsertingelement - startTimeForinsertingelement;
            System.out.println("time taken to insert 25 elements in the same tree, updated size is " + ob.getTree().size() + "," + differenceForinsertion + "ms.");
            

            // search for the next 25 contacts from the file
            long startTimeForsearchingelement = System.currentTimeMillis();
            searchConatactsFromRandomFile(Message.RANDOM_INPUT_FILE_PATH, ob);
            long endTimeForsearchingelement = System.currentTimeMillis();
            long differenceForSearching = endTimeForsearchingelement - startTimeForsearchingelement;
            System.out.println("time taken to search 25 element in the same tree, updated size is " + ob.getTree().size() + "," + differenceForSearching + "ms.");
            
            // delete the next 25 contacts from the file
            long startTimeFordeletingelement = System.currentTimeMillis();
            deleteContactsFromFile(Message.RANDOM_INPUT_FILE_PATH, ob);
            long endTimeFordeletingelement = System.currentTimeMillis();
            long differenceFordeletion = endTimeFordeletingelement - startTimeFordeletingelement;
            System.out.println("time taken to delete 25 element in the same tree, updated size is " + ob.getTree().size() + "," + differenceFordeletion + "ms.");
            
            System.out.println("clearing the tree");
            ob.getTree().clear();
            System.out.println();
        }
        
    }

    /**
     * When searching a contact by name, all contacts by the name should be returned,
     * sorted by id.
     * @param ob
     */
    private static List<Contact> testSearch(Main ob, String name) {
        List<Contact> contacts = SearchContact.searchContactByName((AVLTreeImpl<Contact>)ob.getTree(), name);
        return contacts;
    }
}
