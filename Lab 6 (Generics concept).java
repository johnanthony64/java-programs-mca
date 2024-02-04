// Defining a Rentable interface
interface Rentable {
    void rentDetails();
}

// Defining a generic class Car
class Car<T, U, V, W> implements Rentable {
    private T carId;
    private U model;
    private V available;
    private W rentalPrice;

    public Car(T carId, U model, V available, W rentalPrice) {
        this.carId = carId;
        this.model = model;
        this.available = available;
        this.rentalPrice = rentalPrice;
    }

    public T getCarId() {
        return carId;
    }

    public U getModel() {
        return model;
    }

    public V getAvailable() {
        return available;
    }

    public W getRentalPrice() {
        return rentalPrice;
    }

    @Override
    public void rentDetails() {
        System.out.println("Car ID: " + carId + ", Model: " + model + ", Available: " + available + ", Rental Price: " + rentalPrice);
    }
}

// Defining a CarRentalStore class using generics
class CarRentalStore<T, U, V, W> {
    private Car<T, U, V, W> car;

    public CarRentalStore(Car<T, U, V, W> car) {
        this.car = car;
    }

    public void displayCarDetails() {
        System.out.println("Car Rental Store Details:");
        car.rentDetails();
    }
}

public class Lab6 {
    public static void main(String[] args) {
        // Creating instances of Cars
        Car<Integer, String, Boolean, Double> car1 = new Car<>(1, "Maruti Suzuki Swift", true, 1499.0);
        Car<Integer, String, Boolean, Double> car2 = new Car<>(2, "Tata Nexon", false, 2999.0);

        // Creating instances of CarRentalStore
        CarRentalStore<Integer, String, Boolean, Double> rentalStore1 = new CarRentalStore<>(car1);
        CarRentalStore<Integer, String, Boolean, Double> rentalStore2 = new CarRentalStore<>(car2);

        // Display car details using CarRentalStore
        rentalStore1.displayCarDetails();
        rentalStore2.displayCarDetails();
    }
}
