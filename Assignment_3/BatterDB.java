// CS 0401 BatterDB class
// This class is a simple database of Batter objects.  Note the
// instance variables and methods and read the comments carefully.  You minimally
// must implement the methods shown.  You may also need to add some private methods.
// To get you started I have implemented the constructor and the addBatter method for you.

public class BatterDB {
	private Batter[] theBatters; // Array of Batters to store the objects
	private int num; // int to store logical size of DB

	// Initialize this BatterDB
	public BatterDB(int size) {
		theBatters = new Batter[size];
		num = 0;
	}

	// Take already created Batter and add it to the DB. This is simply putting
	// the new Batter at the end of the array, and incrementing the int to
	// indicate that a new movie has been added. If no room is left in the
	// array, resize to double the previous size, then add at the end. Note that
	// the user never knows that resizing has even occurred, and the resize()
	// method is private so it cannot be called by the user.
	public void addBatter(Batter b) {
		if (num == theBatters.length)
			resize(2 * theBatters.length);

		theBatters[num] = b;
		num++;
	}

	// Remove and return the Batter that equals() the argument Batter, or
	// return null if the Batter is not found. You should not leave a gap in
	// the array, so elements after the removed Batter should be shifted over.
	public Batter removeBatter(Batter b) {
		int index=0;
		for (int i = 0; i < num; i++) {
			if (b.equals(theBatters[i])) {
				Batter r = theBatters[i];
				index=i;
				Batter[] temp = new Batter[num];
				for (int j = 0; j < index; j++) {
						temp[j] = theBatters[j];
				}
				for(int k=index+1;k<num;k++){
					temp[k-1]=theBatters[k];
				}
				
				num--;
				theBatters = temp;
				return r;
			}
		}
		return null;
	}

	// Return logical size of the DB
	public int numBatters() {
		return num;
	}

	// Resize the Batter array to that specified by the argument
	private void resize(int newsize) {
		Batter[] temp = new Batter[newsize];
		for (int i = 0; i < theBatters.length; i++) {
			temp[i] = theBatters[i];
		}
		theBatters = temp;
	}

	// Find and return the Batter in the DB matching the first and last
	// names provided. Return null if not found.
	public Batter findBatter(String fName, String lName) {
		String s = (lName + ", " + fName);
		for (int i = 0; i < theBatters.length; i++) {
			if (theBatters[i].getName().equalsIgnoreCase(s))
				return theBatters[i];
		}
		return null;
	}

	// Sort the DB alphabetically using the getName() method of Batters for
	// comparison
	public void sortName() {
		int start, index, minIndex;
		String min;
		for (start = 0; start < num - 1; start++) {
			minIndex = start;
			min = theBatters[start].getName().toUpperCase();
			Batter temp = new Batter("temp", "batter");
			for (index = start + 1; index < num; index++) {
				if (theBatters[index].getName().toUpperCase().compareTo(min) < 0) {
					min = theBatters[index].getName();
					minIndex = index;
					temp = theBatters[index];
					theBatters[minIndex] = theBatters[start];
					theBatters[start] = temp;

				}

			}

		}

	}

	// Sort the DB from high to low using the getAve() method of Batters for
	// comparison
	public void sortAve() {
		int start, index, minIndex;
		double min;
		Batter temp = new Batter("temp", "batter");
		for (start = num - 1; start >= 0; start--) {
			minIndex = start;
			min = theBatters[start].getAve();
			for (index = start - 1; index >= 0; index--) {
				if (theBatters[index].getAve() < min) {
					min = theBatters[index].getAve();
					minIndex = index;
					temp = theBatters[index];
					theBatters[minIndex] = theBatters[start];
					theBatters[start] = temp;
				}

			}

		}
	}

	// Return a formatted string containing all of the Batters' info. Note
	// that to do this you should call the toString() method for each Batter in
	// the DB.
	public String toString() {
		if(num==0)
			return("NO PLAYERS IN THE DATABASE");
		StringBuilder sb = new StringBuilder("");

		for (int i = 0; i < num; i++) {

			if (i == 0)
				sb.append(theBatters[i].toString());
			else
				sb.append("\n" + theBatters[i].toString());

		}

		return sb.toString();
	}

	// Similar to the method above, but now we are not formatting the
	// string, so we can write the data to the file.
	public String toStringFile() {
		StringBuilder sb = new StringBuilder("");
		// first line of file is length of database
		sb.append(num);
		//loop for each players information
		for (int i = 0; i < num; i++) {
			sb.append("\n" + theBatters[i].getFirst());
			sb.append("\n" + theBatters[i].getLast());
			sb.append("\n" + theBatters[i].getBats());
			sb.append("\n" + theBatters[i].getHits());
			sb.append("\n" + theBatters[i].getDoubles());
			sb.append("\n" + theBatters[i].getTriples());
			sb.append("\n" + theBatters[i].getHR());
		}
		return sb.toString();
	}

}
