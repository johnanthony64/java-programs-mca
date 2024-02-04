import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Advertisement {
    private static int nextId = 1;
    protected int advertisementId;
    protected String clientName;
    protected String startDate;

    public Advertisement(String clientName, String startDate) {
        this.advertisementId = nextId++;
        this.clientName = clientName;
        this.startDate = startDate;
    }
}

class CommercialAdd extends Advertisement {
    private double sizeOfAdd;
    private double pricePerCm;

    public CommercialAdd(String clientName, String startDate, double sizeOfAdd, double pricePerCm) {
        super(clientName, startDate);
        this.sizeOfAdd = sizeOfAdd;
        this.pricePerCm = pricePerCm;
    }

    public double addCost() {
        return sizeOfAdd * pricePerCm;
    }

  
    public double getSizeOfAdd() {
        return sizeOfAdd;
    }

    public double getPricePerCm() {
        return pricePerCm;
    }
}

class FreeAdd extends Advertisement {
    private int freeAddTimeDuration;

    public FreeAdd(String clientName, String startDate, int freeAddTimeDuration) {
        super(clientName, startDate);
        this.freeAddTimeDuration = freeAddTimeDuration;
    }

   
    public int getFreeAddTimeDuration() {
        return freeAddTimeDuration;
    }
}

public class AdvertisementManagement {
    private List<CommercialAdd> commercialAdds;
    private List<FreeAdd> freeAdds;

    public AdvertisementManagement() {
        commercialAdds = new ArrayList<>();
        freeAdds = new ArrayList<>();
    }

    public void readCommercialAdd() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter start date: ");
        String startDate = scanner.nextLine();
        System.out.print("Enter size of add in cm: ");
        double sizeOfAdd = scanner.nextDouble();
        System.out.print("Enter price per cm: ");
        double pricePerCm = scanner.nextDouble();

        CommercialAdd commercialAdd = new CommercialAdd(clientName, startDate, sizeOfAdd, pricePerCm);
        commercialAdds.add(commercialAdd);
    }

    public void readFreeAdd() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter start date: ");
        String startDate = scanner.nextLine();
        System.out.print("Enter free add time duration in months: ");
        int freeAddTimeDuration = scanner.nextInt();

        FreeAdd freeAdd = new FreeAdd(clientName, startDate, freeAddTimeDuration);
        freeAdds.add(freeAdd);
    }

    public void displayCommercialAdds() {
        System.out.println("Commercial Adds:");
        for (CommercialAdd commercialAdd : commercialAdds) {
            System.out.println("Advertisement ID: " + commercialAdd.advertisementId);
            System.out.println("Client Name: " + commercialAdd.clientName);
            System.out.println("Start Date: " + commercialAdd.startDate);
            System.out.println("Size of Add: " + commercialAdd.getSizeOfAdd() + " cm");
            System.out.println("Price per cm: Rs" + commercialAdd.getPricePerCm());
            System.out.println("Add Cost: Rs" + commercialAdd.addCost());
            System.out.println("--------------------------------");
        }
    }

    public void displayFreeAdds() {
        System.out.println("Free Adds with Duration > 3 months:");
        for (FreeAdd freeAdd : freeAdds) {
            if (freeAdd.getFreeAddTimeDuration() > 3) {
                System.out.println("Advertisement ID: " + freeAdd.advertisementId);
                System.out.println("Client Name: " + freeAdd.clientName);
                System.out.println("Start Date: " + freeAdd.startDate);
                System.out.println("Free Add Time Duration: " + freeAdd.getFreeAddTimeDuration() + " months");
                System.out.println("--------------------------------");
            }
        }
    }

    public void displayIncomeFromCommercialAdds() {
        double totalIncome = 0;
        for (CommercialAdd commercialAdd : commercialAdds) {
            totalIncome += commercialAdd.addCost();
        }
        System.out.println("Total Income from Commercial Adds: Rs" + totalIncome);
    }

    public static void main(String[] args) {
        AdvertisementManagement advertisementManagement = new AdvertisementManagement();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Read Commercial Add");
            System.out.println("2. Read Free Add");
            System.out.println("3. Display Commercial Adds");
            System.out.println("4. Display Free Adds with Duration > 3 months");
            System.out.println("5. Display Total Income from Commercial Adds");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    advertisementManagement.readCommercialAdd();
                    break;
                case 2:
                    advertisementManagement.readFreeAdd();
                    break;
                case 3:
                    advertisementManagement.displayCommercialAdds();
                    break;
                case 4:
                    advertisementManagement.displayFreeAdds();
                    break;
                case 5:
                    advertisementManagement.displayIncomeFromCommercialAdds();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 0);
    }
}

