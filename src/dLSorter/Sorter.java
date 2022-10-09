package dLSorter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Sorter {
	// The absolute path of the download directory
	private String localDownloadDir;

	private ArrayList<String> folders = new ArrayList<String>();

	public Sorter(String dir) {
		localDownloadDir = dir;
	};

	public void scanSort() {
		/*
		 * Scans files in downloads directory and moves to their appropriate folders
		 * 
		 * @return Void
		 */

		String[] tempSplit;

		try {
			for (final File fileEntry : new File(localDownloadDir).listFiles()) {
				if (fileEntry.isDirectory()) {
					log(fileEntry.getName() + " added");
					folders.add(fileEntry.getName());
					continue;
				}

				// TODO: If file has no extension move to no extension folder
				if (!fileEntry.getName().contains("."))
					continue;

				tempSplit = fileEntry.getName().split("\\.");
				String extension = new String(tempSplit[tempSplit.length - 1]);

				if (!folders.contains(extension)) {
					FileManager.createDir(localDownloadDir + "\\" + extension);
					folders.add(extension);
				}
				
				Path moveFrom = Paths.get(fileEntry.getAbsolutePath());
				Path moveTo = Paths.get(localDownloadDir + "\\" + extension + "\\" + fileEntry.getName());
				
				if (new File(moveTo.toString()).exists()) {
					FileManager.deleteFile(moveFrom);
					continue;
				}

				Path temp = Files.move(moveFrom, moveTo);

				if (temp != null) {
					log("File " + fileEntry.getName() + " moved succesfully");
				} else {
					log("Failed to move the file " + fileEntry.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void log(String message) {
		System.out.println("Sorter: " + message);
	}
}
