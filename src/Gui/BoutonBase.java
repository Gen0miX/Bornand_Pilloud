/*
* BoutonBase.java
* Auteur : Jonas Pilloud
* Date de création : 1 juin 2018
*/

package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import Gui.PanelImage;
import Gui.BoutonBase.MouseHover;

public class BoutonBase extends JButton {
	private Color color;
	private Color hover;

	/**
	 * Constructeur par défaut
	 * @author Jonas
	 */
	public BoutonBase() 
	{
		// Suppression des bordures
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(new Color(0, 0, 0, 0));
		// Suppression de l'effet de click
		setContentAreaFilled(false);

		setLayout(new BorderLayout());

		// Supprime les bordures interieures de bases
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	/**
	 * Bouton transparent
	 * @param image
	 * @author Jonas
	 */
	public BoutonBase(ImageIcon image) 
	{
		super(image);
		// Suppression des bordures
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(new Color(0, 0, 0, 0));
		// Suppression de l'effet de click
		setContentAreaFilled(false);

		setLayout(new BorderLayout());

		// Supprime les bordures interieures de bases
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	/**
	 * Bouton transparent avec une icône et une dimension
	 * @param image
	 * @param width
	 * @param height
	 */
	public BoutonBase(ImageIcon image, int width, int height)
	{
		super(image);
		// Suppression des bordures
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(new Color(0, 0, 0, 0));
		// Suppression de l'effet de click

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	/**
	 * Bouton avec du texte, une image, une dimension et en changeant la taille de la police
	 * @param text
	 * @param image
	 * @param width
	 * @param height
	 * @param taillePolice
	 */

	public BoutonBase(String text, ImageIcon image, int width, int height, int taillePolice) 
	{
		super(image);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		ImageIcon imgCalque = new ImageIcon("image/fonds/calque.png");
		PanelImage calque = new PanelImage(imgCalque);
		calque.setPreferredSize(new Dimension(143, 143));
		calque.setOpaque(false);
		calque.setLayout(new BorderLayout());
		add(calque, BorderLayout.CENTER);
		
		JLabel label = new JLabel(text);
		calque.add(label, BorderLayout.CENTER);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Montserrat", Font.BOLD, taillePolice));
		
		// Suppression des bordures
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(new Color(0, 0, 0, 0));
		// Suppression de l'effet de click
		setPreferredSize(new Dimension(width, height));
	}
	/**
	 * Bouton avec une image, une dimension et une couleur
	 * @param image
	 * @param width
	 * @param height
	 * @param color
	 */

	public BoutonBase(ImageIcon image, int width, int height, Color color)
	{
		super(image);
		// Suppression des bordures
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(color);
		// Suppression de l'effet de click

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Bouton avec une image, une couleur et une couleur de hover
	 * @param image
	 * @param color
	 * @param hover
	 */
	public BoutonBase(ImageIcon image, Color color, Color hover) 
	{
		super(image);
		this.color = color;
		this.hover = hover;
		// Suppression des bordures
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(color);

		addMouseListener(new MouseHover());
	}

	/**
	 * Bouton avec du texte, une couleur et une taille de police
	 * @param texte
	 * @param color
	 * @param taillePolice
	 */
	public BoutonBase(String texte, Color color, int taillePolice) 
	{
		super(texte);
		this.color = color;
		// Suppression des bordures
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(color);
		// Couleur du texte
		setForeground(Color.WHITE);
		// Changement police en Montserrat, Gras,22px
		setFont(new Font("Montserrat", Font.BOLD, taillePolice));

		addMouseListener(new MouseHover());
	}
	/**
	 * Bouton avec du texte, une image, une couleur, une couleur d'hover et une taile de police
	 * @param texte
	 * @param image
	 * @param color
	 * @param hover
	 * @param taillePolice
	 */

	public BoutonBase(String texte, ImageIcon image, Color color, Color hover, int taillePolice) 
	{
		super(texte);
		this.color = color;
		this.hover = hover;

		setLayout(new BorderLayout());
		add(new JLabel(image), BorderLayout.WEST);
		setBorderPainted(false);
		// Suppression du focus
		setFocusable(false);
		// Couleur d'arrière plan
		setBackground(color);
		// Changement police en Montserrat, Gras,22px
		setFont(new Font("Montserrat", Font.BOLD, taillePolice));
		setMaximumSize(new Dimension(480, 70));
		setMinimumSize(new Dimension(462, 70));
		setPreferredSize(new Dimension(462, 70));
		addMouseListener(new MouseHover());
		setAlignmentX(CENTER_ALIGNMENT);
	}
	/**
	 * Listener du hover sur les boutons
	 * @author Jonas
	 *
	 */

	class MouseHover extends MouseAdapter 
	{
		// quand souris rentre
		@Override
		public void mouseEntered(MouseEvent e) 
		{
			super.mouseEntered(e);
			setBackground(hover);
			setForeground(Color.WHITE);
		}
		
		// quand la souris pars
		@Override
		public void mouseExited(MouseEvent e) 
		{
			super.mouseExited(e);
			setBackground(color);
			setForeground(Color.BLACK);
		}
	}
}
