import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	private static JFrame frame = new JFrame("Bouncing ball");
	private static int width = 600;
	private static int height = 400;
	private static Color colorFondo = Color.LIGHT_GRAY;
	private static List<Pelota> pelotas;
    private static final long serialVersionUID = 7148504528835036003L;
    private static boolean hovering = false;
    private static Corredor visualizando;
    private static JFrame popup = new JFrame();
	private static JLabel dorsal = new JLabel();
	private static String path = "C:\\Users\\Andrés\\git\\repository2\\GTAP_visualizer\\data\\cropped-GTTAP-logo-32x32.png";
	//private static Path filepath = Paths.get(path);
	private static ImageIcon img = new ImageIcon(path);


	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
        for (Pelota pelota : pelotas) {
        	if(pelota!=null) {
        		g.fillOval(pelota.getPosX(), pelota.getPosY(), pelota.getRadio(), pelota.getRadio());
        		g.setColor(pelota.getColor());
        	}
		}
        
    }
    
	public static void main(String[] args) {
		
		//Iniciamos la aplicación y hace la lectura del archivo
		pelotas = Lector.lectorCorredor();
		
		var panel = new Main();
        panel.setBackground(colorFondo);
        
        frame.getContentPane().setPreferredSize(new Dimension(width,height));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setTitle("Gran Trail Aneto Posets");
        frame.setIconImage(img.getImage());
        
        popup.getContentPane().setPreferredSize(new Dimension(300,250));
        popup.pack();
        popup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        popup.setIconImage(img.getImage());
        popup.setVisible(false);
        
        for (Pelota pelota : pelotas) {
        	if(pelota!=null) {
        		
        		int altura = 350;
        		
        		for (Pelota enRango : pelotas) {
        			if (Math.abs(pelota.getPosX() - enRango.getPosX())<50 ) {
        				altura = altura - 3;
        			}
        		}
        		
        		pelota.setPosY(altura);
        		
        		int verde = (int) (0+altura*255/height);
        		
        		pelota.setColor(new Color(0,verde,0,255));
        	}
		}
       
        
         while (1==1) {
        	 
        	 boolean modificado = false;
        	 
        	 System.out.println(hovering);
        	 
        	 if(hovering) {
        		 
        		 popup.setTitle(visualizando.getNombre());
        		 try {
        		 popup.setLocation(panel.getMousePosition().x+15, panel.getMousePosition().y+30);
        		 popup.setVisible(true);
        		 } catch (NullPointerException e) {
        			 System.err.println("NullPointerException");
        		 }
        		 addCorredor(popup);
        		 popup.repaint();
        		 
        	 } else {
        		 popup.setVisible(false);
        	 }
        	 
             for (Pelota circulo : pelotas) {
            	 
            	 try {
            		
            		int ratonXinPanel = panel.getMousePosition().x;
                	int ratonYinPanel = panel.getMousePosition().y;
                	int distanciaX = ratonXinPanel-circulo.getPosX();
     				int distanciaY = ratonYinPanel-circulo.getPosY();
     				int limite = 8;
     				
     				if(distanciaX<limite && distanciaX>-limite && distanciaY<limite && distanciaY>-limite) {
     					System.out.println("Estas encima de un participante"+distanciaX + " " + distanciaY);
     					System.out.println(circulo);
     					hovering = true;
     					visualizando = (Corredor) circulo;
     					System.out.println(hovering);
     					modificado = true;
     				} 
     					
            	 } catch (NullPointerException e) {
            		 e.getStackTrace();
            		 System.err.println("El ratón esta fuera del panel.");
            	 }
				
			}
        	
             if (!modificado && hovering) {
            	 hovering = false;
             }
             
        	 panel.repaint();
        	 
        	 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
         }
	}

	private static void addCorredor(JFrame popup2) {
		dorsal.setText(String.format("<html> Posición: %s<br>Nombre: %s <br>Dorsal: %s<br>Ritmo: %s", visualizando.getPosicion(),visualizando.getNombre(), visualizando.getDorsal(),visualizando.getRitmo()));
		popup2.add(dorsal);
	}

}
