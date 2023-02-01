import java.awt.Color;

public class Pelota extends Corredor{
	
	private int radio;
	private Color color;
	//PosX viene determinada por el ritmo
	//El ritmo mas lento que puede llevar alguien y terminar la carrera a tiempo es 21:42,86 min/km
	private int PosX, PosY;
	
	public Pelota() {};
	
	public Pelota(String posicion, String dorsal, String nombre, String sexo, String control_1, String control_2,
			String control_3, String control_4, String control_5, String control_6, String control_7, String control_8,
			String control_9, String control_10, String control_11, String control_12, String control_13,
			String control_14, String control_15, String ritmo, int radio, int posY) {
		
		super( posicion,  dorsal,  nombre,  sexo,  control_1,  control_2,
				 control_3,  control_4,  control_5,  control_6,  control_7,  control_8,
				 control_9,  control_10,  control_11,  control_12,  control_13,
				 control_14,  control_15,  ritmo);
		
		
		this.radio = radio;
		this.color = randomColor();
		this.PosX = posX(ritmo);
		this.PosY = posY;
	}
	private int posX(String ritmo) {
		String[] separado = ritmo.split(":");
		double tiempo = Double.parseDouble(separado[0]) + (Double.parseDouble(separado[1])/60);
		double posicion = 600 * tiempo/21.71428;
		int redondeado = (int) posicion;
		return redondeado;
	}
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getPosX() {
		return PosX;
	}
	public void setPosX(int posX) {
		PosX = posX;
	}
	public int getPosY() {
		return PosY;
	}
	public void setPosY(int posY) {
		PosY = posY;
	}
	
	private static Color randomColor() {
		Color[] colores ={Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink,Color.red, Color.white, Color.yellow};
		return colores[(int) (Math.random()*colores.length)];
	}

	@Override
	public String toString() {
		return "Pelota  PosX=" + PosX + ", PosY=" + PosY + ", Ritmo"
				+ getRitmo() + "]";
	}

	
	
}
