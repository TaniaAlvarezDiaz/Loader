package persistence;

import java.util.List;
import model.User;
import persistence.util.Jpa;

public class UserFinder {

	public static List<User> findByIdentificador(String identificador) {
		return Jpa.getManager().createNamedQuery("User.findByIdentificador", User.class).setParameter(1, identificador)
				.getResultList();
	}

	public static List<User> findByEmail(String email) {
		return Jpa.getManager().createNamedQuery("User.findByEmail", User.class).setParameter(1, email).getResultList();
	}

}
