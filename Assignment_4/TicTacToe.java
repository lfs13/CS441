import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class TicTacToe extends JPanel {
	private int row, col;
	private JFrame theWindow;
	private JPanel bp;
	private JPanel ip;
	private JButton[][] theButtons;
	private JButton undo;
	private JLabel info;
	private int index = 0;
	private int[] mr;
	private int[] mc;
	private int moves;
	private boolean turnx = true;
	private boolean done = false;

	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe(args[0]);
	}

	public TicTacToe(String s) {
		row = Integer.parseInt(s);
		col = Integer.parseInt(s);
		mr = new int[row * col];
		mc = new int[row * col];
		theWindow = new JFrame("Tic-Tac-Toe");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setLayout(new BorderLayout());
		// //////////////BUTTON/PANEL/////////////
		bp = new JPanel();
		bp.setLayout(new GridLayout(row, col));
		theButtons = new JButton[row][col];
		Font f = new Font("Dialog", Font.PLAIN, 30);
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				theButtons[r][c] = new JButton(" ");
				theButtons[r][c].addActionListener(new ButtonPanelListener());
				theButtons[r][c].setFont(f);
				bp.add(theButtons[r][c]);
			}
		}
		// //////////////INFO/PANEL////////////////////////
		ip = new JPanel();
		setLayout(new GridLayout(1, 3));
		JButton reset = new JButton("New Game");
		reset.addActionListener(new ResetListener());
		ip.add(reset);
		undo = new JButton("Undo");
		undo.setEnabled(false);
		undo.addActionListener(new UndoListener());
		ip.add(undo);
		info = new JLabel("X goes first!");
		ip.add(info);
		// ///////////////////////////////////////////////
		theWindow.add(bp, BorderLayout.CENTER);
		theWindow.add(ip, BorderLayout.SOUTH);
		theWindow.pack();
		theWindow.setVisible(true);

	}

	// //----------------------------------LISTENERS---------------------------------------////

	private class ButtonPanelListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals(" ")) {
				int r = 0, c = 0;
				for (int i = 0; i < theButtons.length; i++) {
					for (int j = 0; j < theButtons.length; j++) {
						if (b == theButtons[i][j]) {
							r = i;
							c = j;
						}
					}

				}
				undo.setEnabled(true);
				mr[index] = r;
				mc[index] = c;
				index++;
				if (turnx == true) {
					b.setText("X");
					moves++;
					if (check(r, c))
						win(0);
					else if (!(check(r, c)) && (moves == (row * col)))
						win(2);
					else {
						turnx = false;
						info.setText("O goes next!");
					}
				} else {
					b.setText("O");
					moves++;
					if (check(r, c))
						win(1);
					else if (!(check(r, c)) && (moves == (row * col)))
						win(2);
					else {
						info.setText("X goes next!");
						turnx = true;
					}
				}

			}

		}

		public boolean check(int a, int b) {
			boolean win = false;
			String value = theButtons[a][b].getText();
			int count = 0;
			int r = a;
			int c = b;
			// DIAGONAL FOR TOP LEFT & BOTTOM RIGHT//
			if ((r == row-1 && c == col-1) || (r == 0 && c == 0)) {
				int c1 = 0;
				for (int i = 0; i < row; i++) {
					if (theButtons[i][i].getText().equals(value))
						c1++;
				}
				if (c1 == row)
					return true;
			}
			// DIAGONAL FOR TOP RIGHT & BOTTOM LEFT//
			if ((r == 0 && c == col-1) || (r == row-1 && c == 0)) {
				int c2 = 0;
				int r2 =0;
				for (int i = row - 1; i >=0; i--) {
					if (theButtons[i][r2].getText().equals(value))
						c2++;
					r2++;
				}
				if (c2 == row)
					return true;
			}
			// HORIZONTAL CHECK//
			for (int i = 0; i < col; i++) {
				if (theButtons[r][i].getText().equals(value))
					count++;
			}
			if (count == row)
				return true;
			else
				count = 0;
			// VERTICAL CHECK//
			for (int i = 0; i < row; i++) {
				if (theButtons[i][c].getText().equals(value))
					count++;
			}
			if (count == row)
				return true;
			else
				return false;

		}
	}

	private class ResetListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int n = JOptionPane.showConfirmDialog(null,
					"Do you really want to start a new game?", "New Game?",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				for (int r = 0; r < row; r++) {
					for (int c = 0; c < col; c++) {
						theButtons[r][c].setText(" ");
						theButtons[r][c].setEnabled(true);
					}
				}
				done=false;
				undo.setEnabled(false);
				info.setText("X goes first!");
				moves = 0;
				index = 0;
				turnx = true;

			}
		}
	}

	private class UndoListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (done) {
				for (int r = 0; r < row; r++) {
					for (int c = 0; c < col; c++) {
						theButtons[r][c].setEnabled(true);
					}
				}

			}

			theButtons[mr[index - 1]][mc[index - 1]].setText(" ");
			if (index == 1)
				b.setEnabled(false);
			moves--;
			index--;
			turnx = !turnx;

		}
	}

	public void win(int w) {
		done=true;

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				theButtons[r][c].setEnabled(false);
			}
		}

		if (w == 0)
			info.setText("X Wins!");
		else if (w == 1)
			info.setText("O Wins!");
		else
			info.setText("DRAW!");

	}
}
