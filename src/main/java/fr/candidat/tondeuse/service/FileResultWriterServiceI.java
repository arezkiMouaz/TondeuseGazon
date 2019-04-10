package fr.candidat.tondeuse.service;

import exceptions.TondeuseException;
import fr.candidat.tondeuse.model.TondeuseAutomatique;

public interface FileResultWriterServiceI {
	
	//service pour ecrire le resultat finaux dans un fichier
	public void writeResult(TondeuseAutomatique tondeuse) throws TondeuseException;;

}
