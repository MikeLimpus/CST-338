/*
 * Mike Limpus
 * CST 338
 * Lab 1 - String Manipulation
 * Part 1: Get the user's name and output the length and the name in upper and 
 * lower case.
 * Part 2: Get the number of hours the user worked on class and output the 
 * value rounded to a tenth.
 */
import java.util.Scanner;
import java.text.DecimalFormat;
public class StringManipulation {
    public static void main(String[] args) {
        /* The two parts of the lab are seperated into different methods for 
        the sake ease of evaluating them seperating them */
        manipulateName();
        classHours();
    }

    // Part 1
    public static void manipulateName() {
        // Get the user's first and last name and store in variables
        String firstName; 
        String lastName;
        Scanner input = new Scanner(System.in);
        System.out.println("Part 1");
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
            + "\nLower case: " + lowerCase + "\n");
    }

    // Part 2
    // These are the global constants 
    public static final int MIN_HOURS = 12;
    public static final int MAX_HOURS = 20;

    public static void classHours() {
        Scanner input = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat("00.0");
        double hoursWorked;
        String roundedHours;
        System.out.println("Part 2");
        System.out.println("You should work anywhere between " + MIN_HOURS 
            + " and " + MAX_HOURS + " week in this course.\nTo three decimal" 
            + " places, how many hours did you work?");
        // Get the value from the user and round to one dec. place
        System.out.print("Number of hours: " );
        hoursWorked = input.nextDouble();
        roundedHours = formatter.format(hoursWorked);
        // For the purpose of testing printf vs println as discussed in the text
        System.out.printf("\nYou worked %s hours this week\n", roundedHours);
        // Close input to prevent resource leak
        input.close();
    }
}