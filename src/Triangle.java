import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;


public class Triangle extends Shape {
	
	Point second_point;
	Point third_point;
	int xPoints[] = new int[3];
    int yPoints[] = new int[3];
    int nPoints = 3;
    public Triangle()
    {
    	
    }
	public Triangle(int x, int y, int x1, int y1, int x2, int y2, Color strokeColor, Color fillColor)
	{
		this.start_point = new Point(x, y);
		this.type = ShapesType.Triangle;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		second_point = new Point(x1, y1);
		third_point = new Point(x2, y2);
		xPoints[0] = start_point.x;
		xPoints[1] = second_point.x;
		xPoints[2] = third_point.x;
		yPoints[0] = start_point.y;
		yPoints[1] = second_point.y;
		yPoints[2] = third_point.y;
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(fillColor);
		g.fillPolygon(xPoints, yPoints, nPoints);
		g.setStroke(new BasicStroke(3));
		g.setColor(strokeColor);
		g.drawLine(start_point.x, start_point.y, second_point.x, second_point.y);
		g.drawLine(second_point.x, second_point.y, third_point.x, third_point.y);
		g.drawLine(third_point.x, third_point.y, start_point.x, start_point.y);
		
		
		
	}

	@Override
	public boolean isIn(Point p) {
		
		Point p0 = new Point(start_point.x, start_point.y);
		Point p1 = new Point(second_point.x, second_point.y);
		Point p2 = new Point(third_point.x, third_point.y);
		int Ymax = Math.max(p0.y, p1.y);
		int YMax = Math.max(Ymax, p2.y);
		
		int Ymin = Math.min(p0.y, p1.y);
		int YMin = Math.min(Ymin, p2.y);
		
		double Area = 1.0/(2*(((-1)*(p1.y))*(p2.x) + (p0.y)*(((-1)*(p1.x)) + (p2.x)) + (p0.x)*((p1.y) - (p2.y)) + (p1.x)*(p2.y)));
		double s = 1.0/(2*Area)*(p0.y*p2.x - p0.x*p2.y + (p2.y - p0.y)*p.x + (p0.x - p2.x)*p.y);
		double t = 1.0/(2*Area)*(p0.x*p1.y - p0.y*p1.x + (p0.y - p1.y)*p.x + (p1.x - p0.x)*p.y);
		
		if((s>0) && (t>0) && (p.y<=YMax) && (p.y>YMin))
			return true;
		return false;
		
	}
	
	@Override
	public void drawSelection(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		
				
		g.setStroke(new BasicStroke(2));
		g.drawLine(start_point.x, start_point.y, second_point.x, second_point.y);
		g.drawLine(second_point.x, second_point.y, third_point.x, third_point.y);
		g.drawLine(third_point.x, third_point.y, start_point.x, start_point.y);
		g.setStroke(new BasicStroke(1));
		g.drawRect(start_point.x - 4, start_point.y - 4, 8, 8);
		g.drawRect(second_point.x - 4, second_point.y - 4, 8, 8);
		g.drawRect(third_point.x - 4, third_point.y - 4, 8, 8);
		
	}
	public void removeSelection(Graphics2D g) {
		g.setColor(Color.WHITE);
		
				
		g.setStroke(new BasicStroke(2));
		g.drawLine(start_point.x, start_point.y, second_point.x, second_point.y);
		g.drawLine(second_point.x, second_point.y, third_point.x, third_point.y);
		g.drawLine(third_point.x, third_point.y, start_point.x, start_point.y);
		g.setStroke(new BasicStroke(1));
		g.drawRect(start_point.x - 4, start_point.y - 4, 8, 8);
		g.drawRect(second_point.x - 4, second_point.y - 4, 8, 8);
		g.drawRect(third_point.x - 4, third_point.y - 4, 8, 8);
		
	}
}
