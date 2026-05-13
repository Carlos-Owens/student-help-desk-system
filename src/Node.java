public class Node {
    // Reference to the student stored in this node
    Student data;

    // Points to the next node in the queue
    Node next;

    public Node(Student data) {
        this.data = data;
        this.next = null;
    }
}