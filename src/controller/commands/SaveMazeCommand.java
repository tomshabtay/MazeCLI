package controller.commands;

import controller.Controller;

public class SaveMazeCommand extends CommandClass {

	public SaveMazeCommand(Controller c) {
		super(c);
	}

	@Override
	public void doCommand(String args) {
		c.saveMaze(args);

	}

}
