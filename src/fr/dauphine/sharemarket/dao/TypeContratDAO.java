package fr.dauphine.sharemarket.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.dauphine.sharemarket.error.MessagesDErreurs;
import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Secteur_activite;
import fr.dauphine.sharemarket.model.Societe;
import fr.dauphine.sharemarket.model.Type_Contrat;

@Stateless
public class TypeContratDAO implements TypeContratDAOInterface {
	private static final Logger LOGGER = Logger.getLogger(UtilisateurDAO.class.getCanonicalName());
	@PersistenceContext(name = "ShareMarket")
	private EntityManager em;


	@SuppressWarnings("unchecked")
	@Override
	public List<Type_Contrat> getAll() throws ShareMarketException {
		Query query = em.createNamedQuery("Type_Contrat.findAll");
		List<Type_Contrat> types = null;
		try {
			types = (List<Type_Contrat>) query.getResultList();
		} catch (NoResultException e) {
			throw new ShareMarketException("12");
		}
		return types;
	}


	@Override
	public Type_Contrat findById(int parseInt) throws ShareMarketException {
		Type_Contrat type_Contrat =em.find(Type_Contrat.class, new Integer(parseInt));
		if(type_Contrat==null)throw new ShareMarketException("11");
		return type_Contrat;
	}

}
