/** 
    _\/_
     /\          A program to figure out possible minumum combinations and find grand sum
     /\          Solution to advent of code: Day 2/25
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
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class minPossibleComb {
    //Regex patterns
    public static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    public static final Pattern COLOR_PATTERN = Pattern.compile("(\\d+) (blue|green|red)");
    
    //The grand sum
    private static int grandSum = 0;

    public static void main(String args[]) {
        System.out.println("We out here bruv");
        parseData("input.txt");
        System.out.println(grandSum);
    }

    /**
     * A method to handle each line and uses methods to
     * check if each game is valid and adds to the number
     * of possible combinations
     *
     * @param String fileName - the name of the file containing
     *                          the data
     */
    public static void parseData(String fileName) {
        //Stream to read from data
        Scanner scan = null;
        try {
            scan = new Scanner(new File(fileName));
            System.out.println("Parsing file: "+fileName);
        } catch(FileNotFoundException e) {
            System.err.println("File not found");
        }

        //Parse each line
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            //Separate the first portion
            int index = line.indexOf(':');
            String gameInfo = line.substring(0, index);
            
            //Find the game number
            String game = "";
            int gameNum = 0;
            Matcher matchGame = NUMBER_PATTERN.matcher(gameInfo);
            while(matchGame.find()){
                game = matchGame.group();
                gameNum = Integer.parseInt(game);
            }
            
            //String with game combinations
            String gameComb = line.substring(index+1, line.length());
            System.out.println("Game num" + gameNum);
            compute(checkCombination(gameComb));
        }
    }
        
    /**
     * A method to extract the possible minimum combination of draws 
     * for the amount cubes identified. 
     * 
     * @param String gameComb - A combination of draws
     * @return int[] - an int array of the minumum values
     *                 in the order red, green, blue
     **/
    public static int[] checkCombination(String gameComb) {
        //Split into each draw
        String[] games = gameComb.split(";");
        //Strings for buffers
        String bufferColor = "";
        String bufferNum = "";
        
        int minBlue = 0;
        int minGreen = 0;
        int minRed = 0;

        //Iterate over each draw in a game
        for (String game: games) {
            String[] draw = game.split(",");
            //find each color 
            for(String colorDraw: draw) {
                Matcher match = COLOR_PATTERN.matcher(colorDraw);
                while(match.find()){
                    bufferColor = match.group(2);
                    bufferNum = match.group(1);
                    
                    //Handling value for each color
                    if(bufferColor.equalsIgnoreCase("blue"))
                        if(minBlue < Integer.parseInt(bufferNum))
                            minBlue = Integer.parseInt(bufferNum);
                        
                    if(bufferColor.equalsIgnoreCase("green"))
                        if(minGreen < Integer.parseInt(bufferNum))
                            minGreen = Integer.parseInt(bufferNum);

                    if(bufferColor.equalsIgnoreCase("red"))
                        if(minRed < Integer.parseInt(bufferNum))
                            minRed = Integer.parseInt(bufferNum);
                }
            }            
        }
        //Returning the Mins
        int[] mins = {minRed, minGreen, minBlue}; 
        return mins;
    }

    /**
     * A method to compute the grand sum.
     * The grand sum is the sum of all 
     * the products of the minimum possible 
     * values of each cube for each game
     *
     * @param int[] mins - teh minimums of each game
     */
    public static void compute(int[] mins) {
        int product = 1;
        for(int e: mins)
            product *= e;
        grandSum += product;
    }
}  

