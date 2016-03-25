package fr.dauphine.sharemarket.dao;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Societe;

public interface SocieteDAOInterface {
	Societe findByMemberSociete(String membersociete) throws ShareMarketException;
	void updateSociete(String id, String nom, String resume) throws ShareMarketException;
	Societe findById(int societe_id) throws ShareMarketException;
	void save(Societe societeEntity);
	void removeSecteur(int societe, int secteur) throws ShareMarketException;
}
