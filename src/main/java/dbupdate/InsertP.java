package dbupdate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.lowagie.text.DocumentException;

import model.Agent;
import parser.cartas.Letter;
import parser.cartas.PdfLetter;
import parser.cartas.TxtLetter;
import parser.cartas.WordLetter;
import persistence.UserFinder;
import persistence.util.Jpa;
import reportwriter.ReportWriter;

public class InsertP implements Insert {

	@Override
	public Agent save(Agent agent) throws FileNotFoundException, DocumentException, IOException {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		try {
			if (!UserFinder.findByIdentificador(agent.getIdentificador()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING, "El usuario con el identificador "
						+ agent.getIdentificador() + " ya existe en la base de datos");
				trx.rollback();
			} else if (!UserFinder.findByEmail(agent.getEmail()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"Ya existe un usuario con el email " + agent.getEmail() + " en la base de datos");
				trx.rollback();
			} else {
				Jpa.getManager().persist(agent);
				trx.commit();
				Letter letter = new PdfLetter();
				letter.createLetter(agent);
				letter = new TxtLetter();
				letter.createLetter(agent);
				letter = new WordLetter();
				letter.createLetter(agent);
			}
		} catch (PersistenceException ex) {
			ReportWriter.getInstance().getWriteReport().log(Level.WARNING, "Error de la BBDD");
			if (trx.isActive())
				trx.rollback();
		} finally {
			if (mapper.isOpen())
				mapper.close();
		}
		return agent;
	}

	@Override
	public List<Agent> findByIdentificador(String identificador) {
		return UserFinder.findByIdentificador(identificador);
	}

	@Override
	public List<Agent> findByEmail(String email) {
		return UserFinder.findByEmail(email);
	}
}
