package dbupdate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import model.Agent;

/**
 * 
 * Gestiona la conexión con la base de datos
 *
 */
public interface Insert {

	Agent save(Agent agent) throws FileNotFoundException, DocumentException, IOException;

	List<Agent> findByIdentificador(String identificador);

	List<Agent> findByEmail(String email);
}
