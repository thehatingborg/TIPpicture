package tiptoipicture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * Create the view/frame where information about the individual persons can be provided
 * 
 * @author the hatingborg
 *
 */

public class PersonView extends JFrame {

	private JPanel mainContainer;
	private JPanel content;

	private JPanel pname;
	private JLabel pnameQuestion;
	private JLabel pnameLabel;
	private JTextField pnameField;

	private JPanel pfeature;
	private JLabel pfeatureQuestion;
	private JLabel pfeatureLabel;
	private JTextField pfeatureField;

	private JPanel pdescription;
	private JLabel pdescriptionQuestion;
	private JLabel pdescriptionLabel;
	private JTextField pdescriptionField;

	private JPanel pgender;
	private JLabel pgenderQuestion;
	private JLabel pgenderLabel;
	private JComboBox genderList;

	private int pronoun;

	public int personcount;
	private JPanel nextPanel;
	private JButton nextButton;

	private String pnameText;
	private String pfeatureText;
	private String pdescriptionText;
	private JLabel pfeatureQuestion2;
	private JLabel pdescriptionQuestion2;

	/**
	 * Initalizes person view sets layout of frame and frame parts.
	 */
	
	public void init() {

		//get information about the ordinal number of this person so we can display it
		personcount = InterfaceController.i + 1;
		
		//use different font for redabilits 
		Font font1 = new Font("SansSerif", Font.BOLD, 16);


		// set default operations
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Initialise main container which holds everything else, set coloured border
		mainContainer = new JPanel(new BorderLayout());
		mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainContainer.setBackground(Color.decode("#BBEDFD"));


		// Sets panel to put the content in and thereby the layout the rest orients at
		content = new JPanel(new GridBagLayout());
		content.setOpaque(false);

		
		/**
		 * In the name panel, it is asked for the name of a person,
		 * which can be provided in a textfield.
		 */
		//create panel for name informations
		pname = new JPanel();
		pname.setLayout(null);
		//ask for the name 
		pnameQuestion = new JLabel("Whats the name of the " + personcount + ". person?");
		pnameQuestion.setBounds(10, 20, 400, 25);
		pnameQuestion.setFont(font1);
		pnameLabel = new JLabel("Name:");
		pnameLabel.setBounds(10, 50, 80, 25);
		pname.add(pnameQuestion);
		pname.add(pnameLabel);
		// add a Text field
		pnameField = new JTextField(40);
		pnameField.setBounds(80, 50, 400, 25);
		// add to field to panel
		pname.add(pnameField);

		

		/**
		 * In the feature panel, it is asked for the feature of a person,
		 * which can be provided in a textfield. Further we give information in which 
		 * structure this informaiton should be given.
		 */
		//create panel for name informations
		pfeature = new JPanel();
		// Layout of panel
		pfeature.setLayout(null);
		
		//ask for name and give structure information and ideas
		pfeatureQuestion = new JLabel("Whats the feature of the " + personcount + ". person?");
		pfeatureQuestion.setBounds(10, 10, 400, 25);
		pfeatureQuestion2 = new JLabel("Use the structure: verb + adjective/noun. (Ideas: likes Unicorns, is a professional witcher)");
		pfeatureQuestion2.setBounds(10, 30, 500, 25);
		pfeatureQuestion.setFont(font1);
		//add labels to panel
		pfeature.add(pfeatureQuestion);
		pfeature.add(pfeatureQuestion2);
		
		pfeatureLabel = new JLabel("Feature:");
		pfeatureLabel.setBounds(10, 55, 80, 25);
		pfeature.add(pfeatureLabel);
		// add a Text field
		pfeatureField = new JTextField(40);
		pfeatureField.setBounds(80, 55, 500, 25);
		// add to panel
		pfeature.add(pfeatureField);
		
		//In case the descriptionmode is choosen make sure a description panel is provide to give a description
		if (InterfaceController.descriptiongame) {
			pdescription = new JPanel();
			// Layout of panel
			pdescription.setLayout(null);
			
			//ask for a description and provide information about the necessary structure as well as ideas
			pdescriptionQuestion = new JLabel("Whats the description of the " + personcount + ". person?");
			pdescriptionQuestion.setBounds(10, 10, 450, 25);
			pdescriptionQuestion.setFont(font1);			
			pdescriptionQuestion2 = new JLabel("Use the structure: verb + adjective/noun. (Ideas: wears a pink dress and smiles, is crying)");
			pdescriptionQuestion2.setBounds(10, 30, 500, 25);
			pdescription.add(pdescriptionQuestion);
			pdescription.add(pdescriptionQuestion2);
			
			pdescriptionLabel = new JLabel("Description:");
			pdescriptionLabel.setBounds(10, 55, 80, 25);
			pdescription.add(pdescriptionLabel);
			// add a Text field
			pdescriptionField = new JTextField(40);
			pdescriptionField.setBounds(80, 55, 500, 25);
			// add to panel
			pdescription.add(pdescriptionField);
		}

		/*
		 * Add a drop down such that the most approprtiate gender can be choosen
		 * (Note this is somewhat a limited choice and might be expanded in the future)
		 */
		//Set panel for gender information
		pgender = new JPanel();
		// Layout of panel
		pgender.setLayout(null);
		//Ask for the gender
		pgenderQuestion = new JLabel("Whats the gender of the " + personcount + ". person?");
		pgenderQuestion.setBounds(10, 20, 450, 25);
		pgenderQuestion.setFont(font1);
		pgender.add(pgenderQuestion);
		
		pgenderLabel = new JLabel("Gender:");
		pgenderLabel.setBounds(10, 50, 80, 25);
		pgender.add(pgenderLabel);
		
		//The possible choice for geneder 
		String[] genderStrings = { "They/their", "She/her", "He/his" };
		//create a dropdown menu to choose the gender
		genderList = new JComboBox(genderStrings);
		genderList.setBounds(80, 50, 200, 25);
		genderList.addActionListener(new ActionListener() {
			// add actionlistner to listen for change
			public void actionPerformed(ActionEvent e) {

				String s = (String) genderList.getSelectedItem();// get the selected item

				switch (s) {// check which gender was chosen
				case "She/her":
					pronoun = 1;
					break;
				case "He/his":
					pronoun = 1;
					break;
				default:
					//set they them pronoun as default option
					pronoun = 0;
					break;
				}
			}
		});
		//add dropdown to the panel
		pgender.add(genderList);
		
		// add a next button with which we get to the next view
		nextPanel = new JPanel();
		nextPanel.setLayout(null);
		nextButton = new JButton("Next");
		nextButton.setBounds(600, 20, 100, 25);
		// Upon click of the next button executes the functions inside the listener
		nextButton.addActionListener(new ActionListener() {
			/**
			 * get the given information to create a new person object
			 * and add it to the script via makePerson
			 */
			public void actionPerformed(ActionEvent e) {
				//get the name
				pnameText = pnameField.getText();
				//get the feature
				pfeatureText = pfeatureField.getText();
				//if descriptionmode chosen get the description
				if (InterfaceController.descriptiongame) {
				pdescriptionText = pdescriptionField.getText();}
				//get the oridnal number of the person
				int personnumber = InterfaceController.i;
				//creat person object
				Person person = new Person(personnumber, pnameText, pfeatureText, pdescriptionText, pronoun);
				//add to script
				Person.makePerson();
				//make the next view is called correctly
				InterfaceController.controllNextView();
			}
		});
		nextPanel.add(nextButton);

		// To layout parts within the content panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.5f;
		gbc.weightx = 1f;
		gbc.fill = GridBagConstraints.BOTH;


		// add everything to the content panel
		content.add(pname, gbc);
		content.add(pfeature, gbc);
		//add descriptionpanel only if existing
		if (InterfaceController.descriptiongame) {
			content.add(pdescription, gbc);
		}
		content.add(pgender, gbc);
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
