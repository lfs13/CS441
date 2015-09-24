/* CS 0401 Fall 2013
   Assignment 5 Main Program Class
   You must use the program as is -- no changes!
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import javax.swing.*;

public class Assig5 {
	private StopWatch thePanel; // StopWatch is a subclass of JPanel
	private JFrame theWindow;

	public Assig5(int sz, String fName) throws FileNotFoundException {
		theWindow = new JFrame("CS 401 Assignment 5");

		thePanel = new StopWatch(sz, fName);
		// The arguments to this constructor
		// determines the size of the digits in the stopwatch and the
		// file to which lap times will be written

		Container c = theWindow.getContentPane();
		c.add(thePanel, BorderLayout.CENTER);
		theWindow.setResizable(false);
		theWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				try {
					thePanel.close();
				} catch (FileNotFoundException n) {
					System.exit(0);
				}
			}
		};
		theWindow.addWindowListener(exitListener);
		theWindow.pack();
		theWindow.setVisible(true);
	}

	public static void main(String[] args) throws FileNotFoundException {
		int sz = Integer.parseInt(args[0]);
		String fName = new String(args[1]);
		new Assig5(sz, fName);
	}
}
