import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ContactManagerApp extends JFrame {

    private JTextField nameField, emailField, phoneField;
    private DefaultTableModel tableModel;
    private JTable contactTable;

    private Connection connection;
    private Statement statement;

    public ContactManagerApp() {
        super("Contact Manager");

        // database connection
        connectToDatabase();

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");

        tableModel = new DefaultTableModel();
        contactTable = new JTable(tableModel);

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");

        retrieveContacts();

        
        JScrollPane scrollPane = new JScrollPane(contactTable);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(addButton);
        formPanel.add(updateButton);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);


        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContact();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchContact();
            }
        });

    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/contact_manager";
            String username = "";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void addContact() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        try {
            
            String insertQuery = "INSERT INTO contacts (name, email, phone) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, phone);
                preparedStatement.executeUpdate();
            }

            Vector<Object> rowData = new Vector<>();
            rowData.add(getLastInsertedId());
            rowData.add(name);
            rowData.add(email);
            rowData.add(phone);
            tableModel.addRow(rowData);

            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add contact.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getLastInsertedId() throws SQLException {
        try (ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()")) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    private void retrieveContacts() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");

            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getInt("id"));
                rowData.add(resultSet.getString("name"));
                rowData.add(resultSet.getString("email"));
                rowData.add(resultSet.getString("phone"));
                tableModel.addRow(rowData);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve contacts.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateContact() {
        int selectedRow = contactTable.getSelectedRow();

        if (selectedRow != -1) {
            int contactId = (int) tableModel.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            try {
                String updateQuery = "UPDATE contacts SET name=?, email=?, phone=? WHERE id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, phone);
                    preparedStatement.setInt(4, contactId);
                    preparedStatement.executeUpdate();
                }

                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(email, selectedRow, 2);
                tableModel.setValueAt(phone, selectedRow, 3);

            
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update contact.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a contact to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteContact() {
        int selectedRow = contactTable.getSelectedRow();

        if (selectedRow != -1) {
            int contactId = (int) tableModel.getValueAt(selectedRow, 0);

            try {
                
                String deleteQuery = "DELETE FROM contacts WHERE id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, contactId);
                    preparedStatement.executeUpdate();
                }

                tableModel.removeRow(selectedRow);


                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to delete contact.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchContact() {
        String searchText = JOptionPane.showInputDialog(this, "Enter name or email to search:");

        try {
            String searchQuery = "SELECT * FROM contacts WHERE name LIKE ? OR email LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
                preparedStatement.setString(1, "%" + searchText + "%");
                preparedStatement.setString(2, "%" + searchText + "%");

                ResultSet resultSet = preparedStatement.executeQuery();

               
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(resultSet.getInt("id"));
                    rowData.add(resultSet.getString("name"));
                    rowData.add(resultSet.getString("email"));
                    rowData.add(resultSet.getString("phone"));
                    tableModel.addRow(rowData);
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to search for contacts.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManagerApp();
            }
        });
    }
}

