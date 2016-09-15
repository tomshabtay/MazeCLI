package controller.commands;

import controller.Controller;

public class DisplaySolutionCommand extends CommandClass {

	public DisplaySolutionCommand(Controller c) {
		super(c);
	}

	@Override
	public void doCommand(String args) {
		c.displaySolution(args);

	}

}
