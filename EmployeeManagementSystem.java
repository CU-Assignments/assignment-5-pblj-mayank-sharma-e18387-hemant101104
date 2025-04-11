import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("Employee ID: " + id + ", Name: " + name +
                ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employeeList = readEmployeesFromFile();

        while (true) {
            System.out.println("\nMenu:\n1. Add an Employee\n2. Display All Employees\n3. Exit");
            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Employee ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter Employee Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Designation: ");
                        String designation = scanner.nextLine();

                        System.out.print("Enter Salary: ");
                        double salary = Double.parseDouble(scanner.nextLine());

                        Employee emp = new Employee(id, name, designation, salary);
                        employeeList.add(emp);
                        saveEmployeesToFile(employeeList);
                        System.out.println("Employee added successfully!");
                    } catch (Exception e) {
                        System.out.println("Error adding employee: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (employeeList.isEmpty()) {
                        System.out.println("No employee records found.");
                    } else {
                        for (Employee emp : employeeList) {
                            emp.display();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting... 👋");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void saveEmployeesToFile(List<Employee> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving employees to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> readEmployeesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
