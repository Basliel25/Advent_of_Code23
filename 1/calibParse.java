/** 
    _\/_
     /\          A simple program to calculate calibrations 
     /\          Solution to advent of code: Day 1/25
    /  \
    /~~\o        @author Basliel B. Gugsa
   /o   \
  /~~*~~~\
 o/    o \
 /~~~~~~~~\~`
/__*_______\
     ||
   \====/
    \__/
/ * * * * *  
*/
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

public class calibParse {
    //The sum of the calibration file is stored here
    private static int sumCalib = 0;
    private static final Map<String, Integer> verbalMap = initializeVerbalMap(); 

    public static void main(String args[]) {
        fileParse("input.txt");
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
        //RegEx matching with separate groups and a lookaround
        Pattern pattern = Pattern.compile("(?=(one|two|three|four|five|six|seven|eight|nine|zero|\\d))");
       
        Matcher match = pattern.matcher(parseLine);
        int firstDigit = -1;
        int secondDigit = -1;

        while(match.find()) {
            String foundSeqVerbal = match.group(1);
            //Handling first input
            if(isNumeric(foundSeqVerbal) && firstDigit == -1) {
                firstDigit = Character.getNumericValue(foundSeqVerbal.charAt(0));
            } else if(!isNumeric(foundSeqVerbal) && firstDigit != -1)
                firstDigit = convertToDigit(foundSeqVerbal);
            //Handling Second Input
            if(isNumeric(foundSeqVerbal))
                secondDigit = Character.getNumericValue(foundSeqVerbal.charAt(foundSeqVerbal.length() - 1));
            else
                secondDigit = convertToDigit(foundSeqVerbal);
        }

        int digit = (firstDigit * 10) + secondDigit;
        return digit;
    }
    
    /**
     * A method to read each line and feed the parser
     *
     * @param String fileName - name of the input file
     */
    public static void fileParse(String fileName) {
        try{
            Scanner scan = new Scanner(new File(fileName));
            while(scan.hasNext()) {
                String line = scan.nextLine();
                addCalib(parseLine(line));
            }
        } catch(FileNotFoundException e) {
            System.err.println("File not found, ");
        }
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

    /**
     * A method that returns a map that maps textual versions of
     * digits to be recognized in the pattern.
     *
     * @return Map<String, Integer> a map of verbal versions of numbers
     */
    public static Map<String, Integer> initializeVerbalMap() {
        Map<String, Integer> verbalMap = new HashMap<>();
        verbalMap.put("one", 1);
        verbalMap.put("two", 2);
        verbalMap.put("three", 3);
        verbalMap.put("four", 4);
        verbalMap.put("five", 5);
        verbalMap.put("six", 6);
        verbalMap.put("seven", 7);
        verbalMap.put("eight", 8);
        verbalMap.put("nine", 9);
        verbalMap.put("zero", 0);

        return verbalMap;
    }
    /**
     * A method to implement the map of number and digits, to convert
     * a verbal digit into an integer
     *
     * @param String verbalNum - the text containing a digit
     * @return int the number version of the integer
     */
    public static int convertToDigit(String verbalNum) {
        int digitNum = verbalMap.getOrDefault(verbalNum.toLowerCase(), -1);
        
        return digitNum;
    }

    /**
     * A method to check whether a data is a verbal number or
     * a digit.
     *
     * @param String data - data to be checked
     * @return boolean true - if it is a digit
     */
    public static boolean isNumeric(String str) {
        try { 
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){ 
            return false;
        }
    }
}
