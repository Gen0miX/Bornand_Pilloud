/*
 * GalerieApp.java
 * Auteur : Jonas Pilloud
 * Date de création : 7 juin 2018
 */

package Vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Gui.AppModifImage;
import Gui.BoutonBase;
import Gui.PanelListApplication;
import Gui.PanelModifApplication;






public class GalerieApp extends JPanel{

	private ArrayList<Images> imgall = new ArrayList<Images>() ;
	
	private GridLayout grid = new GridLayout(0,3,7,7) ;
	
	private JScrollPane scroll ;
	private JPanel centrePan, centrecentrePane ;
	private JFileChooser choix = new JFileChooser();
	private FileFilter imagesFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "jpeg");
	
	private CardLayout cardLayoutPhoto = new CardLayout();
	private JPanel contenuPanelPhoto = new JPanel(cardLayoutPhoto);
	
	private Galerie photoGalerie ;
	

	public GalerieApp() {
		deSerializeObject();
		
		photoGalerie = new Galerie();
		setLayout(new BorderLayout());
		add(contenuPanelPhoto);
		contenuPanelPhoto.add(photoGalerie, "Galerie");
		cardLayoutPhoto.show(contenuPanelPhoto, "Galerie");
	}


	class Galerie extends JPanel {
		
		private CentreGalerie centreGalerie ;
		private TopGalerie topGalerie ;
		
		public Galerie() {
			setLayout(new BorderLayout());
			centreGalerie = new CentreGalerie();
			topGalerie = new TopGalerie();
			add(centreGalerie, BorderLayout.CENTER);
			add(topGalerie, BorderLayout.NORTH);
			setVisible(true) ;
			
			scroll =  new JScrollPane(centrePan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ;
			add(scroll);
		}
		
		 public void update() 
			{
			 	centrecentrePane.removeAll();
				affichePhotos();
				centrecentrePane.updateUI();
			}
	}
	
	 class CentreGalerie extends JPanel {
	
		public CentreGalerie(){
			
//			deSerializeObject();
			centrePan = this ;
			this.setLayout(new FlowLayout());
			this.setOpaque(false);
			centrecentrePane = new CentreCentreGalerie();
			this.add(centrecentrePane);
			
			affichePhotos();
		}	
	}
	
	class CentreCentreGalerie extends JPanel{
		
		private GridLayout grid = new GridLayout(0, 3, 7, 7);
		
		public CentreCentreGalerie() {
			centrecentrePane = this ;
			this.setAlignmentX(CENTER_ALIGNMENT);
			this.setOpaque(true);
			this.setLayout(grid);
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
	 
	 class ImageGrand extends AppModifImage {
		
		private Images image ;
		
		private BoutonBase photoAgrandie;
		private BoutonBase gauche = new BoutonBase(new ImageIcon("photo/Icones/gauche.png"));
		private BoutonBase droite = new BoutonBase(new ImageIcon("photo/Icones/droite.png"));
		private BoutonBase supprim = new BoutonBase(new ImageIcon("photo/Icones/supprimer.png"), 480, 40, new Color(0,200,109,150));
		
		private JPanel frame = new JPanel(new BorderLayout());
		
		private boolean isedit = false ;
		
		public ImageGrand(Images image) {
			super("Image", new Color(0,200,109,150));
			this.image = image ;

			this.setOpaque(false);
			photoAgrandie = new BoutonBase(image.getThumbnailFull());
			photoAgrandie.setBackground(Color.BLACK.brighter());
			photoAgrandie.setPreferredSize(new Dimension(455, 675));
			add(photoAgrandie);
			
			createPanel();
		}
		
		private void createPanel() {
			
			photoAgrandie.add(gauche, BorderLayout.WEST);
			gauche.setEnabled(false);
			
			photoAgrandie.add(droite, BorderLayout.EAST);
			droite.setEnabled(false);
			
			gauche.addActionListener(new GaucheClick());
			droite.addActionListener(new DroiteClick());
			
			gauche.addMouseListener(new ArrowHover());
			droite.addMouseListener(new ArrowHover());
			
			supprim.addActionListener(new SupprimClick());
			
			super.getAnnuler().addActionListener(new AnnulClick());
			super.getModifier().addActionListener(new ModifClick());
			super.getRetour().addActionListener(new RetourClick());
			
			
		}
		
		class ModifClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isedit = true;
				photoAgrandie.add(supprim, BorderLayout.SOUTH);
				edit();
				updateUI();
			}
		}
		
		class AnnulClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isedit = false;
				photoAgrandie.remove(supprim);
				cancel();
				updateUI();
			}
		}
		
		private class DroiteClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int nextPhoto = imgall.indexOf(image) + 1;
				
				if (isedit)
					return;
				if (nextPhoto > imgall.size() - 1)
				{
					cardLayoutPhoto.show(contenuPanelPhoto, "0");
					return;
				}
				cardLayoutPhoto.show(contenuPanelPhoto, "" + nextPhoto);
			}
		}
		
		 private class GaucheClick implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					int previousPhoto = imgall.indexOf(image) - 1;
					
					if (isedit)
						return;
					if (previousPhoto < 0) 
					{
						cardLayoutPhoto.show(contenuPanelPhoto, "" + (imgall.size() - 1));
						return;
					}
					cardLayoutPhoto.show(contenuPanelPhoto, "" + previousPhoto);
				}
			}
		 
		 private class ArrowHover extends MouseAdapter 
			{
				@Override
				public void mouseEntered(MouseEvent e) 
				{
					if (isedit)
						return;
					if (e.getSource() == gauche)
						gauche.setEnabled(true);
					if (e.getSource() == droite)
						droite.setEnabled(true);
				}
				
				@Override
				public void mouseExited(MouseEvent e) 
				{
					if (isedit)
						return;
					if (e.getSource() == gauche)
						gauche.setEnabled(false);
					if (e.getSource() == droite)
						droite.setEnabled(false);
				}
			}
		 
		 class RetourClick implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					cardLayoutPhoto.show(contenuPanelPhoto, "Galerie");
				}
			}
		 
		 class SupprimClick implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					File fileToDelete = new File(image.getUrl());
					fileToDelete.delete();
					removeImage(image);
					photoGalerie.update();
					cardLayoutPhoto.show(contenuPanelPhoto, "Galerie");
				}
			}
		 
	 }
	 
	 
	 class PhotoClick implements ActionListener
		{
			private Images img;

			public PhotoClick(Images img) 
			{
				this.img = img;
			}

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cardLayoutPhoto.show(contenuPanelPhoto, "" + imgall.indexOf(this.img));
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
	 
	 private void removeImage(Images img)
		{
			imgall.remove(img);
		}
	 
	 //méthode d'ajout d'image
	 private void addImage(Images img)
		{
			imgall.add(img);
		}
	 
	 public void affichePhotos()
		{
		 	ImageGrand imageGrand ;
			BoutonBase vignette;
			
			for (Images img : imgall) 
			{
				vignette = new BoutonBase(img.getThumbnail143143(), 143, 143);
				vignette.addActionListener(new PhotoClick(img));
				centrecentrePane.add(vignette);
				imageGrand = new ImageGrand(img);
				contenuPanelPhoto.add(imageGrand, "" + imgall.indexOf(img));
			}
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
	 public void update() 
		{
		 	centrecentrePane.removeAll();
			affichePhotos();
			centrecentrePane.updateUI();
		}
}
