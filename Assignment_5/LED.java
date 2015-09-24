// CS 0401 Fall 2013
// (abstract) LED class
// This class represents all of the segments that make up an "led".
// It is abstract, and leaves the draw() method to be implemented in the
// subclass.

import java.awt.*;
import java.awt.geom.*;
public abstract class LED implements Drawable
{
	protected Line2D [] segments;
	protected int X, Y, size;

	// This constructor creates all of the 7 segments in the proper
	// relative locations.
	public LED(int newX, int newY, int sz)
	{
		X = newX;
		Y = newY;
		size = sz;
		segments = new Line2D[7];
		int startX = X, startY = Y;
		int endX = startX + size, endY = startY;
		segments[0] = new Line2D.Double(startX, startY, endX, endY);
		startX = endX; startY = endY; endY = endY + size;
		segments[1] = new Line2D.Double(startX, startY, endX, endY);
		startX = endX; startY = endY; endY = endY + size;
		segments[2] = new Line2D.Double(startX, startY, endX, endY);
		startX = endX; startY = endY; endX = endX - size;
		segments[3] = new Line2D.Double(startX, startY, endX, endY);
		startX = endX; startY = endY; endY = endY - size;
		segments[4] = new Line2D.Double(startX, startY, endX, endY);
		startX = endX; startY = endY; endY = endY - size;
		segments[5] = new Line2D.Double(startX, startY, endX, endY);
		startX = X; startY = Y + size; endX = X + size; endY = startY;
		segments[6] = new Line2D.Double(startX, startY, endX, endY);
	}
	
	// Copy constructor.  This allows a copy to be made of this LED with a
	// separate array of segments.  This may be useful in your projects.
	public LED(LED old)
	{
		this(old.X, old.Y, old.size);
	}
	
	public abstract void draw(Graphics2D g);
}