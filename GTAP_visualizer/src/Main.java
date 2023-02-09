import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
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
        
        popup.getContentPane().setPreferredSize(new Dimension(width/10,height/10));
        popup.pack();
        popup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        		 
        		 popup.setVisible(true);
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

}
