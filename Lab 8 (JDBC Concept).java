import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarRentalSystem {

    static final String DB_URL = "jdbc:mysql://localhost:3306/carrental";
    static final String USER = "";
    static final String PASSWORD = "";

    public static void main(String[] args) {
        createDatabase();
        createTables();
        insertData();
        retrieveData();
        updateData();
        retrieveData();
        deleteData();
        retrieveData();
    }

    private static void createDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS carrental";
            stmt.executeUpdate(sql);
            System.out.println("Database Created Successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables() {
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS customers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50)," +
                "email VARCHAR(50)" +
                ")";
        String createCarTable = "CREATE TABLE IF NOT EXISTS cars (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "brand VARCHAR(50)," +
                "model VARCHAR(50)," +
                "year INT" +
                ")";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createCustomerTable);
            stmt.executeUpdate(createCarTable);
            System.out.println("Tables Created Successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Insert customers
            String insertCustomer = "INSERT INTO customers (name, email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertCustomer)) {
                pstmt.setString(1, "Rahul Sharma");
                pstmt.setString(2, "rahul@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Priya Kapoor");
                pstmt.setString(2, "priya@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Amit Patel");
                pstmt.setString(2, "amit@gmail.com");
                pstmt.executeUpdate();
                pstmt.setString(1, "Sunita Verma");
                pstmt.setString(2, "sunita@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Vikram Singh");
                pstmt.setString(2, "vikram@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Anita Joshi");
                pstmt.setString(2, "anita@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Rajiv Kapoor");
                pstmt.setString(2, "rajiv@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Meera Gupta");
                pstmt.setString(2, "meera@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Kiran Sharma");
                pstmt.setString(2, "kiran@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Sanjay Mehra");
                pstmt.setString(2, "sanjay@gmail.com");
                pstmt.executeUpdate();
    
                pstmt.setString(1, "Deepa Singh");
                pstmt.setString(2, "deepa@gmail.com");
                pstmt.executeUpdate();
    
                // Insert cars
                String insertCar = "INSERT INTO cars (brand, model, year) VALUES (?, ?, ?)";
                try (PreparedStatement pstmtCar = conn.prepareStatement(insertCar)) {
                    pstmtCar.setString(1, "Maruti");
                    pstmtCar.setString(2, "Swift");
                    pstmtCar.setInt(3, 2022);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Hyundai");
                    pstmtCar.setString(2, "Creta");
                    pstmtCar.setInt(3, 2021);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Tata");
                    pstmtCar.setString(2, "Harrier");
                    pstmtCar.setInt(3, 2020);
                    pstmtCar.executeUpdate();
    
                    // Add 2 more cars with Indian names
                    pstmtCar.setString(1, "Mahindra");
                    pstmtCar.setString(2, "XUV500");
                    pstmtCar.setInt(3, 2023);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Honda");
                    pstmtCar.setString(2, "City");
                    pstmtCar.setInt(3, 2022);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Maruti");
                    pstmtCar.setString(2, "Baleno");
                    pstmtCar.setInt(3, 2021);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Toyota");
                    pstmtCar.setString(2, "Innova");
                    pstmtCar.setInt(3, 2022);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Nissan");
                    pstmtCar.setString(2, "Kicks");
                    pstmtCar.setInt(3, 2020);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Volkswagen");
                    pstmtCar.setString(2, "Polo");
                    pstmtCar.setInt(3, 2023);
                    pstmtCar.executeUpdate();
    
                    pstmtCar.setString(1, "Ford");
                    pstmtCar.setString(2, "EcoSport");
                    pstmtCar.setInt(3, 2021);
                    pstmtCar.executeUpdate();
    
                    System.out.println("Data Inserted Successfully...");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void retrieveData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Retrieve customers
            String retrieveCustomers = "SELECT * FROM customers";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(retrieveCustomers)) {
                System.out.println("\nCustomers:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Email: " + rs.getString("email"));
                }
            }

            // Retrieve cars
            String retrieveCars = "SELECT * FROM cars";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(retrieveCars)) {
                System.out.println("\nCars:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") +
                            ", Brand: " + rs.getString("brand") +
                            ", Model: " + rs.getString("model") +
                            ", Year: " + rs.getInt("year"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Update customer
            String updateCustomer = "UPDATE customers SET email = ? WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateCustomer)) {
                pstmt.setString(1, "rahul.sharma@gmail.com");
                pstmt.setString(2, "Rahul Sharma");
                pstmt.executeUpdate();

                // Update car
                String updateCar = "UPDATE cars SET year = ? WHERE brand = ?";
                try (PreparedStatement pstmtCar = conn.prepareStatement(updateCar)) {
                    pstmtCar.setInt(1, 2021);
                    pstmtCar.setString(2, "Maruti");
                    pstmtCar.executeUpdate();
                }

                System.out.println("Data Updated Successfully...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Delete customer
            String deleteCustomer = "DELETE FROM customers WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteCustomer)) {
                pstmt.setString(1, "Amit Patel");
                pstmt.executeUpdate();

                // Delete car
                String deleteCar = "DELETE FROM cars WHERE brand = ?";
                try (PreparedStatement pstmtCar = conn.prepareStatement(deleteCar)) {
                    pstmtCar.setString(1, "Hyundai");
                    pstmtCar.executeUpdate();
                }

                System.out.println("Data Deleted Successfully...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
