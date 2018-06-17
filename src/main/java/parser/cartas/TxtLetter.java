package parser.cartas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Agent;

public class TxtLetter extends Letter {
	
	private BufferedWriter writer;

	public void createLetter(Agent agent) throws IOException {
		File path = new File("cartas/txt/");
		path.mkdirs();
		
		//File: para comprobar si existe el fichero especificado.
	    //FileWriter: para especificar el archivo en el que se va a escribir.
	    //BufferedWriter: se encarga de escribir en el archivo.
		File letter = new File(path, agent.getIdentificador() + ".txt");
		writer = new BufferedWriter(new FileWriter(letter));
		writer.write("Usuario: " + agent.getIdentificador() + "\n" + "Password: " + agent.getPassword());
	}
}
