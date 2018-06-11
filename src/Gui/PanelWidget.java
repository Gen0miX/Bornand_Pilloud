/*
* PanelWidget.java
*Auteur : Mathias Bornand
*Date de création : 11 juin 2018
*/


package Gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;




	public class PanelWidget extends InterfaceApplication {

		// Boutons
		private ImageIcon imgBack = new ImageIcon("photo/Icones/retour.png");
		private JButton back = new BoutonBase(imgBack);
		private JButton empty = new BoutonBase();

		/**
		 * Constructeur des panels widget
		 * @param NomPanel
		 * @param couleurPan
		 */
		public PanelWidget(String NomPanel, Color couleurPan) 
		{
			super(NomPanel, couleurPan);
			boutonPanel1.add(back); // COMM A EFFACER => Verifier nom variables
			boutonPanel2.add(empty);
		}

		// Getters et setters
		public JButton getBack() 
		{
			return back;
		}

		public void setBack(JButton back) 
		{
			this.back = back;
		}
	}