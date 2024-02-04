public class CarRentalSystem {
    // Outer class attributes
    private StringBuffer rentalCompany;

    // Outer class constructor
    public CarRentalSystem(StringBuffer rentalCompany) {
        this.rentalCompany = rentalCompany;
    }

    // Inner class
    public class Car {
        private String make;
        private String model;
        private int year;

        // Inner class constructor
        public Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        // Inner class method
        public StringBuffer getCarInfo() {
            StringBuffer carInfo = new StringBuffer();
            carInfo.append(year).append(" ").append(make).append(" ").append(model);
            return carInfo;
        }
    }

    // Nested class for RentalContract
    public class RentalContract {
        private int contractNumber;
        private Car rentedCar;
        private StringBuffer renterName;

        // Nested class constructor
        public RentalContract(int contractNumber, Car rentedCar, StringBuffer renterName) {
            this.contractNumber = contractNumber;
            this.rentedCar = rentedCar;
            this.renterName = renterName;
        }

        // Nested class method
        public StringBuffer getContractDetails() {
            StringBuffer contractDetails = new StringBuffer();
            contractDetails.append("Contract Number: ").append(contractNumber)
                    .append("\nRenter: ").append(renterName)
                    .append("\nCar: ").append(rentedCar.getCarInfo());
            return contractDetails;
        }
    }

    public static void main(String[] args) {
        
        CarRentalSystem rentalSystem = new CarRentalSystem(new StringBuffer("Car Rentals"));
        CarRentalSystem.Car car = rentalSystem.new Car("Tesla", "S", 2023);
        CarRentalSystem.RentalContract contract = rentalSystem.new RentalContract(101, car, new StringBuffer("Ravi Anna"));
        System.out.println(rentalSystem.rentalCompany + " Rental Contract:\n" + contract.getContractDetails());
    }
}

