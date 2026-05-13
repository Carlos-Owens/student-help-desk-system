import java.util.ArrayList;

// Manages students waiting for tutoring help.
// Uses a custom linked queue to maintain FIFO ordering.
public class HelpDesk {
    private final CustomStudentQueue waitingStudents;

    public HelpDesk() {
        waitingStudents = new CustomStudentQueue();
    }

    public void addStudent(Student student) {
        waitingStudents.offer(student);
    }

    public boolean hasWaitingStudents() {
        return !waitingStudents.isEmpty();
    }

    public void clearStudents() {
        waitingStudents.clear();
    }

    /* synchronized prevents multiple tutor threads
       from removing the same student simultaneously */
    public synchronized Student helpNextStudent() {
        if (!hasWaitingStudents()) return null;
        else {
            return waitingStudents.poll();
        }
    }

    public int getWaitingCount() {
        return waitingStudents.getSize();
    }

    public ArrayList<Student> getWaitingStudents() {
        return waitingStudents.toArrayList();
    }

    public double getAverageDifficulty() {
        if (!hasWaitingStudents()) return 0;
        else {
            double total = 0;
            for (Student student : waitingStudents.toArrayList()) {
                total += student.getDifficultyLevel();
            }
            return total / waitingStudents.getSize();
        }
    }

    public void displayWaitingStudent() {
        waitingStudents.displayQueue();
    }

    public Student peekNextStudent() {
        return waitingStudents.peek();
    }

    public int countStudentRecursive() {
        ArrayList<Student> list = waitingStudents.toArrayList();
        return countStudentRecursive(list, 0);
    }

    private int countStudentRecursive(ArrayList<Student> list, int index) {
        if (index == list.size()) {
            return 0;
        } else {
            return 1 + countStudentRecursive(list, index + 1);
        }
    }

    public void printStudentRecursive() {
        ArrayList<Student> list = waitingStudents.toArrayList();
        printStudentRecursive(list, 0);
    }

    private void printStudentRecursive(ArrayList<Student> list, int index) {
        if (index == list.size()) {
            return;
        } else {
            System.out.println(list.get(index));
            printStudentRecursive(list, index + 1);
        }
    }

    public Student searchByName(String name) {
        ArrayList<Student> list = waitingStudents.toArrayList();
        return searchByName(list, name, 0);
    }

    private Student searchByName(ArrayList<Student> list, String name, int index) {

        if (index == list.size()) return null;
        else if (list.get(index).getName().equalsIgnoreCase(name)) {
            return list.get(index);
        } else {
            return searchByName(list, name, index + 1);
        }
    }

    public int findHighestDifficulty() {
        ArrayList<Student> list = waitingStudents.toArrayList();

        if (list.isEmpty()) return 0;

        return findHighestDifficulty(list, 0);
    }

    private int findHighestDifficulty(ArrayList<Student> list, int index) {

        if (index == list.size() - 1) {
            return list.get(index).getDifficultyLevel();
        }

        int currentDifficulty = list.get(index).getDifficultyLevel();
        int highestFromRest = findHighestDifficulty(list, index + 1);

        return Math.max(currentDifficulty, highestFromRest);
    }
}