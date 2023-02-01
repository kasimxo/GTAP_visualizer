import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Input {
	
	private static String root = "C:\\Users\\Andrés\\workspace\\GravityBall\\data\\";
	
	/**
	 * Este método de lee un archivo y te devuelve un listado separado por las líneas del archivo. 
	 * @param fileName
	 * Es el nombre del archivo
	 * @param extension
	 * Es la extensión del archivo
	 * @return
	 */
	public static List<String> listaString(String fileName, String extension) {
		
		List<String> input = new ArrayList<String>();
		String name = root + fileName + extension;
		Path path = Paths.get(name);
		try {
			input = Files.readAllLines(path);
		} catch (Exception e) {
			System.err.println("No se ha encontrado el archivo con ruta " + name);
		}
		return input;
	}
	
	/**
	 * Este método de lee un archivo y te devuelve un array separado según el divisor 
	 * @param fileName
	 * Es el nombre del archivo
	 * @param extension
	 * Es la extensión del archivo
	 * @return
	 */
public static String[] listaSeparada(String divisor, String fileName, String extension) {
		
		String[] error = {};
		String name = root + fileName + extension;
		Path path = Paths.get(name);
		try {
			String linea = Files.readString(path);
			String[] input = linea.split(divisor);
			return input;
		} catch (Exception e) {
			System.err.println("No se ha encontrado el archivo con ruta " + name);
		} 
		return error;
	}
	
}
