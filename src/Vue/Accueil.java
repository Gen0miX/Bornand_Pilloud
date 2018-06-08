/*
 * Accueil.java
 * Auteur : Jonas Pilloud
<<<<<<< HEAD
 * Date de création : 2 juin 2018

 */

package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Gui.BoutonBase;
import Gui.PanelImage;


public class Accueil extends PanelImage {


	private ImageIcon background ;

	private BoutonBase verrouiller = new BoutonBase(new ImageIcon("photo/Icones/verrouiller.png"));
	private BoutonBase exit = new BoutonBase(new ImageIcon("photo/Icones/sortie.png")) ;
	private BoutonBase imageGal = new BoutonBase(new ImageIcon("photo/Icones/imageGal.png")) ;
	private BoutonBase contact = new BoutonBase(new ImageIcon("photo/Icones/Contact.png"));
	private BoutonBase calculatrice = new BoutonBase(new ImageIcon("photo/Icones/Calculatrice.png"));

	public Accueil(ImageIcon bg) {
		super(bg);
		setLayout(new BorderLayout());
		add(new FondPanel(),BorderLayout.SOUTH);
		add(new CentrePanel(), BorderLayout.CENTER);
		setVisible(true);
	}

	private class FondPanel extends JPanel {

		private FlowLayout flow = new FlowLayout();
		private Color bg = new Color(0, 0, 0, 200) ;



		public FondPanel() {
			super() ;
			flow.setHgap(40);
			flow.setVgap(20);
			this.setLayout(flow);
			this.add(imageGal) ;
			this.add(contact) ;
			this.add(calculatrice) ;
			this.setBackground(bg) ;
		}

	}
	private class CentrePanel extends JPanel {
		public CentrePanel() {
			FlowLayout flow = new FlowLayout() ;
			flow.setVgap(35);
			flow.setHgap(35);
			flow.setAlignment(FlowLayout.LEFT);
			this.setLayout(flow);
			this.add(exit) ;
			this.add(verrouiller) ;
			this.setOpaque(false);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	public BoutonBase getImageGal() {
		return imageGal;
	}

	public BoutonBase getContact() {
		return contact;
	}

	public BoutonBase getCalculatrice() {
		return calculatrice;
	}
	public BoutonBase getVerrouiller() {
		return verrouiller;
	}

	public BoutonBase getExit() {
		return exit;
	}

}
