package dbupdate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import model.User;

/**
 * 
 * Gestiona la conexión con la base de datos
 *
 */
public interface Insert {

	User save(User user) throws FileNotFoundException, DocumentException, IOException;

	List<User> findByIdentificador(String identificador);

	List<User> findByEmail(String email);
}
