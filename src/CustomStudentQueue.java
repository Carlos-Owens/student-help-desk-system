import java.util.ArrayList;

// Custom FIFO queue implementation using linked nodes.
// Students are added to the rear and removed from the front.
public class CustomStudentQueue {
    private Node front;
    private Node rear;
    private int size;

    public CustomStudentQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return front == null;
    }

    // Adding students to the rear of the queue
    public void offer(Student student) {
        // Creating a new node with the student object
        Node newNode = new Node(student);

        // If the queue is empty then the front and rear will point towards the new node.
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    // Removes and returns the student at the front of the queue
    public Student poll() {
        if (isEmpty()) {
            return null;
        }


        Student removedStudent = front.data;

        front = front.next;

        size--;

        if (front == null) {
            rear = null;
        }

        return removedStudent;
    }

    // Shows the Student at the front of the Queue
    public Student peek() {
        if (isEmpty()) {
            return null;
        }

        return front.data;
    }

    // Traverse through the linked nodes from front to rear
    public void displayQueue() {

        // Temporary pointer used for traversal
        Node current = front;

        while (current != null) {
            // Move through the queue, one node at a time
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Completely wipes the queue of all student data
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    // Returns a copy of the queue while transforming it into an arraylist
    public ArrayList<Student> toArrayList() {
        ArrayList<Student> list = new ArrayList<>();

        Node current = front;

        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        return list;
    }
}