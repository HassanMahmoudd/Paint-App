import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
public class myFrame extends JFrame{
	JPanel main_panel = new JPanel();
	
	public myFrame(String title)
	{
		
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(1500, 750);
		main_panel.setBackground(new Color(139,	115,	85));
		main_panel.setLayout(null);
		
		
		this.add(main_panel);
		this.setVisible(true);
		
		
	}
	public void addComponent(Component c)
	{
		main_panel.add(c);
	}
	
	
}
