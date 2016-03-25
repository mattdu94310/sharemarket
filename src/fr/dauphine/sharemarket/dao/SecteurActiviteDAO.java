package fr.dauphine.sharemarket.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Secteur_activite;
import fr.dauphine.sharemarket.model.Utilisateur;

@Stateless
public class SecteurActiviteDAO implements SecteurActiviteDAOInterface{
	@PersistenceContext(name = "ShareMarket")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Secteur_activite> getAllSecteur() throws ShareMarketException {
		Query query = em.createNamedQuery("Secteur_activite.findAll");
		List<Secteur_activite> secteurs = null;
		try {
			secteurs = (List<Secteur_activite>) query.getResultList();
		} catch (NoResultException e) {
			throw new ShareMarketException("9");
		}
		return secteurs;
	}

	@Override
	public Secteur_activite getSecteurById(int secteur) throws ShareMarketException {
		Secteur_activite secteur_activite =em.find(Secteur_activite.class, new Integer(secteur));
		if(secteur_activite==null)throw new ShareMarketException("11");
		return secteur_activite;
	}

	@Override
	public void save(Secteur_activite secteur_activite) {
		em.merge(secteur_activite);
		em.flush();
	}
	
	
}
