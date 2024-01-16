import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    private String name;
    private int age;

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Hospital {
    private Connection connection;

    public Hospital() {
        try {
            // Connect to the H2 in-memory database
            connection = DriverManager.getConnection("jdbc:h2:mem:hospital;DB_CLOSE_DELAY=-1");
            initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeDatabase() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Create the Patient table if it doesn't exist
            statement.execute("CREATE TABLE IF NOT EXISTS Patients (name VARCHAR(255), age INT)");
        }
    }

    public void addPatient(Patient patient) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Patients VALUES (?, ?)")) {
            // Set the values for the prepared statement
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            // Execute the SQL update
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            // Execute the SELECT query to retrieve all patients
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Patients");
            // Process the result set and populate the ArrayList
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                patients.add(new Patient(name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void closeConnection() {
        try {
            // Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


