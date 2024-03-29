package parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import reportwriter.ReportWriter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lowagie.text.DocumentException;

import executer.*;
import model.Agent;

public class RList implements ReadList {
	
	private ActionFacade aF = new ActionFacadeClass();
	private ArrayList<List<XSSFCell>> allUsers;

	private Map<Integer, String> agents;
	private final static String AGENTSFILE = "src/main/resources/agents.csv";

	public ActionFacade getaF() {
		return aF;
	}

	public void setaF(ActionFacade aF) {
		this.aF = aF;
	}
	
	public ArrayList<List<XSSFCell>> getAllUsers() {
		return allUsers;
	}
	
	/**
	 * Lee el fichero excel de la ruta pasada por parametro Si el fichero no esta en
	 * formato excel, detiene la lectura y escribe en el log la causa del error. Va
	 * leyendo linea por linea(hay un usuario en cada linea): Para cada linea crea
	 * un objeto Agent y se lo pasa al metodo cargarDatos del AtionFacade. Si existe
	 * algun fallo de FORMATO se ignora esa linea y se pasa a la siguiente, ademas
	 * de escribir dicho error en el log.
	 * 
	 * @param path
	 *            ruta del fichero
	 * 
	 * @exception FileNotFoundException
	 *                No se encuentra el fichero excel
	 * @throws DocumentException
	 */
	@Override
	public void load(String path) throws FileNotFoundException, DocumentException {
		//Cargamos los agentes del fichero csv
		loadAgents();
		
		InputStream excelFile = null;
		XSSFWorkbook excel = null;
		allUsers = new ArrayList<List<XSSFCell>>();
		int i = 0;
		try {
			excelFile = new FileInputStream(path);
			excel = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = excel.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			List<XSSFCell> user;
			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {
				user = new ArrayList<XSSFCell>();
				row = (XSSFRow) rows.next();
				if (i > 0 && row.getCell(0) != null) {
					//Son 5 columnas
					for (int k = 0; k < 5; k++) {
						cell = (XSSFCell) row.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						user.add(cell);
						System.out.print(cell.toString() + " ; ");
					}
					//Si el tipo de agente no esta disponible se lanza excepcion
					if (!agents.containsKey((int)user.get(4).getNumericCellValue()))
						throw new IOException("El tipo de agente no esta disponible");
					
					System.out.println();
					
					//Comprobamos la localizacion
					if (comprobarLocalizacion(user.get(1).getStringCellValue(), (int)user.get(4).getNumericCellValue())) {
						allUsers.add(user);
						crearUsuarios(user);
					}
				}
				i++;
			}
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ioe) {
			System.err.println("Problema con la lectura del excel en la linea " + i);
			ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
					"Problema con la lectura del excel en la linea " + i);
		} finally {
			if (excelFile != null) {
				try {
					excelFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (excel != null) {
				try {
					excel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void crearUsuarios(List<XSSFCell> list) throws FileNotFoundException, DocumentException, IOException {
		Agent agent = new Agent(list.get(0).getStringCellValue(), list.get(1).getStringCellValue(),
				list.get(2).getStringCellValue(), list.get(3).getStringCellValue(),
				agents.get((int) list.get(4).getNumericCellValue()));
		InsertR insert = new InsertR();
		insert.save(agent);
	}
	
	/**
	 * Método para cargar los agentes del archivo csv
	 */
	private void loadAgents() {
		BufferedReader br = null;
		agents = new HashMap<Integer, String>();

		try {
			br = new BufferedReader(new FileReader(AGENTSFILE));
			while (br.ready()) {
				String[] linea = br.readLine().split(";");
				Integer key = Integer.parseInt(linea[0]);
				String value = linea[1];
				agents.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Metodo que comprueba que la localizacion tenga el formato correcto.
	 * Además comprueba que los sensores tengan siempre locacalizacion.
	 * 
	 * @param localizacion
	 * @param tipo
	 * @return
	 */
	private boolean comprobarLocalizacion(String localizacion, int tipo) {
		boolean formatoValido = false;
		//Si no hay localizacion y es una persona o entidad, la localizacion es valida
		if (localizacion.isEmpty() && (tipo == 1 || tipo == 2))
			return true;
		//Comprobamos el formato
		if (localizacion.contains("&")) {
			try {
				String[] trozos = localizacion.split("&");
				Double.parseDouble(trozos[0]);
				Double.parseDouble(trozos[1]);
				//Si no puede convertir a double salta excepcion
				formatoValido = true;
			}catch (NumberFormatException e) {
				System.err.println(e.getMessage());
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"El formato de la localizacion no es valido");
			}
		}
		return formatoValido;
}

}
