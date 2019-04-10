package fr.candidat.tondeuse.model;

import java.util.List;

public class DataInput {
	
	private Gazon pointSuperieur;
	
	private List<String> coordonnees;

	private List<String> parcours;
	
	public void setPointSuperieur(Gazon pointSuperieur) {
		this.pointSuperieur = pointSuperieur;
	}
	
	
	public Gazon getPointSuperieur() {
		return pointSuperieur;
	}



	public List<String> getCoordonnees() {
		return coordonnees;
	}



	public void setCoordonnees(List<String> coordonnees) {
		this.coordonnees = coordonnees;
	}



	public List<String> getParcours() {
		return parcours;
	}



	public void setParcours(List<String> parcours) {
		this.parcours = parcours;
	}

}
