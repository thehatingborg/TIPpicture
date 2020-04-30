package tiptoipicture;

import java.util.HashMap;
import java.util.Map;

/**
 * Hold all information which can be found in the script part of the YAML file
 * Maps key string to objects. These can be strings or arraylist.  
 * @author thehatingborg
 *
 */

public class Scripts {

	private static Map<String, Object> myscript;

	//Initialise HashMap
	public Scripts() {
		Map<String, Object> scriptInit = new HashMap<String, Object>();
		this.myscript = scriptInit;

	}

	// Getter
	public static Map<String, Object> getScript() {
		return myscript;
	}

	// Setter
	public static void setScript(Map<String, Object> thescript) {
		myscript = thescript;
	}

}
