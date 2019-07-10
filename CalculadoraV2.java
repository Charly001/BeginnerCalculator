package calculadora;

import javax.swing.JFrame;

public class CalculadoraV2 {

	public static void main(String[] args) {
		
		MarcoCalculadora marco1= new MarcoCalculadora();
		marco1.setVisible(true);
	}
}

class MarcoCalculadora extends JFrame{

	public MarcoCalculadora() {
		
		setTitle("Calculadora");
		setBounds(500,300,450,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(new PanelCalculadora());
	}
}