package fr.dauphine.sharemarket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Societe database table.
 * 
 */
@Entity
@Table(name="Societe")
@NamedQueries({
	@NamedQuery(name="Societe.findAll", query="SELECT s FROM Societe s"),
	@NamedQuery(name="Societe.findByName", query="SELECT s FROM Societe s where s.nom=:nom")
	@NamedQuery(name="Societe.findById",query="SELECT s FROM")
})
public class Societe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_societe", unique=true, nullable=false)
	private int idSociete;

	@Column(name="membre_societe_login", nullable=false, length=255)
	private String membreSocieteLogin;

	@Column(nullable=false, length=100)
	private String nom;

	@Column(length=255)
	private String resume;

	//bi-directional many-to-one association to Contrat
	@OneToMany(mappedBy="societe")
	private List<Contrat> contrats;

	//bi-directional many-to-many association to Secteur_activite
	@ManyToMany(mappedBy="societes")
	private List<Secteur_activite> secteurActivites;

	public Societe() {
	}

	public int getIdSociete() {
		return this.idSociete;
	}

	public void setIdSociete(int idSociete) {
		this.idSociete = idSociete;
	}

	public String getMembreSocieteLogin() {
		return this.membreSocieteLogin;
	}

	public void setMembreSocieteLogin(String membreSocieteLogin) {
		this.membreSocieteLogin = membreSocieteLogin;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public List<Contrat> getContrats() {
		return this.contrats;
	}

	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	public Contrat addContrat(Contrat contrat) {
		getContrats().add(contrat);
		contrat.setSociete(this);

		return contrat;
	}

	public Contrat removeContrat(Contrat contrat) {
		getContrats().remove(contrat);
		contrat.setSociete(null);

		return contrat;
	}

	public List<Secteur_activite> getSecteurActivites() {
		return this.secteurActivites;
	}

	public void setSecteurActivites(List<Secteur_activite> secteurActivites) {
		this.secteurActivites = secteurActivites;
	}

}