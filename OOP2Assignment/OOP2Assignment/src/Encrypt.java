import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Encrypt {
    //private attributes
    private WordStore adjectiveStore = new WordStore("adjectives.txt");
    private WordStore nounStore =  new WordStore("nouns.txt");
    private VerbStore verbStore = new VerbStore("verbs.txt");
    private WordStore adverbStore = new WordStore("adverbs.txt");
    private String message;
    private int messageLength;


    //public constructor
    public Encrypt(String message) {
        this.message = message;
        this.messageLength = message.length() -1;
    }

    //function that encrypts a message
    public List<String> encrypt()  {
        List<String> encryptedMsg = new ArrayList<>();
        //checking if the input is null
        if (message== null || message.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }
        //there is always a noun in the encrypted message
        encryptedMsg.add(encryptNoun());
        //checking if the input is big enough to add an adjective
        if (messageLength > 0) {
            encryptedMsg.add(encryptAdjective());
        }
        encryptedMsg.add(encryptVerbsAdverbs());
        Collections.reverse(encryptedMsg);
        return encryptedMsg;
    }
    
    //function to encrypt verbs and adverbs
    public String encryptVerbsAdverbs(){
        boolean adverbCondition = true;
        StringBuilder encrypted = new StringBuilder();
        while (messageLength > 0) {
            char encryptedChar = message.charAt(messageLength);
            if (adverbCondition) {
                encrypted.append(adverbStore.getRandomItem(encryptedChar));
            } else {
                encrypted.append(verbStore.getRandomItem(encryptedChar));
            }
            adverbCondition = !adverbCondition;
            messageLength--;
        }
        return encrypted.toString();
    }

    //function to encrypt the noun
    public String encryptNoun(){
        String encrypted = nounStore.getRandomItem(message.charAt(messageLength));
        return encrypted;
    }
    
    //function to encrypt the adjective
    public String encryptAdjective(){
        String encrypted = adjectiveStore.getRandomItem(message.charAt(messageLength));
        messageLength--;
        return encrypted;
    }

    public static void main(String[] args) throws WordStore.NotFoundInStore {
        String message = args[0];
        Encrypt encryptInstance = new Encrypt(message);
        List<String> encryptedWords = encryptInstance.encrypt();
        for (String word : encryptedWords) {
            System.out.println(word);
        }
    }
}

