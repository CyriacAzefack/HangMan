package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
	
	/**
	 * Full Path of the file to read
	 * @param path
	 * 			Full Path of the file to read
	 * @return <i>ArrayList<String></i> of the line in the file
	 * @throws FileNotFoundException
	 */
	public static ArrayList<String> readFile(String path) throws FileNotFoundException {
		File file = new File(path);
		ArrayList<String> toReturn= new ArrayList<String>();
		Scanner scan= new Scanner(file);
		while(scan.hasNextLine())
		{
			toReturn.add(scan.nextLine());
		}
		scan.close();
		return toReturn;
	}
	
	/**
	 * Write in the file from scratch
	 * @param path
	 * 			Full path of the file
	 * @param data
	 * 			Data to write in the file
	 * @throws IOException
	 */
	public static void writeFile(String path, ArrayList<String> data) throws IOException {   
		File file = new File(path);
	    FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
	    BufferedWriter bw = new BufferedWriter(fw);
	    
	    for (String line : data)
	    {
			StringBuilder builder = new StringBuilder();
			
			// empty the builder
			builder.delete(0, builder.length());
			
			String lineWithoutSpaces = line.trim();
			builder.append(lineWithoutSpaces);
			bw.write(builder.toString() + "\n");
	    }
	    
	    bw.flush();
	    bw.close();
	}
	
	public static String ressourceRootPath() {
		String path = System.getProperty("java.class.path");
		path = path.substring(0, path.lastIndexOf("\\"));
		
		path += "/ressources";
		return path;
	}
	
	
}
