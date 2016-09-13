package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Controller;
import controller.commands.Command;

public class MyView implements View {
	private CLI cli;
	private HashMap<String, Command> commands;
	private Controller c;

	public MyView(BufferedReader in, PrintWriter out) {
		cli = new CLI(in, out, null);
	}

	public void setController(Controller c) {
		this.c = c;
	}

	@Override
	public void start() {
		// creating a thread and starting it
		Thread cli = new Thread(this.cli);
		cli.start();
	}

	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
		cli.setCommands(commands);
	}

	public void printToOut(String str) {
		cli.printToOut(str);
	}

}
