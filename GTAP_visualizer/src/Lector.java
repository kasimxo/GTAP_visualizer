import java.util.ArrayList;
import java.util.List;

public class Lector {

	public static List<Pelota> lectorCorredor() {
		
		String[] input = Input.listaSeparada("<", "2022", ".xls");
		
		List<String> constructorCorredor = new ArrayList<String>();
		List<Pelota> listadoCorredores = new ArrayList<Pelota>();
		
		for (int i = 4; i<input.length; i++) {
			if(input[i].length()>4) {
				
				
				//Nos quedamos solo con los valores
				String[] limpio = input[i].split(">");
				
				//Eliminamos a todos los retirados
				if (limpio.length>1 && limpio[1].compareTo(" &nbsp;-&nbsp; ")==0) {
					break;
				}
				
				try {
					
					System.out.print(limpio[1] + " ");
					constructorCorredor.add(limpio[1]);
				} catch (Exception e) {
					
					if (input[i].charAt(0)=='t' && input[i].charAt(1)=='r') {
						System.out.println();
						
						listadoCorredores.add(addCorredor(constructorCorredor));
						constructorCorredor.removeAll(constructorCorredor);
					}
				}
				
			}
		}
		
		for (Corredor corredor : listadoCorredores) {
			if(corredor.getRitmo()!=null)
			System.out.println(corredor.getRitmo());
		}
		
		
		return listadoCorredores;
	}

	private static Pelota addCorredor(List<String> constructorCorredor) {
		try {
			Pelota a = new Pelota(constructorCorredor.get(0),constructorCorredor.get(1),constructorCorredor.get(2),constructorCorredor.get(3),constructorCorredor.get(constructorCorredor.size()-16), constructorCorredor.get(constructorCorredor.size()-15),constructorCorredor.get(constructorCorredor.size()-14),constructorCorredor.get(constructorCorredor.size()-13),constructorCorredor.get(constructorCorredor.size()-12),constructorCorredor.get(constructorCorredor.size()-11),constructorCorredor.get(constructorCorredor.size()-10),constructorCorredor.get(constructorCorredor.size()-9),constructorCorredor.get(constructorCorredor.size()-8),constructorCorredor.get(constructorCorredor.size()-7),constructorCorredor.get(constructorCorredor.size()-6),constructorCorredor.get(constructorCorredor.size()-5),constructorCorredor.get(constructorCorredor.size()-4),constructorCorredor.get(constructorCorredor.size()-3),constructorCorredor.get(constructorCorredor.size()-2),constructorCorredor.get(constructorCorredor.size()-1), 8, 350);
			return a;
		} catch (Exception e) {
			Pelota b = new Pelota();
			System.err.println("Corredor no válido" + constructorCorredor +e.getMessage());
			return b;
		}
	}

}
