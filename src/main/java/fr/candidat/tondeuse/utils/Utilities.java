package fr.candidat.tondeuse.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import exceptions.TondeuseException;

public class Utilities {

	
	
	public static List<String> getCharArrayFromString (String instructionsChaine) {
		
		List<String> instructions=new ArrayList<String>();
		char[] instructionsToCharArray = instructionsChaine.toCharArray();
 
		for (char intruction : instructionsToCharArray) {
			if(intruction!=' ') {
				instructions.add(String.valueOf(intruction));
			}
		}
		return instructions;
	}
	
	public static Properties getProperties() throws TondeuseException {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = Utilities.class.getClassLoader().getResourceAsStream("config.properties");

			// load a properties file
			prop.load(input);


		} catch (IOException ex) {
			throw new TondeuseException(ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new TondeuseException(e.getMessage());
				}
			}
		}
		return prop;

	  }
		
	}

