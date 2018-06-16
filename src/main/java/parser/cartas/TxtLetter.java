package parser.cartas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.User;

public class TxtLetter extends Letter {
	
	private BufferedWriter writer;

	public void createLetter(User user) throws IOException {
		File path = new File("cartas/txt/");
		path.mkdirs();
		
		//File: para comprobar si existe el fichero especificado.
	    //FileWriter: para especificar el archivo en el que se va a escribir.
	    //BufferedWriter: se encarga de escribir en el archivo.
		File letter = new File(path, user.getIdentificador() + ".txt");
		writer = new BufferedWriter(new FileWriter(letter));
		writer.write("Usuario: " + user.getIdentificador() + "\n" + "Password: " + user.getPassword());
	}
}
