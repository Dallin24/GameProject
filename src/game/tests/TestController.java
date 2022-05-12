package game.tests; // Change!!!!

import game.controller.Controller; // Change!!!!

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import javax.swing.JFrame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestController
{
	private Controller testedController;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController  = null;
	}

	@Test
	void testLoadData()
	{
		System.out.println(testedController.toString());
		assertTrue(!testedController.toString().contains("The current records are: " + "Red Wins: " + "0" + " Blue Wins: " + "0" + " Ties: " + "0"), "Didn't Load Data or Data was Reset");
		
	}
	
	@Test
	void testSaveFileLocation()
	{
		assertTrue(testedController.toString().contains("File: save.wins"), "Save file location not found");
	}
	
	@Test
	void testDataLength()
	{
		assertTrue(testedController.toString().contains("Array Length: 3"), "WinRecrods Array was not initialized or initialized incorrectly");
	}

}