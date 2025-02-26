import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class WordStore{
    //assigning private attributes
    private  Node head;

    // public constructor (0 args)
    public WordStore() {
        this.head = null;
    }

    //process files into keys and items
    public static ArrayList<String[]> processFiles(String fileName){
        ArrayList<String[]> keyItems = new ArrayList<>();
        BufferedReader reader = null;
        String line;
        try{
            //read through the file and assign the key and item to correct values
            reader = new BufferedReader(new java.io.FileReader(fileName));
            while ((line = reader.readLine()) != null){
                String[] keyItem =  line.split(",");
                keyItems.add(keyItem);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch (IOException e ){
            System.out.println("The file is empty!");
        }
        finally{
            //making sure that the reader is closed after being opened
            if (reader != null){
                try{
                    reader.close();
                }
                catch (IOException e){
                    System.out.println("Error closing the buffer!");
                }
            }
        }
        return keyItems;
    }

    //public constructor (file args)
    public WordStore(String fileName) {
        this.head = null;
        ArrayList<String[]> keyItems = processFiles(fileName);
        for (String[] mapping : keyItems){
            char key = mapping[1].charAt(0);
            String item = mapping[0];
            add(key,item);
        }
    }

    //getter for head of class
    public Node getHead(){
        return this.head;
    }

    //associate the key to the item
    public void add(char key, String item){
        Node newMapping = new Node(key,item);
        Node current = this.head;
        if (this.head == null){
            this.head = newMapping;
        }
        else{
            while (current.getNext() !=null){
                current = current.getNext();
            }
            current.setNext(newMapping);
        }
    }

    //returning a random item associated with key
    public String getRandomItem (char key)  {
        //assigning variables needed for the function
        key = Character.toLowerCase(key);
        ArrayList<String> associatedItems = new ArrayList<>();
        Node current = this.head;
        Random rand = new Random();
        //allocating all the items with key specified to an array list
        while (current != null){
            if (current.getKey() == key){
                associatedItems.add(current.getItem());
            }
            current = current.getNext();
        }
        if (associatedItems.isEmpty()){
            return null;
        }
        //no key mappings
        int noOfMappings = associatedItems.size();
        //generating a random integer which is used as an index to find a random item
        int randIndex = rand.nextInt(noOfMappings);
        String randomItem = associatedItems.get(randIndex);
        randomItem = randomItem.toLowerCase();
        return randomItem;
    }

    //function to retrieve the associated key to an item
    public char getKeyOfItem(String item) throws NotFoundInStore {
        Node current = this.head;
        while (current != null) {
            if (current.getItem().equals(item)) {
                return current.getKey();
            }
            current = current.getNext();
        }
        throw new NotFoundInStore("Item is not in the store");
    }

    //function for testing to check that elements which have been added are actually within the store
    public boolean checkInstancesOfItem(char key,String item) throws NotFoundInStore {
        Node current = this.head;
        while(current != null){
            if (current.getItem().equals(item) && current.getKey() == key){
                return true;
            }
            current = current.getNext();
        }
        throw new NotFoundInStore("Item was not added to the store");
    }

    //overriding the toString method to make a cleaner more readable string output
    @Override
    public String toString() {
        Node current = this.head;
        ArrayList<String> stringRepresentation = new ArrayList<>();
        while (current != null) {
            stringRepresentation.add(current.getKey() + ": " + current.getItem());
            current = current.getNext();
        }
        return String.join(" -> ", stringRepresentation);
    }

    //exception to show that something is not in the list
    class NotFoundInStore extends Exception{
        public NotFoundInStore(String message){
            super(message);
        }
    }
}