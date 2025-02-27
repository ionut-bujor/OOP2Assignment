import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public class WordStore{
    //assigning private attributes
    private HashMap<Character,LinkedList> wordStore;

    // public constructor (0 args)
    public WordStore() {
        this.wordStore = new HashMap<>();
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
        this.wordStore = new HashMap<>();
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
    public HashMap<Character,LinkedList> getMap(){
        return this.wordStore;
    }

    //associate the key to the item
    public void add(char key, String item){
        this.wordStore.putIfAbsent(key,new LinkedList());
        this.wordStore.get(key).addItemToMapping(item);
    }
    
    //returning a random item associated with key
    public String getRandomItem (char key)  {
        key = Character.toLowerCase(key);
        LinkedList associatedItems = createAssociatedItemsWithKey(key);
        if (associatedItems == null) {return null;}
        ArrayList<String> associatedItemsList = associatedItems.turnLinkedListIntoArrayList();
        String randomItem = getRandomItemAtIndex(associatedItemsList);
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

    public LinkedList createAssociatedItemsWithKey(char key){
        LinkedList associatedItems = wordStore.get(key);
        return associatedItems;
    }

    //function to retrieve the associated key to an item
    public char getKeyOfItem(String item) throws MappingNotFoundInStore {
        for (HashMap.Entry<Character, LinkedList> entry : wordStore.entrySet()) {
            char key = entry.getKey();
            LinkedList list = entry.getValue();
            Node current = list.getHead();
            while (current != null) {
                if (current.getItem().equals(item)) {
                    return key;
                }
                current = current.getNext();
            }
        }
        throw new MappingNotFoundInStore("Item not found in the store.");
    }

    //function for testing to check that elements which have been added are actually within the store
    public boolean checkInstancesOfItem(char key, String item) {
        LinkedList itemsWithKey = wordStore.get(key);
        if (itemsWithKey == null) {return false;}
        Node current = itemsWithKey.getHead();
        while (current != null) {
            if (current.getItem().equals(item)) {return true;}
            current = current.getNext();
        }
        return false;
    }
    
    //overriding the toString method to make a cleaner more readable string output
    @Override
    public String toString() {
        ArrayList<String> stringRepresentation = new ArrayList<>();
        for(HashMap.Entry<Character,LinkedList> entry: wordStore.entrySet()){
            char key = entry.getKey();
            LinkedList list = entry.getValue();
            stringRepresentation.add(key + ": " + list.toString());

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
