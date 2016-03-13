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

}