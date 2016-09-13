package controller.commands;

import controller.Controller;

public class DisplayMazeCommand extends CommandClass {

	public DisplayMazeCommand(Controller c) {
		super(c);
	}

	@Override
	public void doCommand(String args) {
		c.displayMaze(args);

	}

}
