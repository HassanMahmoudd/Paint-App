import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;


public class myPanel extends JPanel{
	private Shape.ShapesType shape;
	private BufferedImage image,original_image;
	private Shape cShape;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Color> strokeColors = new ArrayList<Color>();
	private ArrayList<Color> fillColors = new ArrayList<Color>();
	private int shapeIndex = 0;
	private int strokeColorsIndex = 0;
	private int fillColorsIndex = 0;
	private Point clickPoint;
	private boolean isShapePressed = false;
	private boolean isResizable = false;
	private boolean isMoved = false;
	private boolean isColored = false;
	private int pressedShapeIndex;
	private boolean isLine = false;
	private boolean isSouthEast = false;
	private boolean isNorthEast = false;
	private boolean isNorthWest = false;
	private boolean isSouthWest = false;
	private boolean isSouth = false;
	private boolean isEast = false;
	private boolean isWest = false;
	private boolean isNorth = false;
	private Color strokeColor;
	private Color fillColor;
	private boolean removeSelection = false;
	private boolean drawSelection = true;
	private Stack<Shape> undoShapes = new Stack<Shape>();
	private Shape undoShape;
	private Shape deletedShape;
	private Color deletedShapeStrokeColor;
	private Color deletedShapeFillColor;
	private boolean isDeleted = false;
	private int undoCount = 0;
	private int undoCountIndex = 0;
	private Shape redoShape;
	private Shape redoShapeTemp;
	private boolean isUndo = false;
	private boolean isPeekSameAsShape = false;
	private boolean isStartPointTriangle = false;
	private boolean isSecondPointTriangle = false;
	private boolean isThirdPointTriangle = false;
	public static final String fileName = "Shapes.xml";
	private ArrayList<Shape> shapesLoaded = new ArrayList<Shape>();
	

	public myPanel() 
	{
		super();
		this.setBackground(Color.WHITE);
		this.addMouseListener(m1);
		this.addMouseMotionListener(mm1);
	}
	
