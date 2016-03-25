package fr.dauphine.sharemarket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Contrat database table.
 * 
 */
@Entity
@Table(name="Contrat")
@NamedQuery(name="Contrat.findAll", query="SELECT c FROM Contrat c")
public class Contrat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contrat", unique=true, nullable=false)
	private int idContrat;

	@Column(name="achat_vente", nullable=false)
	private byte achatVente;

	@Column(name="date_emission", nullable=false)
	private Timestamp dateEmission;

	@Column(name="date_fin_enchere", nullable=false)
	private Timestamp dateFinEnchere;

	@Column(nullable=false)
	private int etat;

	@Column(name="prix_depart", nullable=false)
	private double prixDepart;

	@Column(name="prix_fixe", nullable=false)
	private double prixFixe;

	//bi-directional many-to-one association to Type_Contrat
	@ManyToOne
	@JoinColumn(name="id_type_contrat", nullable=false)
	private Type_Contrat typeContrat;

	//bi-directional many-to-one association to Societe
	@ManyToOne
	@JoinColumn(name="id_societe", nullable=false)
	private Societe societe;

	//bi-directional many-to-one association to Offre
	@OneToMany(mappedBy="contrat")
	private List<Offre> offres;

	public Contrat() {
	}

	public int getIdContrat() {
		return this.idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public byte getAchatVente() {
		return this.achatVente;
	}

	public void setAchatVente(byte achatVente) {
		this.achatVente = achatVente;
	}

	public Timestamp getDateEmission() {
		return this.dateEmission;
	}

	public void setDateEmission(Timestamp dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Timestamp getDateFinEnchere() {
		return this.dateFinEnchere;
	}

	public void setDateFinEnchere(Timestamp dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public double getPrixDepart() {
		return this.prixDepart;
	}

	public void setPrixDepart(double prixDepart) {
		this.prixDepart = prixDepart;
	}

	public double getPrixFixe() {
		return this.prixFixe;
	}

	public void setPrixFixe(double prixFixe) {
		this.prixFixe = prixFixe;
	}

	public Type_Contrat getTypeContrat() {
		return this.typeContrat;
	}

	public void setTypeContrat(Type_Contrat typeContrat) {
		this.typeContrat = typeContrat;
	}

	public Societe getSociete() {
		return this.societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public List<Offre> getOffres() {
		return this.offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public Offre addOffre(Offre offre) {
		getOffres().add(offre);
		offre.setContrat(this);

		return offre;
	}

	public Offre removeOffre(Offre offre) {
		getOffres().remove(offre);
		offre.setContrat(null);

		return offre;
	}

}