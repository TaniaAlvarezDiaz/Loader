package parsertest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.lowagie.text.DocumentException;

import model.Agent;
import parser.cartas.*;

public class LetterTest {

	@Test
	public void creadasCorrectamente() throws FileNotFoundException, DocumentException, IOException {
		Letter letter = new PdfLetter();
		Agent user1 = new Agent("Fernando Perez Menendez", "", "ferpm@gmail.com", "87654321P", "Person");
		letter.createLetter(user1);

		File file = new File("cartas/pdf/87654321P.pdf");
		assertTrue(file.exists());
		file.delete();

		letter = new WordLetter();
		letter.createLetter(user1);

		file = new File("cartas/word/87654321P.docx");
		assertTrue(file.exists());
		file.delete();

		letter = new TxtLetter();
		letter.createLetter(user1);

		file = new File("cartas/txt/87654321P.txt");
		assertTrue(file.exists());
		file.delete();
	}

}
