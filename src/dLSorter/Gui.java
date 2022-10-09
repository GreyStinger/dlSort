package dLSorter;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DirChooserButton extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7050564823009016979L;

	private JButton button;

	private JFileChooser chooser;
	private String choosertitle;

	private ConfigManager manager;

	public DirChooserButton(ConfigManager manager) {
		button = new JButton("Find Downloads Dir");
		button.addActionListener(this);
		this.manager = manager;
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

			manager.writeDownloadDir(chooser.getSelectedFile().toString());
		} else {
			System.out.println("No Selection ");
		}

		System.exit(0);
	}
}
public class Gui {

	public static void start(ConfigManager manager) {
		JFrame frame = new JFrame("GUI");
		DirChooserButton panel = new DirChooserButton(manager);
		ImageIcon icon = new ImageIcon("logo.png");
		
		frame.setSize(getPreferredSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		log("Setting icon to icon.png");
		frame.setIconImage(icon.getImage());
		
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
		
		frame.getContentPane().add(panel, "Center");
		frame.setVisible(true);
	}
	
	public static Dimension getPreferredSize() {
		return new Dimension(240, 100);
	}

	private static void log(String message) {
		System.out.println("GUI: " + message);
	}
}
