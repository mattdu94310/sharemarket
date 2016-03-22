package ejb.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpa.entities.Utilisateur;;

@Stateless
public class UtilisateurServiceImpl implements UtilisateurService {

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

	@Override
	public List<Utilisateur> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supprimer(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maj(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utilisateur creerMembreSociete(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inscriptionInvestisseur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Utilisateur> find(String login, String nom, String prenom, byte adminstrateur, byte memberSociety, byte investisseur, byte valide) {
		Query query = em.createNamedQuery("Utilisateur.findWithField");
		query.setParameter("login", "%"+login+"%");
		query.setParameter("nom", "%"+nom+"%");
		query.setParameter("prenom", "%"+prenom+"%");
		query.setParameter("administrateur", adminstrateur);
		query.setParameter("investisseur", investisseur);
		query.setParameter("membreSociete", memberSociety);
		return (List<Utilisateur>) query.getResultList();
	}

}