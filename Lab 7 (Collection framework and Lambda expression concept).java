import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface RentalInvoiceGenerator {
    double generateRentalInvoice(List<Car> cars);
}

interface CarAvailabilityChecker {
    boolean isCarAvailable(List<Car> cars, String model);
}

class Car {
    private String model;
    private String brand;
    private double rentalRate;

    public Car(String model, String brand, double rentalRate) {
        this.model = model;
        this.brand = brand;
        this.rentalRate = rentalRate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public double getRentalRate() {
        return rentalRate;
    }
}

public class lab7 {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Swift", "Maruti", 6000.0));
        cars.add(new Car("Creta", "Hyundai", 9000.0));
        cars.add(new Car("City", "Honda", 7500.0));
        cars.add(new Car("Innova", "Toyota", 11000.0));

        // Filtering cars based on a condition using Lambda expression
        List<Car> availableCars = cars.stream()
                .filter(car -> car.getRentalRate() < 10000.0)
                .collect(Collectors.toList());

        // Printing the available cars
        for (Car car : availableCars) {
            System.out.println(
                    "Model: " + car.getModel() + ", Brand: " + car.getBrand() + ", Rental Rate: ₹" + car.getRentalRate());
        }

        // Lambda function to generate rental invoice
        RentalInvoiceGenerator invoiceGenerator = (List<Car> rentalList) -> rentalList.stream().mapToDouble(Car::getRentalRate).sum();
        double totalInvoice = invoiceGenerator.generateRentalInvoice(availableCars);
        System.out.println("Total Rental Invoice: ₹" + totalInvoice);

        // Lambda function to check car availability
        CarAvailabilityChecker availabilityChecker = (List<Car> carList, String model) -> carList.stream()
                .anyMatch(car -> car.getModel().equalsIgnoreCase(model));

        String carToCheck = "Dzire";
        boolean isAvailable = availabilityChecker.isCarAvailable(cars, carToCheck);
        System.out.println(carToCheck + " is available: " + isAvailable);
    }
}
