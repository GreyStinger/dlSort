package dLSorter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SetupManager {
	public static void createLocalBat() {
		/*
		 * Creates a start.bat file in the local dir
		 */

		File file = FileManager.createFile(System.getProperty("user.dir") + "\\startDLSort.bat");

		try (FileWriter writer = new FileWriter(file)) {
			writer.write("@echo off\n");
			writer.write("java .\\dLSorter +from-term\n");
			writer.write("echo \"Completed. Press any key to exit\"\n");
			writer.write("pause\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean argumentHandler(String[] args) {
		/*
		 * Takes arguments from launch and sorts them
		 * 
		 * @param String[] args
		 * 
		 * @return boolean
		 */

		for (String arg : args) {
			if (arg.equals("+from-term"))
				return true;
		}
		return false;
	}
}
