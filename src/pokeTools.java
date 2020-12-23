import java.util.*;

public class pokeTools {  //Class contains some essential methods for the game
    private static Scanner input = new Scanner(System.in);  //Scanner for input
    private static Random randNum = new Random();

    public static String getData(String helper, String error, String[] data) { //"helper prints a hint, "error" if user enters invalid input, "data" good inputs
        HashSet<String> correctResponse = new HashSet<>(Arrays.asList(data));  //Makes a hash-set for easier comparison
        String response;  // the response from the user

        while (true) {  //While the user has not entered a valid response
            delayPrint(helper);  //Prints the helper
            response = input.next();  //Gets input
            if (correctResponse.contains(response)) {  //If the input is valid then return the input
                return response;
            }
            delayPrintln(error);
        }
    }

    public static int getRange(String error, String helper, int starting, int end) {  //"starting" the start for inclusive range, "end" end of inclusive range
        String response;
        int selection;

        while (true) {
            delayPrint(helper);
            response = input.next();

            if (response.matches("^[0-9]+$")) {  //Regular expression to check if input is a number from the start of the input, match digits 0-9, match 1 or more times, until the end of the string
                selection = Integer.parseInt(response);  //Parses the input if it's an integer
                if (selection >= starting && selection <= end) {  //Checks if its within the valid range
                    return selection;  //Returns input
                }
            }
            delayPrintln(error);
        }
    }

    public static int randint(int min, int max) {
        return randNum.nextInt(max - min + 1) + min;
    }

    public static void delayPrintln(String toPrint) {  //"toPrint" object to be printed
        delayPrint(toPrint + "\n", 5, 0);
    }

    public static void delayPrintTable(String toPrint) {
        String[] table = toPrint.split("\n");  //Splits the input into lines

        for (String line : table) {  //Goes through each individual line
            System.out.println(line);

            try {
                Thread.sleep(5);  //Stops printing for 5 milliseconds after every line
            } catch (Exception e) {
            }

        }
    }

    public static void delayPrint(String toPrint) {
        delayPrint(toPrint, 5, 0);
    }

    public static void delayPrint(String toPrint, int micro, int nano) { //"micro": num of milliseconds to wait. "nano":num of nano secs to wait
        for (char c : toPrint.toCharArray()) {  //Iterates through each character of the input
            System.out.print(c);
            try {
                Thread.sleep(micro, nano);  //Stops the program
            } catch (Exception e) {
            }
        }
    }

    public static void pause() {  //Adds a pause effect to allow the user to read the information before progressing
        delayPrintln("\nPress enter to continue...");
        try {
            System.in.read();  //System.in.read is used here because scanner considers <enter>(\n) to be a white space therefore skipping it
        } catch (Exception e) {
        }
    }

    public static void clearScreen() {  //Prints a bunch of blank lines, "clearing" the screen
        for (int j = 0; j < 100; j++)
            System.out.println();
    }

    public static void mainMenu() {  //Prints the main title graphics for the starting screen


        delayPrintln("Welcome to pokemon");

        pause();
    }

    public static void win() {
        clearScreen();

        delayPrintln(" __     ______  _    _  __          _______ _   _ _ \n" +
                " \\ \\   / / __ \\| |  | | \\ \\        / /_   _| \\ | | |\n" +
                "  \\ \\_/ / |  | | |  | |  \\ \\  /\\  / /  | | |  \\| | |\n" +
                "   \\   /| |  | | |  | |   \\ \\/  \\/ /   | | | . ` | |\n" +
                "    | | | |__| | |__| |    \\  /\\  /   _| |_| |\\  |_|\n" +
                "    |_|  \\____/ \\____/      \\/  \\/   |_____|_| \\_(_)\n");

        pause();
    }

    public static void lose() {
        clearScreen();

        delayPrintln("      _____          __  __ ______    ______      ________ _____  _            \n" +
                "     / ____|   /\\   |  \\/  |  ____|  / __ \\ \\    / /  ____|  __ \\| |           \n" +
                "    | |  __   /  \\  | \\  / | |__    | |  | \\ \\  / /| |__  | |__) | |           \n" +
                "    | | |_ | / /\\ \\ | |\\/| |  __|   | |  | |\\ \\/ / |  __| |  _  /| |           \n" +
                "    | |__| |/ ____ \\| |  | | |____  | |__| | \\  /  | |____| | \\ \\|_|           \n" +
                "     \\_____/_/    \\_\\_|  |_|______|  \\____/   \\/   |______|_|  \\_(_)           ");

        pause();
    }

