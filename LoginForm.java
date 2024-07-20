import java.util.Scanner;

public class LoginForm {

    private static final String VALID_LOGIN_ID = "sam";
    private static final String VALID_PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Reservation System");

        System.out.print("Enter Login ID: ");
        String loginId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authenticateUser(loginId, password)) {
            System.out.println("Login successful! Accessing main system...");
            ReservationSystem.main(null);  // Call the ReservationSystem main method
        } else {
            System.out.println("Invalid login ID or password. Please try again.");
        }

        scanner.close();
    }

    private static boolean authenticateUser(String loginId, String password) {
        return VALID_LOGIN_ID.equals(loginId) && VALID_PASSWORD.equals(password);
    }
}
