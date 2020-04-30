package tiptoipicture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The person object holds all information of a person as well as the 
 * functions to add this information to the script
 * 
 * @author thehatingborg
 *
 */
public class Person {

	private static int number;
	private static String name;
	private static String feature;
	private static String description;
	private static Map<String, Object> scripts;
	private static int pronoun;
	private static List<String> personlines;

	
	/**
	 * 
	 * @param number ordinal number of the person
	 * @param name  name of the person
	 * @param feature a feature string describing a attribute of the person
	 * @param description a description of the person
	 * @param pronoun the pronoun for the person
	 */
	public Person(int number, String name, String feature, String description, int pronoun) {
		this.number = number;
		this.name = name;
		this.feature = feature;
		this.description = description;
		this.pronoun = pronoun;

	}

	/**
	 * Adds the information of a person to the script 
	 */
	public static void makePerson() {
		//Will give the name in case currently in name mode
		String line1 = "$nano == 1 ? $nameOrfeature0 ==  0? $nameOrfeature0 :=  1 $whichfile := " + number
				+ "$pronoun := " + pronoun + " J(namenamenormal)";
		//On second tap a feature is given if currently in name mode
		String line2 = "$nano == 1 ? $nameOrfeature0 ==  1? $nameOrfeature0 :=  0 $whichfile := " + number
				+ "$pronoun := " + pronoun + "J(featurenormal)";
		//In case the descriptionmode is currently playes randomly a description, feature or name is given
		String line3 = "$deno == 1 ? P(p" + number + "description,p" + number + "name,p" + number + "feature)";
		//If this person is the correct answer in the name game
		String line4 = "$naga == 1 ? $answer == " + number + "? P(correct,perfect)";
		//If this person is the false answer in the name game
		String line5 = "$naga == 1 ? $answer != " + number + "? P(again,sorry)";
		//If this person is the correct answer in the feature game		
		String line6 = "$fega == 1 ? $answer == " + number + "? P(correct,perfect)";
		//If this person is the false answer in the feature game
		String line7 = "$fega == 1 ? $answer != " + number + "? P(again,sorry)";
		
		//add all the lines to an array list to provide the needed itemization in the YAML file
		personlines = Arrays.asList(line1, line2, line3, line4, line5, line6, line7);
		//create individual key for this person
		String namenum = "person" + number;
		//add the current version of the script
		Map<String, Object> thescript = Scripts.getScript();
		//add the person information as key value pair to the script
		thescript.put(namenum, personlines);
		Scripts.setScript(thescript);
		
		//add information to the mapping functions to ensure proper mapping of name to question or assertion
		ArrayList<String> mappingname = Games.getMappingName();
		String formappingname = "$whichfile == " + number + " ?  P(p" + number + "name) $answer := " + number
				+ " J(random)";
		mappingname.add(formappingname);
		Games.setMappingName(mappingname);
		
		//add information to the mapping functions to ensure proper mapping of feature to question or assertion
		ArrayList<String> mappingfeature = Games.getMappingFeature();		
		String formappingfeature = "$whichfile == " + number + " ?  P(p" + number + "feature) $answer := " + number
				+ " J(random)";
		mappingfeature.add(formappingfeature);
		Games.setMappingFeature(mappingfeature);
		
		/**
		 * add key string pairs to be voiced by the computer voice 
		 * each person has an individual name,description and feature key
		 */
		Map<String, String> thespeak = Speak.getSpeak();
		String namekey = "p"+number+"name";
		String descriptionkey = "p"+number+"description";
		String featurekey = "p"+number+"feature";
		thespeak.put(namekey, name);
		thespeak.put(descriptionkey, description);
		thespeak.put(featurekey, feature);
		
	}
	
	

	// needed else JACKSON throws exception
	public Person() {
	}


}
