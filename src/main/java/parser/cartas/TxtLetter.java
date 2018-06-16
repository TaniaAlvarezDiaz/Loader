package parser.cartas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import model.User;

public class TxtLetter extends Letter {
	
	private Writer writer;

	public void createLetter(User user) throws IOException {
		File path = new File("cartas/txt/");
		path.mkdirs();
		File letter = new File(path, user.getIdentificador() + ".txt");
		writer = new FileWriter(letter);
		writer.write("Usuario: " + user.getIdentificador() + "\n" + "Password: " + user.getPassword());
	}
}
