package persistence;

import java.util.List;
import model.Agent;
import persistence.util.Jpa;

public class UserFinder {

	public static List<Agent> findByIdentificador(String identificador) {
		return Jpa.getManager().createNamedQuery("Agent.findByIdentificador", Agent.class).setParameter(1, identificador)
				.getResultList();
	}

	public static List<Agent> findByEmail(String email) {
		return Jpa.getManager().createNamedQuery("Agent.findByEmail", Agent.class).setParameter(1, email).getResultList();
	}

}
