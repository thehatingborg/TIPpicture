package tiptoipicture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A view where the created YAML will be saved
 * 
 * @author thehatingborg
 *
 */
public class OverView extends JFrame {

	private JPanel mainContainer;
	private JPanel content;

	public int personcount;
	private JPanel nextPanel;
	private JButton nextButton;
	private JButton save;
	private File file;
	private JPanel infoPanel;
	private JLabel infoLabel;
	private JLabel infoLabel2;
	private JPanel infoPanel2;
	private Component info2Label;
	private Component info2Label2;

	/**
	 * Initialized the save view Overview and saves the YAML file. Gives a save view
	 * to choose the destination of the YAML file.
	 */
	public void init() {

		// set default operations
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Initialise main container which holds everything else, set coloured border
		mainContainer = new JPanel(new BorderLayout());
		mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainContainer.setBackground(Color.decode("#C0FFA7"));

		// Sets panel to put the content in and thereby the layout the rest orients at
		content = new JPanel(new GridBagLayout());
		content.setOpaque(false);

		// Use a bigger font than the standard
		Font font1 = new Font("SansSerif", Font.BOLD, 16);

		// create information Panel with recommendation for the file destination
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoLabel = new JLabel("Please choose a destination to save the yaml file to.");
		infoLabel.setBounds(200, 20, 600, 25);
		infoLabel2 = new JLabel("It is recommended to save this file in the folders of the tttool.");
		infoLabel2.setBounds(200, 40, 600, 25);
		infoLabel.setFont(font1);
		infoPanel.add(infoLabel);
		infoPanel.add(infoLabel2);

		// create anther information Panel giving information about the associated
		// github page
		infoPanel2 = new JPanel();
		infoPanel2.setLayout(null);
		info2Label = new JLabel("In case of questions or remarks visit:");
		info2Label.setBounds(200, 20, 600, 25);
		info2Label2 = new JLabel("https://github.com/thehatingborg/TIPpicture/");
		info2Label2.setBounds(200, 40, 600, 25);
		info2Label.setFont(font1);
		infoPanel2.add(info2Label);
		infoPanel2.add(info2Label2);

		// add a next button with which we get to the next view and initate the saving
		// of the file
		nextPanel = new JPanel();
		nextPanel.setLayout(null);
		nextButton = new JButton("Save & go to instructions for the next steps");
		nextButton.setBounds(200, 20, 400, 25);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// call the main method to create and save the YAML file
				main.createYAMLFile(file);
				// call controllNextView to make sure the next view is called correctly
				InterfaceController.controllNextView();
			}
		});
		nextPanel.add(nextButton);
		// make the button unclickable to insure proper functioning
		nextButton.setEnabled(false);

		// the save button will initiate a view for choosing a file destination
		save = new JButton("Choose destination");
		save.addActionListener(e -> createFile());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(save);

		// To layout parts within the content panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.05f;
		gbc.weightx = 1f;
		gbc.fill = GridBagConstraints.BOTH;

		// add everything to the content panel
		content.add(infoPanel, gbc);
		content.add(buttonPanel, gbc);
		content.add(infoPanel2, gbc);
		content.add(nextPanel, gbc);

		// add content panel to main container
		mainContainer.add(content);

		// add main container to frame
		this.add(mainContainer);
		this.setTitle("TIPpicture");
		this.pack();
		this.setLocationRelativeTo(null);

	}

	/**
	 * To create a view to choose file path source:
	 * https://www.codejava.net/java-se/swing/show-save-file-dialog-using-jfilechooser
	 */

	protected void createFile() {

		JFileChooser fileChooser = new JFileChooser();
		/**
		 * Sets previously chosen script name as suggest name to save document in Also
		 * suggests using a yaml extension
		 */
		fileChooser.setSelectedFile(new File(main.comment + ".yaml"));
		// show save dialog
		int retval = fileChooser.showSaveDialog(save);
		// get file from choosen destination in dialog
		if (retval == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			System.out.println(file);
			// only if save destination choose the next button is clickable
			nextButton.setEnabled(true);
			if (file == null) {
				System.out.print("null");
				return;
			}
			// in case no ending provide give the necessary yaml ending
			if (!file.getName().toLowerCase().endsWith("yaml")) {
				file = new File(file.getParentFile(), file.getName() + ".yaml");
			}
		}
	}

}
