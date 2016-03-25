package fr.dauphine.sharemarket.dao;

import java.util.logging.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.dauphine.sharemarket.error.MessagesDErreurs;
import fr.dauphine.sharemarket.error.ShareMarketException;
import fr.dauphine.sharemarket.model.Secteur_activite;
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
			throw new ShareMarketException(MessagesDErreurs.getMessageDerreur("7"));
		}
		societe.setNom(nom);
		societe.setResume(resume);
		em.merge(societe);
		return;
	}


	@Override
	public Societe findById(int societe_id) throws ShareMarketException {
		Query query = em.createNamedQuery("Societe.findById");
		query.setParameter("id", societe_id);
		Societe societe = null;
		try {
			societe = (Societe) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ShareMarketException(MessagesDErreurs.getMessageDerreur("12"));
		}
		return societe;
	}


	@Override
	public void save(Societe societeEntity) {
		em.merge(societeEntity);
		em.flush();
	}


	@Override
	public void removeSecteur(int societeid, int secteur) throws ShareMarketException {
		Query query = em.createNamedQuery("Societe.findById");
		query.setParameter("id", societeid);
		Societe societe = null;
		try {
			societe = (Societe) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ShareMarketException(MessagesDErreurs.getMessageDerreur("12"));
		}
		Secteur_activite secteur_activite =em.find(Secteur_activite.class, new Integer(secteur));
		if(secteur_activite==null)throw new ShareMarketException("11");
		List<Secteur_activite> sectors = societe.getSecteurActivites();
		sectors.remove(secteur_activite);
		societe.setSecteurActivites(sectors);
		List<Societe> societes = secteur_activite.getSocietes();
		societes.remove(societe);
		secteur_activite.setSocietes(societes);
		em.merge(societe);
		em.merge(secteur_activite);
		em.flush();
	}
	
	
}
