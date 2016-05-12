import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;


public class Eraser extends Shape{
	public Point[] points = new Point[1000];
	public int index = 0;
	public Eraser()
	{
		
	}
	public Eraser(int x, int y)
	{
		this.start_point = new Point(x, y);
		this.type = ShapesType.Eraser;
		this.strokeColor = Color.WHITE;
	}
	
	public boolean addPoint(Point p)
	{
		if(index < points.length)
		{
			points[index++] = new Point(p.x, p.y);
			return true;
		}
		return false;
	}

	public void draw_all(Graphics2D g) 
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(strokeColor);
		g.setStroke(new BasicStroke(3));
		g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
		for(int i = 1; i < index; i++)
		{
			g.drawLine(points[i-1].x, points[i-1].y, points[i].x, points[i].y);
		}
		
	}
	@Override
	public void draw(Graphics2D g) 
	{
		g.setColor(strokeColor);
		g.setStroke(new BasicStroke(3));
		if(index == 1)
			g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
		else
			g.drawLine(points[index-2].x, points[index-2].y, points[index-1].x, points[index-1].y);
		
	}
	@Override
	public boolean isIn(Point p) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void drawSelection(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
