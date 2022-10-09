package dLSorter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
	public static File createFile(String absoluteFilePath) {
		/*
		 * Creates a file in the path specified
		 * 
		 * @param String absoluteFilePath - The path where the file is to be created
		 */
	
		File file = new File(absoluteFilePath);
		try {
			if (file.createNewFile()) {
				log("Created file " + absoluteFilePath);
			} else {
				log("File " + absoluteFilePath + " already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return file;
	}
	
	public static boolean deleteFile(Path fileToDelete) {
		try {
			Files.delete(Paths.get(fileToDelete.toString()));
		} catch (IOException e) {
			e.printStackTrace();
			log("Failed to delete: " + fileToDelete.getFileName().toString());
			return false;
		}
		log("Succesfully deleted: " + fileToDelete.getFileName().toString());
		return true;
	}

	public static File getDataFile(String fileName) {
		/*
		 * Creates a file in the data directory
		 * 
		 * @param String fileName - The name of the file you wish to be created
		 * 
		 * @return String - The absolute path of the newly created file
		 */

		String currentDataDir = System.getProperty("user.dir") + "\\dLSortData\\";

		createDir(currentDataDir);

		return createFile(currentDataDir + fileName);
	}

	public static void createDir(String dir) {
		/*
		 * Creates a folder in the file system
		 * 
		 * @param String dir - The directory you wish to be created
		 */

		File newDir = new File(dir);

		if (newDir.mkdir()) {
			log("Created dir " + newDir.getName());
		} else {
			log("Directory " + newDir.getName() + " already exists");
		}
	}

	private static void log(String message) {
		System.out.println("FMGR: " + message);
	}
}
