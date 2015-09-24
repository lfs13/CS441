import java.util.*;
import java.io.*;

// CS 0401 Fall 2013.  Assignment 3 main program class.  Note how this class is set up
// with instance variables and methods.  The idea is that the main program is itself an
// object and the methods being called are parts of the object that implement the various
// requirements.  I have implemented the initial reading of the data from the file to
// get you started.  You must add the menu and all of its required functionality.

// Note that this program WILL NOT COMPILE until you have completed the Batter and BatterDB
// classes to some extent.  They do not have to be totally working but all of the methods
// used here must be implemented in order for this code to compile.

public class Assig3 {
	private BatterDB theDB;
	private Batter currBatter;
	private Scanner inScan;
	private String fName;

	// Note that the main method here is simply creating an Assig3 object. The
	// rest of the execution is done via the constructor and other instance
	// methods
	// in the Assig3 class. Note also that this is using a command line argument
	// for
	// the name of the file. All of our programs so far have had the
	// "String [] args"
	// list in the header -- we are finally using it here to read the file name
	// from the
	// command line. That name is then passed into the Assig3 constructor.
	public static void main(String[] args) throws IOException {
		Assig3 A3 = new Assig3(args[0]);
	}

	// Constructor is really where the execution begins. Initialize the
	// database (done for you) and then go into the main interactive loop (you
	// must add this code).
	public Assig3(String fstring) throws IOException {
		fName = fstring;
		theDB = new BatterDB(2);
		getBatters(fName);
		System.out.println("The database has been loaded");
		System.out.println(theDB.toString());
		boolean quit = false;
		while (quit == false) {
			Scanner input = new Scanner(System.in);
			int choice;
			System.out.println("1) Show the list of players" + "\n"
					+ "2) Add a new player" + "\n" + "3) Search for a player"
					+ "\n" + "4) Remove a player" + "\n" + "5) Upate a player"
					+ "\n" + "6) Sort the list alphabetically" + "\n"
					+ "7) Sort the list by batting average" + "\n"
					+ "8) Quit the program [list will be saved]");
			choice = input.nextInt();
			// Print out information of all batters in DB
			if (choice == 1) {
				System.out.println(theDB.toString());
			}
			// Add a new batter to the DB and set private variables
			if (choice == 2) {
				System.out.println("Enter first name of batter to add:");
				input.nextLine();
				String first = input.nextLine();
				System.out.println("Enter last name of batter to add:");
				String last = input.nextLine();
				Batter currBatter = new Batter(first, last);
				int b = 0;
				int h = 0;
				int d = 0;
				int t = 0;
				int hr = 0;

				// bats
				System.out.println("At Bats?");
				b = input.nextInt();
				currBatter.setBats(b);

				// hits
				do {
					h = 0;
					System.out.println("Hits (<=" + b + ")?");
					h = input.nextInt();
					currBatter.setHits(h);
				} while (h > b);

				// doubles
				do {
					d = 0;
					System.out.println("Doubles (<=" + (h - (d + t + hr))
							+ ")?");
					d = input.nextInt();
					currBatter.setDoubles(d);
				} while (d > h || (d + t + hr) > h);

				// triples

				do {
					t = 0;
					System.out.println("Triples (<=" + (h - (d + t + hr))
							+ ")?");
					t = input.nextInt();
					currBatter.setTriples(t);
				} while (t > h || (d + t + hr) > h);

				// HR
				do {
					hr = 0;
					System.out.println("Homeruns (<=" + (h - (d + t + hr))
							+ ")?");
					hr = input.nextInt();
					currBatter.setHR(hr);
				} while (hr > h || (d + t + hr) > h);
				System.out.println(currBatter.toString());
				theDB.addBatter(currBatter);
				System.out.println(theDB.numBatters());

			}
			// Search DB for batter by name
			// Print out information if the batter exists
			if (choice == 3) {
				System.out.println("Enter first name of batter to find:");
				input.nextLine();
				String first = input.nextLine();
				// System.out.println(first);
				System.out.println("Enter last name of batter to find:");
				String last = input.nextLine();
				// System.out.println(last);
				currBatter = theDB.findBatter(first, last);
				if (currBatter == null)
					System.out.println("CANNOT FIND BATTER");
				else
					System.out.println(currBatter.toString());
			}
			// Remove player from DB, if they exist
			if (choice == 4) {
				System.out.println("Enter first name of batter to remove:");
				input.nextLine();
				String first = input.nextLine();
				System.out.println("Enter last name of batter to remove:");
				String last = input.nextLine();
				currBatter = theDB.findBatter(first, last);
				if (currBatter != null)
					theDB.removeBatter(currBatter);
				else
					System.out.println("CANNOT FIND BATTER");
			}
			// update player info
			if (choice == 5) {
				System.out.println("Enter first name of batter to update:");
				input.nextLine();
				String first = input.nextLine();
				System.out.println("Enter last name of batter to update:");
				String last = input.nextLine();
				currBatter = theDB.findBatter(first, last);
				if (currBatter == null)
					System.out.println("Cannot find batter.");
				else {
					int b = 0;
					int h = 0;
					int d = 0;
					int t = 0;
					int hr = 0;

					// bats
					System.out.println("At Bats?");
					b = input.nextInt();
					currBatter.setBats(b);

					// hits
					do {
						h = 0;
						System.out.println("Hits (<=" + b + ")?");
						h = input.nextInt();
						currBatter.setHits(h);
					} while (h > b);

					// doubles
					do {
						d = 0;
						System.out.println("Doubles (<=" + (h - (d + t + hr))
								+ ")?");
						d = input.nextInt();
						currBatter.setDoubles(d);
					} while (d > h || (d + t + hr) > h);

					// triples

					do {
						t = 0;
						System.out.println("Triples (<=" + (h - (d + t + hr))
								+ ")?");
						t = input.nextInt();
						currBatter.setTriples(t);
					} while (t > h || (d + t + hr) > h);

					// HR
					do {
						hr = 0;
						System.out.println("Homeruns (<=" + (h - (d + t + hr))
								+ ")?");
						hr = input.nextInt();
						currBatter.setHR(hr);
					} while (hr > h || (d + t + hr) > h);
				}
			}
			// Sort the database alphabetically using the method sortName()
			if (choice == 6) {
				theDB.sortName();
			}
			// Sort the database numerically using the method sortAve()
			if (choice == 7) {
				theDB.sortAve();
			}
			if (choice == 8) {
				File save_file = new File(fName);
				PrintWriter pw = new PrintWriter(save_file);
				pw.println(theDB.toStringFile());
				pw.close();
				System.exit(0);
			}
		}
	}

	// Note how this method is working. It first reads the number of Batters
	// from the
	// file, then for each Batter it gets the names, creates the object, and
	// mutates it
	// with the instance methods shown. Finally, it adds the new object to the
	// BatterDB object.
	public void getBatters(String fName) throws IOException {
		Batter currB;
		File inFile = new File(fName);
		Scanner inScan = new Scanner(inFile);
		int numBatters = inScan.nextInt();
		inScan.nextLine();
		for (int i = 0; i < numBatters; i++) {
			String first = inScan.nextLine();
			String last = inScan.nextLine();
			currB = new Batter(first, last);

			int ab, h, d, t, hr;
			ab = inScan.nextInt();
			inScan.nextLine();
			currB.setBats(ab);
			h = inScan.nextInt();
			inScan.nextLine();
			currB.setHits(h);
			d = inScan.nextInt();
			inScan.nextLine();
			currB.setDoubles(d);
			t = inScan.nextInt();
			inScan.nextLine();
			currB.setTriples(t);
			hr = inScan.nextInt();
			if (inScan.hasNextLine())
				inScan.nextLine();
			currB.setHR(hr);
			theDB.addBatter(currB);
		}
	}

}