package game.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import game.view.GameFrame;

public class IOController
{
	public static int[] loadData(String dataFile, GameFrame frame)
	{
		int[] winData = new int[3];
		
		try (FileInputStream loadStream = new FileInputStream(dataFile); ObjectInputStream input = new ObjectInputStream(loadStream))
		{
			int[] loadedWin = new int[3];
			loadedWin = (int[]) input.readObject();
			winData = loadedWin;
		}
		catch(IOException readError)
		{
			JOptionPane.showMessageDialog(frame, readError.getMessage(), "Could not open file", JOptionPane.ERROR_MESSAGE);
		}
		catch(ClassNotFoundException classError)
		{
			JOptionPane.showMessageDialog(frame, classError.getMessage(), "Class Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return winData;
	}

	public static void saveData(String dataFile, int[] win, GameFrame frame)
	{
		try (FileOutputStream saveStream = new FileOutputStream(dataFile); ObjectOutputStream output = new ObjectOutputStream(saveStream))
		{
			output.writeObject(win);
		}
		catch (IOException saveError)
		{
			JOptionPane.showMessageDialog(frame, saveError.getMessage(), "Unable to save", JOptionPane.ERROR_MESSAGE);
		}
	}
}
