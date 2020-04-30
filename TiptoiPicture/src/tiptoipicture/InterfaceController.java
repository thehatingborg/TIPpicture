package tiptoipicture;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;


/**
 * Controls the different views and their frames
 * 
 * @author thehatingborg
 *
 */
public class InterfaceController {

	private InterfaceView view;
	private static boolean namegame;
	private static boolean featuregame;
	static boolean descriptiongame;
	private static String name;
	static int number;
	static int i;
	private static PersonView[] personViewArray;
	private static boolean namegameboolean;
	private static boolean namenormalboolean;
	private static boolean featuregameboolean;
	private static boolean descriptionnormalboolean;
	private static OverView overview;
	static boolean descriptionNotThere;
	private static DescriptionPanel descriptionView;
	static String pdescriptionText;

	
	/**
	 * Initialises the first view and a index parameter i 
	 * @param view the initial view
	 */
	public InterfaceController(InterfaceView view) {
		this.view = view;
		//to index personViewArray
		i = 0;
	}

	/**get the information given in the inital view
	 * 
	 * @param ng whether the checkbox namegame was selected in the initial view
	 * @param fg whether the checkbox featuregame was selected in the initial view
	 * @param dg whether the checkbox description mode was selected in the initial view
	 * @param namef the name given to the script given in the inital view 
	 * @param numberf the number of persons in the picture given by the user in the initial view
	 */
	
	public static void getinfo(JCheckBox ng, JCheckBox fg, JCheckBox dg, String namef, String numberf) {
		namegame = ng.isSelected();
		featuregame = fg.isSelected();
		descriptiongame = dg.isSelected();
		/**
		 * In case the description game is chosen set descriptionNotThere to true to make sure
		 * a description will be given at some point. That is not necessary if the description mode
		 * is not chosen in that case the description Text is set for error avoidance
		 */
		
		if (descriptiongame) {
			descriptionNotThere = true;
		} else {
			descriptionNotThere = false;
			pdescriptionText = "no description found";
		}
		System.out.print("getinfo d:" + descriptionNotThere);
		name = namef;
		number = Integer.parseInt(numberf);

		//start the creation of the Person frames here to avoid collisions with controllNextView
		personViewCreator();
	}

	/**
	 * Creates a array with personViews and thereby a frame for each person
	 */
	public static void personViewCreator() {
		personViewArray = new PersonView[number];
		//create as many views as persons in the picture(number)
		for (int i = 0; i < number; i++) {
			PersonView aPersonView = new PersonView();
			personViewArray[i] = aPersonView;
		}
		//here to avoid collisions with other functions
		setMainVars();
	}
	
	
	/**
	 * Sets comment in main
	 * Create the games according to the users choice
	 */
	public static void setMainVars() {
		//if namegame chosen also name mode will be created
		if (namegame) {
			namenormalboolean = true;
			namegameboolean = true;
		} else {
			namenormalboolean = false;
			namegameboolean = false;
		}
		if (featuregame) {
			featuregameboolean = true;
		} else {
			featuregameboolean = false;
		}
		if (descriptiongame) {
			descriptionnormalboolean = true;
		} else {
			descriptionnormalboolean = false;
		}
		main.comment = name;
		//create games according to set parameters
		main.games = new Games(namenormalboolean, descriptionnormalboolean, namegameboolean, featuregameboolean);
		main.next = true;
		//call controll NextView here to avoid missing information in personViewArray
		controllNextView();
	}

	
	/**
	 * Controlls which view will be shown next and hides the previous view
	 */
	
	public static void controllNextView() {
		//In case a description for the whole picture needs to be provide create a frame to do so
		if (descriptionNotThere) {
			descriptionView = new DescriptionPanel();
			DescriptionPanel nextView = descriptionView;
			InterfaceView previousView = main.myinitialView;
			//initialise the view as done for the initial view
			nextView.init();
			nextView.setSize(800, 500);
			nextView.setVisible(true);
			//hide the previous view
			previousView.setVisible(false);
		} else if (i < personViewArray.length && !descriptionNotThere) {
			//show the next person view and hide the last
			if (i != 0) {
				PersonView previousView = personViewArray[i - 1];
				previousView.setVisible(false);
			}
			//for the first person view the description panel or the initial view must be hidden instead
			if (i == 0) {
				if (descriptionView == null) {
					InterfaceView previousView = main.myinitialView;
					previousView.setVisible(false);
				} else {
					DescriptionPanel previousView = descriptionView;
					previousView.setVisible(false);					
				}
			}
			PersonView nextView = personViewArray[i];
			nextView.init();
			nextView.setSize(800, 500);
			nextView.setVisible(true);
			i++;
		} else if (i == personViewArray.length) {
			//make sure that after the last person view the save view OverView will be shown
			OverView nextView = new OverView();
			overview = nextView;
			PersonView previousView = personViewArray[i - 1];
			nextView.init();
			nextView.setSize(800, 500);
			nextView.setVisible(true);
			previousView.setVisible(false);
			i++;
		} else {
			//show the final information view
			InfoView nextView = new InfoView();
			OverView previousView = overview;
			nextView.init();
			nextView.setSize(800, 500);
			nextView.setVisible(true);
			previousView.setVisible(false);
		}

	}

}
