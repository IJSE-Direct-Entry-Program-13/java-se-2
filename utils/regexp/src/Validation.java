import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter your nic: ");
            String nic = scanner.nextLine();
            // boolean matches = Pattern.compile("\\d{9}[Vv]").matcher(nic).matches();
            // boolean matches = Pattern.matches("\\d{9}[Vv]", nic);
            System.out.println(nic.matches("\\d{9}[Vv]") ? "Valid NIC" : "Invalid NIC");
        } while (true);
    }
}
