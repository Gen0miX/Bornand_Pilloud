/*
 * GalerieApp.java
 * Auteur : Jonas Pilloud
 * Date de création : 7 juin 2018
 */

package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Gui.BoutonBase;
import Gui.PanelListApplication;

public class GalerieApp extends JPanel{

	private BoutonBase imgall [] ;

	private  static File img = new File("photo/ImageGal");
	private static int count = img.list().length ;

	public GalerieApp() {
		setLayout(new BorderLayout());
		add(new PanelListApplication("Image Gallery", new Color(0,200,109)), BorderLayout.NORTH) ;
		add(new CentreGalerie(), BorderLayout.CENTER);
		setVisible(true) ;
	}

	private class CentreGalerie extends JPanel {
		
		public CentreGalerie(){
			FlowLayout flowL = new FlowLayout();
			flowL.setVgap(35);
			flowL.setHgap(35);
			flowL.setAlignment(FlowLayout.LEFT);
			this.setLayout(flowL);
			
			for(int i = 0 ; i != count ; i++) {
				this.add(imgall [i] = new BoutonBase(new ImageIcon("photo/ImageGal/photo"+i+".png"),50,50));
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
