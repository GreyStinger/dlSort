package dLSorter;

public class Main {
	public static void main(String[] args) {
		ConfigManager configManager = new ConfigManager();
		
		Sorter sorter = new Sorter(configManager.readDownloadDir());

		if (configManager.readDownloadDir().isEmpty())
			Gui.start(configManager);
		else {
			sorter.scanSort();
		}
	}
}
