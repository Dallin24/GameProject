package game.tests; // Change!!!!

import game.controller.Controller; // Change!!!!

import static org.junit.jupiter.api.Assertions.*;

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
		assertTrue(!testedController.toString().equals("The current records are: " + "Red Wins: " + "0" + " Blue Wins: " + "0" + " Ties: " + "0"), "Didn't Load Data or Data was Reset");
		
	}

}