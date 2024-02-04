import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Car {
    private String model;
    private String brand;
    private double rentalPrice;

    public Car(String model, String brand, double rentalPrice) {
        this.model = model;
        this.brand = brand;
        this.rentalPrice = rentalPrice;
    }

    @Override
    public String toString() {
        return "Model: " + model + ", Brand: " + brand + ", Rental Price: " + rentalPrice;
    }
}

class CarRentalGUI extends JFrame {
    private List<Car> cars;

    private JTextField modelField;
    private JTextField brandField;
    private JTextField rentalPriceField;
    private JTextArea displayArea;

    public CarRentalGUI() {
        super("Car Rental System");

        cars = new ArrayList<>();

        setLayout(new FlowLayout());

        modelField = new JTextField(20);
        brandField = new JTextField(20);
        rentalPriceField = new JTextField(10);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JButton addButton = new JButton("Add Car");
        JButton displayButton = new JButton("Display Cars");
        JButton exitButton = new JButton("Exit");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCar();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCars();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        
        add(new JLabel("Brand: "));
        add(brandField);
        add(new JLabel("Model: "));
        add(modelField);
        add(new JLabel("Rental Price: "));
        add(rentalPriceField);
        add(addButton);
        add(displayButton);
        add(exitButton);
        add(new JScrollPane(displayArea));

        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addCar() {
        String model = modelField.getText();
        String brand = brandField.getText();
        double rentalPrice = Double.parseDouble(rentalPriceField.getText());

        Car newCar = new Car(model, brand, rentalPrice);
        cars.add(newCar);

        modelField.setText("");
        brandField.setText("");
        rentalPriceField.setText("");

        JOptionPane.showMessageDialog(this, "Car added successfully!");
    }

    private void displayCars() {
        displayArea.setText("");
        if (cars.isEmpty()) {
            displayArea.setText("No cars available.");
        } else {
            for (Car car : cars) {
                displayArea.append(car.toString() + "\n");
            }
        }
    }
}

public class Lab9 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CarRentalGUI();
            }
        });
    }
}
