import java.util.Scanner;
import java.util.HashMap;

class Train {
    private String trainNumber;
    private String trainName;

    public Train(String trainNumber, String trainName) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }
}

class Reservation {
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(String passengerName, String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Reservation Details:\n" +
                "Passenger Name: " + passengerName + '\n' +
                "Train Number: " + trainNumber + '\n' +
                "Train Name: " + trainName + '\n' +
                "Class Type: " + classType + '\n' +
                "Date of Journey: " + dateOfJourney + '\n' +
                "From: " + from + '\n' +
                "To: " + to;
    }
}

public class ReservationSystem {
    private static HashMap<String, Train> trainDatabase = new HashMap<>();
    private static HashMap<String, Reservation> reservationDatabase = new HashMap<>();

    static {
        // Pre-populated train data (for testing purposes)
        trainDatabase.put("12345", new Train("12345", "Express Train"));
        trainDatabase.put("67890", new Train("67890", "Superfast Train"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("Reservation System");

        System.out.print("Enter your name: ");
        String passengerName = scanner.nextLine();

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        Train train = trainDatabase.get(trainNumber);
        if (train == null) {
            System.out.println("Invalid train number. Please try again.");
            return;
        }

        String trainName = train.getTrainName();
        System.out.println("Train Name: " + trainName);

        System.out.print("Enter class type (e.g., AC, Sleeper): ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey (e.g., 2023-07-20): ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter departure station: ");
        String from = scanner.nextLine();

        System.out.print("Enter destination station: ");
        String to = scanner.nextLine();

        Reservation reservation = new Reservation(passengerName, trainNumber, trainName, classType, dateOfJourney, from, to);
        reservationDatabase.put(passengerName, reservation);

        System.out.println("Reservation successful!");
        System.out.println(reservation);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.println("Cancellation Form");

        System.out.print("Enter your PNR number (Passenger Name): ");
        String pnr = scanner.nextLine();

        Reservation reservation = reservationDatabase.get(pnr);
        if (reservation == null) {
            System.out.println("No reservation found for the given PNR number.");
            return;
        }

        System.out.println("Reservation found:");
        System.out.println(reservation);

        System.out.print("Do you want to cancel this reservation? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            reservationDatabase.remove(pnr);
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }
}
