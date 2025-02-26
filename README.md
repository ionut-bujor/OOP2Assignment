# OOP2Assignment




Overview
In this assignment you will implement a rudimentary encryption tool for secret inter-agent communication. Each word in the dictionary has been mapped to a letter. A single word can be encoded by the sequence of words associated with each letter. For example, suppose the mapping included

Quickly -> P
Fly -> A
Loudly -> S
Squawk -> S
Quietly -> W
Rest -> O
Cheerful -> R
Bird -> D
then the word PASSWORD would be encoded as “quickly flying loudly squawking quietly resting cheerful bird”. Notice that the tense of the verbs has changed.

You will implement programs that encrypt and decrypt the communications. For instance, the encryption program could run as follows:

$ java Encrypt password
quickly
flying
loudly
squawking
quietly
resting
cheerful
bird
and the decryption program could run as

$ java Decrypt quickly flying loudly squawking quietly resting cheerful bird
password
The words used for each character or character sequence are randomly chosen from some given lists, and multiple runs can produce different results.

$ java Encrypt password
questionably
dating
cozily
dying
wistfully
criminalizing
palpable
roughage
Carefully crafted mapping files with adjectives, adverbs, nouns, and verbs are provided. You should store these files in the same directory as your Java code.

You will need several methods of the Java API for this assignment. The best resource for help on how to invoke a method or use an object is the official API documentation.

You are free to make design decisions throughout the assignment and may make use of the Java standard library classes as you feel appropriate. However, all code should compile with javac *.java. You cannot use external or third-party libraries in your solutions.

Question 1: WordStore[10 Marks]
Data Structure. The encryption relies on the WordStore class for mapping characters to lists of words. Mathematically, the structure corresponds to a partial function from characters to lists of strings.

Create a WordStore class that has

a constructor WordStore() that takes no arguments and initialises the store;
a method void add(char key, String item) that associates the key to the item. It should not overwrite existing mappings but add to them;
a method String getRandomItem(char key) that returns a random word (using the Random class) from the strings that key has been associated with. The word returned should be in lower case. If key is not mapped to any words, the method should return null.
As an example, suppose the following code was run

WordStore store = new WordStore();
store.add('a', "Chair");
store.add('a', "Table");
Then store.getRandomItem('a') should return either chair or table.

Ensure that your code is case-insensitive. The store should return the same items regardless of the case of the key. E.g. store.getRandomItem('A') should behave the same as store.getRandomItem('a'). Returned words should be in lower case.

Testing You can test your answer with the testing tool.

$ java -jar assignment1TesterStudent.jar
You should see that the teststestQ1_01_MainStore, testQ1_02_MainStore_NonExisting, and testQ1_03_MainStore_Random all pass.

Your Own Testing Class. Now write a your own tests for your new class. Create a class AssignmentTest. This class should have a main method that runs a series of tests to make sure that your WordStore class works as intended. You should test all of the methods in the WordStore class. Marks will be awarded for good code organisation.

You may find the assert keyword in Java useful for your testing code. If you add lines such as

assert x > 0;
And run your code with the -enableassertions flag

$ java -enableassertions AssignmentTest
the run will fail if an assertion is not true.

Question 2: Populating the WordStore[30 Marks]
Loading the Word Lists. Declare a second constructor for WordStore that takes a file name as an argument and loads a list of activities into the map using the class BufferedReader. This constructor should be declared to throw an IOException, which signals that it could not successfully load the file. Make sure to call close() on the BufferedReader within the constructor, even in the event of an exception being thrown. Use an appropriate technique from the lectures.

Each line in the item list is a word mapping of the form “word,letter”. Find an appropriate method in the Java String documentation for splitting a line of the file into the word and the letter. You should fill the store such that the letter of each item is associated with the word.

Testing Test your new code by adding more testing code to AssignmentTest that checks that the new WordStore constructor loads activities from the passed file.

When running the testing tool, you should now see that the tests testQ2_01_LoadFile, testQ2_02_LoadFileThenRetrieve, and testQ2_03_LoadFileThenRetrieve all pass.

Verb Store. The verbs in the word files are given in the infinitive form. For constructing sentences, the present continuous tense will yield better results. Write a class VerbStore that acts like WordStore except the returned words are converted to the present continuous tense. For the purposes of this assignment, you can do this by removing a trailing “e” (if present) and adding “ing” as a suffix.

