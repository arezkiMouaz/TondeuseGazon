package fr.candidat.tondeuse.service;



import java.util.List;

import exceptions.TondeuseException;
import fr.candidat.tondeuse.model.DataInput;
import fr.candidat.tondeuse.model.Gazon;
import fr.candidat.tondeuse.model.TondeuseAutomatique;


//service commun pour la tondeuse à gazon
public interface TondeuseGazonServiceI {

	//initialisation des données du gazon à tondre
	public DataInput InitialiserDonneeGazon() throws TondeuseException;
	
	public TondeuseAutomatique getDonneesPelouseAtondre(String coordonnees);
	
	public List<String> getInstructions(String coordonnees);
	//service qui permet de calculer l'orientation cardinale selon les instructions
	public void calculateOrientation(TondeuseAutomatique tondeuse, String instruction);
	//methode qui permet de piloter la tondeuse et rafraichir ses coordonnées
	public void piloterTondeuse(TondeuseAutomatique tondeuse, Gazon gazon);
	
	
}
