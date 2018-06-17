package parser.cartas;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.lowagie.text.DocumentException;

import model.Agent;

public abstract class Letter {

	public abstract void createLetter(Agent agent) throws DocumentException, FileNotFoundException, IOException;
}
