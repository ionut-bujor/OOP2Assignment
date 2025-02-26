import java.util.ArrayList;
import java.util.Collections;

public class Decrypt {
    //private attributes
    private WordStore adjectives = new WordStore("adjectives.txt");
    private VerbStore verbs = new VerbStore("verbs.txt");
    private WordStore adverbs = new WordStore("adverbs.txt");
    private WordStore nouns = new WordStore("nouns.txt");
    private final int position = 0;
    private ArrayList<String> encrypted;

    //public constructor
    public Decrypt(ArrayList<String> encrypted){
        this.encrypted = encrypted;
    }

    //function which is used to decrypt a message inputted
    public String decrypt() throws WordStore.NotFoundInStore {
        if (encrypted == null || encrypted.isEmpty()) {
            return null;
        }
        //variables needed for the function
        Collections.reverse(encrypted);
        StringBuilder decryptedMessage = new StringBuilder();

        //there always has to be a noun in every encrypted password
        decryptedMessage.append(decryptNoun());
        encrypted.remove(position);

        //checking if there is an adjective in the encrypted message
        if (!encrypted.isEmpty()) {
            decryptedMessage.append(decryptAdj());
            encrypted.remove(position);
        }

        //decrypt adverbs and verbs and add these to the final message
        if (!encrypted.isEmpty()) {
            decryptedMessage.append(decryptVerbAndAdverbs());
        }
        return decryptedMessage.toString();
    }

    //function to decrypt verbs and adverbs
    public String decryptVerbAndAdverbs() throws WordStore.NotFoundInStore {
        int amountOfVerbsAndAdverbs = encrypted.size();
        int indexBeingChecked = 0;
        boolean verbBeingChecked = true;
        StringBuilder decryptedMsg = new StringBuilder();
        while (indexBeingChecked < amountOfVerbsAndAdverbs) {
            if (verbBeingChecked) {
                String verb = encrypted.get(indexBeingChecked);
                decryptedMsg.append(verbs.getKeyOfItem(verb)).append(" ");
            } else {
                String adverb = encrypted.get(indexBeingChecked);
                decryptedMsg.append(adverbs.getKeyOfItem(adverb)).append(" ");
            }
            indexBeingChecked++;
            verbBeingChecked = !verbBeingChecked;
        }
        return decryptedMsg.toString();
    }

    //function to decrypt the adjective in the encrypted message
    public char decryptAdj() throws WordStore.NotFoundInStore{
        char decrypt;
        String adjective = encrypted.get(position);
        decrypt = adjectives.getKeyOfItem(adjective);
        return decrypt;
    }

    //function to decrypt the noun in the encrypted message
    public char decryptNoun() throws WordStore.NotFoundInStore{
        String noun = encrypted.get(position);
        char decrypt=nouns.getKeyOfItem(noun);
        return decrypt;
    }

    public static void main(String[] args) throws WordStore.NotFoundInStore {
        ArrayList<String> encryptedMessage = new ArrayList<>();
        for (String encryptedWords : args){
            encryptedMessage.add(encryptedWords);
        }
        Decrypt decryptInstance = new Decrypt(encryptedMessage);
        String message = decryptInstance.decrypt();
        System.out.println(message);
    }
}

