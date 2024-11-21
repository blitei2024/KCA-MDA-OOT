import java.util.ArrayList;
import java.util.List;

// Class representing a Professor
class Professor {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private double salary;

    public Professor(String name, String address, String phoneNumber, String email, double salary) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
    }

    // Operation to get contact information
    public String getContactInfo() {
        return "Address: " + address + ", Phone: " + phoneNumber + ", Email: " + email;
    }

    // Getter for professor's name
    public String getName() {
        return name;
    }
}

// Class representing a Student
class Student {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private double averageMark;
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Operation to enroll in a seminar
    public void enrollInSeminar(Seminar seminar) {
        Enrollment enrollment = new Enrollment(this, seminar);
        enrollments.add(enrollment);
        seminar.addStudent(enrollment);
    }

    // Operation to withdraw from a seminar (only for master seminars)
    public void withdrawFromSeminar(Seminar seminar) {
        if (seminar.getType().equals("Master")) {
            enrollments.removeIf(enrollment -> enrollment.getSeminar() == seminar);
            seminar.removeStudent(this);
        } else {
            System.out.println("Cannot withdraw from bachelor seminars.");
        }
    }

    // Operation to calculate average mark
    public void calculateAverageMark() {
        double totalMarks = 0;
        int count = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getFinalMark() > 0) {
                totalMarks += enrollment.getFinalMark();
                count++;
            }
        }
        this.averageMark = count > 0 ? totalMarks / count : 0;
    }

    // Operation to get the list of enrolled seminars
    public List<Seminar> getEnrolledSeminars() {
        List<Seminar> seminars = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            seminars.add(enrollment.getSeminar());
        }
        return seminars;
    }

    // Getter for student's name
    public String getName() {
        return name;
    }

    // Getter for student's average mark
    public double getAverageMark() {
        return averageMark;
    }
}

// Class representing a Seminar
class Seminar {
    private String name;
    private String number;
    private String type; // Bachelor or Master
    private List<Professor> professors = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();

    public Seminar(String name, String number, String type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }

    // Operation to add a professor to the seminar
    public void addProfessor(Professor professor) {
        if (professors.size() < 3) {
            professors.add(professor);
        } else {
            System.out.println("Maximum number of professors reached.");
        }
    }

    // Operation to add a student to the seminar
    public void addStudent(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    // Operation to remove a student from the seminar
    public void removeStudent(Student student) {
        enrollments.removeIf(enrollment -> enrollment.getStudent() == student);
    }

    // Getter for seminar type
    public String getType() {
        return type;
    }

    // Getter for seminar details
    public String getSeminarDetails() {
        String profNames = "";
        for (Professor prof : professors) {
            profNames += prof.getName() + " ";
        }
        return "Seminar Name: " + name + ", Number: " + number + ", Type: " + type + ", Professors: " + profNames;
    }

    // Public method to get enrollments
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}

// Class representing an Enrollment
class Enrollment {
    private Student student;
    private Seminar seminar;
    private double currentMark;
    private double finalMark;

    public Enrollment(Student student, Seminar seminar) {
        this.student = student;
        this.seminar = seminar;
    }

    // Operation to get current mark
    public double getCurrentMark() {
        return currentMark;
    }

    // Operation to set current mark
    public void setCurrentMark(double currentMark) {
        this.currentMark = currentMark;
    }

    // Operation to get final mark
    public double getFinalMark() {
        return finalMark;
    }

    // Operation to set final mark
    public void setFinalMark(double finalMark) {
        this.finalMark = finalMark;
    }

    // Getter for seminar
    public Seminar getSeminar() {
        return seminar;
    }

    // Getter for student
    public Student getStudent() {
        return student;
    }
}

// Main class to test the implementation
public class UniversitySystem {
    public static void main(String[] args) {
        // Creating professors
        Professor prof1 = new Professor("Prof. Smith", "123 Main St", "555-1234", "smith@example.com", 75000);
        Professor prof2 = new Professor("Prof. Johnson", "456 Elm St", "555-5678", "johnson@example.com", 80000);

        // Creating seminars
        Seminar seminar1 = new Seminar("Introduction to Programming", "101", "Bachelor");
        Seminar seminar2 = new Seminar("Advanced Algorithms", "202", "Master");

        // Adding professors to seminars
        seminar1.addProfessor(prof1);
        seminar2.addProfessor(prof2);

        // Creating students
        Student student1 = new Student("Alice", "789 Oak St", "555-8765", "alice@example.com");
        Student student2 = new Student("Bob", "101 Maple St", "555-4321", "bob@example.com");

        // Students enrolling in seminars
        student1.enrollInSeminar(seminar1);
        student1.enrollInSeminar(seminar2);
        student2.enrollInSeminar(seminar2);

        // Recording marks for students
        for (Enrollment enrollment : seminar1.getEnrollments()) {
            if (enrollment.getStudent() == student1) {
                enrollment.setFinalMark(85);
            }
        }

        student1.calculateAverageMark();
        System.out.println(student1.getName() + "'s average mark: " + student1.getAverageMark());

        // Display seminar details
        System.out.println(seminar1.getSeminarDetails());
        System.out.println(seminar2.getSeminarDetails());
    }
}
