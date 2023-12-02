/** 
    _\/_
     /\          A program to figure out possible combinations 
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

public class possibleComb {
    //Regex patterns
    public static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    public static final Pattern COLOR_PATTERN = Pattern.compile("(\\d+) (blue|green|red)");
    
    
    //The number of cubes for the games
    public static final int BLUE = 14;
    public static final int RED = 12;
    public static final int GREEN = 13;
    
    private static int sumComb = 0;

    public static void main(String args[]) {
        System.out.println("We out here bruv");
        parseData("input.txt");
        System.out.println(sumComb);
        // boolean iz = checkCombination("blue 30, green 2;green 1, red 6;red 5");
        // System.out.println(iz);
        // Matcher match = COLOR_PATTERN.matcher("blue 1");
        // while(match.find()) {
            // System.out.println(match.group());
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
            if(checkCombination(gameComb)){
               sumComb += gameNum;
            }
        }
    }
        
    /**
     * A method to check if the combination of draws is valid
     * for the amount cubes identified. The uses another method
     * to do increment
     *
     * @param String gameComb - A combination of draws
     * @return boolean - true if the combination is valid
     */
    public static boolean checkCombination(String gameComb) {
        //Split into each draw
        String[] games = gameComb.split(";");
                 //Values of each cubes found
        //Strings for buffers
        String bufferColor = "";
        String bufferNum = "";
        //Iterate over each draw in a game
        for (String game: games) {
            int foundBlue = 0;
            int foundGreen = 0;
            int foundRed = 0;
            String[] draw = game.split(",");
            //find each color 
            for(String colorDraw: draw) {
                Matcher match = COLOR_PATTERN.matcher(colorDraw);
                while(match.find()){
                    bufferColor = match.group(2);
                    bufferNum = match.group(1);
                    
                    //Handling value for each color
                    if(bufferColor.equalsIgnoreCase("blue"))
                        if(foundBlue < Integer.parseInt(bufferNum))
                            foundBlue = Integer.parseInt(bufferNum);
                        
                    if(bufferColor.equalsIgnoreCase("green"))
                        if(foundGreen < Integer.parseInt(bufferNum))
                            foundGreen = Integer.parseInt(bufferNum);

                    if(bufferColor.equalsIgnoreCase("red"))
                        if(foundRed < Integer.parseInt(bufferNum))
                            foundRed = Integer.parseInt(bufferNum);
                }
                
                if(foundBlue > BLUE)
                    return false;
                if(foundGreen > GREEN)
                    return false;
                if(foundRed > RED)
                    return false;

            }            
        }
        return true; 
    }
}  
