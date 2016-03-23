package fr.dauphine.sharemarket.dao;

import java.util.List;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Utilisateur;

public interface UtilisateurDAOInterface {
	
	//Utilisateur
	public Utilisateur connexion(String login, String password);
	public List<Utilisateur> selectAll();
	public boolean supprimer(String login);
	public boolean maj(Utilisateur utilisateur);
	public List<Utilisateur> find(String login, String nom, String prenom, byte adminstrateur, byte membersociety, byte investisseur, byte valide );
	public Utilisateur findById(String login);
	//Membre société
	public Utilisateur creerMembreSociete(String login, String password, String nom, String prenom, String societe) throws ShareMarketException;
	
	//Administrateur
	 
	
	//Investisseur
	public boolean inscriptionInvestisseur(Utilisateur utilisateur);
}