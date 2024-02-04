class CarRentalSystem {
    private static final String[] CAR_TYPES = {"Maruthi", "Tata", "Kia"};
    private static final double[] CAR_PRICES = {50.0, 80.0, 120.0};
    private static final double[] CAR_DISCOUNTS = {0.1, 0.15, 0.2};

    public synchronized void rentCar(String carType) {
        System.out.println("Renting car: " + carType);

        // Generate bill
        double price = calculatePrice(carType);

        // Apply discount
        applyDiscount(carType);

        // Final price after discount
        price -= (price * calculateDiscount(carType));

        System.out.println("Bill generated for " + carType + ": " + price);
    }

    private double calculatePrice(String carType) {
        for (int i = 0; i < CAR_TYPES.length; i++) {
            if (CAR_TYPES[i].equals(carType)) {
                return CAR_PRICES[i];
            }
        }
        return 0.0; 
    }

    private double calculateDiscount(String carType) {
        for (int i = 0; i < CAR_TYPES.length; i++) {
            if (CAR_TYPES[i].equals(carType)) {
                return CAR_DISCOUNTS[i];
            }
        }
        return 0.0; 
    }

    public void applyDiscount(String carType) {
        double discount = calculateDiscount(carType);

        System.out.println("Discount applied for " + carType + ": " + discount * 100 + "%");
    }
}

class Customer implements Runnable {
    private String carType;
    private CarRentalSystem carRentalSystem;

    public Customer(String carType, CarRentalSystem carRentalSystem) {
        this.carType = carType;
        this.carRentalSystem = carRentalSystem;
    }

    public void run() {
        carRentalSystem.rentCar(carType);
    }
}

public class CarRentalApp1 {
    public static void main(String[] args) {
        CarRentalSystem carRentalSystem = new CarRentalSystem();

        Customer customer1 = new Customer("Maruthi", carRentalSystem);
        Customer customer2 = new Customer("Tata", carRentalSystem);
        Customer customer3 = new Customer("Kia", carRentalSystem);

        
        Thread thread1 = new Thread(customer1);
        Thread thread2 = new Thread(customer2);
        Thread thread3 = new Thread(customer3);

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
