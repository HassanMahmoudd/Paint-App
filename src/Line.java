import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

public class Line extends Shape {
	
	Point end_point;
	public Line()
	{
		
	}
	public Line(int x, int y, int xEnd, int yEnd, Color strokeColor, Color fillColor)
	{
		this.start_point = new Point(x, y);
		this.type = ShapesType.Line;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		end_point = new Point(xEnd, yEnd);
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(this.strokeColor);
		g.setStroke(new BasicStroke(3));
		g.drawLine(start_point.x, start_point.y, end_point.x, end_point.y);
		
	}

	@Override
	public boolean isIn(Point p) {
		double distSE = Math.sqrt(Math.pow((double)(end_point.x - start_point.x), 2.0) + Math.pow((double)(end_point.y - start_point.y), 2.0));
		double distEP = Math.sqrt(Math.pow((double)(end_point.x - p.x), 2.0) + Math.pow((double)(end_point.y - p.y), 2.0));
		double distSP = Math.sqrt(Math.pow((double)(p.x - start_point.x), 2.0) + Math.pow((double)(p.y - start_point.y), 2.0));
		
		if ((int)(distEP + distSP) == (int)distSE)
		    return true;
		return false; 
	}
	@Override
	public void drawSelection(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(2));
		g.drawLine(start_point.x, start_point.y, end_point.x, end_point.y);
		g.setStroke(new BasicStroke(1));
		g.drawRect(start_point.x - 4, start_point.y - 4, 8, 8);
		g.drawRect(end_point.x - 4, end_point.y - 4, 8, 8);
		
	}
	
	public void removeSelection(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(2));
		g.drawLine(start_point.x, start_point.y, end_point.x, end_point.y);
		g.setStroke(new BasicStroke(1));
		g.drawRect(start_point.x - 4, start_point.y - 4, 8, 8);
		g.drawRect(end_point.x - 4, end_point.y - 4, 8, 8);
		
	}
}
