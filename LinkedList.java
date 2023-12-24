class LinkedList {
    Node head;

    LinkedList() {
        head = null;
    }

    void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    void clear() {
        head = null;
    }

    String displayList() {
        StringBuilder result = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            result.append(temp.data).append(" ");
            temp = temp.next;
        }
        return result.toString();
    }

    Node getHead() {
        return head;
    }
}