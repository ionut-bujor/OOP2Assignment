import java.util.ArrayList;
public class VerbStore extends WordStore {
    //attributes
    private  Node head;

    //constructor (0 args)
    public VerbStore(){super();}

    //constructor (file args)
    public VerbStore(String fileName) {
        super();
        ArrayList<String[]> keyItems = WordStore.processFiles(fileName);
        for (String[] mapping : keyItems) {
            char key = mapping[1].charAt(0);
            String item = mapping[0];
            this.add(key, item);
        }
    }


    //changing verbs from the infinitive form to a present continuous tense
    public String verbTransformation(String verb){
        if (verb.endsWith("e")) {
            verb = verb.substring(0, verb.length() - 1) + "ing";
        }
        if (verb.endsWith("ing")){
            return verb;
        }
        else {
            verb = verb + "ing";
        }
        return verb;
    }

    //overriding the method to ensure each verb is in the present continuous tense even if not in file
    @Override
    public void add(char key, String item) {
        item = verbTransformation(item);
        super.add(key, item);
    }
}

