package game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import game.controller.Controller;

public class GamePanel extends JPanel
{
	private Controller app;
	private JPanel fieldPanel;

	private JLabel[][] fieldGrid;

	private SpringLayout layout;
	private GridLayout fieldLayout;

	public GamePanel(Controller app)
	{
		super();

		this.app = app;
		this.fieldPanel = new JPanel();
		this.fieldGrid = new JLabel[15][35];

		this.layout = new SpringLayout();
		this.fieldLayout = new GridLayout(fieldGrid.length, fieldGrid[0].length);

		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(800, 600));

		fieldPanel.setLayout(fieldLayout);
		fieldPanel.setPreferredSize(new Dimension(800, 400));
		
		setupInitialGameField();

		this.add(fieldPanel);

	}

	private void setupInitialGameField()
	{
		for (int row = 0; row < fieldGrid.length; row++)
		{
			for (int column = 0; column < fieldGrid[0].length; column++)
			{
				fieldPanel.add(new JLabel(new ImageIcon(getClass().getResource("/game/view/images/RedFireDown.png"))));
				
			}
		}
	}

	private void setupListeners()
	{

	}

	private void setupLayout()
	{

	}
}
