
class Car1 {
    private String make;
    private String model;
    private int year;
    private double rentalPricePerDay;
    private boolean isRented;
    private int mileage;

    // Default constructor
    public Car() {
        this.make = "Unknown";
        this.model = "Unknown";
        this.year = 0;
        this.rentalPricePerDay = 0.0;
        this.isRented = false;
        this.mileage = 0;
    }

    // Constructor overloading
    public Car(String make, String model, int year, double rentalPricePerDay) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalPricePerDay = rentalPricePerDay;
        this.isRented = false;
        this.mileage = 0;
    }

    // Methods
    public void rentCar() {
        if (!isRented) {
            isRented = true;
            System.out.println(model + " has been rented.");
        } else {
            System.out.println(model + " is already rented.");
        }
    }

    public void returnCar() {
        if (isRented) {
            isRented = false;
            System.out.println(model + " has been returned.");
        } else {
            System.out.println(model + " was not rented out.");
        }
    }

    // Method overloading based on mileage update
    public void updateMileage(int newMileage) {
        mileage = newMileage;
    }

    public void updateMileage(int newMileage, boolean serviced) {
        mileage = newMileage;
        if (serviced) {
            System.out.println(model + " has been serviced.");
        }
    }

    public String getModel() {
        return model;
    }

    // Main method
    public static void main(String[] args) {
        Sedan sedan = new Sedan("Honda", "Civic", 2019, 55.0, true);
        SUV suv = new SUV("Toyota", "Land Cruiser", 2020, 80.0, true);
        Truck truck = new Truck("Ford", "150", 2018, 75.0, 5000);

        sedan.displaySedanDetails();
        suv.displaySUVDetails();
        truck.displayTruckDetails();

        // Rent a car
        sedan.rentCar();
        // Return the car
        sedan.returnCar();
    }
}

// Subclass for Sedan
class Sedan extends Car {
    private boolean hasLeatherSeats;

    // Default constructor
    public Sedan() {
        super();
        this.hasLeatherSeats = false;
    }

    // Constructor overloading
    public Sedan(String make, String model, int year, double rentalPricePerDay, boolean hasLeatherSeats) {
        super(make, model, year, rentalPricePerDay);
        this.hasLeatherSeats = hasLeatherSeats;
    }

    //method specific to Sedan
    public void displaySedanDetails() {
        System.out.println("Sedan: " + getModel() + " with leather seats: " + hasLeatherSeats);
    }
}

// Subclass for SUV
class SUV extends Car {
    private boolean hasFourWheelDrive;

    // Constructor
    public SUV(String make, String model, int year, double rentalPricePerDay, boolean hasFourWheelDrive) {
        super(make, model, year, rentalPricePerDay);
        this.hasFourWheelDrive = hasFourWheelDrive;
    }

    //method specific to SUV
    public void displaySUVDetails() {
        System.out.println("SUV: " + getModel() + " with 4WD: " + hasFourWheelDrive);
    }
}

// Subclass for Truck
class Truck extends Car {
    private int cargoCapacity;

    // Constructor
    public Truck(String make, String model, int year, double rentalPricePerDay, int cargoCapacity) {
        super(make, model, year, rentalPricePerDay);
        this.cargoCapacity = cargoCapacity;
    }

    // method specific to Truck
    public void displayTruckDetails() {
        System.out.println("Truck: " + getModel() + " with cargo capacity: " + cargoCapacity + "kg");
    }
}

