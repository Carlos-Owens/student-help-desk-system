public class Tutor extends Person implements Reportable, Runnable {
    private final String specialty;
    private final HelpDesk helpDesk;

    public Tutor(String name, String tutorId, String specialty, HelpDesk helpDesk) {
        super(name, tutorId);
        this.specialty = specialty;
        this.helpDesk = helpDesk;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public void run() {

        // Continuously help students until the queue becomes empty
        while (true) {
            Student student = helpDesk.helpNextStudent();

            if (student == null) {
                System.out.println("Tutor has no more students.");
                break;
            }

            System.out.println(getName() + " is helping " + student.getName());

            try {

                // Simulate time spent helping the student
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Tutor was interrupted");
                break;
            }

            System.out.println(getName() + " finished helping " + student.getName());
        }
    }

    @Override
    public String getRole() {
        return "Tutor";
    }

    @Override
    public String toString() {
        return "Tutor name: " + getName()
                + "\nTutor Id: " + getId()
                + "\nSpecialty: " + specialty;
    }

    @Override
    public String generateReport() {
        return "Tutor Report\n"
                + "Name: " + getName()
                + "\nId: " + getId()
                + "\nSpecialty: " + specialty;
    }
}