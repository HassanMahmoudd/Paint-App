import java.awt.*;

public abstract class Shape {
	public enum ShapesType {Ellipse, Rectangle, Pen, Square, Circle, Line, Triangle, Eraser};
	public Point start_point;
	public Color strokeColor;
	public Color fillColor;
	public ShapesType type;
	public abstract void draw(Graphics2D g);
	public Shape()
	{
		
	}
	public void draw(Graphics2D g, Color c) 
	{
		this.strokeColor = c;
		draw(g);
		
	}
	public abstract boolean isIn(Point p);
	public abstract void drawSelection(Graphics2D g);
}
