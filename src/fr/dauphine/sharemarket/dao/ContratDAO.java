package fr.dauphine.sharemarket.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.dauphine.sharemarket.model.Contrat;
@Stateless
public class ContratDAO implements ContratDAOInterface{
	@PersistenceContext(name = "ShareMarket")
	private EntityManager em;
	
	@Override
	public void insert(Contrat contrat) {
		em.persist(contrat);
	}

}
