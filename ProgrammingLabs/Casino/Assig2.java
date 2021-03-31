/**
 * Jonathan Limpus 
 * CST 338
 * 3/10/2021
 * 
 * Module 2: Casino
 * From the Assignment Page: "The program will loop, asking the user for a bet 
 * amount from 0 to 100 (assume dollars, you can use ints or longs).  If the 
 * user types a 0 that means she wants to quit.  Otherwise, accept the amount as
 * their bet and simulate a slot machine pull.  Your program will print out a 
 * line that looks like a slot machine result containing three strings."
 */

import java.util.*;
import java.lang.Math;

public class Assig2 {
    static Scanner in = new Scanner(System.in);
    public static int getBet() {
        int bet = -1;
        int input = in.nextInt();
        if (input >= 0 && input <= 100) {
            bet = input;
        }
        return bet;
    }

    public static ThreeString pull() {
        ThreeString random = new ThreeString();
        random.setString1(randString());
        random.setString2(randString());
        random.setString3(randString());
        return random;
    }

    /**
     * "Produces and returns a single random string based on the required 
     * probabilities.  It does this by calling the java Math.random() function
     * and using the return result of that function as a means of deciding
     * which of the four possible strings to return." 
     * PROBABILITIES:   
     * space 50%
     * cherries 25%
     * BAR 12.5
     * 7 12.5
     */
    private static String randString() {
        // Get a random double between 0 and 1, multiply by 1000 for a "percent"
        int rand = (int) (Math.random() * 1000);
        if (rand <= 500) {   // 50% chance of space
            return "(space)"; 
        }
        else if (rand > 500 && rand <= 750) { // 25% chance of cherries
            return "cherries";
        }
        else if (rand > 750 && rand <= 875) { // 12.5% chance of BAR  
            return "BAR";
        }
        else {                                // 12.5% chance of 7
            return "7";
        }
    }

    /**
     * "getPayMultiplier() takes the pullString as a parameter, and inspects it
     * to determine what its pay multiplier should be:  5?  15?  100?  0?   It
     * does this by looking at the three strings inside the passed-in 
     * ThreeString object and using if statements to determine and return the
     * right value. The method will return one of the values;  0, 5, 15, 30, 50
     * or 100."
     */
    public static int getPayMultiplier(ThreeString thePull) {
        // 7  7  7 pays 100 × bet
        if (thePull.toString() == "7 7 7") {
            return 100;
        }
        // BAR  BAR  BAR pays 50 × bet
        if (thePull.toString() == "BAR BAR BAR") {
            return 50;
        }
        // cherries  cherries  cherries pays 30 × bet
        if (thePull.toString() == "cherries cherries cherries") { 
            return 30;
        }
        // cherries  cherries  [not cherries] pays 15 × bet
        else if (thePull.getString1() == "cherries" 
            && thePull.getString2() == "cherries") {
            return 15;
        }
        // cherries  [not cherries]  [any] pays 5 × bet 
        else if (thePull.getString1() == "cherries") {
            return 5;
        }
        else {
            return 0;
        }
    }

    /**
     * Display the winnings and the ThreeString output to the user
     */
    public static void display(ThreeString thePull, int winnings) {
        System.out.println(thePull.toString());
        if (winnings == 0) {
            System.out.println("Sorry - you lost");
        }
        else {
            System.out.println("Congrats, you won $" + winnings);
        }
    }

    /**
     * main() will have a loop that is controlled by a value returned from 
     * getBet().  As long as that value is non-zero, we keep playing. Each time 
     * through the loop, we have to call pull() to get the pullString as a 
     * return value. Then we need to pass that to getPayMultiplier() to find the
     * multiplier.  We then compute the winnings based on the previous 
     * information, and finally we display it all using display(). When the user
     * wants to quit, call toStringWinnings() and print the returned string. 
     */
    public static void main(String[] args) {
        int bet = -1;
        ThreeString pullString = new ThreeString();
        do {
            System.out.println(
                "How much would you like to bet? (1-100 or 0 to quit)");
            bet = getBet();
            if (bet > 0) {
                pullString = pull();
                int winnings = getPayMultiplier(pullString) * bet;
                if(!pullString.saveWinnings(winnings)) 
                    bet = 0;    // End the loop if max pulls are met
                display(pullString, winnings);
            }
        }
        while (bet != 0);
        // User quits the program - display winnings
        System.out.println("Congrats - You won a total of $" 
            + pullString.toStringWinnings());
    }
}

/**
 * Sibling class to support the main program.
 * Contains three private member Strings, public static final int MAX_LEN 
 * and MAX_PULLS, static final int array pullWinnings
 * and an int numPulls. Public methods: Default constructor
 * ThreeString(), accessors and boolean mutators, and toString() boolean 
 * saveWinnings(int winnings), and String toStringWinnings() methods.
 */
class ThreeString {
    // Private members 
    private String string1;
    private String string2;
    private String string3;
    private static int numPulls;
    // Public members
    public static final int MAX_LEN = 20;
    public static final int MAX_PULLS = 40;
    public static int[] pullWinnings = new int[MAX_PULLS];

    // Constructor
    public ThreeString() {
        string1 = "";
        string2 = "";
        string3 = "";
    }

    // Methods
    // Accessors 
    public String getString1() {
        return string1;
    }

    public String getString2() {
        return string2;
    }

    public String getString3() {
        return string3;
    }

    // Boolean mutators 
    public boolean setString1(String input) {
        if(validString(input)) {
            string1 = input;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setString2(String input) {
        if(validString(input)) {
            string2 = input;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setString3(String input) {
        if(validString(input)) {
            string3 = input;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * From the assignment page: "determine whether a String is legal. 
     * This method returns true if both the string  is not null and its length
     * <= MAX_LEN and false, otherwise."
     */
    private boolean validString(String str) {
        if (str != null && str.length() <= MAX_LEN)
            return true;
        else 
            return false;
    }

    /**
     * "toString() method that will return all of the strings as one string."
     */
    @Override
    public String toString() {
        return string1 + " " + string2 + " " + string3;
    }

    /**
     * Save the winnings from the round and stop the main program loop
     * if the max pulls have been met.
     */
    public boolean saveWinnings(int winnings) {
        if (numPulls != MAX_PULLS) {
            pullWinnings[numPulls] = winnings;  // Add winnings to array
            numPulls++;                         // Add to total pulls
            return true;     
        }
        else {
            return false;                       // End loop
        }
    }

    /**
     * Use a loop to get the values out of the array as well as the total
     * winnings and return a string
     */
    public String toStringWinnings() {
        // This integer will ultimately store our result
        int total = 0; 
        // Use a for-loop to iterate through the main array and add to the total
        for(int i = 0; i < pullWinnings.length; ++i) {
            total += pullWinnings[i];
        } 
        // Convert total to a string and return the result
        String totalWinnings = Integer.toString(total);
        return totalWinnings;
    }
}