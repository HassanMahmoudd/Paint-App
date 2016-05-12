import java.awt.*;


public class Ellipse extends Shape {
	public int width;
	public int height;
	public Ellipse()
	{
		
	}
	public Ellipse(int x, int y, int width, int height, Color strokeColor, Color fillColor)
	{
		this.start_point = new Point(x, y);
		this.width = width;
		this.height = height;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.type = ShapesType.Ellipse;
		
	}
	@Override
	public void draw(Graphics2D g) 
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(fillColor);
		g.fillOval(this.start_point.x, this.start_point.y, this.width, this.height);
		g.setStroke(new BasicStroke(3));
		g.setColor(strokeColor);
		g.drawOval(this.start_point.x, this.start_point.y, this.width, this.height);
		
		
	}
	@Override
	public boolean isIn(Point p)
	{
		if(p.x > this.start_point.x && p.y > this.start_point.y && 
				p.x < (this.width + this.start_point.x) && p.y < (this.height + this.start_point.y))
			return true;
		return false;
		
	}
	@Override
	public void drawSelection(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(2));
		g.drawRect(start_point.x, start_point.y, width, height);
		g.setStroke(new BasicStroke(1));
		g.drawRect(start_point.x - 4, start_point.y - 4, 8, 8);
		g.drawRect(start_point.x + width - 4, start_point.y + height - 4, 8, 8);
		g.drawRect(start_point.x + width - 4, start_point.y - 4, 8, 8);
		g.drawRect(start_point.x - 4, start_point.y + height - 4, 8, 8);
		g.drawRect(start_point.x + width/2 - 4, start_point.y - 4, 8, 8);
		g.drawRect(start_point.x + width - 4, start_point.y + height/2 - 4, 8, 8);
		g.drawRect(start_point.x - 4, start_point.y + height/2 - 4, 8, 8);
		g.drawRect(start_point.x + width/2 - 4, start_point.y + height - 4, 8, 8);
		
	}
	public void removeSelection(Graphics2D g) {
		g.setColor(Color.WHITE);
			
		g.setStroke(new BasicStroke(2));
		g.drawRect(start_point.x, start_point.y, width, height);
		g.setStroke(new BasicStroke(1));
		g.drawRect(start_point.x - 4, start_point.y - 4, 8, 8);
		g.drawRect(start_point.x + width - 4, start_point.y + height - 4, 8, 8);
		g.drawRect(start_point.x + width - 4, start_point.y - 4, 8, 8);
		g.drawRect(start_point.x - 4, start_point.y + height - 4, 8, 8);
		g.drawRect(start_point.x + width/2 - 4, start_point.y - 4, 8, 8);
		g.drawRect(start_point.x + width - 4, start_point.y + height/2 - 4, 8, 8);
		g.drawRect(start_point.x - 4, start_point.y + height/2 - 4, 8, 8);
		g.drawRect(start_point.x + width/2 - 4, start_point.y + height - 4, 8, 8);
		
	}
	
}
