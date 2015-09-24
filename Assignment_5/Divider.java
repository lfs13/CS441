// CS 0401 Fall 2013
// (abstract) LED class
// This class represents all of the segments that make up an "led".
// It is abstract, and leaves the draw() method to be implemented in the
// subclass.

import java.awt.*;
import java.awt.geom.*;

public class Divider implements Drawable {
	protected Line2D[] segments;
	protected int X, Y,size;

	// This constructor creates all of the 7 segments in the proper
	// relative locations.
	public Divider(int newX, int newY, int sz) {
		X = newX;
		Y = newY;
		size = sz;
		segments = new Line2D[2];
		
		int startX = X, startY = Y;
		int endX = startX , endY = startY;
		segments[0] = new Line2D.Double(startX, startY, endX, endY);
		startY =(3*size)-(startY-size);
		endY = startY;
		segments[1] = new Line2D.Double(startX, startY, endX, endY);
	}

	public void draw(Graphics2D g) {
		// I am increasing the stroke width so the LED looks better
		g.setStroke(new BasicStroke(8));
		g.setColor(Color.RED);
		for (int c = 0; c < segments.length; c++) {
			g.draw(segments[c]);
		}
		// Setting the stroke back to the default here -- see what happens
		// if you don't do this.
		g.setStroke(new BasicStroke());
	}
}