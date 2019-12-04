package client.handler;

import java.io.File;

public class InputCustomization {
	private static String filePath = "C:/Users/mr_fe/Desktop/Natverksprogrammering/task3/src/client/files/";
	
	public InputCustomization() {}
	
	public String[] parseInput(String str) {
		String[] input = str.split(" ");
		return input;
	}
	
	public String[] fileAttr(String fileName, String owner) {
		String[] attributes = new String[3];
		File file = new File(filePath + fileName);
		
		attributes[0] = fileName;
		attributes[1] = owner;
		attributes[2] = Long.toString(file.length());
		
		return attributes;
	}
	
	public boolean fileExist(String fileName) {
		File file = new File(filePath + fileName);
		return file.exists();
	}
}
