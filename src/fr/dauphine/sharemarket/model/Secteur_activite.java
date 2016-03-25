package fr.dauphine.sharemarket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Secteur_activite database table.
 * 
 */
@Entity
@Table(name="Secteur_activite")
@NamedQuery(name="Secteur_activite.findAll", query="SELECT s FROM Secteur_activite s")
public class Secteur_activite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_secteur", unique=true, nullable=false)
	private int idSecteur;

	@Column(nullable=false, length=100)
	private String secteur;

	//bi-directional many-to-many association to Societe
	@ManyToMany
	@JoinTable(
		name="Secteur_activite_Societe"
		, joinColumns={
			@JoinColumn(name="id_secteur", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_societe", nullable=false)
			}
		)
	private List<Societe> societes;

	public Secteur_activite() {
	}

	public int getIdSecteur() {
		return this.idSecteur;
	}

	public void setIdSecteur(int idSecteur) {
		this.idSecteur = idSecteur;
	}

	public String getSecteur() {
		return this.secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public List<Societe> getSocietes() {
		return this.societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSecteur;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Secteur_activite other = (Secteur_activite) obj;
		if (idSecteur != other.idSecteur)
			return false;
		return true;
	}

}