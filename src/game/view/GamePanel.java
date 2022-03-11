package game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import game.controller.Controller;

public class GamePanel extends JPanel
{
	private Controller app;
	private JPanel fruit;

	private SpringLayout layout;
	
	private int x = 0, y = 15;

	private String[][] twoDCells;

	public GamePanel(Controller app)
	{
		super();

		this.app = app;

		this.layout = new SpringLayout();
		this.fruit = new JPanel();

		twoDCells = new String[][] 
				{ 
					{ "Apples", "Oranges", "Bananas" }, 
					{ "Cherries", "Plums", "Nectarines" }, 
					{"Coconuts", "Mango", "Dragon Fruit" },
					{ "Blueberries", "Raspberries", "Poisonberries" } 
				};

		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(800, 600));

		fruit.setBackground( Color.WHITE );
		fruit.setDoubleBuffered( true );
	}

	public void paint(Graphics g) 
	{
        super.paint( g );			
        Graphics2D g2 = ( Graphics2D )g;   
        
        for( int i = 0; i < 4; i++ )
        {
        	for( int j = 0; j < 3; j++ )
        	{
        		g2.drawString( twoDCells[i][j], x, y ) ;
        		y += 30;
        	}
        	
        }
       
        Toolkit.getDefaultToolkit().sync();
	}
	
	private void setupListeners()
	{

	}

	private void setupLayout()
	{

	}
}
