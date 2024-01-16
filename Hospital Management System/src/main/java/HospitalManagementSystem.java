import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

        while (true) {
            System.out.println("1. Add Patient");
            System.out.println("2. Display Patients");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();


            switch (choice) {
                //The first case gathers the patients information(more questions can be added)
                case 1:
                    System.out.print("Enter patient name: ");
                    String name = scanner.next();
                    System.out.print("Enter patient age: ");
                    int age = scanner.nextInt();

                    Patient patient = new Patient(name, age);
                    hospital.addPatient(patient);
                    System.out.println("A patient has been added successfully!");
                    break;

                    //The second case displays any saved patients
                case 2:
                    System.out.println("List of Patients:");
                    ArrayList<Patient> patients = hospital.getAllPatients();
                    for (Patient p : patients) {
                        System.out.println(p);
                    }
                    break;

                    //The third case stops and exits the program
                case 3:
                    System.out.println("Exiting in progress...");
                    hospital.closeConnection();
                    System.exit(0);
                    break;

                    //This is displayed whenever a user enters an invalid value
                default:
                    System.out.println("You've entered an invalid choice; please try again.");
            }
        }
    }
}