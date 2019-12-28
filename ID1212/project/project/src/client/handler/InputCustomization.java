package client.handler;

//import java.io.File;

public class InputCustomization {
	
	public InputCustomization() {}
	
	public String[] parseInput(String str) {
		String[] input = str.split(" ");
		input[0] = input[0].toUpperCase();
		return input;
	}
}
