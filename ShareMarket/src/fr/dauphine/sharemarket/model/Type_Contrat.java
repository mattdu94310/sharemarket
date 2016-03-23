package fr.dauphine.sharemarket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Type_Contrat database table.
 * 
 */
@Entity
@Table(name="Type_Contrat")
@NamedQuery(name="Type_Contrat.findAll", query="SELECT t FROM Type_Contrat t")
public class Type_Contrat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_type_contrat", unique=true, nullable=false)
	private int idTypeContrat;

	@Column(name="type_contrat", nullable=false, length=50)
	private String typeContrat;

	//bi-directional many-to-one association to Contrat
	@OneToMany(mappedBy="typeContrat")
	private List<Contrat> contrats;

	public Type_Contrat() {
	}

	public int getIdTypeContrat() {
		return this.idTypeContrat;
	}

	public void setIdTypeContrat(int idTypeContrat) {
		this.idTypeContrat = idTypeContrat;
	}

	public String getTypeContrat() {
		return this.typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public List<Contrat> getContrats() {
		return this.contrats;
	}

	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	public Contrat addContrat(Contrat contrat) {
		getContrats().add(contrat);
		contrat.setTypeContrat(this);

		return contrat;
	}

	public Contrat removeContrat(Contrat contrat) {
		getContrats().remove(contrat);
		contrat.setTypeContrat(null);

		return contrat;
	}

}