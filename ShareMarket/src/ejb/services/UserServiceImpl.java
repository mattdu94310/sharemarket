package ejb.services;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.entities.User;
 
@Stateless
public class UserServiceImpl implements UserService {
 
	@PersistenceContext(name = "ShareMarket")
	private EntityManager em;
 
	@Override
	public void addUser(User user) {
		em.persist(user);
	}
 
}