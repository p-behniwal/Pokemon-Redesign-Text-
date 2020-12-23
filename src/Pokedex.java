//Datafile of 151 pokemon was made by Aaron Lee

import java.io.File;
import java.util.LinkedHashMap; // A linked hash map is used to maintain order
import java.util.Map;
import java.util.Scanner;

public class Pokedex {  //Reads pokemon from data file and stores the data
    private LinkedHashMap<Integer, Pokemon> pokeData = new LinkedHashMap<>();  //Pokemon are stored according to their number

    public Pokedex() {
        try {  //Try/catch is used to handle the file; not found exception
            Scanner artIn;  // The Scanner for the ASCII art
            Scanner infile = new Scanner(new File("Data/allPokemon.txt"));  //The scanner used to read all the pokemon txt file's data
            String data;  //The data read by the scanner as single line
            String[] processData; //The data that has been split properly
            String art;  //The ASCII art for the pokemon
            int counter = 1;  //The counter to allow pokemon numbering

            while (infile.hasNextLine()) {  //Reads until file's end
                art = "";  //Clears/Resets the art variable

                data = infile.nextLine();  //Reading the data
                processData = data.split(",");

                pokeData.put(counter, new Pokemon(data));  //Creates a new pokemon object and stores it with the pokemon number into the linked hash map

                //Creates a new Scanner to read pokemon and formats everything properly
                artIn = new Scanner(new File("Data/images/" + processData[0].toLowerCase().replace(".", "").replace(" ", "-").replace("(", "-").replace(")", "").replace("'", "") + ".txt"));

                //Reads everything from the ASCII art file per line
                while (artIn.hasNextLine()) {
                    art += artIn.nextLine() + "\n";
                }

                pokeData.get(counter).setArt(art);  //Sets the ASCII art for that pokemon by its number (counter)

                counter++;  //Add to counter

                artIn.close(); //Close to save memory and not lose anything accidentally
            }

            infile.close();  //Close pokemon file to save memory and not lose anything accidentally
        } catch (Exception e) {
            e.printStackTrace();  //Prints the error if it occurs
        }
    }

    public LinkedHashMap<Integer, Pokemon> getPokeDex() {  //Gets the "pokedata" as a method
        return pokeData;
    }

    public Pokemon getPokemon(int number) { //Another "getting" method
        if (pokeData.containsKey(number)) {  //If the pokemon number is valid
            return pokeData.get(number);
        }
        return null;  //Returns null if entered number is invalid
    }

    public void printTable() {  //Prints the full table
        pokeTools.delayPrintTable(String.format("║ %3s ║ %-20s ║ %-5s ║ %-20s ║ %-20s ║ %-20s ║", "#", "Name", "HP", "Type", "Resistance", "weakness"));
        pokeTools.delayPrintTable("╠══════╬════════════════════════════╬═════════╬═══════════════════════════╬════════════════════════════╬═══════════════════════════║");

        for (Map.Entry<Integer, Pokemon> entry : pokeData.entrySet()) {  // Printing every pokemon in the pokedex
            pokeTools.delayPrintTable(String.format("║ %3d ", entry.getKey()) + entry.getValue().toString());
        }
    }
}