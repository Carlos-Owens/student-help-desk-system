
# Student Help Desk System

A Java console-based help desk simulator that manages a tutoring waitlist using custom data structures, recursion, multithreading, file I/O, and object-oriented programming principles.

## Overview

This project simulates a university tutoring/help desk system where students join a waiting queue to receive help from tutors.

The project was designed to practice and demonstrate:

* Custom linked-node queue implementation
* Object-oriented programming
* Recursion
* File I/O
* Exception handling
* Multithreading
* Interfaces and abstraction
* Menu-driven application design

Instead of relying entirely on Java's built-in collections, the queue was implemented manually using custom `Node` objects.

---

# Features

## Student Queue Management

* Add students to the waiting queue
* Help/remove the next student (FIFO behavior)
* View all waiting students
* Peek at the next student without removing them
* Track total waiting students
* Calculate average difficulty level

---

## Custom Linked Queue

Implemented from scratch using:

* `Node` class
* `CustomStudentQueue` class

Queue operations include:

* `offer()`
* `poll()`
* `peek()`
* `displayQueue()`
* `isEmpty()`
* `getSize()`
* `clear()`

The queue uses linked nodes rather than Java's built-in `Queue` implementation.

---

## Recursion Tools

Recursive algorithms include:

* Count students recursively
* Print students recursively
* Search students recursively
* Find highest difficulty recursively

---

## File I/O

The program supports:

* Saving students to `students.txt`
* Loading students from `students.txt`
* Logging helped students to `helped_students.txt`

---

## Multithreading

The system supports multiple tutor threads working simultaneously.

Tutors:

* Run on separate threads
* Help students concurrently
* Safely access the shared queue using synchronization

---

# Technologies Used

* Java
* Object-Oriented Programming
* Linked Data Structures
* Recursion
* File I/O
* Threads / Concurrency
* Exception Handling

---

# Project Structure

```text
src/
 ├── Main.java
 ├── HelpDesk.java
 ├── CustomStudentQueue.java
 ├── Node.java
 ├── Student.java
 ├── Tutor.java
 ├── Person.java
 ├── Reportable.java
 └── InvalidStudentDataException.java
```

---

# Example Queue Structure

```text
front
 ↓
[Student 1] -> [Student 2] -> [Student 3] -> null
                                        ↑
                                        rear
```

---

# Example Menu

```text
[KSU Help Desk Simulator]
1. Add student
2. View waiting students
3. Help next student
4. View stats
5. Recursion tools
6. Save students to file
7. Load students from file
8. Start tutor threads
9. View next student
10. Exit
```

---

# Concepts Demonstrated

This project demonstrates understanding of:

* FIFO queue behavior
* Linked node traversal
* Recursive problem solving
* Thread synchronization
* Encapsulation
* Abstraction
* Interfaces
* Inheritance
* Exception handling
* File management

---

# Future Improvements

Potential future upgrades:

* GUI version
* Database integration
* Priority queue by difficulty
* Undo functionality using a stack
* Direct recursive node traversal
* Networking support

---

# Author

Carlos Owens
Computer Science Student at Kennesaw State University

Menu driven application design