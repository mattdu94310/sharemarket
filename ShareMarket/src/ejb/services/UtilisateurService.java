package ejb.services;

import java.util.List;

import jpa.entities.Societe;
import jpa.entities.Utilisateur;

public interface UtilisateurService {
	
	//Utilisateur
	public Utilisateur connexion(String login, String password);
	public List<Utilisateur> getAll();
	public boolean supprimer(Utilisateur utilisateur);
	public boolean maj(Utilisateur utilisateur);
	
	//Membre société
	public Utilisateur creerMembreSociete(String login, String password);
	
	//Administrateur
	 
	
	//Investisseur
	public boolean inscriptionInvestisseur(Utilisateur utilisateur);
}