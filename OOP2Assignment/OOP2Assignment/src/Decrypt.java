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
        Collections.reverse(encrypted);
        StringBuilder decryptedMessage = new StringBuilder();
        decryptedMessage.append(decryptWordTypes());
        return decryptedMessage.reverse().toString();
    }

    //function to decrypt verbs and adverbs
    public String decryptVerbAndAdverbs() throws WordStore.NotFoundInStore {
        int index = 0;
        boolean verbCheck = true;
        StringBuilder decryptedMsg = new StringBuilder();

        while (index <encrypted.size()) {
            if (verbCheck) {decryptedMsg.append(decryptVerb(index));}
            else {decryptedMsg.append(decryptAdverb(index));}
            index++;
            verbCheck = !verbCheck;
        }
        return decryptedMsg.toString();
    }

    //function to decrypt verbs
    public char decryptVerb(int index) throws WordStore.NotFoundInStore{
        String verb = encrypted.get(index);
        char decrypt = verbs.getKeyOfItem(verb);
        return decrypt;
    }

    //function to decrypt adverbs
    public char decryptAdverb(int index) throws WordStore.NotFoundInStore{
        String adverb = encrypted.get(index);
        char decrypt = adverbs.getKeyOfItem(adverb);
        return decrypt;

    }

    //function to decrypt all words
    public String decryptWordTypes() throws WordStore.NotFoundInStore{
        StringBuilder decrypted = new StringBuilder();
        decrypted.append(decryptNoun());
        decrypted.append(decryptAdj());
        decrypted.append(decryptVerbAndAdverbs());
        return decrypted.toString();
    }

    //function to decrypt the adjective in the encrypted message
    public char decryptAdj() throws WordStore.NotFoundInStore{
        char decrypt;
        String adjective = encrypted.get(position);
        decrypt = adjectives.getKeyOfItem(adjective);
        encrypted.remove(position);
        return decrypt;
    }

    //function to decrypt the noun in the encrypted message
    public char decryptNoun() throws WordStore.NotFoundInStore{
        String noun = encrypted.get(position);
        char decrypt=nouns.getKeyOfItem(noun);
        encrypted.remove(position);
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
