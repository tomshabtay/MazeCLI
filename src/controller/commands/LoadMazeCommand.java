package controller.commands;

import controller.Controller;

public class LoadMazeCommand extends CommandClass {

	public LoadMazeCommand(Controller c) {
		super(c);
	}

	@Override
	public void doCommand(String args) {
		c.loadMaze(args);

	}

}
