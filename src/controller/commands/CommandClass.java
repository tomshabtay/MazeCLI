package controller.commands;

import controller.Controller;

public abstract class CommandClass implements Command{
	Controller c;
	
	public CommandClass(Controller c)
	{
		this.c = c;
	}
}
