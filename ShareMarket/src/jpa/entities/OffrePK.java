package jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Offre database table.
 * 
 */
@Embeddable
public class OffrePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=255)
	private String login;

	@Column(name="id_contrat", insertable=false, updatable=false, unique=true, nullable=false)
	private int idContrat;

	public OffrePK() {
	}
	public String getLogin() {
		return this.login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getIdContrat() {
		return this.idContrat;
	}
	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OffrePK)) {
			return false;
		}
		OffrePK castOther = (OffrePK)other;
		return 
			this.login.equals(castOther.login)
			&& (this.idContrat == castOther.idContrat);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.login.hashCode();
		hash = hash * prime + this.idContrat;
		
		return hash;
	}
}