import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StopWatch extends JPanel {
	private Digit[] theDigits; // Object to be drawn graphically
	private Divider[] theDividers;// Objects between digits
	private JButton start, stop, reset, show; // Some buttons
	private JPanel buttonPanel; // Another panel to hold the buttons
	private ButtonListener bListener; // listener for the buttons
	private TimerListener tListener;
	private Timer T; // Timer -- see the API in javax.swing.Timer
	private int prefWid, prefHt; // see details on these below
	private int size;
	private File savefile;
	private boolean lap = false;
	PrintWriter pw;
	private int timing, numlap;

	public StopWatch(int s, String n) throws FileNotFoundException {
		savefile = new File(n);
		pw = new PrintWriter(savefile);
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm.ss").format(Calendar.getInstance().getTime());
		String signature =("Stopwatch initialized: "+ timeStamp);
		pw.println(signature);
		timing = 0;
		numlap = 0;
		size = s;
		prefWid = 10 * size;
		prefHt = 4 * size;
		setPreferredSize(getPreferredSize());

		// /Dividers//
		theDividers = new Divider[3];
		theDividers[2] = new Divider((2 * size) + ((int) (.25 * size)), size
				+ ((int) (.25 * size)), size);
		theDividers[1] = new Divider((5 * size), size + ((int) (.25 * size)),
				size);
		theDividers[0] = new Divider((7 * size) + ((int) (.75 * size)), size
				+ ((int) (.25 * size)), size);

		// Digit Scaled-Spacing//
		theDigits = new Digit[6];
		int index = ((int) (.5 * size));
		theDigits[5] = new Digit(size, size, size);
		theDigits[4] = new Six_Digit((2 * size) + index, size, size);
		index += ((int) (.25 * size));
		theDigits[3] = new Digit((3 * size) + index, size, size);
		index += ((int) (.5 * size));
		theDigits[2] = new Six_Digit((4 * size) + index, size, size);
		index += ((int) (.25 * size));
		theDigits[1] = new Digit((5 * size) + index, size, size);
		index += ((int) (.5 * size));
		theDigits[0] = new Digit((6 * size) + index, size, size);

		// /Button Panel///////
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 4));
		bListener = new ButtonListener();
		start = new JButton("Start Timer");
		start.addActionListener(bListener);
		buttonPanel.add(start);
		stop = new JButton("Stop Timer");
		stop.addActionListener(bListener);
		stop.setEnabled(false);
		buttonPanel.add(stop);
		reset = new JButton("Reset Timer");
		reset.addActionListener(bListener);
		buttonPanel.add(reset);
		show = new JButton("Show Lap");
		show.addActionListener(bListener);
		buttonPanel.add(show);
		this.add(buttonPanel, BorderLayout.NORTH);

		// TIMER//
		tListener = new TimerListener();
		T = new Timer(100, tListener);

	}

	public Dimension getPreferredSize() {
		return new Dimension(prefWid, prefHt);
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == start) {
				T.start();
				start.setEnabled(false);
				stop.setEnabled(true);
			} else if (e.getSource() == stop) {
				T.stop();
				stop.setEnabled(false);
				start.setEnabled(true);
			} else if (e.getSource() == reset) {
				newTimer();
				if(start.isEnabled())
					repaint();
				for (int i = 0; i < 6; i++) {
					theDigits[i].reset();
				}

			} else {
				if (lap == false) {
					lap=true;
					show.setText("Show Time");
					numlap++;
					int[] lap = new int[6];
					for (int i = 0; i < 5; i++) {
						lap[i] = theDigits[i].getActive();
					}
					String s = (lap[5] + ":" + lap[4] + lap[3] + ":" + lap[2]
							+ lap[1] + "." + lap[0]);
					saveLap(s);
				} else {
					show.setText("Show Lap");
					lap = false;
				}

			}
		}
	}

	class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (theDigits[0].getActive() == 9) {
				theDigits[0].mutate();
				if (theDigits[1].getActive() == 9) {
					theDigits[1].mutate();
					if (theDigits[2].getActive() == 5) {
						theDigits[2].mutate();
						if (theDigits[3].getActive() == 9) {
							theDigits[3].mutate();
							if (theDigits[4].getActive() == 5) {
								theDigits[4].mutate();
								if (theDigits[5].getActive() == 9) {
									T.stop();
								} else
									theDigits[5].mutate();
							} else
								theDigits[4].mutate();
						} else
							theDigits[3].mutate();
					} else
						theDigits[2].mutate();
				} else
					theDigits[1].mutate();
			} else
				theDigits[0].mutate();
			if (lap == false)
				repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Make sure you call the super version
		// before anything else
		Graphics2D g2 = (Graphics2D) g; // The Graphics2D class is a
		// subclass of Graphics with added functionality. By
		// casting we get access to this functionality, including
		// the ability to draw simple objects such as
		// Rectangle2D, Ellipse2D and Line2D
		for (int i = 0; i < theDividers.length; i++) {
			// if (theDigits[i] != null) // It's a good idea to check for null
			theDividers[i].draw(g2);
		}
		for (int i = 0; i < theDigits.length; i++) {
			// if (theDigits[i] != null) // It's a good idea to check for null
			theDigits[i].draw(g2);
		}
	}

	public void saveLap(String s) {
		pw.println("Lap " + numlap + ": " + s);
	}

	public void newTimer() {
		timing++;
		numlap = 0;
		pw.println("Ready For Timing: " + timing);
	}

	public void close() throws FileNotFoundException {
		pw.close();
		Scanner read = new Scanner(savefile);
		while (read.hasNextLine()) {
			System.out.println(read.nextLine());
		}
		System.exit(0);
	}
}
