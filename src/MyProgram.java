import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MyProgram {
	static myPanel panel = new myPanel();
	static Color strokeColor = Color.black;
	static Color fillColor = Color.white;
	
	public static void main(String[] args) {
		//Look and feel buffer.
		try {
			//UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
		
		
		myFrame frame = new myFrame("Java Paint Application");
		ActionListener a1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String shape = ((JButton) e.getSource()).getText();
				if(shape == "Ellipse")
					panel.setShape(Shape.ShapesType.Ellipse, strokeColor, fillColor);
				else if(shape == "Rectangle")
					panel.setShape(Shape.ShapesType.Rectangle, strokeColor, fillColor);
				else if(shape == "Pen")
					panel.setShape(Shape.ShapesType.Pen, strokeColor, fillColor);
				else if(shape == "Eraser")
					panel.setShape(Shape.ShapesType.Eraser, strokeColor, fillColor);
				else if(shape == "Square")
					panel.setShape(Shape.ShapesType.Square, strokeColor, fillColor);
				else if(shape == "Circle")
					panel.setShape(Shape.ShapesType.Circle, strokeColor, fillColor);
				else if(shape == "Line")
					panel.setShape(Shape.ShapesType.Line, strokeColor, fillColor);
				else if(shape == "Triangle")
					panel.setShape(Shape.ShapesType.Triangle, strokeColor, fillColor);
				
			}
			
		};
		
		ActionListener a2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.clear();
				
			}
			
		};
		
		ActionListener a3 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				strokeColor = JColorChooser.showDialog(null, "Pick a Stroke", Color.BLACK);
				panel.isStrokrOrFillSelected(strokeColor, true);
			}
			
		};
		
		ActionListener a4 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fillColor = JColorChooser.showDialog(null, "Pick a Fill", Color.BLACK);
				panel.isStrokrOrFillSelected(fillColor, false);
			}
			
		};
		
		ActionListener a5 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
	
				panel.deleteShape();
			}
			
		};
		
		ActionListener a6 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
	
				panel.undo();
			}
			
		};
		
		ActionListener a7 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
	
				panel.redo();
			}
			
		};
		
		ActionListener a8 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					panel.encodeObjectToXML();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		};
		
		ActionListener a9 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					panel.decodeObjectFromXML();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		};
		
		JButton ellipse = new JButton("Ellipse");
		ellipse.setSize(100,65);
		ellipse.setLocation(35,35);
		ellipse.setBackground(new Color(205,170,125));
		ellipse.setFont(new Font("Serif", Font.BOLD, 14));
		Icon ellipseIcon = new ImageIcon("./src/Ellipse.png");
		ellipse.setIcon(ellipseIcon);
		
		JButton rectangle = new JButton("Rectangle");
		rectangle.setSize(100,65);
		rectangle.setLocation(145,35);
		rectangle.setFont(new Font("Serif", Font.BOLD, 14));
		rectangle.setBackground(new Color(205,170,125));
		Icon rectangleIcon = new ImageIcon("./src/Rectangle.png");
		rectangle.setIcon(rectangleIcon);
		
		JButton circle = new JButton("Circle");
		circle.setSize(100,65);
		circle.setLocation(35,110);
		circle.setFont(new Font("Serif", Font.BOLD, 14));
		circle.setBackground(new Color(205,170,125));
		Icon circleIcon = new ImageIcon("./src/Circle.png");
		circle.setIcon(circleIcon);
		
		JButton square = new JButton("Square");
		square.setSize(100,65);
		square.setLocation(145,110);
		square.setFont(new Font("Serif", Font.BOLD, 14));
		square.setBackground(new Color(205,170,125));
		Icon squareIcon = new ImageIcon("./src/Square.png");
		square.setIcon(squareIcon);
		
		JButton line = new JButton("Line");
		line.setSize(100,65);
		line.setLocation(35,185);
		line.setFont(new Font("Serif", Font.BOLD, 14));
		line.setBackground(new Color(205,170,125));
		Icon lineIcon = new ImageIcon("./src/Line.png");
		line.setIcon(lineIcon);
		
		JButton triangle = new JButton("Triangle");
		triangle.setSize(100,65);
		triangle.setLocation(145,185);
		triangle.setFont(new Font("Serif", Font.BOLD, 14));
		triangle.setBackground(new Color(205,170,125));
		Icon triangleIcon = new ImageIcon("./src/Triangle.png");
		triangle.setIcon(triangleIcon);
		
		JButton brush = new JButton("Pen");
		brush.setSize(100,65);
		brush.setLocation(35,260);
		brush.setFont(new Font("Serif", Font.BOLD, 14));
		brush.setBackground(new Color(205,170,125));
		Icon brushIcon = new ImageIcon("./src/Brush.png");
		brush.setIcon(brushIcon);
		
		JButton eraser = new JButton("Eraser");
		eraser.setSize(100,65);
		eraser.setLocation(145,260);
		eraser.setFont(new Font("Serif", Font.BOLD, 14));
		eraser.setBackground(new Color(205,170,125));
		Icon eraserIcon = new ImageIcon("./src/Eraser.png");
		eraser.setIcon(eraserIcon);
		
		JButton clear = new JButton("Clear");
		clear.setSize(100,65);
		clear.setLocation(35,335);
		clear.setFont(new Font("Serif", Font.BOLD, 14));
		clear.setBackground(new Color(205,170,125));
		Icon clearIcon = new ImageIcon("./src/Clear.png");
		clear.setIcon(clearIcon);
		
		JButton deleteshape = new JButton("DeleteShape");
		deleteshape.setSize(100,65);
		deleteshape.setLocation(145,335);
		deleteshape.setFont(new Font("Serif", Font.BOLD, 14));
		deleteshape.setBackground(new Color(205,170,125));
		Icon deleteShapeIcon = new ImageIcon("./src/Delete.png");
		deleteshape.setIcon(deleteShapeIcon);
		
		JButton strokecolor = new JButton("StrokeColor");
		strokecolor.setSize(100, 65);
		strokecolor.setLocation(35,410);
		strokecolor.setFont(new Font("Serif", Font.BOLD, 14));
		strokecolor.setBackground(new Color(205,170,125));
		Icon strokecolorIcon = new ImageIcon("./src/StrokeColor.png");
		strokecolor.setIcon(strokecolorIcon);
		
		JButton fillcolor = new JButton("FillColor");
		fillcolor.setSize(100,65);
		fillcolor.setLocation(145,410);
		fillcolor.setFont(new Font("Serif", Font.BOLD, 14));
		fillcolor.setBackground(new Color(205,170,125));
		Icon fillcolorIcon = new ImageIcon("./src/FillColor.png");
		fillcolor.setIcon(fillcolorIcon);
		
		JButton undo = new JButton("Undo");
		undo.setSize(100,65);
		undo.setLocation(35,485);
		undo.setFont(new Font("Serif", Font.BOLD, 14));
		undo.setBackground(new Color(205,170,125));
		Icon undoIcon = new ImageIcon("./src/Undo.png");
		undo.setIcon(undoIcon);
		
		JButton redo = new JButton("Redo");
		redo.setSize(100,65);
		redo.setLocation(145,485);
		redo.setFont(new Font("Serif", Font.BOLD, 14));
		redo.setBackground(new Color(205,170,125));
		Icon redoIcon = new ImageIcon("./src/Redo.png");
		redo.setIcon(redoIcon);
		
		JButton load = new JButton("Load");
		load.setSize(100,65);
		load.setLocation(35,560);
		load.setFont(new Font("Serif", Font.BOLD, 14));
		load.setBackground(new Color(205,170,125));
		Icon loadIcon = new ImageIcon("./src/Load.png");
		load.setIcon(loadIcon);
		
		JButton save = new JButton("Save");
		save.setSize(100,65);
		save.setLocation(145,560);
		save.setFont(new Font("Serif", Font.BOLD, 14));
		save.setBackground(new Color(205,170,125));
		Icon saveIcon = new ImageIcon("./src/Save.png");
		save.setIcon(saveIcon);
		
		ellipse.addActionListener(a1);
		rectangle.addActionListener(a1);
		clear.addActionListener(a2);
		brush.addActionListener(a1);
		square.addActionListener(a1);
		circle.addActionListener(a1);
		line.addActionListener(a1);
		triangle.addActionListener(a1);
		strokecolor.addActionListener(a3);
		fillcolor.addActionListener(a4);
		deleteshape.addActionListener(a5);
		undo.addActionListener(a6);
		redo.addActionListener(a7);
		save.addActionListener(a8);
		load.addActionListener(a9);
		eraser.addActionListener(a1);
		
		panel.setSize(1180,580);
		panel.setLocation(260, 40);
		
		
		frame.addComponent(ellipse);
		frame.addComponent(rectangle);
		frame.addComponent(clear);
		frame.addComponent(brush);
		frame.addComponent(square);
		frame.addComponent(circle);
		frame.addComponent(line);
		frame.addComponent(triangle);
		frame.addComponent(strokecolor);
		frame.addComponent(fillcolor);
		frame.addComponent(deleteshape);
		frame.addComponent(undo);
		frame.addComponent(redo);
		frame.addComponent(save);
		frame.addComponent(load);
		frame.addComponent(eraser);
		
		frame.addComponent(panel);

	}
	
}
