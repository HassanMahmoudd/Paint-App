import java.awt.*;

public class Pen extends Shape{
	public Point[] points = new Point[1000];
	public int index = 0;
	public Pen()
	{
		
	}
	public Pen(int x, int y, Color strokeColor)
	{
		this.start_point = new Point(x, y);
		this.type = ShapesType.Pen;
		this.strokeColor = strokeColor;
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
	public boolean isIn(Point p)
	{
		for(int i = 1; i < index; i++)
		{
			if(p.x == points[i].x && p.y == points[i].y)
				return true;
		}
			
		return false;
		
	}
	
	public void shiftPoints(int xShift, int yShift)
	{
		start_point.x += xShift;
		start_point.y += yShift;
		for(int i = 0; i < index; i++)
		{
			points[i].x += xShift;
			points[i].y += yShift;
		}
	}
	@Override
	public void drawSelection(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(2));
		g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
		for(int i = 1; i < index; i++)
		{
			g.drawLine(points[i-1].x, points[i-1].y, points[i].x, points[i].y);
		}
	}
	
	public void removeSelection(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(2));
		g.drawLine(start_point.x, start_point.y, points[0].x, points[0].y);
		for(int i = 1; i < index; i++)
		{
			g.drawLine(points[i-1].x, points[i-1].y, points[i].x, points[i].y);
		}
	}

}
