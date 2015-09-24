import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Results {

	private int rounds, wins, losses = 0;
	private PrintWriter pw;
	private File file_r;
	private Scanner result_reader;

	public Results(String s) {
		try {
			file_r = new File(s);
			pw = new PrintWriter(file_r);
			result_reader = new Scanner(file_r);
		} catch (FileNotFoundException e) {
			System.out.println("Can't find file");
		}

	}

	public void won() {
		wins++;
		rounds++;

	}

	public void lost() {
		losses++;
		rounds++;

	}

	public void saved() {
		pw.println("Rounds:" + rounds);
		pw.println("Wins:" + wins);
		pw.println("Losses:" + losses);
		pw.close();

	}

	public String toString() {
		
		while (result_reader.hasNextLine()) {
			System.out.println(result_reader.next());
			result_reader.nextLine();
		}
		return "";

	}
}
