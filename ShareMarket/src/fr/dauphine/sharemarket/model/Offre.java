package fr.dauphine.sharemarket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Offre database table.
 * 
 */
@Entity
@Table(name="Offre")
@NamedQuery(name="Offre.findAll", query="SELECT o FROM Offre o")
public class Offre implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OffrePK id;

	@Column(nullable=false)
	private Timestamp date;

	@Column(nullable=false)
	private int etat;

	@Column(nullable=false)
	private double montant;

	//bi-directional many-to-one association to Contrat
	@ManyToOne
	@JoinColumn(name="id_contrat", nullable=false, insertable=false, updatable=false)
	private Contrat contrat;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="login", nullable=false, insertable=false, updatable=false)
	private Utilisateur utilisateur;

	public Offre() {
	}

	public OffrePK getId() {
		return this.id;
	}

	public void setId(OffrePK id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Contrat getContrat() {
		return this.contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}