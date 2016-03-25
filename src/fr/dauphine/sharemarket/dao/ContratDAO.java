package fr.dauphine.sharemarket.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Contrat;
import fr.dauphine.sharemarket.model.Type_Contrat;
@Stateless
public class ContratDAO implements ContratDAOInterface{
	@PersistenceContext(name = "ShareMarket")
	private EntityManager em;
	
	@Override
	public void insert(Contrat contrat) {
		em.persist(contrat);
	}

	@Override
	public Contrat findById(int parseInt) throws ShareMarketException {
		Contrat contrat =em.find(Contrat.class, new Integer(parseInt));
		if(contrat==null)throw new ShareMarketException("15");
		return contrat;
	}

	@Override
	public void update(Contrat contrat) {
		em.merge(contrat);
		em.flush();
	}

}