Marks will be awarded for a good program design that avoids repetition of code.

The testing tool should now pass tests testQ2_04_SpecialStore1 and testQ2_05_SpecialStore2.

Help My Tests are Failing! Common causes of failure may be the following.

The word list files are not in the directory where you are running the tester. Make sure all files are in the same directory.
When declaring the WordStore constructor that takes a file, do not delete the constructor that takes no arguments: the tester will expect both to be present.
Question 3: Creating Encrypted Sentences[25 Marks]
Generating. Create a new class Encrypt that does the actual encryption of words. It should load a different WordStore for each of the different word lists (adjectives, adverbs, nouns, and verbs).

Add a method List<String> encrypt(String input) that iterates through a string, looking up characters in the different stores and adding the resulting words to a list of strings that is then returned as the result. Structure your code elegantly, use any additional methods you think are appropriate, and avoid duplicating code.

To make the encrypted sentence sound convincing, you have to enforce a certain order of word types. The sentence should always end with a noun, preceded by an adjective if there are enough letters. Remaining letters should be encoded by an alternating sequence of adverbs and verbs that ends with a verb.

Written as a regular expression, the pattern should match a suffix of:

Verb? (Adverb Verb)* Adjective Noun
Hint: sometimes problems are easier if you work backwards.

Testing Test your new code by adding more testing code to AssignmentTest. Although the output is random, you can still verify that the result is of the right length, for example.

Input Processing. Add a main method to Encrypt that takes the word given as the first command line argument and prints the resulting sentence with a word on each line. Make sure to handle any exceptions properly. Test your code by running your program with several different words as inputs.

Your program should now pass testQ3_Encrypt of the testing tool.

Question 4: Decryption [20 Marks]
Write a counterpart program Decrypt. It should provide a main method and when run from the command line, should decrypt a given sentence. For example

$ java Decrypt quickly flying loudly squawking quietly resting cheerful bird
password
(Note that the input words are not in quotes.)

You will need to design your own program structure. Marks will be awarded for good choices of data structures and well-designed code. You may add methods and fields to existing classes, as long as they do not break your solutions to previous parts.

Hint: use a StringBuilder to build strings. You may find some of its features useful.

Your program should now pass testQ4_Decrypt of the testing tool.

Testing Test your new code by adding more testing code to AssignmentTest. Although the output is random, you can still verify that the result is of the right length, for example.

Marking Criteria
Full marks will be awarded for correct answers to the questions below. The assessment of the correctness of submissions is based on

functionality (i.e., whether the submitted code performs the requested tasks);
proper use of object-oriented design as taught during the course lectures and exercise classes; and
coding style.
You should aim to write well-formatted and well-commented code that compiles, and to make use of informative and meaningful variable names. You should also aim to make the code as readable as possible, i.e., do not clutter the code by providing too much information.

You should attempt all parts and submit what you have done, even if you are unable to complete some of the questions. Marks are awarded for each question individually, even when the overall solution is incomplete or incorrect.

Please ensure submitted code can be compiled and run. Partial solutions that do not compile can be submitted as comments in your code. We cannot fix broken submissions. Redownload your submission and test it to verify everything is as expected.

A jar file for testing is provided. Please use this testing tool as it will give an indication of your progress. This tool will be used by the markers in assessing your work. Please note that passing all the provided tests does not guarantee full marks, since an automatic testing process cannot verify proper use of object-oriented design, coding style, or readability. We also use additional tests when checking your submitted work.

The number of marks available is indicated in each question. Markers will use the following table to guide their marking decisions.

0%: No attempt was made at the question.
20%: The code is difficult to follow and contains significant errors or omissions.
40%: The code is functional and passes a majority of the tests. However, it is not well-structured, contains frequent repetition, or does not make good use of classes and methods.
60%: All or almost all tests are passed. The code is of a clean standard but perhaps could be tidier in places.
80%: An excellent submission for a new programmer. All tests pass, except maybe one or two of the most tricky cases. The structure is confident, making use of classes and methods as appropriate.
100%: A flawless submission going above and beyond expectations. The code is robust to errors and unusual input, all tests pass, it is well commented to help future programmers, the design could easily be extended to suit future developments, the purpose of each part of the code is clear and unmuddled, etc.
Please contact matthew.hague@rhul.ac.uk or matteo.sammartino@rhul.ac.uk, or post in the Moodle forums if you have any specific questions.

