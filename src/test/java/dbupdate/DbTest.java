package dbupdate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Test;

import com.lowagie.text.DocumentException;

import executer.ActionSingleton;
import model.Agent;
import persistence.UserFinder;
import persistence.util.Jpa;

public class DbTest {

	@Test
	public void usuarioYaExistenteIdentificador() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		Agent user1 = new Agent("Fernando Perez Menendez", "", "ferpm@gmail.com", "ST1", "Person");
		Agent user2 = new Agent("Fernando Perez Menendez", "", "ferpm@gmail.com", "ST1", "Person");

		aS.getAF().saveData(user1);
		aS.getAF().saveData(user2);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Agent> test = UserFinder.findByIdentificador("ST1");
		assertEquals(test.get(0).getEmail(), "ferpm@gmail.com");

		trx.commit();
		mapper.close();
	}

	@Test
	public void usuarioYaExistenteEmail() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		Agent user1 = new Agent("Sensor temperatura S1", "43.36&-5.85", "francisco@gmail.com", "ST1", "Sensor");
		Agent user3 = new Agent("Sensor temperatura S1", "43.36&-5.85", "francisco@gmail.com", "ST1", "Sensor");

		aS.getAF().saveData(user1);
		aS.getAF().saveData(user3);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Agent> test = UserFinder.findByEmail("francisco@gmail.com");
		assertEquals(test.get(0).getIdentificador(), "ST1");

		trx.commit();
		mapper.close();

	}

	@After
	public void deleting() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<Agent> aBorrar = UserFinder.findByIdentificador("ST1");
		Jpa.getManager().remove(aBorrar.get(0));
		trx.commit();
		mapper.close();
	}

}
