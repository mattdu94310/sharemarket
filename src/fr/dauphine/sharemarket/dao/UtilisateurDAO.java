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
import fr.dauphine.sharemarket.model.Societe;
import fr.dauphine.sharemarket.model.Utilisateur;

@Stateless
public class UtilisateurDAO implements UtilisateurDAOInterface {

	private static final Logger LOGGER = Logger.getLogger(UtilisateurDAO.class.getCanonicalName());
	@PersistenceContext(name = "ShareMarket")
	private EntityManager em;

	@Override
	public Utilisateur connexion(String login, String password) {
		Query query = em.createNamedQuery("Utilisateur.connexion");
		query.setParameter("password", password);
		query.setParameter("login", login);
		Utilisateur utilisateur = null;
		try {
			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return utilisateur;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> selectAll() {
		Query query = em.createNamedQuery("Utilisateur.findAll");
		return query.getResultList();
	}

	@Override
	public boolean supprimer(String login) {
		Query query = em.createNamedQuery("Utilisateur.findByLogin");
		query.setParameter("login", login);
		Utilisateur utilisateur = null;
		try {
			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.severe("Pas d'utilisateur trouvé (login = "+login+") : "+e);
			return false;
		}
		em.remove(utilisateur);
		return true;
	}

	@Override
	public boolean maj(Utilisateur utilisateur) {
		em.merge(utilisateur);
		return true;
	}

	@Override
	public boolean inscriptionInvestisseur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> find(String login, String nom, String prenom, byte adminstrateur, byte memberSociety, byte investisseur, byte valide) {
		Query query = em.createNamedQuery("Utilisateur.findWithField");
		query.setParameter("login", "%"+login+"%");
		query.setParameter("nom", "%"+nom+"%");
		query.setParameter("prenom", "%"+prenom+"%");
		query.setParameter("administrateur", adminstrateur);
		query.setParameter("investisseur", investisseur);
		query.setParameter("membreSociete", memberSociety);
		query.setParameter("valide", valide);
		return (List<Utilisateur>) query.getResultList();
	}

	@Override
	public Utilisateur findById(String login) {
		Query query = em.createNamedQuery("Utilisateur.findByLogin");
		query.setParameter("login", login.trim());
		Utilisateur utilisateur = null;
		try {
			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.severe("Pas d'utilisateur trouvé (login = "+login+") : "+e);
			return null;
		}
		return utilisateur;
	}

	@Override
	public Utilisateur creerMembreSociete(String login, String password, String nom, String prenom, String societename) throws ShareMarketException {
		if(findById(login)!=null)throw new ShareMarketException(MessagesDErreurs.getMessageDerreur("1"));
		Query query = em.createNamedQuery("Societe.findByName");
		query.setParameter("nom", societename);
		if(query.getSingleResult()!=null)throw new ShareMarketException(MessagesDErreurs.getMessageDerreur("2"));
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin(login);
		utilisateur.setPassword(password);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setMembreSociete((byte)1);
		utilisateur.setValide((byte)1);
		Societe societe = new Societe();
		societe.setNom(societename);
		societe.setMembreSocieteLogin(login);
		em.persist(utilisateur);
		em.flush();
		em.persist(societe);
		em.flush();
		return utilisateur;
	}

}