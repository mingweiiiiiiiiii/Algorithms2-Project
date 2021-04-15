import functions.Function2;
import functions.Function3;
import utility.Time;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Eligijus Skersonas
 * 
 * CLI For Vancouver Bus Transport System
 */

public class MainProgram {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
    	// Initialise variables
    	boolean exit = false;
        String userInput = "";
        int query = -1;
        Scanner inputScanner = new Scanner(System.in);
		System.out.println("INITIALISING FUNCTIONS");
		System.out.println("Initialising Function2 (too fast for a progress bar :D)");
		System.out.println("Initialising Function3");
		Function3 arrivalMethod = new Function3();

		while(!exit) {
    		// Reset variables
    		userInput = "";
    		query = -1;
    		
	    	System.out.println("\n	   __      __                                        \n"
	    			+ "	   \\ \\    / /                                        \n"
	    			+ "		\\ \\  / /_ _ _ __   ___ ___  _   ___   _____ _ __ \n"
	    			+ "		 \\ \\/ / _` | '_ \\ / __/ _ \\| | | \\ \\ / / _ \\ '__|\n"
	    			+ "		  \\  / (_| | | | | (_| (_) | |_| |\\ V /  __/ |   \n"
	    			+ "		   \\/ \\__,_|_| |_|\\___\\___/ \\__,_| \\_/ \\___|_| ");
	        System.out.println("\n ____              _______                                   _      _____           _                 \n"
	        		+ "|  _ \\            |__   __|                                 | |    / ____|         | |                \n"
	        		+ "| |_) |_   _ ___     | |_ __ __ _ _ __  ___ _ __   ___  _ __| |_  | (___  _   _ ___| |_ ___ _ __ ___  \n"
	        		+ "|  _ <| | | / __|    | | '__/ _` | '_ \\/ __| '_ \\ / _ \\| '__| __|  \\___ \\| | | / __| __/ _ \\ '_ ` _ \\ \n"
	        		+ "| |_) | |_| \\__ \\    | | | | (_| | | | \\__ \\ |_) | (_) | |  | |_   ____) | |_| \\__ \\ ||  __/ | | | | |\n"
	        		+ "|____/ \\__,_|___/    |_|_|  \\__,_|_| |_|___/ .__/ \\___/|_|   \\__| |_____/ \\__, |___/\\__\\___|_| |_| |_|\n"
	        		+ "                                           | |                             __/ |                      \n"
	        		+ "                                           |_|                            |___/                       \n"
	        		+ "\n############################################################################################################\n");
	        System.out.println(""
	        		+ "		################\n"
	        		+ "		# INSTRUCTIONS #\n"
	        		+ "		################\n\n"
	        		+ "		A message will come up to prompt you to select a query.\n"
	        		+ "		To Select a query simply type a number from the list of\n"
	        		+ "		queries and press ENTER.\n");
	        System.out.println("\n\n"
	        		+ "		###########\n"
	        		+ "		# QUERIES #\n"
	        		+ "		###########\n\n"
	        		+ "		0 --> Exit the program\n"
	        		+ "		1 --> Get a list of stops between 2 bus stops and the associated cost\n"
	        		+ "		2 --> Search for a bus stop by its full name or first few letters\n"
	        		+ "		3 --> Search for all trips with a given arrival time\n");
	        System.out.print("\n\n"
	        		+ "Select a query -> ");
	        
	        // Get query from System.in (Handle error if needed)
	        if(inputScanner.hasNextInt()) {
	        	query = inputScanner.nextInt();
	        	inputScanner.nextLine(); // clear System.in
        		boolean goBack = false;
        		
	        	switch(query) {
	        	case 0: // exit
	        		exit = true;
	        		break;
	        	case 1: // list bus stops en route between 2 stops
	        		System.out.println("NOT IMPLEMENTED YET");
	        		break;
	        	case 2: // search bus stops
	        		System.out.println("\nQuery 2 selected!");
	        		while(!goBack && !exit) {
		        		System.out.print("\nTo go back to the main page type 'back'\n"
		        				+ "To exit the program type 'exit'\n\nSearch for a bus stop -> ");
		        		userInput = inputScanner.nextLine().strip().toUpperCase();
		        		if(userInput.equalsIgnoreCase("back"))
		        			goBack = true;
		        		else if(userInput.equalsIgnoreCase("exit"))
		        			exit = true;
		        		else {
			        		Function2 myuserInterce = new Function2(userInput);
			        		// printing this arraylist
			        		ArrayList<String> fullInformation = myuserInterce.getFullInformation();
			        		System.out.println("\n			Number of matches found: " + fullInformation.size() + "\n");
			        		for(int i = 0; i < fullInformation.size(); i++)
			        			System.out.println(fullInformation.get(i)); 
		        		}
	        		}
	        		break;
	        	case 3: // search for all trips with a given arrival time
	        		System.out.println("\nQuery 3 selected");

	        		while(!goBack && !exit) {
		        		System.out.print("\nTo go back to the main page type 'back'\n"
		        				+ "To exit the program type 'exit'\n\nSearch for trips with arrival time -> ");
		        		userInput = inputScanner.nextLine().strip();
		        		if(Time.isValidTime(userInput))
		        			arrivalMethod.printTripsWithGivenArrivalTimes(userInput);
		        		else if(userInput.equalsIgnoreCase("back"))
		        			goBack = true;
		        		else if(userInput.equalsIgnoreCase("exit"))
		        			exit = true;
		        		else
			        		System.out.println("\nInvalid time entered! Must be of the form hh:mm:ss 24-hour \n");

	        		}	        		
	        		break;
	        	default:
	        		System.out.println("\nInvalid query selected please try again\n");
		        	for(int i = 5; i >= 0; i--) {
		        		System.out.print("\rTRY AGAIN IN: " + i + ((i == 1)? " SECOND":" SECONDS"));
		        		TimeUnit.SECONDS.sleep(1);
		        	}
	        		break;
	        	}
	        }
	        else {
	        	inputScanner.nextLine(); // clear System.in
	        	System.out.println("\nInvalid input please type the number of one of the queries above and press ENTER\n"
	        			+ "Example for selecting query 2: Select a query -> 2\n");
	        	for(int i = 5; i >= 0; i--) {
	        		System.out.print("\rTRY AGAIN IN: " + i + ((i == 1)? " SECOND":" SECONDS"));
	        		TimeUnit.SECONDS.sleep(1);
	        	}
	        }
	        
    	}
    	
    	System.out.println("\n"
    			+ "				  PROGRAM TERMINATED\n"
    			+ "############################################################################################################");
    }
}