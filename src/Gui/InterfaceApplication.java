/*
* InterfaceApplication.java
* Auteur : Jonas Pilloud
* Date de création : 3 juin 2018
*/

package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

public abstract class InterfaceApplication extends JPanel {

	//Panels
	private JPanel titreP = new JPanel(new GridBagLayout());
	private JPanel texteP = new JPanel(new BorderLayout());
	protected JPanel boutonPanel1 = new JPanel(new GridBagLayout());
	protected JPanel boutonPanel2 = new JPanel(new GridBagLayout());
	
	//Nom de l'application et couleur
	private final String NomPanel ;
	private final Color couleurPan ;

	//titre et police
	private JLabel titre = new JLabel() ;
	private Font police18 = new Font("Arial", Font.BOLD, 18);
	
	public InterfaceApplication(String NomPanel, Color couleurPan) {
		
		this.NomPanel = NomPanel ;
		this.couleurPan = couleurPan ;
		
		//ajout du panel titre
		add(titreP, BorderLayout.NORTH) ;
		titreP.setBackground(couleurPan);
		titreP.setPreferredSize(new Dimension(480, 40));
		titreP.add(texteP) ;
		
		//Parametre texte
		texteP.setOpaque(false);
		texteP.setPreferredSize(new Dimension(480, 22));
		texteP.add(titre, BorderLayout.CENTER);
		
		//Parametre du titre
		titre.setForeground(Color.WHITE);
		titre.setFont(police18);
		titre.setHorizontalAlignment(JLabel.CENTER);
				
		//Parametre des boutons
		texteP.add(boutonPanel1, BorderLayout.EAST) ;
		boutonPanel1.setOpaque(false);
		boutonPanel1.setPreferredSize(new Dimension(64, 22));
		
		texteP.add(boutonPanel2, BorderLayout.WEST);
		boutonPanel2.setOpaque(false);
		boutonPanel2.setPreferredSize(new Dimension(64, 22));
		
	}
	
}
