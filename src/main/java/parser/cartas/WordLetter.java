package parser.cartas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.Agent;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.lowagie.text.DocumentException;

public class WordLetter extends Letter {

	private FileOutputStream carta;

	public void createLetter(Agent agent) throws FileNotFoundException, DocumentException, IOException {
		XWPFDocument documento = new XWPFDocument();
		File path = new File("cartas/word/");
		path.mkdir();
		carta = new FileOutputStream(new File(path, agent.getIdentificador() + ".docx"));
		XWPFParagraph paragraph = documento.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText("Usuario: " + agent.getIdentificador());
		run.addBreak();
		run.setText("Password: " + agent.getPassword());
		documento.write(carta);
		documento.close();
		carta.close();
	}
}
