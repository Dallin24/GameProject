package game.tests; //Change!

/**
 * Project imports
 */
import game.controller.Controller; //Change!
import game.view.GamePanel; //Change!
import game.view.GameFrame; //Change!
import javax.swing.*;

import java.awt.Component;
/**
 * Reflection imports
 */
import java.lang.reflect.*;
/**
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGameFrame
{
	private Controller testedController; //Change!
	private GameFrame testedFrame; //Change!

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
		this.testedFrame = new GameFrame(testedController);
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController = null;
		this.testedFrame = null;
	}

	@Test
	void testGameFrame()
	{
		assertTrue(testedFrame instanceof JFrame, "... needs to extend JFrame");
		Method [] methods = testedFrame.getClass().getDeclaredMethods();
		assertTrue(methods.length != 0, "You need at least 1 method in the GameFrame");
		assertTrue(testedFrame.getTitle().length() > 5, "Your title needs at least 6 letters");
		assertTrue(testedFrame.getContentPane() instanceof GamePanel, "Your GameFrame needs to have a GamePanel inside");  //Change!
	}

}