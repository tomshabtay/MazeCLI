package controller;

import javax.swing.text.View;

import model.Model;

public interface Controller {

	public void printToOut(String str);
	public void GenerateMaze(String name, int x, int y, int z);
	public void displayMaze(String name);
	public void displayCross(String args);
	public void saveMaze(String args);
	public void loadMaze(String args);
	public void solveMaze(String args);
	public void displaySolution(String args);
	public void exit();
}
