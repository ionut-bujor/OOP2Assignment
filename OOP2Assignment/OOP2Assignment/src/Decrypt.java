import java.util.ArrayList;
import java.util.Collections;

public class Decrypt {
    //private attributes
    private final WordStore adjectives = new WordStore("adjectives.txt");
    private final WordStore verbs = new VerbStore("verbs.txt");
    private final WordStore adverbs = new WordStore("adverbs.txt");
    private final WordStore nouns = new WordStore("nouns.txt");
    private ArrayList<String> encrypted;
    private int indexBeingDecrypted = 0;

    private WordStore getVerbs(){
        return this.verbs;
    }
    private WordStore getAdverbs(){
        return this.adverbs;
    }
    private WordStore getAdjectives(){
        return this.adjectives;
    }
    private WordStore getNouns(){
        return this.nouns;
    }

    //public constructor
    public Decrypt(ArrayList<String> encrypted){
        this.encrypted = encrypted;
        Collections.reverse(encrypted);
    }

    //function to decrypt the message inputted by the user
    public String decrypt() throws WordStore.MappingNotFoundInStore {
        if (this.encrypted == null || this.encrypted.isEmpty()) {
            return null;
        }
        String decryptedMessage = decryptWordTypes();
        return decryptedMessage;
    }

    //function to decrypt verbs and adverbs
    public String decryptVerbAndAdverbs() throws WordStore.MappingNotFoundInStore {
        boolean verbCheck = false;
        StringBuilder decryptedMsg = new StringBuilder();
        for (int index = 2; index < this.encrypted.size(); index++) {
            if (verbCheck) {
                decryptedMsg.append(decryptVerb(index));
            } else {
                decryptedMsg.append(decryptAdverb(index));
            }
            verbCheck = !verbCheck;
        }
        return decryptedMsg.toString();
    }

    //function to decrypt verbs
    public Character decryptVerb(int index) throws WordStore.MappingNotFoundInStore {
        if (validateIndexBounds()) {
            String verb = this.encrypted.get(index);
            char decryptVerb = getVerbs().getKeyOfItem(verb);
            return decryptVerb;
        }
        else return null;
    }

    //function to decrypt adverbs
    public Character decryptAdverb(int index) throws WordStore.MappingNotFoundInStore {
        if (validateIndexBounds()) {
            String adverb = this.encrypted.get(index);
            char decrypt = getAdverbs().getKeyOfItem(adverb);
            return decrypt;
        }
        else return null;
    }

    //function to decrypt the adjective in the encrypted message
    public Character decryptAdj() throws WordStore.MappingNotFoundInStore {
        if (validateIndexBounds()) {
            String adjective = this.encrypted.get(indexBeingDecrypted);
            char decrypt = getAdjectives().getKeyOfItem(adjective);
            indexBeingDecrypted++;
            return decrypt;
        }
        else return null;
    }

    //function to decrypt the noun in the encrypted message
    public Character decryptNoun() throws WordStore.MappingNotFoundInStore {
        String noun = this.encrypted.get(indexBeingDecrypted);
        char decrypt = getNouns().getKeyOfItem(noun);
        indexBeingDecrypted ++;
        return decrypt;
    }

    //function to decrypt all words
    public String decryptWordTypes() throws WordStore.MappingNotFoundInStore {
        StringBuilder decrypted = new StringBuilder();
        decrypted.append(decryptNoun());
        decrypted.append(decryptAdj());
        decrypted.append(decryptVerbAndAdverbs());
        return decrypted.reverse().toString();
    }

    public boolean validateIndexBounds(){
        if (indexBeingDecrypted >= this.encrypted.size()){
            return false;
        }
        else return true;
    }

    public static void main(String[] args) throws WordStore.MappingNotFoundInStore {
        ArrayList<String> encryptedMessage = new ArrayList<>();
        if (args.length == 0){
            throw new IllegalArgumentException("The message can't be null.");
        }

        for (String encryptedWords : args) {
            encryptedMessage.add(encryptedWords);
        }

        Decrypt decryptInstance = new Decrypt(encryptedMessage);
        String message = decryptInstance.decrypt();
        System.out.println(message);
    }
}
// Error running test testQ4_Decrypt. Caused by java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0 at Preconditions.java:100
