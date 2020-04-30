package tiptoipicture;

/**
 * Copyright (c) 2020 thehatingborg * 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * @author thehatingborg https://github.com/thehatingborg/
 * 
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Main class The tiptoipicture program implements a GUI for the creation of a
 * YAML file this file can be then loaded on a tiptoi pen. The YAML file creates
 * a name learning scenario and offers two games the "name game" and the
 * "feature game". Besides that it offers a description mode.
 * 
 * @author thehatingborg
 * @version 1.0
 *
 */
public class main {

	protected static boolean namenormalboolean;
	protected static boolean descriptionnormalboolean;
	protected static boolean namegameboolean;
	protected static boolean featuregameboolean;
	protected static String comment;
	protected static boolean next;
	protected static boolean waiting;
	protected static String resetgamemode;
	protected static String welcome;
	protected static String init;
	protected static InterfaceView myinitialView;
	protected static InterfaceController myinterfacecontroller;

	private static Scripts script;
	private static Speak speak;
	protected static Games games;
	protected static boolean ownaudio;

	/**
	 * Main
	 * 
	 * Initialises GUI by showing the initial view Initialises @script and @speak
	 * such that they can be called by other methods and classes.
	 * 
	 */
	public static void main(String[] args) {
		// weather created audio files are used instead of a computer voice for the
		// static parts
		ownaudio = true;

		/*
		 * Creates the initial GUI view Adds view to controller
		 */
		myinitialView = new InterfaceView();
		myinterfacecontroller = new InterfaceController(myinitialView);
		myinitialView.init();
		myinitialView.setSize(800, 500);
		myinitialView.setVisible(true);

		script = new Scripts();
		speak = new Speak();
	}

	/**
	 * Creates a YAML file Set's init, welcome and reset for YAML Creates random
	 * product id for learning scenario. Uses JACKSON
	 * https://github.com/FasterXML/jackson-dataformats-text/tree/master/yaml for
	 * serialization.
	 * 
	 * @param file
	 *            where the YAML document is saved in
	 */

	public static void createYAMLFile(File file) {
		// intialises all necessary variables in the YAML
		init = "$nano := 0 $deno := 0 $naga := 0 $fega := 0 $numberperson := " + InterfaceController.number
				+ " $answer :=13 $newtoname := 0 " + "$newtofeature := 0 $pronoun := 0";
		welcome = "welcome";
		resetgamemode = "resetgamemode: $nano := 0 $deno := 0 $naga := 0 $fega := 0";
		games.allToScript();
		games.allToSpeak();
		// create random product-id: range recommended in
		// https://tttool.readthedocs.io/de/latest/
		int randomproductid = 900 + (int) (Math.random() * ((950 - 900) + 1));
		// Set up structure which will serialized
		BasicSetUp basic = new BasicSetUp(randomproductid, comment, init, welcome, resetgamemode);

		try {
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
				ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
				mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
				mapper.writeValue(file, basic);
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		modifyFile(file, "productid", "product-id");
		modifyFile(file, "mediapath", "media-path");
	}

	/**
	 * Fixes YAML file as with JAVA variables with - inside are not usable due to
	 * the serialization with JACKSON the variable is first set without - and change
	 * accordingly here. This currently affects the variables product-id and
	 * media-path 
	 * 
	 * source: https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/ (pramodbablad 2017)
	 * 
	 * @param fileToChange
	 *            the YAML file which needs to be corrected
	 * @param oldString
	 *            the old String which should be change
	 * @param newString
	 *            the string the oldString should changed into
	 */
	static void modifyFile(File fileToChange, String oldString, String newString) {
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;

		try {
			// Initalising an Inputstream
			reader = new BufferedReader(new FileReader(fileToChange));
			// Reading all the lines of input text file into oldContent
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}

			// Replacing oldString with newString in the oldContent
			String newContent = oldContent.replaceAll(oldString, newString);

			writer = new FileWriter(fileToChange);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
