package fr.dauphine.sharemarket.dao;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Societe;
import fr.dauphine.sharemarket.model.Utilisateur;

@Stateless
public class SocieteDAO implements SocieteDAOInterface {
	private static final Logger LOGGER = Logger.getLogger(UtilisateurDAO.class.getCanonicalName());
	@PersistenceContext(name = "ShareMarket")
	private EntityManager em;
	
	
	@Override
	public Societe findByMemberSociete(String membersociete) throws ShareMarketException {
		Query query = em.createNamedQuery("Societe.findByMembreSociete");
		query.setParameter("membreSociete", membersociete);
		Societe societe = null;
		try {
			societe = (Societe) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ShareMarketException("7");
		}
		return societe;
	}


	@Override
	public void updateSociete(String id, String nom, String resume) throws ShareMarketException {
		
		Query query = em.createNamedQuery("Societe.findById");
		query.setParameter("id", Integer.parseInt(id));
		Societe societe = null;
		try {
			societe = (Societe) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ShareMarketException("7");
		}
		societe.setNom(nom);
		societe.setResume(resume);
		em.merge(societe);
		return;
	}
	
	
}
