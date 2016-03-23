package fr.dauphine.sharemarket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Utilisateur database table.
 * 
 */
@Entity
@Table(name="Utilisateur")
@NamedQueries({
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u"),
@NamedQuery(name="Utilisateur.findByLogin", query="SELECT u FROM Utilisateur u where u.login=:login"),
@NamedQuery(name="Utilisateur.findWithField", query="SELECT u FROM Utilisateur u where u.login like :login and u.nom like :nom and u.prenom like :prenom and (u.administrateur=:administrateur or u.investisseur=:investisseur or u.membreSociete=:membreSociete) and u.valide=:valide"),
@NamedQuery(name="Utilisateur.connexion", query="SELECT u FROM Utilisateur u where u.login=:login and u.password=:password")
})
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=255)
	private String login;

	@Column(nullable=false)
	private byte administrateur;

	@Column(nullable=false)
	private byte investisseur;

	@Column(name="membre_societe", nullable=false)
	private byte membreSociete;

	@Column(nullable=false, length=30)
	private String nom;

	@Column(nullable=false, length=30)
	private String password;

	@Column(nullable=false, length=30)
	private String prenom;

	@Column(nullable=false)
	private byte valide;

	//bi-directional many-to-one association to Offre
	@OneToMany(mappedBy="utilisateur")
	private List<Offre> offres;

	public Utilisateur() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public byte getAdministrateur() {
		return this.administrateur;
	}

	public void setAdministrateur(byte administrateur) {
		this.administrateur = administrateur;
	}

	public byte getInvestisseur() {
		return this.investisseur;
	}

	public void setInvestisseur(byte investisseur) {
		this.investisseur = investisseur;
	}

	public byte getMembreSociete() {
		return this.membreSociete;
	}

	public void setMembreSociete(byte membreSociete) {
		this.membreSociete = membreSociete;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public byte getValide() {
		return this.valide;
	}

	public void setValide(byte valide) {
		this.valide = valide;
	}

	public List<Offre> getOffres() {
		return this.offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public Offre addOffre(Offre offre) {
		getOffres().add(offre);
		offre.setUtilisateur(this);

		return offre;
	}

	public Offre removeOffre(Offre offre) {
		getOffres().remove(offre);
		offre.setUtilisateur(null);

		return offre;
	}

}