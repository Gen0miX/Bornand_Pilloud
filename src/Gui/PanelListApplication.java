/*
* PanelListApplication.java
* Auteur : Jonas Pilloud
* Date de création : 3 juin 2018
*/

package Gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class PanelListApplication extends InterfaceApplication {

	private BoutonBase ajout = new BoutonBase(new ImageIcon("photo/Icones/ajout.png")) ;
	private BoutonBase retour = new BoutonBase(new ImageIcon("photo/Icones/retour.png")) ;
		
	public PanelListApplication(String NomPanel, Color couleurPan) {
		super(NomPanel, couleurPan);
		boutonPanel1.add(ajout);
		boutonPanel2.add(retour);
	}

	public BoutonBase getAjout() {
		return ajout;
	}

	public BoutonBase getRetour() {
		return retour;
	}
	
}
