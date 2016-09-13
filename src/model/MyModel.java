package model;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import controller.Controller;
import io.MyCompressorOutputStream;

public class MyModel implements Model {

	private HashMap<String, Maze3d> mazes;
	private Controller c;

	public MyModel() {
		mazes = new HashMap<String, Maze3d>();
	}

	public void setController(Controller c) {
		this.c = c;
	}

	public void genrateMaze(String name, int x, int y, int z) {
		Maze3d maze = new Maze3d();
		GrowingTreeGenerator g = new GrowingTreeGenerator();
		maze = g.generate(x, y, z);
		mazes.put(name, maze);
		c.printToOut("a new maze '" + name + "' have been added successfuly.\n");
	}

	public void displayMaze(String name) {
		mazes.get(name).printMaze();
	}

	public void displayCross(String args) {
		String[] argsArray = args.split(" ");
		Maze3d m = mazes.get(argsArray[2]);
		int[][] m2d;

		if (argsArray[0].equalsIgnoreCase("x")) {
			m2d = m.getCrossSectionByX(Integer.valueOf(argsArray[1]));
			m.printCrossSectionByX(m2d);

		}
		if (argsArray[0].equalsIgnoreCase("y")) {
			m2d = m.getCrossSectionByY(Integer.valueOf(argsArray[1]));
			m.printCrossSectionByY(m2d);
		}
		if (argsArray[0].equalsIgnoreCase("z")) {
			m2d = m.getCrossSectionByZ(Integer.valueOf(argsArray[1]));
			m.printCrossSectionByZ(m2d);
		}


	}

	public void saveMaze(String name,String filename){
		try{
		Maze3d m = mazes.get(name);
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(m.toByteArray());
		out.flush();
		out.close();
		}
		catch(Exception e)
		{
			c.printToOut("Problem saving maze.");
		}
	}
}
