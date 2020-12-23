/*
ASCII art code to convert images to text was developed by Ryan Wang and not myself Prab
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class getImage {
    public static void getImages() {
        BufferedImage pokemonImage;  //Creates a "bufferedimage" object
        try {
            Scanner input = new Scanner(new File("allPokemon.txt"));  //Using all our desired pokemon from the data file to match to images
            String pokemonName;  //Also used as a part of url when searching and loading pokemon
            int width;
            int height;
            Color color;
            URL url;  //Allows connection to the internet in order to load files
            HttpURLConnection connection;
            PrintWriter out;
            while (input.hasNextLine()) { //Iterate through our pokemon from txt file line by line

                pokemonName = input.nextLine().split(",")[0].toLowerCase().replace(".", "").replace(" ", "-").replace("(", "-").replace(")", "").replace("'", "");
                System.out.println(pokemonName);
                url = new URL("https://img.pokemondb.net/sprites/red-blue/normal/" + pokemonName + ".png");
                connection = (HttpURLConnection) url.openConnection();  // Creating a new http connection
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");  // Adding a user-agent so the program wont be blocked
                pokemonImage = ImageIO.read(connection.getInputStream());  // Downloads image
                out = new PrintWriter(new File("" + pokemonName + ".txt"));  //creates txt file of ASCII art of pokemon
                width = pokemonImage.getWidth();
                height = pokemonImage.getHeight();

                for (int y = 0; y < height; y++) {  //Iterates through every pixel and converts it to text
                    for (int x = 0; x < width; x++) {
                        color = new Color(pokemonImage.getRGB(x, y));
                        if (color.getRed() > 254) {  //converts colour ranges to a specific shaded text pattern
                            out.print("  ");
                        } else if (color.getRed() < 223 && color.getRed() > 177) {
                            out.print("░░");
                        } else if (color.getRed() < 178 && color.getRed() > 160){
                            out.println("▒▒");
                        } else if (color.getRed() < 161 && color.getRed() > 100) {
                            out.print("▓▓");
                        } else if (color.getRed() < 20) {
                            out.print("██");
                        }
                    }
                    out.println();
                }

                out.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
