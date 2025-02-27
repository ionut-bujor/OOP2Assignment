import java.util.ArrayList;
public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return this.head;
    }

    public LinkedList(String item) {
        Node itemNode = new Node(item);
        this.head = itemNode;
    }

    public void addItemToMapping(String item) {
        Node newItem = new Node(item);
        Node current = this.head;
        if (this.head == null) {
            this.head = newItem;
        } else {
            setNewMapping(current, newItem);
        }
    }

    public void setNewMapping(Node current, Node newMapping) {
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newMapping);

    }

    public ArrayList<String> turnLinkedListIntoArrayList() {
        ArrayList<String> itemList = new ArrayList<>();
        Node current = this.head;
        while (current != null) {
            itemList.add(current.getItem());
            current = current.getNext();
        }
        return itemList;
    }

    @Override
    public String toString() {
        Node current = head;
        ArrayList<String> listRepresentation = new ArrayList<>();
        while (current != null) {
            listRepresentation.add(current.getItem());
            current = current.getNext();
        }
        return String.join("-->", listRepresentation);
    }
}

