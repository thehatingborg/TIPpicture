package tiptoipicture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sets up structure for serialization with JACKSON 
 * 
 * @author theharingborg
 *
 */

public class BasicSetUp {
	private int productid;
	private String comment;
	private String init;
	private String welcome;
	Map<String, Object> scripts;
	private String resetgamemode;
	private Map<String, String> speak;
	private boolean ownaudio;
	private String mediapath;
	
	/**
	 * 
	 * @param productid the individual id for the YAML file
	 * @param comment here the name given by the user
	 * @param init the in main set init
	 * @param welcome links to the key for voice file for the welcome message
	 * @param reset the in the main set reset
	 */

	public BasicSetUp(int productid, String comment, String init, String welcome,String reset) {
		this.ownaudio = main.ownaudio;
		this.productid = productid;
		this.comment = comment;
		this.init = init;
		this.welcome = welcome;
		//get the current scripts
		this.scripts = Scripts.getScript();
		this.resetgamemode = reset;
		//get the current speak (consisting of key value pairs of the to be said string)
		this.speak = Speak.getSpeak();
		//add a mediapath when own audio is used
		if(main.ownaudio) {
			this.mediapath = "audio/photo/%s";			
		}
	}

	// needed as otherwise JACKSON throws exception
	public BasicSetUp() {
	}

}
