//Interface 1
interface Rental {
public void bookRental();
public void returnRental();
}

//Interface 2
interface Payment {
public void makePayment();
public int calculatePayment();
}

//Interface 3
interface VehicleDetails {
public String getVehicleDetails();
public void printVehicleDetails();
}

//Class 1
class Customer implements Rental, Payment {

@Override
public void bookRental() {
System.out.println("Customer books vehicle rental");
}

@Override
public void returnRental() {
System.out.println("Customer returns rented vehicle");
}

@Override
public void makePayment() {
System.out.println("Customer makes payment");
}

@Override
public int calculatePayment() {
System.out.println("Calculates payment amount");
return 0;
}

}

//Class 2
class Car implements VehicleDetails {

private String name;
private String model;

public Car(String name, String model) {
this.name = name;
this.model = model;
}

@Override
public String getVehicleDetails() {
return "Vehicle name: " + name + ", Model: " + model;
}

@Override
public void printVehicleDetails() {
System.out.println(getVehicleDetails());
}

}

public class Lab4 {
public static void main(String[] args) {
Customer customer = new Customer();
customer.bookRental();
customer.returnRental();
customer.makePayment();
customer.calculatePayment();

Car car = new Car("Maruti", "Swift");
car.printVehicleDetails();

}
}
