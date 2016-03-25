package fr.dauphine.sharemarket.dao;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Contrat;

public interface ContratDAOInterface {
	void insert(Contrat contrat);
	Contrat findById(int parseInt) throws ShareMarketException;
	void update(Contrat contrat);
}
