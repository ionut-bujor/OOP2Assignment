public class Node{
    private char key;
    private String item;
    private Node next;

    //public constructor
    public Node(char key,String  item){
        this.key = key;
        this.item = item;
        this.next = null;
    }

    //setters for the class
    public void setKey(char key){
        this.key = key;
    }
    public void setItem(String item){
        this.item = item;
    }
    public void setNext(Node node){
        this.next = node;
    }

    //getters for the class
    public String getItem(){
        return this.item;
    }
    public char getKey(){
        return this.key;
    }
    public Node getNext(){
        return this.next;
    }

    @Override
    public String toString(){
        return ("Key: " + this.key + " |Item: " + this.item);
    }


}
