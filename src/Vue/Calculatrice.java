/*
 * Calculatrice.java
 *Auteur : Mathias Bornand
 *Date de création : 2 juin 2018
 */


package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Calculatrice extends JPanel {
	private JPanel container = new JPanel();
	//Tableau stockant les elements a� afficher dans la calculatrice
	String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "C", "+", "-", "*", "/", "="};
	//Un bouton par element à afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JLabel ecran = new JLabel();
	private Dimension dim = new Dimension(130,50 );
	private Dimension dim2 = new Dimension(80, 50);
	private double chiffre1;
	private boolean clicOperateur = false, update = false;
	private String operateur = "";

	public Calculatrice(){

		setLayout(new BorderLayout());
		add(new TopPanel(),BorderLayout.NORTH);
		add(new CenterPanel(), BorderLayout.CENTER);
		add(new SidePanel(), BorderLayout.EAST);
		this.setVisible(true);
	}

	private class TopPanel extends JPanel {

		private FlowLayout flow = new FlowLayout();
		//private Color bg = new Color(0, 0, 0, 200);

		public TopPanel() {
			
			//On definit la police d'ecriture a utiliser
			Font police = new Font("Arial", Font.BOLD, 80);
			ecran = new JLabel("0");
			ecran.setFont(police);
			//On aligne les informations a� droite dans le JLabel
			ecran.setHorizontalAlignment(JLabel.RIGHT);
			
			ecran.setPreferredSize(new Dimension(400, 100));
			ecran.setBackground(new Color(0,103,54));
		
			this.setLayout(flow);
			this.setPreferredSize(new Dimension(400, 100));
			this.add(ecran);
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			
		}

	}
	private class CenterPanel extends JPanel {

		private GridLayout grid = new GridLayout(3,4);

		public CenterPanel() {

//			JPanel chiffre = new JPanel();
//			chiffre.setPreferredSize(new Dimension(165, 225));
//			grid.setVgap(50);
//			grid.setHgap(20);
			this.setLayout(grid);
			
			for(int i = 0; i <= 11 ; i++){
				tab_button[i] = new JButton(tab_string[i]);
				//tab_button[i].setPreferredSize(dim);
				
				tab_button[i].setForeground(Color.WHITE);
				tab_button[i].setBackground(Color.DARK_GRAY);
				switch(i){
				case 11 :
					tab_button[i].setForeground(Color.red);
					tab_button[i].addActionListener(new ResetListener());
					this.add(tab_button[i]);
					break;
				default :
					//Par defaut, ce sont les premiers elements du tableau
					//donc des chiffres, on affecte alors le bon listener
					this.add(tab_button[i]);
					tab_button[i].addActionListener(new ChiffreListener());
					break;
				}
			}
		}	

	}
	private class SidePanel extends JPanel {

		private GridLayout grid = new GridLayout(4,1);

		public SidePanel() {
			
			
			this.setLayout(grid);
			this.setPreferredSize(dim2);
			grid.setRows(5);

			for(int i = 12; i < tab_string.length  ; i++){
				tab_button[i] = new JButton(tab_string[i]);
				//tab_button[i].setPreferredSize(dim2); //COMM. A EFFACER (dim ou dim2 pas pris en compte ?!)
				tab_button[i].setBackground(Color.LIGHT_GRAY);
				switch(i){
				case 12 :
					tab_button[i].addActionListener(new PlusListener());
					this.add(tab_button[i]);
					break;
				
				case 13 :
					tab_button[i].addActionListener(new MoinsListener());
					//tab_button[i].setPreferredSize(dim2);
					this.add(tab_button[i]);
					break;
				case 14 :
					tab_button[i].addActionListener(new MultiListener());
					//tab_button[i].setPreferredSize(dim2);
					this.add(tab_button[i]);
					break;	
				case 15 :	
					tab_button[i].addActionListener(new DivListener());
					//tab_button[i].setPreferredSize(dim2);
					this.add(tab_button[i]);
					break;
				case 16 :
					tab_button[i].addActionListener(new EgalListener());
					//tab_button[i].setPreferredSize(dim2);
					tab_button[i].setBackground(Color.orange);
					this.add(tab_button[i]);
					break;
				}
			}
		}	
		
	}
	
