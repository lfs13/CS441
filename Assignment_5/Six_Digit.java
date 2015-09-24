//import java.awt.Graphics2D;
import java.awt.*;

public class Six_Digit extends Digit {
	private int[][] seg = { { 0, 1, 2, 3, 4, 5 }, // 0
			{ 1, 2 }, // 1
			{ 0, 1, 3, 4, 6 }, // 2
			{ 0, 1, 2, 3, 6 }, // 3
			{ 1, 2, 5, 6 }, // 4
			{ 0, 2, 3, 5, 6 }, // 5
			{ 2, 3, 4, 5, 6 }, // 6
	};
	private int active;

	public Six_Digit(int newX, int newY, int sz) {
		super(newX, newY, sz);
		active = 0;

	}

	public void draw(Graphics2D g) {
		// I am increasing the stroke width so the LED looks better
		g.setStroke(new BasicStroke(8));
		g.setColor(Color.RED);
		for (int c = 0; c < seg[active].length; c++) {
			g.draw(segments[seg[active][c]]);
		}
		// Setting the stroke back to the default here -- see what happens
		// if you don't do this.
		g.setStroke(new BasicStroke());

	}

	public void mutate() {
		if (active == 5) {
			active = 0;
		} else
			active++;

	}

	public int getActive() {
		return active;
	}

	public void reset(){
		active=0;
	}

}
