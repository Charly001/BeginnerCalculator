package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

class PanelCalculadora extends JPanel{

	//Campos que conforman el teclado
	private final JButton CANT_BOTONES []= new JButton[16];
	private final String CONTENIDO_BOTON []=  {"7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", ".", "0", "=", "/"};
	//-----------------------------------------------------------
	
	private JTextField pantalla;
	private boolean comienzo; //quitara 0 en pantalla al iniciar el programa
	private String entrada;
	private String operacion;
	private String ultimaOperacion="";
	private double resultado;
	private double valorAnterior;
	
	public PanelCalculadora() {
		
		setLayout(new BorderLayout());
		
		//--------------------PANTALLA---------------------------
		comienzo= true;
		JPanel panelNorte = new JPanel();
		panelNorte.setPreferredSize(new Dimension(450, 120));
		/*panelNorte.setMinimumSize(new Dimension(450, 120));*/
		
		pantalla= new JTextField("0");
		pantalla.setEditable(false);
		pantalla.setPreferredSize(new Dimension(450, 120));
		pantalla.setMaximumSize(new Dimension(450, 120));
		pantalla.setFont(new Font("Arial", Font.PLAIN, 45));
		pantalla.setHorizontalAlignment(JTextField.CENTER);
		
		panelNorte.add(pantalla);
		add(panelNorte, BorderLayout.NORTH);
		//--------------------------------------------------------

		
		//--------------------TECLADO---------------------------
		JPanel tecladoCalculadora= new JPanel();
		tecladoCalculadora.setLayout(new GridLayout(4, 4));
		
		for (int i=0; i<CANT_BOTONES.length; i++) {
			CANT_BOTONES[i]= new JButton(CONTENIDO_BOTON[i]);
			
			if (CONTENIDO_BOTON[i].equals("+") ||
			    CONTENIDO_BOTON[i].equals("-") ||
				CONTENIDO_BOTON[i].equals("/") ||
				CONTENIDO_BOTON[i].equals("x") ||
				CONTENIDO_BOTON[i].equals("=") ) {
				
				CANT_BOTONES[i].addActionListener(new EventosOperacion());
				tecladoCalculadora.add(CANT_BOTONES[i]);
				
			}
			
			else {
				
				CANT_BOTONES[i].addActionListener(new EventosValores());
				tecladoCalculadora.add(CANT_BOTONES[i]);
			}	
		}
				
		add(tecladoCalculadora, BorderLayout.CENTER);
		//--------------------------------------------------------

	}
	
		//---------------EVENTOS---------------------------
		
//Clase interna para respuestas botones sin operaciones matemáticas
private class EventosValores implements ActionListener { 
		 														  
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			entrada= e.getActionCommand();	
			
			if(comienzo) {										
				
				pantalla.setText(entrada);
				
				comienzo=false;
			}
			
			
			else {
			
				pantalla.setText(pantalla.getText()+ entrada);
				
				}
			
			valorAnterior= Double.parseDouble(pantalla.getText());

			}
		}
			
//Clase interna para respuestas botones con operaciones matemáticas
private class EventosOperacion implements ActionListener { 

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				operacion=e.getActionCommand();
				
				if (operacion.equals("+")) {
					
					
					resultado+= valorAnterior;
					
					pantalla.setText(""+ resultado);
					
					ultimaOperacion= "+";
					
					
				}else if (operacion.equals("-")) {
					
					double valor= Double.parseDouble(pantalla.getText());
					
					if (contador==0) resultado= valor;
					
					else {resultado-= valorAnterior;}
					
					pantalla.setText(""+ resultado);

					ultimaOperacion= "-";

					contador++;
					
				}else if (operacion.equals("x")) {

					double valor= Double.parseDouble(pantalla.getText());
					
					if (contador==0) resultado= valor*1;
					
					else {resultado= resultado* valorAnterior;}
					
					pantalla.setText(""+ resultado);

					ultimaOperacion= "x";

					contador++;
					
				}else if (operacion.equals("/")) {
								
					double valor= Double.parseDouble(pantalla.getText());
					
					if (contador==0) resultado= valor;

					else {resultado/= valorAnterior;}
					
					pantalla.setText(""+ resultado);
					
					ultimaOperacion= "/";

					contador++;
					
				}else {
				
					
					if (ultimaOperacion.equals("+")) resultado+= valorAnterior;
					if (ultimaOperacion.equals("-")) resultado-= valorAnterior; contador=0;
					if (ultimaOperacion.equals("x")) resultado*= valorAnterior; contador=0;
					if (ultimaOperacion.equals("/")) resultado/= valorAnterior; contador=0;
					
					pantalla.setText(""+ resultado);
					
					valorAnterior=0;
			
				}
				comienzo=true;
			} 
			private int contador=0;   //variable que indicara si es el primer uso de los operadores "x" o "/"
	}
		//--------------------------------------------------------

	
	

}