	MouseMotionListener mm1 = new MouseMotionListener() {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			Point new_point = new Point(e.getX(), e.getY());
		
			if(isShapePressed && shapes.get(pressedShapeIndex).type != Shape.ShapesType.Pen && shapes.get(pressedShapeIndex).type != Shape.ShapesType.Triangle)
			{
				image = cloneImage(original_image);
				int xShift = new_point.x - clickPoint.x;
				int yShift = new_point.y - clickPoint.y;
				clickPoint = new Point(new_point.x, new_point.y);
				if(isResizable && shapes.get(pressedShapeIndex).type == Shape.ShapesType.Rectangle)
				{
					Rectangle r = (Rectangle)shapes.get(pressedShapeIndex);
					if(isSouthEast)
					{
						r.width += xShift;
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorthEast)
					{
						r.width += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouth)
					{
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isEast)
					{
						r.width += xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isWest)
					{
						r.start_point.x += xShift;
						r.width -= xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorth)
					{
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					undoShape = new Rectangle(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
					undoShape.type = Shape.ShapesType.Rectangle;
				}
				else if(isResizable && shapes.get(pressedShapeIndex).type == Shape.ShapesType.Ellipse)
				{
					Ellipse r = (Ellipse)shapes.get(pressedShapeIndex);
					if(isSouthEast)
					{
						r.width += xShift;
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorthEast)
					{
						r.width += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouth)
					{
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isEast)
					{
						r.width += xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isWest)
					{
						r.start_point.x += xShift;
						r.width -= xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorth)
					{
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					undoShape = new Ellipse(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
					undoShape.type = Shape.ShapesType.Ellipse;
				}
				else if(isResizable && shapes.get(pressedShapeIndex).type == Shape.ShapesType.Square)
				{
					Square r = (Square)shapes.get(pressedShapeIndex);
					if(isSouthEast)
					{
						r.width += xShift;
						r.height += yShift;
						
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorthEast)
					{
						r.width += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.height += yShift;
						
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouth)
					{
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isEast)
					{
						r.width += xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isWest)
					{
						r.start_point.x += xShift;
						r.width -= xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorth)
					{
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					undoShape = new Square(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
					undoShape.type = Shape.ShapesType.Square;
				}
				else if(isResizable && shapes.get(pressedShapeIndex).type == Shape.ShapesType.Circle)
				{
					Circle r = (Circle)shapes.get(pressedShapeIndex);
					
					if(isSouthEast)
					{
						r.width += xShift;
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
						
					}
					if(isNorthEast)
					{
						r.width += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouthWest)
					{
						r.width -= xShift;
						r.start_point.x += xShift;
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isSouth)
					{
						r.height += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isEast)
					{
						r.width += xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isWest)
					{
						r.start_point.x += xShift;
						r.width -= xShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isNorth)
					{
						r.start_point.y += yShift;
						r.height -= yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					undoShape = new Circle(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
					undoShape.type = Shape.ShapesType.Circle;
				}
				else
				{
					if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Line)
					{
						if(isResizable)
						{
							Line r = (Line)shapes.get(pressedShapeIndex);
							if(isNorth)
							{
								r.start_point.x += xShift;
								r.start_point.y += yShift;
								r.draw(image.createGraphics());
								r.drawSelection(image.createGraphics());
							}
							else if(isSouth)
							{
								r.end_point.x += xShift;
								r.end_point.y += yShift;
								r.draw(image.createGraphics());
								r.drawSelection(image.createGraphics());
							}
							undoShape = new Line(r.start_point.x, r.start_point.y, r.end_point.x, r.end_point.y, r.strokeColor, r.fillColor);
							undoShape.type = Shape.ShapesType.Line;
						}
						else
						{
							Line r = (Line)shapes.get(pressedShapeIndex);
							r.start_point.x += xShift;
							r.start_point.y += yShift;
							r.end_point.x += xShift;
							r.end_point.y += yShift;
							r.draw(image.createGraphics());
							r.drawSelection(image.createGraphics());
							undoShape = new Line(r.start_point.x, r.start_point.y, r.end_point.x, r.end_point.y, r.strokeColor, r.fillColor);
							undoShape.type = Shape.ShapesType.Line;
							isMoved = true;
						}
					}
					else
					{
						shapes.get(pressedShapeIndex).start_point.x += xShift;
						shapes.get(pressedShapeIndex).start_point.y += yShift;
						shapes.get(pressedShapeIndex).draw(image.createGraphics());
						shapes.get(pressedShapeIndex).drawSelection(image.createGraphics());
						if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Rectangle)
						{
							Rectangle r = (Rectangle)shapes.get(pressedShapeIndex);
							undoShape = new Rectangle(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Ellipse)
						{
							Ellipse r = (Ellipse)shapes.get(pressedShapeIndex);
							undoShape = new Ellipse(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Square)
						{
							Square r = (Square)shapes.get(pressedShapeIndex);
							undoShape = new Square(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Circle)
						{
							Circle r = (Circle)shapes.get(pressedShapeIndex);
							undoShape = new Circle(r.start_point.x, r.start_point.y, r.width, r.height, r.strokeColor, r.fillColor);
						}
						isMoved = true;
					}
					
					
				}
				
				drawSelection = false;
				removeSelection = true;
				repaint();
				
			}
			else if(isShapePressed && shapes.get(pressedShapeIndex).type == Shape.ShapesType.Pen)
			{
				
				image = cloneImage(original_image);
				int xShift = new_point.x - clickPoint.x;
				int yShift = new_point.y - clickPoint.y;
				clickPoint = new Point(new_point.x, new_point.y);
				Pen r = (Pen)shapes.get(pressedShapeIndex);
				
				r.shiftPoints(xShift, yShift);
				r.draw_all(image.createGraphics());
				r.drawSelection(image.createGraphics());
				undoShape = new Pen(r.start_point.x, r.start_point.y, r.strokeColor);
				undoShape.type = Shape.ShapesType.Pen;
				isMoved = true;
				repaint();
			}
			else if(isShapePressed && shapes.get(pressedShapeIndex).type == Shape.ShapesType.Triangle)
			{
				image = cloneImage(original_image);
				int xShift = new_point.x - clickPoint.x;
				int yShift = new_point.y - clickPoint.y;
				clickPoint = new Point(new_point.x, new_point.y);
				
				if(isResizable)
				{
					Triangle r = (Triangle)shapes.get(pressedShapeIndex);
					if(isSecondPointTriangle)
					{
						r.second_point.x += xShift;
						r.second_point.y += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isStartPointTriangle)
					{
						r.start_point.x += xShift;
						r.start_point.y += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					if(isThirdPointTriangle)
					{
						r.third_point.x += xShift;
						r.third_point.y += yShift;
						r.draw(image.createGraphics());
						r.drawSelection(image.createGraphics());
					}
					
					undoShape = new Triangle(r.start_point.x, r.start_point.y, r.second_point.x, r.second_point.y, r.third_point.x, r.third_point.y, r.strokeColor, r.fillColor);
					undoShape.type = Shape.ShapesType.Triangle;
				}
				
				else
				{
					Triangle r = (Triangle)shapes.get(pressedShapeIndex);
					r.start_point.x += xShift;
					r.start_point.y += yShift;
					r.second_point.x += xShift;
					r.second_point.y += yShift;
					r.third_point.x += xShift;
					r.third_point.y += yShift;
					r.draw(image.createGraphics());
					r.drawSelection(image.createGraphics());
					undoShape = new Triangle(r.start_point.x, r.start_point.y, r.second_point.x, r.second_point.y, r.third_point.x, r.third_point.y, r.strokeColor, r.fillColor);
					undoShape.type = Shape.ShapesType.Triangle;
					isMoved = true;
				}
				repaint();
			}
			else 
			{
				if (cShape.type == Shape.ShapesType.Pen) 
				{
					Pen x = (Pen) cShape;
					x.addPoint(new_point);
					x.draw(image.createGraphics());
					undoShape = new Pen(x.start_point.x, x.start_point.y, x.strokeColor);
					repaint();
				} 
				else if (cShape.type == Shape.ShapesType.Eraser) 
				{
					Eraser x = (Eraser) cShape;
					x.addPoint(new_point);
					x.draw(image.createGraphics());
					undoShape = new Eraser(x.start_point.x, x.start_point.y);
					repaint();
				} 
				else if (cShape.type == Shape.ShapesType.Ellipse) 
				{
					image = cloneImage(original_image);
					int width = new_point.x - cShape.start_point.x;
					int height = new_point.y - cShape.start_point.y;
					Ellipse x = (Ellipse) cShape;
					x.width = width;
					x.height = height;
					x.draw(image.createGraphics());
					undoShape = new Ellipse(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
					repaint();
				} 
				else if (cShape.type == Shape.ShapesType.Rectangle) 
				{
					image = cloneImage(original_image);
					int width = new_point.x - cShape.start_point.x;
					int height = new_point.y - cShape.start_point.y;
					Rectangle x = (Rectangle) cShape;
					x.width = width;
					x.height = height;
					x.draw(image.createGraphics());
					undoShape = new Rectangle(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
					repaint();
				}
				else if (cShape.type == Shape.ShapesType.Square) 
				{
					image = cloneImage(original_image);
					int width = new_point.x - cShape.start_point.x;
					int height = width;
					Square x = (Square) cShape;
					x.width = width;
					x.height = height;
					x.draw(image.createGraphics());
					undoShape = new Square(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
					repaint();
				}
				else if (cShape.type == Shape.ShapesType.Circle) 
				{
					image = cloneImage(original_image);
					int width = new_point.x - cShape.start_point.x;
					int height = width;
					Circle x = (Circle) cShape;
					x.width = width;
					x.height = height;
					x.draw(image.createGraphics());
					undoShape = new Circle(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
					repaint();
				} 
				else if(cShape.type == Shape.ShapesType.Line) 
				{
					image = cloneImage(original_image);
					Line x = (Line) cShape;
					x.end_point.x = e.getX();
					x.end_point.y = e.getY();
					x.draw(image.createGraphics());
					undoShape = new Line(x.start_point.x, x.start_point.y, x.end_point.x, x.end_point.y, x.strokeColor, x.fillColor);
					repaint();
				} 
				else if(cShape.type == Shape.ShapesType.Triangle) 
				{
					image = cloneImage(original_image);
					Triangle x = (Triangle) cShape;
					x.second_point.x = e.getX() + 100;
					x.second_point.y = e.getY();
					x.third_point.x = e.getX() - 100;
					x.third_point.y = e.getY();
					x.xPoints[0] = x.start_point.x;
					x.xPoints[1] = x.second_point.x;
					x.xPoints[2] = x.third_point.x;
					x.yPoints[0] = x.start_point.y;
					x.yPoints[1] = x.second_point.y;
					x.yPoints[2] = x.third_point.y;
					x.draw(image.createGraphics());
					undoShape = new Triangle(x.start_point.x, x.start_point.y, x.second_point.x, x.second_point.y, x.third_point.x, x.third_point.y, x.strokeColor, x.fillColor);
					repaint();
				} 
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			boolean isIn = false;
			int index = -1;
			for(int i = 0; i < shapeIndex; i++)
			{
				if(shapes.get(i).isIn(new Point(e.getX(), e.getY())))
				{
					isIn = true;
					index = i;
					break;
				}
			}
			if(isIn)
			{
				if(shapes.get(index).type == Shape.ShapesType.Rectangle)
				{
					Rectangle z = (Rectangle)shapes.get(index);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(e.getX() > end_point.x - 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					else if(e.getX() > end_point.x - 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() < z.start_point.x + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() > end_point.x - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
					else
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else if(shapes.get(index).type == Shape.ShapesType.Ellipse)
				{
					Ellipse z = (Ellipse)shapes.get(index);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(e.getX() > end_point.x - 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					else if(e.getX() > end_point.x - 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() < z.start_point.x + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() > end_point.x - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
					else
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else if(shapes.get(index).type == Shape.ShapesType.Square)
				{
					Square z = (Square)shapes.get(index);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(e.getX() > end_point.x - 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					else if(e.getX() > end_point.x - 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() < z.start_point.x + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() > end_point.x - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
					else
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else if(shapes.get(index).type == Shape.ShapesType.Circle)
				{
					Circle z = (Circle)shapes.get(index);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(e.getX() > end_point.x - 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					else if(e.getX() > end_point.x - 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + 10 && e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() < z.start_point.y + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					else if(e.getX() < z.start_point.x + z.width/2 + 5 && e.getX() > z.start_point.x + z.width/2 - 5 
							&& e.getY() > end_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() < z.start_point.x + 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
					else if(e.getY() < z.start_point.y + z.height/2 + 5 && e.getY() > z.start_point.y + z.height/2 - 5 
							&& e.getX() > end_point.x - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
					else
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else if(shapes.get(index).type == Shape.ShapesType.Triangle)
				{
					Triangle z = (Triangle)shapes.get(index);
					
					if(e.getX() > z.second_point.x - 10 && e.getY() > z.second_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					else if((e.getX() > z.start_point.x - 10) && (e.getX() < z.start_point.x + 10) && (e.getY() > z.start_point.y - 10) && (e.getY() < z.start_point.y + 10))
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					else if(e.getX() > z.third_point.x - 10 && e.getY() > z.third_point.y - 10)
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
					else
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else if(shapes.get(index).type == Shape.ShapesType.Line)
				{
					Line z = (Line)shapes.get(index);
					
					if((e.getX() > z.start_point.x - 10) && (e.getX() < z.start_point.x + 10) && (e.getY() > z.start_point.y - 10) && (e.getY() < z.start_point.y + 10))
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					else if((e.getX() > z.end_point.x - 10) && (e.getX() < z.end_point.x + 10) && (e.getY() > z.end_point.y - 10) && (e.getY() < z.end_point.y + 10))
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
					else
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			else
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			
		}
		
	};
	MouseListener m1 = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			boolean isIn = false;
			isDeleted = false;
			isResizable = false;
			isMoved = false;
			for(int i = 0; i < shapeIndex; i++)
			{
				if(shapes.get(i).isIn(new Point(arg0.getX(), arg0.getY())))
				{
					isIn = true;
					isShapePressed = true;
					pressedShapeIndex = i;
					break;
				}
			}
			
			if(isIn)
			{
				if(pressedShapeIndex != undoCountIndex)
					undoCount = 0;
				isResizable = false;
				isMoved = false;
				isSouthEast = false;
				isNorthEast = false;
				isNorthWest = false;
				isSouthWest = false;
				isSouth = false;
				isEast = false;
				isWest = false;
				isNorth = false;
				isStartPointTriangle = false;
				isSecondPointTriangle = false;
				isThirdPointTriangle = false;
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				clickPoint = new Point(arg0.getX(), arg0.getY());
				setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				original_image = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_ARGB);
				for(int i = 0; i < shapeIndex; i++)
				{
					if(i != pressedShapeIndex)
					{
						if(shapes.get(i).type == Shape.ShapesType.Pen)
						{
							Pen x = (Pen)shapes.get(i);
							x.draw_all(original_image.createGraphics());
						}
						else
							shapes.get(i).draw(original_image.createGraphics());
					}
				}
				if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Rectangle)
				{
					Rectangle z = (Rectangle)shapes.get(pressedShapeIndex);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(drawSelection && !removeSelection)
					{
						z.drawSelection(image.createGraphics());
						drawSelection = false;
						removeSelection = true;
					}
				
					else if(removeSelection && !drawSelection)
					{
						image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
						for(int i = 0; i < shapeIndex; i++)
						{
							if(shapes.get(i).type == Shape.ShapesType.Pen)
							{
								Pen x = (Pen)shapes.get(i);
								x.draw_all(image.createGraphics());
							}
							else
								shapes.get(i).draw(image.createGraphics());
						}
						removeSelection = false;
						drawSelection = true;
					}
				
					if(arg0.getX() > end_point.x - 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
						isResizable = true;
						isSouthEast = true;
					}
					else if(arg0.getX() > end_point.x - 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
						isResizable = true;
						isNorthEast = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
						isResizable = true;
						isNorthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
						isResizable = true;
						isSouthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
						isResizable = true;
						isSouth = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() > end_point.x - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
						isResizable = true;
						isEast = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() < z.start_point.x + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
						isResizable = true;
						isWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						isResizable = true;
						isNorth = true;
					}
					if(!undoShapes.isEmpty())
						if((undoShapes.peek().type != Shape.ShapesType.Rectangle))
						{
							undoShape = new Rectangle(z.start_point.x, z.start_point.y, z.width, z.height, z.strokeColor, z.fillColor);
							undoShapes.push(undoShape);
							isPeekSameAsShape = true;
						}
					repaint();
				}
				else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Ellipse)
				{
					Ellipse z = (Ellipse)shapes.get(pressedShapeIndex);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(drawSelection && !removeSelection)
					{
						z.drawSelection(image.createGraphics());
						drawSelection = false;
						removeSelection = true;
					}
				
					else if(removeSelection && !drawSelection)
					{
						image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
						for(int i = 0; i < shapeIndex; i++)
						{
							if(shapes.get(i).type == Shape.ShapesType.Pen)
							{
								Pen x = (Pen)shapes.get(i);
								x.draw_all(image.createGraphics());
							}
							else
								shapes.get(i).draw(image.createGraphics());
						}
						removeSelection = false;
						drawSelection = true;
					}
					if(arg0.getX() > end_point.x - 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
						isResizable = true;
						isSouthEast = true;
					}
					else if(arg0.getX() > end_point.x - 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
						isResizable = true;
						isNorthEast = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
						isResizable = true;
						isNorthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
						isResizable = true;
						isSouthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
						isResizable = true;
						isSouth = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() > end_point.x - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
						isResizable = true;
						isEast = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() < z.start_point.x + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
						isResizable = true;
						isWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						isResizable = true;
						isNorth = true;
					}
					if(!undoShapes.isEmpty())
						if((undoShapes.peek().type != Shape.ShapesType.Ellipse))
						{
							undoShape = new Ellipse(z.start_point.x, z.start_point.y, z.width, z.height, z.strokeColor, z.fillColor);
							undoShapes.push(undoShape);
							
							isPeekSameAsShape = true;
						}
					repaint();
				}
				else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Square)
				{
					Square z = (Square)shapes.get(pressedShapeIndex);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(drawSelection && !removeSelection)
					{
						z.drawSelection(image.createGraphics());
						drawSelection = false;
						removeSelection = true;
					}
				
					else if(removeSelection && !drawSelection)
					{
						image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
						for(int i = 0; i < shapeIndex; i++)
						{
							if(shapes.get(i).type == Shape.ShapesType.Pen)
							{
								Pen x = (Pen)shapes.get(i);
								x.draw_all(image.createGraphics());
							}
							else
								shapes.get(i).draw(image.createGraphics());
						}
						removeSelection = false;
						drawSelection = true;
					}
					if(arg0.getX() > end_point.x - 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
						isResizable = true;
						isSouthEast = true;
					}
					else if(arg0.getX() > end_point.x - 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
						isResizable = true;
						isNorthEast = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
						isResizable = true;
						isNorthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
						isResizable = true;
						isSouthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
						isResizable = true;
						isSouth = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() > end_point.x - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
						isResizable = true;
						isEast = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() < z.start_point.x + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
						isResizable = true;
						isWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						isResizable = true;
						isNorth = true;
					}
					if(!undoShapes.isEmpty())
						if((undoShapes.peek().type != Shape.ShapesType.Square))
						{
							undoShape = new Square(z.start_point.x, z.start_point.y, z.width, z.height, z.strokeColor, z.fillColor);
							undoShapes.push(undoShape);
							
							isPeekSameAsShape = true;
						}
					repaint();
				}
				else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Circle)
				{
					Circle z = (Circle)shapes.get(pressedShapeIndex);
					Point end_point = new Point((z.start_point.x + z.width), (z.start_point.y + z.height));
					if(drawSelection && !removeSelection)
					{
						z.drawSelection(image.createGraphics());
						drawSelection = false;
						removeSelection = true;
					}
				
					else if(removeSelection && !drawSelection)
					{
						image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
						for(int i = 0; i < shapeIndex; i++)
						{
							if(shapes.get(i).type == Shape.ShapesType.Pen)
							{
								Pen x = (Pen)shapes.get(i);
								x.draw_all(image.createGraphics());
							}
							else
								shapes.get(i).draw(image.createGraphics());
						}
						removeSelection = false;
						drawSelection = true;
					}
					if(arg0.getX() > end_point.x - 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
						isResizable = true;
						isSouthEast = true;
					}
					else if(arg0.getX() > end_point.x - 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
						isResizable = true;
						isNorthEast = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
						isResizable = true;
						isNorthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + 10 && arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
						isResizable = true;
						isSouthWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() > end_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
						isResizable = true;
						isSouth = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() > end_point.x - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
						isResizable = true;
						isEast = true;
					}
					else if(arg0.getY() < z.start_point.y + z.height/2 + 5 && arg0.getY() > z.start_point.y + z.height/2 - 5 
							&& arg0.getX() < z.start_point.x + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
						isResizable = true;
						isWest = true;
					}
					else if(arg0.getX() < z.start_point.x + z.width/2 + 5 && arg0.getX() > z.start_point.x + z.width/2 - 5 
							&& arg0.getY() < z.start_point.y + 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						isResizable = true;
						isNorth = true;
					}
					if(!undoShapes.isEmpty())
						if((undoShapes.peek().type != Shape.ShapesType.Circle))
						{
							undoShape = new Circle(z.start_point.x, z.start_point.y, z.width, z.height, z.strokeColor, z.fillColor);
							undoShapes.push(undoShape);
						
							isPeekSameAsShape = true;
						}
					repaint();
				}
				else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Line)
				{
					Line z = (Line)shapes.get(pressedShapeIndex);
					if(drawSelection && !removeSelection)
					{
						z.drawSelection(image.createGraphics());
						drawSelection = false;
						removeSelection = true;
					}
				
					else if(removeSelection && !drawSelection)
					{
						image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
						for(int i = 0; i < shapeIndex; i++)
						{
							if(shapes.get(i).type == Shape.ShapesType.Pen)
							{
								Pen x = (Pen)shapes.get(i);
								x.draw_all(image.createGraphics());
							}
							else
								shapes.get(i).draw(image.createGraphics());
						}
						removeSelection = false;
						drawSelection = true;
					}
					if((arg0.getX() > z.start_point.x - 10) && (arg0.getX() < z.start_point.x + 10) && (arg0.getY() > z.start_point.y - 10) && (arg0.getY() < z.start_point.y + 10))
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						isResizable = true;
						isNorth = true;
					}
					else if((arg0.getX() > z.end_point.x - 10) && (arg0.getX() < z.end_point.x + 10) && (arg0.getY() > z.end_point.y - 10) && (arg0.getY() < z.end_point.y + 10))
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
						isResizable = true;
						isSouth = true;
					}
					if(!undoShapes.isEmpty())
						if((undoShapes.peek().type != Shape.ShapesType.Line))
						{
							undoShape = new Line(z.start_point.x, z.start_point.y, z.end_point.x, z.end_point.y, z.strokeColor, z.fillColor);
							undoShapes.push(undoShape);
							
							isPeekSameAsShape = true;
						}
					repaint();
				}
				else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Pen)
				{
					Pen z = (Pen)shapes.get(pressedShapeIndex);
					if(drawSelection && !removeSelection)
					{
						z.drawSelection(image.createGraphics());
						drawSelection = false;
						removeSelection = true;
					}
				
					else if(removeSelection && !drawSelection)
					{
						image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
						for(int i = 0; i < shapeIndex; i++)
						{
							if(shapes.get(i).type == Shape.ShapesType.Pen)
							{
								Pen x = (Pen)shapes.get(i);
								x.draw_all(image.createGraphics());
							}
							else
								shapes.get(i).draw(image.createGraphics());
						}
						removeSelection = false;
						drawSelection = true;
					}
					if(!undoShapes.isEmpty())
						if((undoShapes.peek().type != Shape.ShapesType.Pen))
						{
							undoShape = new Pen(z.start_point.x, z.start_point.y, z.strokeColor);
							undoShapes.push(undoShape);
							
							isPeekSameAsShape = true;
						}
					repaint();
				}
				else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Triangle)
				{
					Triangle z = (Triangle)shapes.get(pressedShapeIndex);
					if(drawSelection && !removeSelection)
					{
						z.drawSelection(image.createGraphics());
						drawSelection = false;
						removeSelection = true;
					}
				
					else if(removeSelection && !drawSelection)
					{
						image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
						for(int i = 0; i < shapeIndex; i++)
						{
							if(shapes.get(i).type == Shape.ShapesType.Pen)
							{
								Pen x = (Pen)shapes.get(i);
								x.draw_all(image.createGraphics());
							}
							else
								shapes.get(i).draw(image.createGraphics());
						}
						removeSelection = false;
						drawSelection = true;
					}
					if((arg0.getX() > z.start_point.x - 10) && (arg0.getX() < z.start_point.x + 10) && (arg0.getY() > z.start_point.y - 10) && (arg0.getY() < z.start_point.y + 10))
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						isResizable = true;
						isStartPointTriangle = true;
					}
					else if(arg0.getX() > z.second_point.x - 10 && arg0.getY() > z.second_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
						isResizable = true;
						isSecondPointTriangle = true;
					}
					else if(arg0.getX() > z.third_point.x - 10 && arg0.getY() > z.third_point.y - 10)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
						isResizable = true;
						isThirdPointTriangle = true;
					}
					if(!undoShapes.isEmpty())
						if((undoShapes.peek().type != Shape.ShapesType.Triangle))
						{
							undoShape = new Triangle(z.start_point.x, z.start_point.y, z.second_point.x, z.second_point.y, z.third_point.x, z.third_point.y, z.strokeColor, z.fillColor);
							undoShapes.push(undoShape);
							
							isPeekSameAsShape = true;
						}
					repaint();
				}
				
			}
			else
			{
				isShapePressed = false;
				pressedShapeIndex = -1;
				original_image = cloneImage(image);
				if(shape == Shape.ShapesType.Pen)
				{
					cShape = new Pen(arg0.getX(), arg0.getY(), strokeColor);
				}
				else if(shape == Shape.ShapesType.Eraser)
				{
					System.out.println("Eraser started to draw");
					cShape = new Eraser(arg0.getX(), arg0.getY());
				}
				else if(shape == Shape.ShapesType.Ellipse)
				{
					cShape = new Ellipse(arg0.getX(), arg0.getY(), 0, 0, strokeColor, fillColor);
				}
				else if(shape == Shape.ShapesType.Rectangle)
				{
					cShape = new Rectangle(arg0.getX(), arg0.getY(), 0, 0, strokeColor, fillColor);
				}
				else if(shape == Shape.ShapesType.Square)
				{
					cShape = new Square(arg0.getX(), arg0.getY(), 0, 0, strokeColor, fillColor);
				}
				else if(shape == Shape.ShapesType.Circle)
				{
					cShape = new Circle(arg0.getX(), arg0.getY(), 0, 0, strokeColor, fillColor);
				}
				else if(shape == Shape.ShapesType.Line)
				{
					cShape = new Line(arg0.getX(), arg0.getY(), 0, 0, strokeColor, fillColor);
				}
				else if(shape == Shape.ShapesType.Triangle)
				{
					cShape = new Triangle(arg0.getX(), arg0.getY(), 0, 0, 0, 0, strokeColor, fillColor);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(!isShapePressed)
			{
				shapes.add(cShape);
				shapeIndex++;
				strokeColors.add(strokeColor);
				strokeColorsIndex++;
				fillColors.add(fillColor);
				fillColorsIndex++;
				undoShapes.push(undoShape);
				
			}
			else if(isMoved || isResizable)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				undoShapes.push(undoShape);
				undoCount++;
				undoCountIndex = pressedShapeIndex;
				
			}
			else
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
				
		}
		
	};
	
	public void setShape(Shape.ShapesType shape, Color strokeColor, Color fillColor)
	{
		if(shape == Shape.ShapesType.Eraser)
		{
			
			this.shape = shape;
		}
			
		else
		{
			this.shape = shape;
			this.strokeColor = strokeColor;
			this.fillColor = fillColor;
		}
		
	}
	
	public void undo()
	{
		
		
			if(isDeleted)
			{
				shapes.add(deletedShape);
				strokeColors.add(deletedShapeStrokeColor);
				fillColors.add(deletedShapeFillColor);
				shapeIndex++;
				strokeColorsIndex++;
				fillColorsIndex++;
				undoShapes.push(deletedShape);
				if(deletedShape.type == Shape.ShapesType.Pen)
				{
					Pen x = (Pen)deletedShape;
					x.draw_all(image.createGraphics());
				}
				else
					deletedShape.draw(image.createGraphics());
				isDeleted = false;
			}
			else if(shapeIndex != 0)
			{
				
				redoShape = undoShapes.pop();
				
				if(!(isShapePressed))
				{
					
					
					shapeIndex--;
					redoShapeTemp = shapes.get(shapeIndex);
					shapes.remove(shapeIndex);
					strokeColorsIndex--;
					strokeColors.remove(strokeColorsIndex);
					fillColorsIndex--;
					fillColors.remove(fillColorsIndex);
				}
				if(isShapePressed)
				{
					if(isMoved || isResizable || isColored)
					{
						
						if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Rectangle)
						{
							Rectangle x = (Rectangle)shapes.get(pressedShapeIndex);
							Rectangle y = (Rectangle)undoShapes.peek();
							x = new Rectangle(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
							shapes.set(pressedShapeIndex, x);
							if((undoShapes.peek().type == Shape.ShapesType.Rectangle) && isPeekSameAsShape)
							{
								isShapePressed = false;
								isPeekSameAsShape = false;
							}
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Ellipse)
						{
							
							Ellipse x = (Ellipse)shapes.get(pressedShapeIndex);
							Ellipse y = (Ellipse)undoShapes.peek();
							x = new Ellipse(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
							shapes.set(pressedShapeIndex, x);
							if((undoShapes.peek().type == Shape.ShapesType.Ellipse) && isPeekSameAsShape)
							{
								isShapePressed = false;
								isPeekSameAsShape = false;
							}
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Square)
						{
							Square x = (Square)shapes.get(pressedShapeIndex);
							Square y = (Square)undoShapes.peek();
							x = new Square(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
							shapes.set(pressedShapeIndex, x);
							if((undoShapes.peek().type == Shape.ShapesType.Square) && isPeekSameAsShape)
							{
								isShapePressed = false;
								isPeekSameAsShape = false;
							}
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Circle)
						{
							Circle x = (Circle)shapes.get(pressedShapeIndex);
							Circle y = (Circle)undoShapes.peek();
							x = new Circle(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
							shapes.set(pressedShapeIndex, x);
							if((undoShapes.peek().type == Shape.ShapesType.Circle) && isPeekSameAsShape)
							{
								isShapePressed = false;
								isPeekSameAsShape = false;
							}
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Line)
						{
							Line x = (Line)shapes.get(pressedShapeIndex);
							Line y = (Line)undoShapes.peek();
							x = new Line(y.start_point.x, y.start_point.y, y.end_point.x, y.end_point.y, y.strokeColor, y.fillColor);
							shapes.set(pressedShapeIndex, x);
							if((undoShapes.peek().type == Shape.ShapesType.Line) && isPeekSameAsShape)
							{
								isShapePressed = false;
								isPeekSameAsShape = false;
							}
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Pen)
						{
							Pen x = (Pen)shapes.get(pressedShapeIndex);
							Pen y = (Pen)undoShapes.peek();
							x = new Pen(y.start_point.x, y.start_point.y, y.strokeColor);
							shapes.set(pressedShapeIndex, x);
							if((undoShapes.peek().type == Shape.ShapesType.Pen) && isPeekSameAsShape)
							{
								isShapePressed = false;
								isPeekSameAsShape = false;
							}
						}
						else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Triangle)
						{
							Triangle x = (Triangle)shapes.get(pressedShapeIndex);
							Triangle y = (Triangle)undoShapes.peek();
							x = new Triangle(y.start_point.x, y.start_point.y, y.second_point.x, y.second_point.y,
									y.third_point.x, y.third_point.y, y.strokeColor, y.fillColor);
							shapes.set(pressedShapeIndex, x);
							if((undoShapes.peek().type == Shape.ShapesType.Triangle) && isPeekSameAsShape)
							{
								isShapePressed = false;
								isPeekSameAsShape = false;
							}
						}
						isColored = false;
					}
					else
					{
						
						shapeIndex--;
						shapes.remove(shapeIndex);
						strokeColorsIndex--;
						strokeColors.remove(strokeColorsIndex);
						fillColorsIndex--;
						fillColors.remove(fillColorsIndex);
					}
				}
		
				image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
				
				for(int i = 0; i<shapes.size(); i++)
				{
					
					if(shapes.get(i).type == Shape.ShapesType.Pen)
					{
						Pen x = (Pen)shapes.get(i);
						x.draw_all(image.createGraphics());
					}
					else if(shapes.get(i).type == Shape.ShapesType.Eraser)
					{
						Eraser x = (Eraser)shapes.get(i);
						x.draw_all(image.createGraphics());
					}
					else
						shapes.get(i).draw(image.createGraphics());
				}
			}
			repaint();
			isUndo = true;
		
	}
	
	public void deleteShape()
	{
		if(isShapePressed)
		{
			isDeleted = true;
			deletedShape = shapes.get(pressedShapeIndex);
			deletedShapeStrokeColor = shapes.get(pressedShapeIndex).strokeColor;
			deletedShapeFillColor = shapes.get(pressedShapeIndex).fillColor;
			shapes.remove(pressedShapeIndex);
			strokeColors.remove(pressedShapeIndex);
			fillColors.remove(pressedShapeIndex);
			
			image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			for(int i = 0; i < shapes.size(); i++)
			{
				if(shapes.get(i).type == Shape.ShapesType.Pen)
				{
					Pen x = (Pen)shapes.get(i);
					x.draw_all(image.createGraphics());
				}
				else
					shapes.get(i).draw(image.createGraphics());
			}
			shapeIndex--;
			strokeColorsIndex--;
			fillColorsIndex--;
			repaint();
		}
	}
	
	public void redo()
	{
		
		
		if(isUndo)
		{
			
			undoShapes.push(redoShape);
			if(!(isShapePressed))
			{
				
				shapeIndex++;
				shapes.add(redoShapeTemp);
				strokeColorsIndex++;
				strokeColors.add(redoShapeTemp.strokeColor);
				fillColorsIndex++;
				fillColors.add(redoShapeTemp.fillColor);
			}
			if(isShapePressed)
			{
				if(isMoved || isResizable || isColored)
				{
					
					if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Rectangle)
					{
						Rectangle x = (Rectangle)shapes.get(pressedShapeIndex);
						Rectangle y = (Rectangle)undoShapes.peek();
						x = new Rectangle(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
						shapes.set(pressedShapeIndex, x);
					}
					else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Ellipse)
					{
						
						Ellipse x = (Ellipse)shapes.get(pressedShapeIndex);
						Ellipse y = (Ellipse)undoShapes.peek();
						x = new Ellipse(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
						shapes.set(pressedShapeIndex, x);
					}
					else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Square)
					{
						Square x = (Square)shapes.get(pressedShapeIndex);
						Square y = (Square)undoShapes.peek();
						y.draw(image.createGraphics());
						repaint();
						x = new Square(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
						shapes.set(pressedShapeIndex, x);
					}
					else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Circle)
					{
						Circle x = (Circle)shapes.get(pressedShapeIndex);
						Circle y = (Circle)undoShapes.peek();
						x = new Circle(y.start_point.x, y.start_point.y, y.width, y.height, y.strokeColor, y.fillColor);
						shapes.set(pressedShapeIndex, x);
					}
					else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Line)
					{
						Line x = (Line)shapes.get(pressedShapeIndex);
						Line y = (Line)undoShapes.peek();
						x = new Line(y.start_point.x, y.start_point.y, y.end_point.x, y.end_point.y, y.strokeColor, y.fillColor);
						shapes.set(pressedShapeIndex, x);
					}
					else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Pen)
					{
						Pen x = (Pen)shapes.get(pressedShapeIndex);
						Pen y = (Pen)undoShapes.peek();
						x = new Pen(y.start_point.x, y.start_point.y, y.strokeColor);
						shapes.set(pressedShapeIndex, x);
					}
					else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Triangle)
					{
						Triangle x = (Triangle)shapes.get(pressedShapeIndex);
						Triangle y = (Triangle)undoShapes.peek();
						x = new Triangle(y.start_point.x, y.start_point.y, y.second_point.x, y.second_point.y,
								y.third_point.x, y.third_point.y, y.strokeColor, y.fillColor);
						shapes.set(pressedShapeIndex, x);
					}
					isColored = false;
				}
				else
				{
					
					shapeIndex++;
					shapes.add(redoShapeTemp);
					strokeColorsIndex++;
					strokeColors.add(redoShapeTemp.strokeColor);
					fillColorsIndex++;
					fillColors.add(redoShapeTemp.fillColor);
				}
			}
	
			image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			for(int i = 0; i<shapes.size(); i++)
			{
				
				if(shapes.get(i).type == Shape.ShapesType.Pen)
				{
					Pen x = (Pen)shapes.get(i);
					x.draw_all(image.createGraphics());
				}
				else if(shapes.get(i).type == Shape.ShapesType.Eraser)
				{
					Eraser x = (Eraser)shapes.get(i);
					x.draw_all(image.createGraphics());
				}
				else
					shapes.get(i).draw(image.createGraphics());
			}
			
			repaint();
			isUndo = false;
		}
	}
	
	
	public void isStrokrOrFillSelected(Color test, boolean isStroke)
	{
		if(isShapePressed && isStroke)
		{
			if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Rectangle)
			{
				shapes.get(pressedShapeIndex).strokeColor = test;
				Rectangle x = (Rectangle)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				strokeColors.set(pressedShapeIndex, test);
				undoShape = new Rectangle(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Ellipse)
			{
				shapes.get(pressedShapeIndex).strokeColor = test;
				Ellipse x = (Ellipse)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				strokeColors.set(pressedShapeIndex, test);
				undoShape = new Ellipse(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Square)
			{
				shapes.get(pressedShapeIndex).strokeColor = test;
				Square x = (Square)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				strokeColors.set(pressedShapeIndex, test);
				undoShape = new Square(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Circle)
			{
				shapes.get(pressedShapeIndex).strokeColor = test;
				Circle x = (Circle)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				strokeColors.set(pressedShapeIndex, test);
				undoShape = new Circle(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Line)
			{
				shapes.get(pressedShapeIndex).strokeColor = test;
				Line x = (Line)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				strokeColors.set(pressedShapeIndex, test);
				undoShape = new Line(x.start_point.x, x.start_point.y, x.end_point.x, x.end_point.y, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Pen)
			{
				shapes.get(pressedShapeIndex).strokeColor = test;
				Pen x = (Pen)shapes.get(pressedShapeIndex);
				x.draw_all(image.createGraphics());
				strokeColors.set(pressedShapeIndex, test);
				undoShape = new Pen(x.start_point.x, x.start_point.y, x.strokeColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Triangle)
			{
				shapes.get(pressedShapeIndex).strokeColor = test;
				Triangle x = (Triangle)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				strokeColors.set(pressedShapeIndex, test);
				undoShape = new Triangle(x.start_point.x, x.start_point.y, x.second_point.x, x.second_point.y, x.third_point.x, x.third_point.y, x.strokeColor, x.fillColor);
				repaint();
			}
			undoShapes.push(undoShape);
			isColored = true;
		}
		else if(isShapePressed && !isStroke)
		{
			if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Rectangle)
			{
				shapes.get(pressedShapeIndex).fillColor = test;
				Rectangle x = (Rectangle)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				fillColors.set(pressedShapeIndex, test);
				undoShape = new Rectangle(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Ellipse)
			{
				shapes.get(pressedShapeIndex).fillColor = test;
				Ellipse x = (Ellipse)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				fillColors.set(pressedShapeIndex, test);
				undoShape = new Ellipse(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Square)
			{
				shapes.get(pressedShapeIndex).fillColor = test;
				Square x = (Square)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				fillColors.set(pressedShapeIndex, test);
				undoShape = new Square(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Circle)
			{
				shapes.get(pressedShapeIndex).fillColor = test;
				Circle x = (Circle)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				fillColors.set(pressedShapeIndex, test);
				undoShape = new Circle(x.start_point.x, x.start_point.y, x.width, x.height, x.strokeColor, x.fillColor);
				repaint();
			}
			else if(shapes.get(pressedShapeIndex).type == Shape.ShapesType.Triangle)
			{
				shapes.get(pressedShapeIndex).fillColor = test;
				Triangle x = (Triangle)shapes.get(pressedShapeIndex);
				x.draw(image.createGraphics());
				fillColors.set(pressedShapeIndex, test);
				undoShape = new Triangle(x.start_point.x, x.start_point.y, x.second_point.x, x.second_point.y, x.third_point.x, x.third_point.y, x.strokeColor, x.fillColor);
				repaint();
			}
			undoShapes.push(undoShape);
			isColored = true;
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(image == null)
			image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g.drawRect(1, 1, this.getWidth()-2, this.getHeight()-2);
		g.drawImage(image, 0, 0, null);
	}
	
	public void clear()
	{
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		shapes = new ArrayList<Shape>();
		strokeColors = new ArrayList<Color>();
		fillColors = new ArrayList<Color>();
		strokeColorsIndex = 0;
		fillColorsIndex = 0;
		shapeIndex=0;
		undoShapes = new Stack<Shape>();
		repaint();
	}
	
	private BufferedImage cloneImage(BufferedImage _image)
	{
		if(_image == null)
			return null;
		ColorModel cm = _image.getColorModel();
		boolean isAlpha = cm.isAlphaPremultiplied();
		WritableRaster raster = _image.copyData(null);
		
		return new BufferedImage(cm, raster, isAlpha, null);
	}
	
	public void encodeObjectToXML() throws FileNotFoundException
	{
		JFileChooser chooser = new JFileChooser();
		FileOutputStream fOutput = null;
	    chooser.setCurrentDirectory(new File("/home/me/Documents"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	            fOutput = new FileOutputStream(chooser.getSelectedFile());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
		XMLEncoder xMLEncoder = new XMLEncoder(new BufferedOutputStream(fOutput));
		xMLEncoder.writeObject(shapes);
		xMLEncoder.close();
	}
	
	public void decodeObjectFromXML() throws FileNotFoundException
	{
		
		JFileChooser chooser = new JFileChooser();
		FileInputStream fInput = null;
	    chooser.setCurrentDirectory(new File("/home/me/Documents"));
	    int retrival = chooser.showOpenDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	            fInput = new FileInputStream(chooser.getSelectedFile());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
		XMLDecoder xMLDecoder = new XMLDecoder(new BufferedInputStream((fInput)));
		clear();
		shapes = (ArrayList<Shape>)xMLDecoder.readObject();
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < shapes.size(); i++)
		{
			if(shapes.get(i).type == Shape.ShapesType.Pen)
			{
				Pen x = (Pen)shapes.get(i);
				x.draw_all(image.createGraphics());
			}
			else if(shapes.get(i).type == Shape.ShapesType.Eraser)
			{
				Eraser x = (Eraser)shapes.get(i);
				x.draw_all(image.createGraphics());
			}
			else
				shapes.get(i).draw(image.createGraphics());
			shapeIndex++;
		}
		
		repaint();
		xMLDecoder.close();
	}
}
