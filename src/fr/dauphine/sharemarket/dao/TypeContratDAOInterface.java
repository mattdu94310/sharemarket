package fr.dauphine.sharemarket.dao;

import java.util.List;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Type_Contrat;

public interface TypeContratDAOInterface {
	List<Type_Contrat> getAll() throws ShareMarketException;
	Type_Contrat findById(int parseInt) throws ShareMarketException;
}
