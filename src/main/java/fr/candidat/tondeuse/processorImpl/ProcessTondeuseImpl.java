package fr.candidat.tondeuse.processorImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exceptions.TondeuseException;
import fr.candidat.tondeuse.Processor.ProcessTondeuseI;
import fr.candidat.tondeuse.model.DataInput;
import fr.candidat.tondeuse.model.Gazon;
import fr.candidat.tondeuse.model.TondeuseAutomatique;
import fr.candidat.tondeuse.service.FileResultWriterServiceI;
import fr.candidat.tondeuse.service.TondeuseGazonServiceI;

@Component 
public class ProcessTondeuseImpl implements ProcessTondeuseI{

	
	private final static String TOURNE_LA_GAUCHE="G";
	
	private final static String TOURNE_LA_DROITE="D";
	
	private final static String AVANCE_EN_AVANT="A";
	
	@Autowired
	private TondeuseGazonServiceI tondeuseGazonService;
	
	@Autowired
	private FileResultWriterServiceI FileResultWriterServive;
	


	@Override
	public void PreProcess() {
	
		
	}





	@Override
	public void Process() {
		try {
			//initialisation des données du gazonet des coordonnées des tondeuses
			DataInput donnees=tondeuseGazonService.InitialiserDonneeGazon();
			Gazon gazon=donnees.getPointSuperieur();
			int nombrePelousesAtondre=donnees.getCoordonnees().size();
			//parcours de toutes les pelouses
			for(int i=0 ;i<nombrePelousesAtondre; i++) {
				TondeuseAutomatique tondeuse=new TondeuseAutomatique();
				List<String> instructions=new ArrayList<String>();
				//ramener les instructions des tondeuses
				instructions=tondeuseGazonService.getInstructions(donnees.getParcours().get(i));
				//ramener les données de la pelouse a tondre
				tondeuse= tondeuseGazonService.getDonneesPelouseAtondre(donnees.getCoordonnees().get(i));
				//parcours de toutes les instructions
				for(String instruction: instructions) {
			        switch(instruction) 
			        { 
			            case TOURNE_LA_DROITE: 
			            	tondeuseGazonService.calculateOrientation(tondeuse, TOURNE_LA_DROITE);
			            	
			                break; 
			            case TOURNE_LA_GAUCHE: 
			            	tondeuseGazonService.calculateOrientation(tondeuse, TOURNE_LA_GAUCHE);
			                break;  
			            case AVANCE_EN_AVANT: 
			            	//piloter la tondeuse selon la direction
			            	tondeuseGazonService.piloterTondeuse(tondeuse, gazon);
			                break; 
			            default: 
			                
			        } 
				}
				//ecrire le resultat final de la position de la tondeuse pour chaque pelouse
				FileResultWriterServive.writeResult(tondeuse);
			    }
			
		} catch (TondeuseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	


	public void setTondeuseGazonService(TondeuseGazonServiceI tondeuseGazonService) {
		this.tondeuseGazonService = tondeuseGazonService;
	}










	public void setFileResultWriterServive(FileResultWriterServiceI fileResultWriterServive) {
		FileResultWriterServive = fileResultWriterServive;
	}





	
	
}
