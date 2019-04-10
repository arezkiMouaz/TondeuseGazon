package fr.candidat.tondeuse.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import exceptions.TondeuseException;
import fr.candidat.tondeuse.model.DataInput;
import fr.candidat.tondeuse.model.Direction;
import fr.candidat.tondeuse.model.Gazon;
import fr.candidat.tondeuse.model.TondeuseAutomatique;
import fr.candidat.tondeuse.service.TondeuseGazonServiceI;
import fr.candidat.tondeuse.utils.Utilities;

@Service
public class TondeuseGazonServiceImpl implements TondeuseGazonServiceI {


	

	private final static String TOURNE_LA_GAUCHE="G";
	
	private final static String TOURNE_LA_DROITE="D";
	
	

	@Override
	public DataInput InitialiserDonneeGazon() throws TondeuseException{
		List<String> positions = new ArrayList<String>();
		List<String> parcours = new ArrayList<String>();
		DataInput donnees=new DataInput();


		Gazon gazon=new Gazon();


		try {
			Properties props=Utilities.getProperties();
			String chemin=props.getProperty("INPUT_PATH");
			File f = new File(chemin);

			BufferedReader b = new BufferedReader(new FileReader(f));

			String readLine = "";

			System.out.println("Reading file using Buffered Reader");
			int numLigne=0;
			Gazon gazoFromFile=null;
			while ((readLine = b.readLine()) != null) {
				if(numLigne==0) {
					gazon=getGazon(numLigne, readLine);
				}
			
				if(numLigne % 2 == 0 && (numLigne!=0)) {
					parcours.add(readLine);
					
				}else if(numLigne % 2 != 0 && (numLigne!=0)) {
					positions.add(readLine);
				}	
				System.out.println(readLine);
				numLigne++;
			}

		} catch (IOException e) {
			throw new TondeuseException(e.getMessage());
		}

		donnees.setPointSuperieur(gazon);
		donnees.setCoordonnees(positions);
		donnees.setParcours(parcours);
		return donnees;

	}


	private Gazon getGazon(int numeroLigne, String ligne) {
		Gazon gazon=new Gazon();
		if(numeroLigne==0) {

			String string = ligne;
			String[] parts = string.split("");
			int x=Integer.parseInt(parts[0]);	
			int y=Integer.parseInt(parts[2]);	
			gazon.setX(x);
			gazon.setY(y);
		}
		return gazon;
	}





	@Override
	public TondeuseAutomatique getDonneesPelouseAtondre(String coordonnees) {
		TondeuseAutomatique tondeuse=new TondeuseAutomatique();
		String string = coordonnees;
		String[] parts = string.split("");
		int x=Integer.parseInt(parts[0]);	
		int y=Integer.parseInt(parts[2]);
		Direction direction=Direction.byName(parts[4]);
		tondeuse.setX(x);
		tondeuse.setY(y);
		tondeuse.setDirection(direction);
		return tondeuse;
	}


	@Override
	public List<String> getInstructions(String intructs) {
		List<String> instructions=Utilities.getCharArrayFromString (intructs);
		return instructions;
	}


	@Override
	public void piloterTondeuse(TondeuseAutomatique tondeuse, Gazon gazon) {
		int x=tondeuse.getX();
		int y=tondeuse.getY();
		if(tondeuse.getDirection() == Direction.E) {
			tondeuse.setX((x+1<gazon.getX() || x+1>=0)?x+1:x);
		}else if(tondeuse.getDirection() == Direction.N) {
			tondeuse.setY((y+1<gazon.getY() || y+1>=0)?y+1:y);
		}else if(tondeuse.getDirection() == Direction.W) {
			tondeuse.setX((x-1<gazon.getX() || x-1>=0)?x-1:x);
		}else if(tondeuse.getDirection() == Direction.S) {
			tondeuse.setY((y-1<gazon.getY() || y-1>=0)?y-1:y);
		}
	}


	@Override
	public void calculateOrientation(TondeuseAutomatique tondeuse, String instruction) {
		String orientationInitiale = tondeuse.getDirection().name();
		if(orientationInitiale ==Direction.N.name()) {
			if(instruction.equals(TOURNE_LA_GAUCHE)) {
				tondeuse.setDirection(Direction.W);
			}else if(instruction.equals(TOURNE_LA_DROITE)) {
				tondeuse.setDirection(Direction.E);
			}
		}
		
		
		if(orientationInitiale ==Direction.W.name()) {
			if(TOURNE_LA_GAUCHE.equals(instruction)) {
				tondeuse.setDirection(Direction.S);
			}else if(TOURNE_LA_DROITE.equals(instruction)) {
				tondeuse.setDirection(Direction.N);
			}
		}
		
		if(orientationInitiale ==Direction.E.name()) {
			if(TOURNE_LA_GAUCHE.equals(instruction)) {
				tondeuse.setDirection(Direction.N);
			}else if(TOURNE_LA_DROITE.equals(instruction)) {
				tondeuse.setDirection(Direction.S);
			}
		}
		
		if(orientationInitiale ==Direction.S.name()) {
			if(TOURNE_LA_GAUCHE.equals(instruction)) {
				tondeuse.setDirection(Direction.E);
			}else if(TOURNE_LA_DROITE.equals(instruction)) {
				tondeuse.setDirection(Direction.W);
			}
		}
	}

}








