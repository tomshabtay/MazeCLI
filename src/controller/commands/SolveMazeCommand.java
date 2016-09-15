package controller.commands;

import controller.Controller;

public class SolveMazeCommand extends CommandClass {

	public SolveMazeCommand(Controller c) {
		super(c);
	}

	@Override
	public void doCommand(String args) {
		c.solveMaze(args);

	}

}
