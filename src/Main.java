import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {

    // Continuously prompt the user until a valid integer is entered
    public static int readInt(Scanner input, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int number = input.nextInt();
                input.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    // Validate all student fields before creating the object
    public static void validateStudentData(String name, String id, String topic, int difficulty)
            throws InvalidStudentDataException {

        if (name.isBlank()) {
            throw new InvalidStudentDataException("Student name can't be blank.");
        }

        if (id.isBlank()) {
            throw new InvalidStudentDataException("Student Id can't be blank.");
        }

        if (topic.isBlank()) {
            throw new InvalidStudentDataException("Topic can't be blank.");
        }

        if (difficulty < 1 || difficulty > 5) {
            throw new InvalidStudentDataException("Difficulty must be from 1-5.");
        }
    }

    public static void displayMenu() {
        System.out.println("\n[KSU Help Desk Simulator]");
        System.out.println("1. Add student");
        System.out.println("2. View waiting students");
        System.out.println("3. Help next student");
        System.out.println("4. View stats");
        System.out.println("5. Recursion tools");
        System.out.println("6. Save students to file");
        System.out.println("7. Load students from file");
        System.out.println("8. Start tutor threads");
        System.out.println("9: View next student");
        System.out.println("10: Exit");
    }

    public static void displayRecursionMenu(Scanner input, HelpDesk helpDesk) {
        boolean running = true;

        while (running) {
            System.out.println("[Recursive Tools]");
            System.out.println("1. Count students recursively");
            System.out.println("2. Print students recursively");
            System.out.println("3. Search student by name recursively");
            System.out.println("4. Find highest difficulty recursively");
            System.out.println("5. Back");

            int option = readInt(input, "Choose an option: ");

            switch (option) {
                case 1:
                    System.out.println(helpDesk.countStudentRecursive());
                    break;

                case 2:
                    helpDesk.printStudentRecursive();
                    break;

                case 3:
                    System.out.print("What is the name of the student? ");
                    String name = input.nextLine();

                    Student student = helpDesk.searchByName(name);

                    if (student == null) {
                        System.out.println("No student found.");
                        break;
                    } else {
                        System.out.println(student);
                    }
                    break;

                case 4:
                    System.out.println(helpDesk.findHighestDifficulty());
                    break;

                case 5:
                    System.out.println("\nGoing back to main menu...\n");
                    running = false;
                    break;

                default:
                    System.out.println("\nPlease pick options 1-5\n");
                    break;
            }
        }
    }

    public static void addStudent(Scanner input, HelpDesk helpDesk) {
        System.out.print("What student would you like to add? ");
        String studentName = input.nextLine();

        System.out.print("\nWhat is the student's Id? ");
        String studentId = input.nextLine();

        System.out.print("\nWhat is the student's topic of study? ");
        String studentTopic = input.nextLine();

        int difficulty = readInt(input, "\nWhat is the topic's difficulty level (1-5)? ");

        try {
            validateStudentData(studentName, studentId, studentTopic, difficulty);

            Student student = new Student(studentName, studentId, studentTopic, difficulty);
            helpDesk.addStudent(student);

            System.out.println(student.getName() + " added.");
        } catch (InvalidStudentDataException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Returning to main menu...");
        }
    }

    public static void displayStudents(HelpDesk helpDesk) {
        if (!helpDesk.hasWaitingStudents()) {
            System.out.println("No students are waiting.");
        } else {
            helpDesk.displayWaitingStudent();
        }
    }

    public static void displayHelpResult(Student student) {
        if (student == null) {
            System.out.println("No students available to help.");
        } else {
            System.out.println("Helping "
                    + student.getName() + " with topic "
                    + student.getTopic());

            System.out.println("Student helped and removed from the waiting list.");
        }
    }

    public static void displayStats(HelpDesk helpDesk) {
        System.out.println("Students waiting: " + helpDesk.getWaitingCount());

        if (helpDesk.hasWaitingStudents()) {
            System.out.printf("Average difficulty: %.2f%n", helpDesk.getAverageDifficulty());
        } else {
            System.out.println("Average difficulty: N/A");
        }
    }

    // Save all waiting students to a text file
    public static void saveStudentsToFile(HelpDesk helpDesk) {
        if (!helpDesk.hasWaitingStudents()) {
            System.out.println("No students to save.");
            return;
        }

        try {
            PrintWriter studentWriter = new PrintWriter("students.txt");

            for (Student student : helpDesk.getWaitingStudents()) {
                studentWriter.println(String.format("%s,%s,%s,%d", student.getName(), student.getId(),
                        student.getTopic(), student.getDifficultyLevel()));
            }

            studentWriter.close();
            System.out.println("Saved students to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file 'students.txt'");
        }
    }

    // Clear existing queue before loading saved students
    public static void loadStudentsFromFile(HelpDesk helpDesk) {
        Scanner userInput = null;

        try {
            File studentsFile = new File("students.txt");
            userInput = new Scanner(studentsFile);

            helpDesk.clearStudents();
            while (userInput.hasNextLine()) {
                String line = userInput.nextLine();
                String[] parts = line.split(",");

                if (parts.length != 4) continue;

                String name = parts[0];
                String id = parts[1];
                String topic = parts[2];
                int difficulty = Integer.parseInt(parts[3]);

                Student student = new Student(name, id, topic, difficulty);
                helpDesk.addStudent(student);
            }

            System.out.println("Students loaded from 'students.txt' file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file 'students.txt'");
        } catch (NumberFormatException e) {
            System.out.println("Difficulty was not a number.");
        } finally {
            if (userInput != null) {
                userInput.close();
            }
        }
    }

    public static void appendHelpedStudent(Student student) {
        if (student == null) {
            return;
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter("helped_students.txt", true));

            writer.println(student.getName() + "," + student.getId() + ","
                    + student.getTopic() + ","
                    + student.getDifficultyLevel());

        } catch (IOException e) {
            System.out.println("Error writing to file 'helped_students.txt'");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // Create and start multiple tutor worker threads
    public static void startTutorThreads(Scanner input, HelpDesk helpDesk) {
        int tutorCount = readInt(input, "How many tutors should work? ");

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= tutorCount; i++) {
            Tutor tutor = new Tutor("Tutor " + i, "T" + i, "Java", helpDesk);
            Thread thread = new Thread(tutor);

            threads.add(thread);

            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        HelpDesk helpDesk = new HelpDesk();

        boolean running = true;

        while (running) {
            displayMenu();
            int option = readInt(input, "Choose an option: ");

            System.out.println();
            switch (option) {
                case 1:
                    addStudent(input, helpDesk);
                    break;

                case 2:
                    System.out.println("Viewing students");
                    displayStudents(helpDesk);
                    break;

                case 3:
                    System.out.println("Helping the next student...");
                    Student helpedStudent = helpDesk.helpNextStudent();
                    displayHelpResult(helpedStudent);
                    appendHelpedStudent(helpedStudent);
                    break;

                case 4:
                    displayStats(helpDesk);
                    break;

                case 5:
                    displayRecursionMenu(input, helpDesk);
                    break;

                case 6:
                    saveStudentsToFile(helpDesk);
                    break;

                case 7:
                    loadStudentsFromFile(helpDesk);
                    break;

                case 8:
                    startTutorThreads(input, helpDesk);
                    break;

                case 9:
                    Student nextStudent = helpDesk.peekNextStudent();

                    if (nextStudent == null) {
                        System.out.println("No students are waiting.");
                    } else {
                        System.out.print("Next Student: ");
                        System.out.println(nextStudent);
                    }

                    break;

                case 10:
                    System.out.println("Exiting, goodbye...");
                    running = false;
                    break;

                default:
                    System.out.println("Please choose options 1-9");
                    break;
            }
        }
        input.close();
    }
}