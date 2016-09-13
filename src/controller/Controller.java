package controller;

import javax.swing.text.View;

import model.Model;

public interface Controller {

	public void printToOut(String str);
	public void GenerateMaze(String name, int x, int y, int z);
	public void displayMaze(String name);
	public void displayCross(String args);

}
