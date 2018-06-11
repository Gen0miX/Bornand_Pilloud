/*
* PanelAjout.java
*Auteur : Mathias Bornand
*Date de création : 11 juin 2018
*/


package Gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;



public class PanelAjout extends InterfaceApplication {
	
	private ImageIcon imgSave = new ImageIcon("image/icons/save.png");
	private JButton save = new BoutonBase(imgSave);

	private ImageIcon imgCancel = new ImageIcon("image/icons/cancel.png");
	private JButton cancel = new BoutonBase(imgCancel);

	/**
	 * Constructeur des entêtes d'ajout
	 * @param NomPanel
	 * @param couleurPan
	 */
	public PanelAjout(String NomPanel, Color couleurPan)
	{
		super(NomPanel, couleurPan);
		boutonPanel1.add(save);
		boutonPanel2.add(cancel);

	}

	// Getters et setters
	public JButton getSave() 
	{
		return save;
	}

	public JButton getCancel() 
	{
		return cancel;
	}

	public void setCancel(JButton cancel) 
	{
		this.cancel = cancel;
	}
}

