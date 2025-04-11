import java.io.*;

// Student class implementing Serializable
class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    // Constructor
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Display method
    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student GPA: " + gpa);
    }
}

public class StudentSerializationDemo {
    public static void main(String[] args) {
        Student student = new Student(101, "John Doe", 3.8);
        String filename = "student.ser";

        // Serialize the student object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(student);
            System.out.println("Student details saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }

        System.out.println("\nReading from file...");

        // Deserialize the student object
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student savedStudent = (Student) ois.readObject();
            savedStudent.display();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}
