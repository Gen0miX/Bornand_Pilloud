/*
* AppModifImage.java
* Auteur : Jonas Pilloud
* Date de création : 10 juin 2018
*/

package Gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class AppModifImage extends InterfaceApplication {

	// Boutons
		private ImageIcon imgModify = new ImageIcon("photo/Icones/modifier.png");
		private JButton modify = new BoutonBase(imgModify);

		private ImageIcon imgBack = new ImageIcon("photo/Icones/retour.png");
		private JButton back = new BoutonBase(imgBack);

		private ImageIcon imgCancel = new ImageIcon("photo/Icones/annuler.png");
		private JButton cancel = new BoutonBase(imgCancel);

		/**
		 * Constructeur des panels d'edition des photos
		 * @param PANELNAME
		 * @param COLORPANEL
		 */
		public AppModifImage(String PANELNAME, Color COLORPANEL) 
		{
			super(PANELNAME, COLORPANEL);
			boutonPanel1.add(modify);
			boutonPanel2.add(back);
			setBackground(new Color(162,222,208));
		}

		/**
		 * Méthode lors de l'annulation
		 */
		public void cancel() 
		{
			boutonPanel2.remove(cancel);
			boutonPanel1.add(modify);
			boutonPanel2.add(back);
			boutonPanel1.updateUI();
		}
		/**
		 * Méthode lors de l'édition
		 */

		public void edit() 
		{
			boutonPanel1.remove(modify);
			boutonPanel2.remove(back);
			boutonPanel2.add(cancel);
			boutonPanel1.updateUI();
		}

		// Getters et setters
		public JButton getModifier() 
		{
			return modify;
		}

		public void setModify(JButton modify)
		{
			this.modify = modify;
		}

		public JButton getAnnuler() 
		{
			return cancel;
		}

		public void setCancel(JButton cancel)
		{
			this.cancel = cancel;
		}

		public JButton getRetour() 
		{
			return back;
		}

		public void setBack(JButton back) 
		{
			this.back = back;
		}

	
}
