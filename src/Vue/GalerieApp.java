/*
 * GalerieApp.java
 * Auteur : Jonas Pilloud
 * Date de création : 7 juin 2018
 */

package Vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Gui.BoutonBase;
import Gui.PanelListApplication;



public class GalerieApp extends JPanel{

	private ArrayList<Images> imgall = new ArrayList<Images>() ;
	
	private CardLayout cardlayImg = new CardLayout();
	private JPanel contenuPimg = new JPanel(cardlayImg);
	

	public GalerieApp() {
		setLayout(new BorderLayout());
		add(new TopGalerie(), BorderLayout.NORTH) ;
		add(new CentreGalerie(), BorderLayout.CENTER);
		
		
		
		setVisible(true) ;
	}


	 class CentreGalerie extends JPanel {
		
		private JPanel gridPanel = new JPanel(new GridLayout(0, 3, 7, 7));
		private JPanel photoListe = new JPanel(); // pour le scroll
		
		private JFileChooser choix = new JFileChooser();
		private FileFilter filtreImage = new FileNameExtensionFilter("Image Files","jpg", "png", "gif", "jpeg");
		
		public CentreGalerie(){
			
		
			
			photoListe.setLayout(new FlowLayout());
			photoListe.add(gridPanel);
			photoListe.setBackground(new Color(0,103,56));
			
			gridPanel.setAlignmentX(CENTER_ALIGNMENT);
			gridPanel.setOpaque(false);
			
			
			//ActionListener a ajouter
			
			
			choix.setDialogTitle("Ajouter une image");
			choix.setApproveButtonText("Ajouter");
			choix.addChoosableFileFilter(filtreImage);
			choix.setAcceptAllFileFilterUsed(false);
			choix.setFileFilter(filtreImage);
			
		}	
	}
	 
	 class TopGalerie extends JPanel{
			public TopGalerie() {
				this.setLayout(new BorderLayout());
				this.add(new TopGalerieL(), BorderLayout.CENTER);
				this.add(new TopGalerieR(), BorderLayout.EAST);
			}
		
			class TopGalerieL extends JPanel{
				
				private Font pol = new Font("Arial", Font.BOLD, 25);
				
				public TopGalerieL() {
					
					FlowLayout flow = new FlowLayout();
					flow.setAlignment(FlowLayout.LEFT);
					flow.setVgap(10);
					flow.setHgap(40);
					
					this.setLayout(flow);
					this.setBackground(new Color(0,200,109,150));
					
					JLabel gal = new JLabel("Galerie") ;
					gal.setFont(pol);
					gal.setForeground(Color.WHITE);
					this.add(gal);
				}
			}
			
			class TopGalerieR extends JPanel{
				
				public TopGalerieR() {
					
					FlowLayout flow = new FlowLayout();
					flow.setAlignment(FlowLayout.LEFT);
					flow.setVgap(10);
					flow.setHgap(40);
					
					this.setLayout(flow);
					this.setBackground(new Color(0,200,109,150));
					
					BoutonBase ajout = new BoutonBase(new ImageIcon("photo/Icones/ajout.png"));
					this.add(ajout);
				}
				
			}
			
		}

}
