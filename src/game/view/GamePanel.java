package game.view;

import java.awt.Color;
import java.awt.Dimension;
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
	private JPanel playScreen;
	
	private SpringLayout layout;
	
	private Image image;
	private int cellSize;
	private int numCols;
	private int numRows;
	
	public GamePanel(Controller app, int numRows, int numCols)
	{
		super();
		
		this.app = app;
		this.numRows = numRows;
		this.numCols = numCols;
		this.playScreen = new JPanel();
		
		this.layout = new SpringLayout();
		
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(800, 600));
		
		Dimension size = new Dimension(800, 600);
		int screenHeight = (int)size.getHeight();
		int screenWidth = (int)size.getWidth();
		
		cellSize = (int) Math.max(1, (screenWidth *  0.9) / Math.max(numRows, numCols));
		image = new BufferedImage(numCols * cellSize, numRows * cellSize, BufferedImage.TYPE_INT_RGB);
		
		playScreen.setPreferredSize(new Dimension(numCols * cellSize, numRows * cellSize));
		playScreen.setBackground(Color.black);
		
		this.add(playScreen);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.SOUTH, playScreen, 0, SpringLayout.SOUTH, this);
	}
}
