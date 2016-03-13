package jpa.entities;

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

}