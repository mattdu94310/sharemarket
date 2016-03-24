package fr.dauphine.sharemarket.dao;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Societe;

public interface SocieteDAOInterface {
	Societe findByMemberSociete(String membersociete) throws ShareMarketException;
	void updateSociete(String id, String nom, String resume) throws ShareMarketException;
}