    public static String[] intro(String pokemon) {  //"pokemon" the pokemon to be printed at the introduction
        String[] names = new String[2];

        clearScreen();
        delayPrintln("Oak: Hello there! Welcome to the world of POKEMON! My name is OAK! People call me the POKEMON PROF!");
        pause();

        clearScreen();
        delayPrintTable(pokemon);
        delayPrintln("Oak: This world is inhabited by creatures called POKEMON! For some people, POKEMON are pets. Others use them for fights. Myself... I study POKEMON as a profession.");
        pause();

        clearScreen();
        delayPrintln("Oak: First, what is your name?");
        delayPrint(">>> ");
        names[0] = input.next();

        clearScreen();
        delayPrintln("Oak: Right! So your name is " + names[0] + "!");
        pause();

        clearScreen();
        delayPrintln("Oak: This is my grandson. He's been your rival since you were a baby. ...Erm, what is his name again?");
        delayPrint(">>> ");
        names[1] = input.next();

        clearScreen();
        delayPrintln("Oak: That's right! I remember now! His name is " + names[1] + "!");
        pause();

        clearScreen();
        delayPrintln("Oak: " + names[0] + "! Your very own POKEMON legend is about to unfold! A world of dreams and adventures with POKEMON awaits! Let's go!");
        pause();

        clearScreen();

        return names;  //Returns String[] with the first element being the player's name and the second being the enemy's name
    }

    public static void displaySelected(Pokedex pokemonLibrary, ArrayList<Integer> selected) { //"pokemonLibrary": The pokedex      "selected": The selected pokemons
        clearScreen();

        delayPrintln("Oak: Great choice of POKEMONS.\n");

        delayPrintTable(String.format("║ %-20s ║ %-5s ║ %-20s ║ %-20s ║ %-20s ║", "Name", "HP", "Type", "Resistance", "Weakness"));
        delayPrintTable("╠═══════════════════════════╬═════════╬════════════════════════════╬═══════════════════════════╬═════════════════════════════╣");

        for (Integer p : selected) {  //Iterates through the pokemon printing each one
            delayPrintTable(pokemonLibrary.getPokemon(p).toString());
        }
        pause();
    }

    /**
     * Gettubg the user to select the pokemons
     * @param pokedex The pokedex used
     * @param number  The number of pokemons for the user to select
     * @return the list of pokemons selected
     */

    public static ArrayList<Integer> selectPokemon(Pokedex pokedex, int number) {  //"number": number of pokemon for the user to select
        ArrayList<Integer> selectedPokemons = new ArrayList<>();  //Declares a new list for storing the selections
        Pokemon selectedPokemon; //The current pokemon that is selected
        String confirmation;  //The confirmation

        int pokemonCounter = 0;  //The counter for the number of pokemon selected
        int pokeSelect;

        while (pokemonCounter < number) {
            pokedex.printTable();

            if (pokemonCounter > 0) {  //Prints whats currently on the player's team
                delayPrint("Your team currently has: ");
                for (int n : selectedPokemons) {
                    delayPrint(pokedex.getPokemon(n).getName() + " ");
                }
                System.out.println();
            }


            pokeSelect = getRange("Error, please enter a valid Pokemon number.", "Please select a pokemon >>> ", 1, 151);  //Gets input
            selectedPokemon = pokedex.getPokemon(pokeSelect);

            clearScreen();
            delayPrintTable(pokedex.getPokemon(pokeSelect).getArt());

            if (selectedPokemons.contains(pokeSelect)) {  //If the pokemon has already been selected
                delayPrintln(selectedPokemon.getName() + " is already on your team.");
                pause();
            } else {

                delayPrintTable(String.format("║ %-20s ║ %-10s ║ %-10s ║ %-20s ║", "Attack", "EC", "DMG", "Special"));  //Displays the attacks for the selected pokemon
                delayPrintTable("╠══════════════════════╬════════════╬════════════╬══════════════════════╣");

                for (Attack a : selectedPokemon.getAttack()) {
                    delayPrintTable(a.toString());
                }

                confirmation = getData("You have chosen " + selectedPokemon.getName() + "(y/n) >>> ", "Invalid selection.", new String[]{"y", "n"});  //Gets a confirmation

                if (confirmation.equals("y") && !selectedPokemons.contains(pokeSelect)) { //Confirmed
                    selectedPokemons.add(pokeSelect);  // add the pokemon to the selected
                    pokemonCounter += 1;
                    delayPrintln(selectedPokemon.getName() + " has been added to your team.");
                    pause();

                }

                delayPrintln(confirmation);
            }
        }


        return selectedPokemons;  //Return the list of pokemons selected
    }

}