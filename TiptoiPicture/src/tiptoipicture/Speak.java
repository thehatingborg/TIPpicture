package tiptoipicture;

import java.util.HashMap;
import java.util.Map;
/**
 * Hold all information which can be found in the speak part of the YAML file
 * Maps key string to string.  These will later be voice by a computer voice
 * @author thehatingborg
 *
 */
public class Speak {

	private static Map<String, String> myspeak;

	//Initialise HashMap
	public Speak() {
			Map<String, String> speakInit =  new  HashMap<String, String>();
			myspeak = speakInit;
			
		}

	// Getter
	public static Map<String, String> getSpeak() {
		return myspeak;
	}

	// Setter
	public static void setSpeak(Map<String, String> newmap) {
		myspeak = newmap;
	}

}
