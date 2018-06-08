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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Gui.BoutonBase;
import Gui.PanelListApplication;



public class GalerieApp extends JPanel{

	private ArrayList<Images> imgall = new ArrayList<Images>() ;
	
	private JScrollPane scroll ;
	private JPanel centrePan, centrecentrePane ;
	

	public GalerieApp() {
		setLayout(new BorderLayout());
//		add(new TopGalerie(), BorderLayout.NORTH) ;
		add(new CentreGalerie(), BorderLayout.CENTER);
		setVisible(true) ;
	}


	 class CentreGalerie extends JPanel {
	
		public CentreGalerie(){
			centrePan = this ;
			this.setLayout(new BorderLayout());
			this.setBackground(Color.GRAY.brighter());
			centrecentrePane = new CentreCentreGalerie();
			
			scroll =  new JScrollPane() ;
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
			//actualiseImage();
			this.add(new TopGalerie(), BorderLayout.NORTH);
			this.add(scroll, BorderLayout.WEST) ;
			
		}	
	}
	
	class CentreCentreGalerie extends JPanel{
		
		public CentreCentreGalerie() {
			centrecentrePane = this ;
			this.setBackground(Color.GRAY.brighter());
			FlowLayout flow = new FlowLayout();
			flow.setAlignment(FlowLayout.LEFT);
			flow.setHgap(10);
			flow.setVgap(10);
			this.setLayout(flow);
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
	 
	 class BoutonImage extends BoutonBase {
		 
		 public BoutonImage(ImageIcon image, int width, int height) {
			
		 }
		 
	 }

}
