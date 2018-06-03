/*
* ContactForm.java
* Auteur : Jonas Pilloud
* Date de création : 3 juin 2018
*/

package Gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelContact extends JPanel{

	private TextBase tnom, tprenom, tphone, tadresse, tmail;
	
	private JLabel nom = new JLabel("Nom");
	private JLabel prenom = new JLabel("Prenom");
	private JLabel tel = new JLabel("Téléphone");
	private JLabel adresse = new JLabel("Adresse");
	private JLabel mail = new JLabel("Mail");
	
	private BoutonBase delete = new BoutonBase(new ImageIcon("photo/Icones/supprimer.png"),480, 40, new Color());
	
}
