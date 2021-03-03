/*
 * Mike Limpus
 * CST 338
 * Lab 1 - String Manipulation
 */
import java.util.Scanner;

public class StringManipulation {
    public static void main(String[] args) {
        manipulateName();
        classHours();
    }

    // Part 1
    public static void manipulateName() {
        // Get the user's first and last name and store in variables
        String firstName; 
        String lastName;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your first and last name,"
            + "capitalized properly \ne.g. John Doe");
        // User will input their first and last name, seperately
        System.out.print("First Name: ");
        firstName = input.nextLine();
        System.out.print("Last Name: ");
        lastName = input.nextLine();
        // create the full name variable and get the length
        String fullName = firstName + " " + lastName;
        String upperCase = fullName.toUpperCase();
        String lowerCase = fullName.toLowerCase();
        int nameLength = firstName.length() + lastName.length();
        // Print the name as defined by the assignment 
        System.out.println("\nName: " + fullName + "\nLength: " + nameLength);
        System.out.println("Upper case: " + upperCase
            + "\nLower case: " + lowerCase);
        input.close();

    }

    // Part 2
    public static final int MIN_HOURS = 12;
    public static final int MAX_HOURS = 20;

    public static void classHours() {
        

    }
}