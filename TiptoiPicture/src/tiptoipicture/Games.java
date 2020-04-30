package tiptoipicture;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds all the information for the games
 * @author the hatingborg
 *
 */
public class Games {

	private String namenormalA;
	private String descriptionnormalA;
	private java.util.List<String> namegameA;
	private java.util.List<String> featuregameA;
	private java.util.List<String> randomA;
	private boolean random;
	private java.util.List<String> featurenormalA;
	private String namenamenormalA;
	private String featurequestionA;
	private String namequestionA;
	private static ArrayList<String> mappingName;
	private static ArrayList<String> mappingFeature;
	
	/**
	 * Decides based on the users choice which games will be implemented in the YAML.
	 * If needed sub-functions such as a random function will be implemented as well.
	 * Uses array lists when a itemization is needed in the YAML file. 
	 * One can think of each item (array list entry) as an if, else-if
	 * 
	 * @param namenormal whether the name normal mode should be implemented
	 * @param descriptionnormal whether the description mode should be implemented
	 * @param namegame whether the name game should be implemented
	 * @param featuregame whether the feature game should be implemented
	 */

	public Games(boolean namenormal, boolean descriptionnormal, boolean namegame, boolean featuregame) {
		//create the necessary keys and their mappings for the name mode
		if (namenormal) {
			this.namenamenormalA = "P(thisis) J(mappingname)";
			this.featurequestionA = "P(who) J(mappingfeature)";
			this.featurenormalA = Arrays.asList("$pronoun == 0?", "$pronoun == 1? P(she) J(mappingfeature)",
					"$pronoun == 2? P(he) J(mappingfeature)");
			this.namenormalA = "P(namemode) $nano := 1 $deno := 0 $naga := 0 $fega := 0";
		}

		//create the necessary keys and their mappings for the description mode
		if (descriptionnormal) {
			this.descriptionnormalA = "P(descriptionmode) P(wholepicture) $deno := 1 "
					+ " $nano := 0 $naga := 0 $fega := 0";
		}

		//create the necessary keys and their mappings for the name game
		if (namegame) {
			this.namequestionA = "P(find, lookfor) J(mappingname)";
			this.featurequestionA = "P(who) J(mappingfeature)";
			this.random = true;
			this.namegameA = Arrays.asList(
					"$newtoname == 0? P(namegame)  $naga := 1  $nano := 0 $deno := 0 $fega := 0 "
					+ "$newtoname := 1 J(random)",
					"$newtoname == 1? J(namequestion)");
		}

		//create the necessary keys and their mappings for the feature game
		if (featuregame) {
			this.random = true;
			this.featuregameA = Arrays.asList(
					"$newtofeature == 0?  P(featuregame) $fega := 1   $nano := 0 $deno := 0 "
					+ "$naga := 0 $newtofeature :=1 J(random)",
					"$newtofeature == 1? J(featurequestion)");
		}
		//add the randomfunction if needed for a game 
		if (random) {
			this.randomA = Arrays.asList(
					"T($r,65535) $rnd +=$r $rnd *=25173 $rnd +=13849 $whichfile :=$rnd $whichfile %=$numberperson $whichfile += 1");
		}
		//mappingname is a function in the YAML which maps a name to a question or assertion
		ArrayList<String> mappingnameInit = new ArrayList<String>();
		mappingName = mappingnameInit;

		//mappingname is a function in the YAML which maps a feature to a question or assertion
		ArrayList<String> mappingfeatureInit = new ArrayList<String>();
		mappingFeature = mappingfeatureInit;
	}
	//Needed to avoid errors with JACKSON
	public Games() {
	}
	//getter for mappingName 
	public static ArrayList<String> getMappingName() {
		return mappingName;

	}
	//setter for mappingName
	public static void setMappingName(ArrayList<String> changedmappingName) {
		mappingName = changedmappingName;

	}
	//getter for mappinFeature
	public static ArrayList<String> getMappingFeature() {
		return mappingFeature;

	}
	//setter for mappingFeature
	public static void setMappingFeature(ArrayList<String> changedmappingFeature) {
		mappingFeature = changedmappingFeature;

	}

	//adds the game mode and associated functions to the script
	public void allToScript() {
		Map<String, Object> scripts = Scripts.getScript();
		scripts.put("namenormal", namenormalA);
		scripts.put("descriptionnormal", descriptionnormalA);
		scripts.put("namegame", namegameA);
		scripts.put("random", randomA);
		scripts.put("featuregame", featuregameA);
		scripts.put("mappingname", mappingName);
		scripts.put("mappingfeature", mappingFeature);
		scripts.put("featurenormal", featurenormalA);
		scripts.put("namequestion", namequestionA);
		scripts.put("featurequestion", featurequestionA);
		scripts.put("namenamenormal", namenamenormalA);
		Scripts.setScript(scripts);
	}

	//adds the descriptions of the modes to speak if voicing by a computer voice is needed
	public void allToSpeak() {
		Map<String, String> speak = Speak.getSpeak();
		if(main.ownaudio){
			
		}else {
		speak.put("welcome", "Welcome to this photo game. You have different modes to choose from."
				+ " Click on the different fields for the modes and they will explain themselves. Have fun");
		speak.put("namemode", "This is the name mode."
				+ " If you click on a person I will tell you their name or a feature of them. Ready? Go!");
		speak.put("descriptionmode", "You chose the description mode. "
				+ "If you click on a person you either hear their name, a feature of them or a description. Let’s do this!");
		speak.put("namegame",
				"Let’s play a name game. I will tell you a name and you need to find this person by tapping on them with the pen. "
				+ "Just click on this symbol again to get a name. Let’s go.");
		speak.put("featuregame",
				"It's  feature game time!. In this game I will tell you a feature "
				+ "of a person and you need to find the person who it belongs to."
				+ " To get a feature task just click on this symbol again. The game starts now!");
		speak.put("find", "Please find.");
		speak.put("lookfor", "Please look for.");
		speak.put("correct", "Correct.");
		speak.put("perfect", "Perfect, good job.");
		speak.put("again", "Thats wrong, try again!");
		speak.put("sorry", "Sorry not correct, keep going!");
		speak.put("who", "Who am I looking for? The person in question");
		speak.put("personwho", "Can you find the person who");
		speak.put("she", "She");
		speak.put("he", "He");
		speak.put("they", "They");
		speak.put("thisis", "This is.");}
		Speak.setSpeak(speak);
	}
}
