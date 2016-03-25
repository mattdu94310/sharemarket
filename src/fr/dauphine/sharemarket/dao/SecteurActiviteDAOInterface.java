package fr.dauphine.sharemarket.dao;

import java.util.List;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Secteur_activite;

public interface SecteurActiviteDAOInterface {
	List<Secteur_activite> getAllSecteur() throws ShareMarketException;
	Secteur_activite getSecteurById(int secteur) throws ShareMarketException;
	void save(Secteur_activite secteur_activite);
}
