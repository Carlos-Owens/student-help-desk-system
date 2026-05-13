public class Student extends Person implements Reportable {
    private final String topic;
    private final int difficultyLevel;

    public Student(String name, String studentId, String topic, int difficultyLevel) {
        super(name, studentId);
        this.topic = topic;
        this.difficultyLevel = difficultyLevel;
    }

    public String getTopic() {
        return topic;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student name: " + getName()
                + "\nStudent Id: " + getId()
                + "\nTopic: " + topic
                + "\nDifficulty: " + difficultyLevel;
    }

    @Override
    public String generateReport() {
        return "Student Report\n"
                + "Name: " + getName()
                + "\nId: " + getId()
                + "\nTopic: " + topic
                + "\nDifficulty: " + difficultyLevel;
    }
}