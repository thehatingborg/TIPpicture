package tiptoipicture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * The description panel In case the description mode is chose in this panel the
 * user can give a description of the whole picture.
 * 
 * @author thehatingborg
 *
 */

public class DescriptionPanel extends JFrame {
	private JPanel mainContainer;
	private JPanel content;
	private JLabel dlabel;
	private JPanel dPanel;
	private JLabel dlabel2;
	private JTextArea darea;
	private JPanel nextPanel;
	private JButton nextButton;

	private String pdescriptionText;

	/**
	 * Initalizes description panel sets layout of frame and frame parts.
	 * 
	 */

	public void init() {

		// set default operations
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Use a bigger font than the standard
		Font font1 = new Font("SansSerif", Font.BOLD, 16);

		// Initialise main container which holds everything else, set coloured border
		mainContainer = new JPanel(new BorderLayout());
		mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainContainer.setBackground(Color.decode("#FFDA98"));

		// Sets panel to put the content in and thereby the layout the rest orients at
		content = new JPanel(new GridBagLayout());
		content.setOpaque(false);

		// Create a panel for the description contents
		dPanel = new JPanel();
		dPanel.setLayout(null);
		// Create Label with task information for the user
		dlabel = new JLabel("Please give a description of the picture.");
		dlabel2 = new JLabel("<html>(Ideas: How many people are in the picture? Where was it taken? <br> "
				+ "In which year? What was the occasion? How's the weather?) </html>");
		dlabel.setBounds(200, 40, 400, 25);
		dlabel2.setBounds(200, 80, 400, 25);
		dlabel.setFont(font1);
		dPanel.add(dlabel);
		dPanel.add(dlabel2);
		// Create area write description in
		darea = new JTextArea();
		darea.setBounds(200, 120, 400, 100);
		dPanel.add(darea);

		// add a next button with which we get to the next view
		nextPanel = new JPanel();
		nextPanel.setLayout(null);
		nextButton = new JButton("Next");
		nextButton.setBounds(200, 80, 400, 25);
		// Upon click of the next button executes the functions inside the listener
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the informaiton provided in the textarea
				pdescriptionText = darea.getText();
				/**
				 * add a field to the script such a field is created where the description of
				 * the whole picture can be retrieved
				 */
				Map<String, Object> scripts = Scripts.getScript();
				scripts.put("picturedescription", "P(wholepicture)");
				Scripts.setScript(scripts);
				// add information to speak such the computer voice can read it
				Map<String, String> speak = Speak.getSpeak();
				speak.put("wholepicture", pdescriptionText);
				Speak.setSpeak(speak);
				// Set descriptionNotThere to false as a description is now available
				InterfaceController.descriptionNotThere = false;
				// Call the interfaceController function NextView to intialises the next view
				InterfaceController.controllNextView();
			}
		});
		nextPanel.add(nextButton);

		// To layout parts within the content panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.05f;
		gbc.weightx = 1f;
		gbc.fill = GridBagConstraints.BOTH;

		// add everything to the content panel
		content.add(dPanel, gbc);
		content.add(nextPanel, gbc);

		// add content panel to main container
		mainContainer.add(content);

		// add main container to frame
		this.add(mainContainer);
		this.setTitle("Tiptoi-Picture");
		this.pack();
		this.setLocationRelativeTo(null);

	}

}
