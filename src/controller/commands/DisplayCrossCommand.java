package controller.commands;

import controller.Controller;

public class DisplayCrossCommand extends CommandClass {

	public DisplayCrossCommand(Controller c) {
		super(c);
	}

	@Override
	public void doCommand(String args) {
		c.displayCross(args);

	}

}
