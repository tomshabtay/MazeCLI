package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.SearchableMaze;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {

	private HashMap<String, Maze3d> mazes;
	private HashMap<String, Solution> solutions;
	private Controller c;

	public MyModel() {
		mazes = new HashMap<String, Maze3d>();
		solutions = new HashMap<String, Solution>();
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

	public void saveMaze(String args) {

		try {
			String[] argsArray = args.split(" ");
			Maze3d m = mazes.get(argsArray[0]);
			String filename = argsArray[1] + ".maz";
			int sizeInt = m.toByteArray().length;
			
			
			byte[] size = new byte[2];
			
			size[0] = (byte) (sizeInt/255);
			size[1] = (byte) (sizeInt%255);
			
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(filename));

			// Saving the maze size
			out.write(size);


			// Saving the maze
			out.write(m.toByteArray());
			out.flush();
			out.close();
			c.printToOut("Maze saved.");
		} catch (Exception e) {
			//c.printToOut("Problem saving the maze.");
			e.printStackTrace();
		}
	}

	public void loadMaze(String args){
		try{
			String[] argsArray = args.split(" ");
			String mazename = argsArray[0];
			String filename = argsArray[1] + ".maz";
			int sizeA,sizeB,size;
			
			InputStream in = new MyDecompressorInputStream(new FileInputStream(filename));
			sizeA = (int)in.read();
			sizeB = (int) in.read();
			size = sizeA*255 + sizeB;
			
			byte b[] = new byte[size];
			in.read(b);
			in.close();
			Maze3d m = new Maze3d(b);
			mazes.put(mazename, m);
			c.printToOut("Maze loaded.");
		}
		catch(Exception e)
		{
			//c.printToOut("Problem loading the maze.");
			e.printStackTrace();
		}
	}

	public void solveMaze(String args) {
		String[] argsArray = args.split(" ");
		Maze3d maze = mazes.get(argsArray[0]);
		SearchableMaze searchableMaze = new SearchableMaze(maze);
		Searcher searcher;
		Solution solution = null;

		// Creating solution
		if (argsArray[1].equalsIgnoreCase("bfs")) {
			searcher = new BFS();
			solution = searcher.search(searchableMaze);
		} else if (argsArray[1].equalsIgnoreCase("dfs")) {
			searcher = new DFS();
			solution = searcher.search(searchableMaze);
		}

		this.solutions.put(argsArray[0], solution);
		c.printToOut("Solution for '" + argsArray[0] + "' is ready.\n");

	}

	public void displaySolution(String args) {
		String[] argsArray = args.split(" ");
		solutions.get(argsArray[0]).printSolution();
	}
}
