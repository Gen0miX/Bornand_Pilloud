/*
* PanelImage.java
* Auteur : Jonas Pilloud
* Date de création : 1 juin 2018
*/

package Gui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImage extends JPanel {
	
	ImageIcon image;

	/**
	 * Constructeur du panel
	 * @param image
	 */
	public PanelImage(ImageIcon image) 
	{
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
	}
}

