package tiptoipicture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

/**
 * The initial/first view of the program
 * 
 * @author thehatingborg
 *
 */

public class InterfaceView extends JFrame {

	private JPanel mainContainer;
	private JPanel content;

	private JPanel namePanel;
	private JLabel nameQuestion;
	private JLabel nameLabel;
	private JTextField nameField;

	private JPanel numberPanel;
	private JLabel numberQuestion;
	private JLabel numberLabel;

	private JPanel gamesPanel;
	private JLabel gamesQuestion;
	private JLabel gamesLabel;
	private JCheckBox namegameCheck;
	private JCheckBox descriptiongameCheck;
	private JCheckBox allgameCheck;
	private JCheckBox featuregameCheck;
	private JPanel nextPanel;
	private JButton nextButton;
	private String numberFieldText;
	private String nameFieldText;
	private JFormattedTextField numberField;
	private JLabel numberQuestion2;
	private int numberval;

	/**
	 * Initialises the InterfaceView, sets Layout of frame parts
	 */
	public void init() {

		// Use a bigger font than the standard
		Font font1 = new Font("SansSerif", Font.BOLD, 16);

		// set default operations
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Initialise main container which holds everything else, set coloured border
		mainContainer = new JPanel(new BorderLayout());
		mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainContainer.setBackground(Color.PINK);

		// Sets panel to put the content in and thereby the layout the rest orients at
		content = new JPanel(new GridBagLayout());
		content.setOpaque(false);

		// add a panel for giving the script a name
		namePanel = new JPanel();
		// Layout of panel
		namePanel.setLayout(null);
		// ask for script name
		nameQuestion = new JLabel("Give your picture a unique name");
		nameQuestion.setBounds(10, 20, 400, 25);
		nameQuestion.setFont(font1);
		namePanel.add(nameQuestion);

		// name can be given in a textfield
		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(10, 50, 80, 25);
		namePanel.add(nameLabel);
		// add a Text field
		nameField = new JTextField(40);
		nameField.setBounds(80, 50, 200, 25);
		// add to panel
		namePanel.add(nameField);

		// add a panel for number information
		numberPanel = new JPanel();
		// Layout of panel
		numberPanel.setLayout(null);

		// ask for the number of people in the picture
		numberQuestion = new JLabel("How many people are in the picture");
		numberQuestion.setBounds(10, 10, 400, 25);
		// provide possible structure
		numberQuestion2 = new JLabel("(Natural number/integer)");
		numberQuestion2.setBounds(10, 30, 400, 25);
		numberQuestion.setFont(font1);
		numberLabel = new JLabel("Number:");
		numberLabel.setBounds(10, 60, 80, 25);
		numberPanel.add(numberQuestion);
		numberPanel.add(numberQuestion2);

		numberPanel.add(numberLabel);

		// make sure only numbers can be given in the text field
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0l); // set minimum to one

		// Set up the textfield with the restrictions
		numberField = new JFormattedTextField(numberFormatter);
		numberField.setBounds(80, 60, 200, 25);
		numberPanel.add(numberField);

		// create panel for game information
		gamesPanel = new JPanel();
		gamesPanel.setLayout(null);
		// ask for games/modes which should be included
		gamesQuestion = new JLabel("Tick the games you want to play");
		gamesQuestion.setBounds(10, 10, 400, 25);
		gamesQuestion.setFont(font1);
		gamesPanel.add(gamesQuestion);

		// set up checkbox for the name game
		namegameCheck = new JCheckBox("Name Game");
		namegameCheck.setSelected(false);
		namegameCheck.setBounds(10, 40, 150, 25);

		// set up checkbox for the feature game
		featuregameCheck = new JCheckBox("Feature Game");
		featuregameCheck.setSelected(false);
		featuregameCheck.setBounds(10, 60, 150, 25);

		// set up checkbox for the description mode
		descriptiongameCheck = new JCheckBox("Description Mode");
		descriptiongameCheck.setSelected(false);
		descriptiongameCheck.setBounds(10, 80, 150, 25);

		// add the checkboxes to the panel
		gamesPanel.add(namegameCheck);
		gamesPanel.add(featuregameCheck);
		gamesPanel.add(descriptiongameCheck);

		// size panel correctly
		gamesPanel.setPreferredSize(new Dimension(70, 70));

		// add a next button with which we get to the next view
		nextPanel = new JPanel();
		nextPanel.setLayout(null);
		nextButton = new JButton("Next");
		nextButton.setBounds(600, 20, 100, 25);
		// Upon click of the next button executes the functions inside the listener
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make sure the number of person in the picture is given
				try {
					numberval = Integer.parseInt(numberField.getText());
				} catch (java.lang.NumberFormatException numb) {
					numberval = -1;
				}
				// if no game is given colour the panel red to create attention
				if (numberval == -1) {
					numberPanel.setBackground(Color.decode("#ff6666"));
				} else {// if all information is given get that information
					numberFieldText = numberField.getText();
					nameFieldText = nameField.getText();
					// make sure the next view is called correctly
					InterfaceController.getinfo(namegameCheck, featuregameCheck, descriptiongameCheck, nameFieldText,
							numberFieldText);
				}
				;

			}
		});
		nextPanel.add(nextButton);

		// Size the panel accordingly
		nextPanel.setPreferredSize(new Dimension(10, 10));

		// To layout parts within the content panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.5f;
		gbc.weightx = 1f;
		gbc.fill = GridBagConstraints.BOTH;

		// add everything to the content panel
		content.add(namePanel, gbc);
		content.add(numberPanel, gbc);
		content.add(gamesPanel, gbc);
		content.add(nextPanel, gbc);

		// add content panel to main container
		mainContainer.add(content);

		// add main container to frame
		this.add(mainContainer);
		this.setTitle("TIPpicture");
		this.pack();
		this.setLocationRelativeTo(null);

	}
}
