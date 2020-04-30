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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
/**
 * The last shown panel showing information about how one can proceed after using the GUI.
 * Gives resources and information to the usage of the tttool
 * @author thehatingborg
 *
 */
public class InfoView extends JFrame{
	private JPanel mainContainer;
	private JPanel content;
	private JLabel ilabel;
	private JPanel infoPanel;
	private JLabel infolabel;
	private JTextArea infoarea;
	private JLabel infolabel2;
	private JLabel infolabel0;
	private JLabel infolabel02;


	/** Initalizes description Panel
	 * Sets layout of frame and frame parts
	 * Panels give information over proceedings after using the GUI.
	 * Provides links to information resources
	 */

	public void init() {

		this.setSize(800, 200); // does it do shit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		//set fonts for better readability
		Font font1 = new Font("SansSerif", Font.BOLD, 16);
		Font font2 = new Font("SansSerif", Font.BOLD, 20);

		// Initialise main container which holds everything else, set coloured border
		mainContainer = new JPanel(new BorderLayout());
		mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainContainer.setBackground(Color.decode("#FDFF99"));

		// Sets panel to put the content in and thereby the layout the rest orients at
		content = new JPanel(new GridBagLayout());
		content.setOpaque(false);

		//Create a panel with Information
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infolabel0 = new JLabel("How to proceed:");
		infolabel = new JLabel("<html>1. If not done, install the tttool."
				+ " <br>2. Make sure the file you just created is in the tttool folder."
				+ " <br>3. Open your shell and navigate to that folder."
				+ " <br>4. Add the photo folder to the audio folder "
				+ " <br>5. Use the assemble function from the tttool."
				+ " <br>6. Use the oid-table function to create a pdf with all the codes. "
				+ " <br>7. Print this pdf on your local printer or in a print shop.</html>");
		infolabel2 = new JLabel("<html> - The tttool: https://github.com/entropia/tip-toi-reveng."
				+ " <br>- tttool book (german): https://tttool.readthedocs.io/de/latest/"
				+ " <br>- pool with tiptoi hacks (german): http://tttool.entropia.de/</html>");
		infolabel02 = new JLabel("Resources:");

		//set bounds for created labels
		infolabel0.setBounds(20,10,600,25);
		infolabel.setBounds(20,20, 600, 200);			
		infolabel02.setBounds(20, 220, 600, 25);			
		infolabel2.setBounds(20, 225, 600, 200);	

		//set founds for created labels
		infolabel0.setFont(font2);
		infolabel02.setFont(font2);
		infolabel.setFont(font1);
		infolabel2.setFont(font1);

		//add created labels to panel
		infoPanel.add(infolabel0);
		infoPanel.add(infolabel);
		infoPanel.add(infolabel02);
		infoPanel.add(infolabel2);
		

		// To layout parts within the content panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.05f;
		gbc.weightx = 1f;
		gbc.fill = GridBagConstraints.BOTH;

		// add everything to the content panel
		content.add(infoPanel,gbc);
		
		// add content panel to main container
		mainContainer.add(content);


		// add main container to frame
		this.add(mainContainer);
		this.setTitle("TIPpicture");
		this.pack();
		this.setLocationRelativeTo(null);


}
}