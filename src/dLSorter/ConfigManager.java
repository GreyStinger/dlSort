package dLSorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigManager {
	private File downloadDirFile = FileManager.getDataFile("downloadDir");

	private HashMap<String, String> config = new HashMap<String, String>();

	public String readDownloadDir() {
		/*
		 * Reads and returns the current users download directory
		 * 
		 * @return String dir - Local download dir
		 */

		if (config.containsKey("downloadDir"))
			return config.get("downloadDir");

		String dir = new String();

		try (Scanner reader = new Scanner(downloadDirFile)) {

			if (reader.hasNextLine())
				dir = reader.nextLine();

			if (!dir.isEmpty())
				config.put("downloadDir", dir);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return dir;
	}

	public void writeDownloadDir(String dir) {
		/*
		 * Writes the users current directory to a download file
		 * 
		 * @param String dir
		 */

		try (FileWriter writer = new FileWriter(downloadDirFile)) {
			writer.write(dir);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
