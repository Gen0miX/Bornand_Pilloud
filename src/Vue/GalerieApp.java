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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	private GridLayout grid = new GridLayout(0,3,7,7) ;
	
	private JScrollPane scroll ;
	private JPanel centrePan, centrecentrePane ;
	private JFileChooser choix = new JFileChooser();
	private FileFilter imagesFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "jpeg");
	
	private CentreGalerie centreGalerie ;
	

	public GalerieApp() {
		setLayout(new BorderLayout());
//		add(new TopGalerie(), BorderLayout.NORTH) ;
		centreGalerie = new CentreGalerie();
		add(centreGalerie, BorderLayout.CENTER);
		setVisible(true) ;
		
	}


	 class CentreGalerie extends JPanel {
	
		public CentreGalerie(){
			
			deSerializeObject();
			
			centrePan = this ;
			this.setLayout(new BorderLayout());
			this.setBackground(Color.GRAY.brighter());
			centrecentrePane = new CentreCentreGalerie();
			
			
			affichePhotos();
			
			scroll =  new JScrollPane() ;
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
			
			this.add(new TopGalerie(), BorderLayout.NORTH);
			this.add(scroll, BorderLayout.WEST) ;
			this.add(centrecentrePane, BorderLayout.CENTER);
			
		}	
	}
	
	class CentreCentreGalerie extends JPanel{
		
		private FlowLayout flow = new FlowLayout();
		
		public CentreCentreGalerie() {
			centrecentrePane = this ;
			flow.setAlignment(FlowLayout.LEFT);
			flow.setHgap(12);
			this.setBackground(Color.GRAY.brighter());
			this.setLayout(flow);
			setVisible(true);
			
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
					ajout.addActionListener(new AddClick());
					
					choix.setDialogTitle("Ajouter une image");
					choix.setApproveButtonText("Ajouter");
					choix.addChoosableFileFilter(imagesFilter);
					choix.setAcceptAllFileFilterUsed(false);
					choix.setFileFilter(imagesFilter);
					
				}
				
			}
			
		}
	 
	 public void serializeObject() 
		{
			try 
			{
				FileOutputStream fichier = new FileOutputStream("serialisation/images.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fichier);
				oos.writeObject(imgall);
				oos.flush();
				oos.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	 
	 @SuppressWarnings("unchecked")
		public void deSerializeObject() 
		{
			try 
			{
				FileInputStream fichier = new FileInputStream("serialisation/images.ser");
				ObjectInputStream ois = new ObjectInputStream(fichier);
				imgall = (ArrayList<Images>) ois.readObject();
				ois.close();
			} 
			catch (IOException e) 
			{
				imgall = new ArrayList<Images>();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();

			}
		}
	 
	 
	 private void addImage(Images img)
		{
			imgall.add(img);
		}
	 
	 public void affichePhotos()
		{
			BoutonBase vignette;
			for (Images img : imgall) 
			{
				vignette = new BoutonBase(img.getThumbnail143143(), 143, 143);
//				vignette.addActionListener(new PhotoClick(photo));
				centrecentrePane.add(vignette);
//				photoDetail = new PhotoDetail(photo);
//				contentPanelPhoto.add(photoDetail, "" + photos.indexOf(photo));
			}
		}
	 
	 public void update() 
		{
		 	centrecentrePane.removeAll();
			affichePhotos();
			centrecentrePane.updateUI();
		}
	 
	 private int getNextId()
		{
			if(imgall.size() == 0)
				return 0;
			return imgall.get(imgall.size()-1).getID()+1;
		}
	 
	 public void copyFiles(File source) 
		{
			int id = getNextId();
			File destination = new File("photo/ImageGal/image" + getNextId() + "." + getFileExtension(source));
			Images photo;

			try 
			{
				Files.copy(source.toPath(), destination.toPath());
				photo = new Images(getNextId(),"photo" + getNextId(), destination);
				addImage(photo);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	 
	 private String getFileExtension(File file)
		{
			String fileName = file.getName();
			if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
				return fileName.substring(fileName.lastIndexOf(".") + 1);
			else
				return "";
		}
	 
	 class AddClick implements ActionListener 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int resultat = choix.showOpenDialog(centrePan);
				
				if(resultat == choix.CANCEL_OPTION)
				{
					choix.cancelSelection();
					return;
				}
				
				if (resultat == choix.APPROVE_OPTION) 
				{
					copyFiles(choix.getSelectedFile());
					update();
				}
			}
		}
	 
	 class BoutonImage extends BoutonBase {
		 
		 public BoutonImage(ImageIcon image, int width, int height) {
			
		 }
		 
	 }

}
