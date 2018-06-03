/*
* PanelModifApplication.java
* Auteur : Jonas Pilloud
* Date de création : 3 juin 2018
*/

package Gui;

import java.awt.Color;

import javax.swing.ImageIcon;

public class PanelModifApplication extends InterfaceApplication {
	
	private BoutonBase modifier = new BoutonBase(new ImageIcon("photo/Icones/modifier.png"));
	private BoutonBase retour = new BoutonBase(new ImageIcon("photo/Icones/retour.png"));
	private BoutonBase sauvegarde = new BoutonBase(new ImageIcon("photo/Icones/sauvegarder.png"));
	
	public PanelModifApplication(String NomPanel, Color couleurPan) {
		super(NomPanel, couleurPan);
		boutonPanel1.add(modifier);
		boutonPanel2.add(retour);
	}
	
	public void save() {
		boutonPanel1.remove(sauvegarde);
		boutonPanel1.add(modifier);
		boutonPanel1.updateUI();
	}
	
	public void edit() {
		boutonPanel1.remove(modifier);
		boutonPanel1.add(sauvegarde);
		boutonPanel1.updateUI();
	}

	public BoutonBase getModifier() {
		return modifier;
	}

	public BoutonBase getRetour() {
		return retour;
	}

	public BoutonBase getSauvegarde() {
		return sauvegarde;
	}
	
}
