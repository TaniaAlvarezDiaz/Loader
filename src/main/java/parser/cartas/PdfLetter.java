package parser.cartas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import model.Agent;

public class PdfLetter extends Letter {
	
	private Document document;

	public void createLetter(Agent agent) throws DocumentException, FileNotFoundException {
		document = null;
		FileOutputStream letter = null;
		File path = new File("cartas/pdf/");
		path.mkdirs();
		letter = new FileOutputStream(new File(path, agent.getIdentificador() + ".pdf"));
		document = new Document();
		PdfWriter.getInstance(document, letter);
		document.open();
		document.add(new Paragraph("Usuario: " + agent.getIdentificador() + "\n Password: " + agent.getPassword()));
		document.close();
	}
}
