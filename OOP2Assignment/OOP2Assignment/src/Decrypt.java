import java.util.ArrayList;
import java.util.Collections;

public class Decrypt {
    //private attributes
    private WordStore adjectives = new WordStore("adjectives.txt");
    private VerbStore verbs = new VerbStore("verbs.txt");
    private WordStore adverbs = new WordStore("adverbs.txt");
    private WordStore nouns = new WordStore("nouns.txt");

    //function which is used to decrypt a message inputted
    public String decrypt(ArrayList<String> encryptedMessage) throws WordStore.NotFoundInStore {
        if (encryptedMessage == null || encryptedMessage.isEmpty()) {
            return "";
        }
        //variables needed for the function
        int amountOfWords = encryptedMessage.size();
        Collections.reverse(encryptedMessage);
        int indexBeingChecked = 0;
        boolean verbBeingChecked = true;
        StringBuilder decryptedMessage = new StringBuilder();

        //there always has to be a noun in every encrypted password
        String noun = encryptedMessage.get(indexBeingChecked);
        indexBeingChecked++;
        decryptedMessage.append(nouns.getKeyOfItem(noun)).append(" ");

        //checking if there is an adjective in the encrypted message
        if (indexBeingChecked < amountOfWords) {
            String adjective = encryptedMessage.get(indexBeingChecked);
            decryptedMessage.append(adjectives.getKeyOfItem(adjective)).append(" ");
            indexBeingChecked++;
        }

        //iterating through the rest of the encrypting words in a verb/adverb way
        while (indexBeingChecked < amountOfWords) {
            if (verbBeingChecked) {
                String verb = encryptedMessage.get(indexBeingChecked);
                decryptedMessage.append(verbs.getKeyOfItem(verb)).append(" ");
            } else {
                String adverb = encryptedMessage.get(indexBeingChecked);
                decryptedMessage.append(adverbs.getKeyOfItem(adverb)).append(" ");
            }
            indexBeingChecked++;
            verbBeingChecked = !verbBeingChecked;
        }
        return decryptedMessage.toString().trim();
    }

    public static void main(String[] args) throws WordStore.NotFoundInStore {
        ArrayList<String> encryptedMessage = new ArrayList<>();
        for (String encryptedWords : args){
            encryptedMessage.add(encryptedWords);
        }
        Decrypt decryptInstance = new Decrypt();
        String message = decryptInstance.decrypt(encryptedMessage);
        System.out.println(message);
    }
}