//	private void initComposant(){
//
//		JPanel operateur = new JPanel();      
//		operateur.setPreferredSize(new Dimension(55, 225));
//		JPanel chiffre = new JPanel();
//		chiffre.setPreferredSize(new Dimension(165, 225));
//
//
//		//On parcourt le tableau initialisé
//		//afin de créer nos boutons
//		for(int i = 0; i < tab_string.length; i++){
//			tab_button[i] = new JButton(tab_string[i]);
//			tab_button[i].setPreferredSize(dim);
//			switch(i){
//			//Pour chaque élément situé à la fin du tableau
//			//et qui n'est pas un chiffre
//			//on définit le comportement à avoir grâce à un listener
//			case 11 :
//				tab_button[i].addActionListener(new EgalListener());
//				chiffre.add(tab_button[i]);
//				break;
//			case 12 :
//				tab_button[i].setForeground(Color.red);
//				tab_button[i].addActionListener(new ResetListener());
//				operateur.add(tab_button[i]);
//				break;
//			case 13 :
//				tab_button[i].addActionListener(new PlusListener());
//				tab_button[i].setPreferredSize(dim2);
//				operateur.add(tab_button[i]);
//				break;
//			case 14 :
//				tab_button[i].addActionListener(new MoinsListener());
//				tab_button[i].setPreferredSize(dim2);
//				operateur.add(tab_button[i]);
//				break;	
//			case 15 :	
//				tab_button[i].addActionListener(new MultiListener());
//				tab_button[i].setPreferredSize(dim2);
//				operateur.add(tab_button[i]);
//				break;
//			case 16 :
//				tab_button[i].addActionListener(new DivListener());
//				tab_button[i].setPreferredSize(dim2);
//				operateur.add(tab_button[i]);
//				break;
//			default :
//				//Par défaut, ce sont les premiers éléments du tableau
//				//donc des chiffres, on affecte alors le bon listener
//				chiffre.add(tab_button[i]);
//				tab_button[i].addActionListener(new ChiffreListener());
//				break;
//			}
//		}
//		//		panEcran.add(ecran);
//		//		panEcran.
//		//		container.add(panEcran, BorderLayout.NORTH);
//		container.add(chiffre, BorderLayout.CENTER);
//		container.add(operateur, BorderLayout.EAST);
//	}

	//Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
	private void calcul(){
		if(operateur.equals("+")){
			chiffre1 = chiffre1 + 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if(operateur.equals("-")){
			chiffre1 = chiffre1 - 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}          
		if(operateur.equals("*")){
			chiffre1 = chiffre1 * 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}     
		if(operateur.equals("/")){
			try{
				chiffre1 = chiffre1 / 
						Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(chiffre1));
			} catch(ArithmeticException e) {
				ecran.setText("0");
			}
		}
	}

	//Listener utilisé pour les chiffres
	//Permet de stocker les chiffres et de les afficher
	class ChiffreListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//On affiche le chiffre additionnel dans le label
			String str = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
				if(!ecran.getText().equals("0"))
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
	}

	//Listener affect� au bouton =
	class EgalListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			calcul();
			update = true;
			clicOperateur = false;
		}
	}

	//Listener affect� au bouton +
	class PlusListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	}

	//Listener affect� au bouton -
	class MoinsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	}

	//Listener affect� au bouton *
	class MultiListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	}

	//Listener affect� au bouton /
	class DivListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	}

	//Listener affect� au bouton de remise a� zero
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			clicOperateur = false;
			update = true;
			chiffre1 = 0;
			operateur = "";
			ecran.setText("");
		}
	}  

}


