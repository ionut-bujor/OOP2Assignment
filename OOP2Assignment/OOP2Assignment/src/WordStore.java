import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class WordStore{
    //assigning private attributes
    private  Node head;
    private String fileName;

    // public constructor (0 args)
    public WordStore() {
        this.head = null;
    }

    //process files into keys and items
    public static ArrayList<String[]> processFiles(String fileName) {
        ArrayList<String[]> keyItems = new ArrayList<>();
        try { keyItems = assignKeyAndItem(fileName);}
        catch (FileNotFoundException e) {System.out.println("File not found: " + fileName);}
        catch (IOException e) {System.out.println("Error reading the file: " + fileName);}
        return keyItems;
    }

    //public constructor (file args)
    public WordStore(String fileName) {
        this.head = null;
        this.fileName = fileName;
        ArrayList<String[]> keyItems = processFiles(fileName);
        assignMappingsFromFiles(keyItems);
    }

    public void assignMappingsFromFiles(ArrayList<String[]> keyItems){
        for (String[] mapping : keyItems){
            char key = mapping[1].charAt(0);
            String item = mapping[0];
            add(key,item);
        }
    }

    private static ArrayList<String[]> assignKeyAndItem(String fileName) throws IOException {
        String line;
        ArrayList<String[]> keyItems = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
        while ((line = reader.readLine()) != null) {
            String[] keyItem = line.split(",");
            keyItems.add(keyItem);
        }
        closeBufferedReader(reader);
        return keyItems;
    }

    private static void closeBufferedReader(BufferedReader reader){
        if (reader != null){
            try{reader.close();}
            catch (IOException e){System.out.println("Error closing the buffer!");}
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
            this.head = newMapping;}
        else{setNewMapping(current,newMapping);}
    }

    public void setNewMapping(Node current,Node newMapping){
        while (current.getNext() !=null){
            current = current.getNext();
        }
        current.setNext(newMapping);

    }

    //returning a random item associated with key
    public String getRandomItem (char key)  {
        key = Character.toLowerCase(key);
        ArrayList<String> associatedItems = createAssociatedItemsWithKey(key);
        if (associatedItems.isEmpty()){return null;}
        String randomItem = getRandomItemAtIndex(associatedItems);
        return randomItem;
    }

    public String getRandomItemAtIndex(ArrayList<String> associatedItems){
        Random rand = new Random();
        int noOfMappings = associatedItems.size();
        int randIndex = rand.nextInt(noOfMappings);
        String randomItem = associatedItems.get(randIndex);
        randomItem = randomItem.toLowerCase();
        return randomItem;
    }

    public ArrayList<String> createAssociatedItemsWithKey(char key){
        ArrayList<String> associatedItems = new ArrayList<>();
        Node current =  this.head;
        while (current != null){
            if (current.getKey() == key){
                associatedItems.add(current.getItem());
            }
            current = current.getNext();
        }
        return associatedItems;
    }

    //function to retrieve the associated key to an item
    public char getKeyOfItem(String item) throws MappingNotFoundInStore {
        Node current = this.head;
        while (current != null) {
            if (current.getItem().equals(item)) {
                return current.getKey();
            }
            current = current.getNext();
        }
        throw new MappingNotFoundInStore("Item is not in the store");
    }

    //function for testing to check that elements which have been added are actually within the store
    public boolean checkInstancesOfItem(char key,String item) throws MappingNotFoundInStore {
        Node current = this.head;
        while(current != null){
            if (current.getItem().equals(item) && current.getKey() == key){
                return true;
            }
            current = current.getNext();
        }
        throw new MappingNotFoundInStore("Item was not added to the store");
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
    class MappingNotFoundInStore extends Exception{
        public MappingNotFoundInStore(String message){
            super(message);
        }
    }
}
