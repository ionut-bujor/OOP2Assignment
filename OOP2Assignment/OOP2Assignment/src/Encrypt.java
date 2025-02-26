import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Encrypt {
    //private attributes
    private WordStore adjectiveStore;
    private WordStore nounStore;
    private VerbStore verbStore;
    private WordStore adverbStore;

    //public constructor
    public Encrypt() {
        this.adjectiveStore = new WordStore("adjectives.txt");
        this.nounStore = new WordStore("nouns.txt");
        this.verbStore = new VerbStore("verbs.txt");
        this.adverbStore = new WordStore("adverbs.txt");
    }

    //function that encrypts a message
    public List<String> encrypt(String input)  {
        //checking if the input is null
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        //variables needed for the function
        List<String> encryptedMsg = new ArrayList<>();
        int inputLength = input.length() - 1;
        boolean adverbCondition = true;// dictates if a verb or an adverb is added

        //there is always a noun in the encrypted message
        encryptedMsg.add(nounStore.getRandomItem(input.charAt(inputLength)));

        //checking if the input is big enough to add an adjective
        if (inputLength > 0) {
            encryptedMsg.add(adjectiveStore.getRandomItem(input.charAt(inputLength - 1)));
            inputLength--;
        }

        //adding verbs and adverbs in a 1:1 fashion
        while (inputLength > 0) {
            char encryptedChar = input.charAt(inputLength);
            if (adverbCondition) {
                encryptedMsg.add(adverbStore.getRandomItem(encryptedChar));
            } else {
                encryptedMsg.add(verbStore.getRandomItem(encryptedChar));
            }
            adverbCondition = !adverbCondition;
            inputLength--;
        }
        Collections.reverse(encryptedMsg);
        return encryptedMsg;
    }

    public static void main(String[] args) throws WordStore.NotFoundInStore {
        String messageEncrypted = args[0];
        Encrypt encryptInstance = new Encrypt();
        List<String> encryptedWords = encryptInstance.encrypt(messageEncrypted);
        for (String word : encryptedWords) {
            System.out.println(word);
        }
    }
}
