public class AssignmentTest {

    public static boolean isLowerCase(String item){
        for (int indexString = 0 ; indexString < item.length();indexString ++){
            if (Character.isUpperCase(item.charAt(indexString))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[ ] args) throws WordStore.NotFoundInStore{
        //create WordStore instance
        WordStore wordStore = new WordStore();

        //testing the add function
        wordStore.add('a',"laptop");
        wordStore.add('a',"ipad");
        wordStore.add('a',"monitor");
        wordStore.add('a',"mOuse");
        wordStore.add('a',"Keyboard");

        //testing the getRandomItemFunction
        String randomItemTest = wordStore.getRandomItem('a');
        String randomItemTestCapitalChar = wordStore.getRandomItem('A');
        String randomItemTestLowerCaseItem = wordStore.getRandomItem('a');

        //printing the entire collection of mappings
        System.out.println(wordStore.toString());

        //assertion testing
        Node headStore = wordStore.getHead();
        try{
            assert headStore != null: "WordStore is empty";
            assert randomItemTest != null: "Random item not retrieved with correct key";
            assert randomItemTestCapitalChar !=null: "Random item not retrieved with capital key char";
            assert isLowerCase(randomItemTestLowerCaseItem): "Random item retrieved is not in lower case characters";
        }
        catch (AssertionError e){
            e.printStackTrace();
        }

        //testing for constructor with file
        WordStore fileMethod = new WordStore("adverbs.txt");

        //gathering test data
        Node headFile = fileMethod.getHead();
        String randomItemTestFile = fileMethod.getRandomItem('c');
        String randomItemTestFileUpper = fileMethod.getRandomItem('C');
        String randomItemTestFileLower = fileMethod.getRandomItem('c');

        //assertion testing
        try{
            assert headFile != null: "The Store is empty!";
            assert randomItemTestFile != null:"Random item cant be retrieved";
            assert randomItemTestFileUpper != null:"Random item cant be retrieved with upper key";
            assert randomItemTestFileLower != null: "Random item is not in lower case characters";
        }
        catch(AssertionError e){
            e.printStackTrace();
        }

        //testing VerbStore class
        VerbStore verbStore = new VerbStore();
        verbStore.add('a',"cling");

        //assertion testing
        try{
            assert verbStore.checkInstancesOfItem('a',"cling") == true: "The verb is still getting put into the store with the wrong prefix";
            assert verbStore.checkInstancesOfItem('a',"clinging") == false: "The mapping is not being put into the store";
        }
        catch(AssertionError e ){
            e.printStackTrace();
        }

        //testing VerbStore class with file args
        VerbStore verbStoreFile = new VerbStore("verbs.txt");
        Node headVerbFile = verbStoreFile.getHead();
        String verb = headVerbFile.getItem();
        String prefix = verb.substring(verb.length()- 4,verb.length() -1);

        //assertion testing
        try{
            assert headVerbFile != null : "The file is not being put into the VerbStore";
            assert prefix.equals("ing"): "The verbs are not being converted properly";
        }
        catch(AssertionError e ){
            e.printStackTrace();
        }
    }
}
