/**
 * A simple program that offers a solution to 
 * the first problem of advent of code.
 *
 * Takes a stream of inputs.
 * Parses the data to extract a two digit number.
 * Sums up all the digits.
 *
 * @author Basliel B. Gugsa
 * @version 01/12/23
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class calibParse {
    //The sum of the calibration file is stored here
    private static int sumCalib = 0;

    public static void main(String args[]) {
        addCalib(parseLine("c7"));
        addCalib(parseLine("askdjfashdf4kjhkl5jkhlkjhjkh6kjh7"));
        addCalib(parseLine("1"));
        addCalib(parseLine("cgdgdgeg47"));

        System.out.println(sumCalib);
    }

    /**
     * A method that accepts a line from the input
     * and parses the data to find the two digit number
     *
     * @param String parseLine - The line from input to 
     *                           be parsed
     * @return int two digit number found in line
     */
    public static int parseLine(String parseLine) {
       Pattern pattern = Pattern.compile("\\d+");

       Matcher match = pattern.matcher(parseLine);
       int firstDigit = -1;
       int secondDigit = -1;

       while(match.find()) {
            String foundSeq = match.group();
            //to make sure firstDigit remains the same throughout
            if(firstDigit == -1)
                firstDigit = Character.getNumericValue(foundSeq.charAt(0));
            
            secondDigit = Character.getNumericValue(foundSeq.charAt(foundSeq.length() - 1));
            System.out.printf("First Digit: %d, Last Digit: %d %n", firstDigit, secondDigit);
       }

       int digit = (firstDigit * 10) + secondDigit;
       return digit;
    }
    
    /**
     * A method that adds to the sum of the numbers while
     * it is still happening.
     * modifies the general addition buffer
     *
     * @param int newCalib - a new Calibration file
     */
    public static void addCalib(int newCalib) {
        sumCalib += newCalib;
    }
}
