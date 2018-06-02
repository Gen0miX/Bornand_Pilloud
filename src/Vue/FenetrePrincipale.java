/*
* MaFenetre.java
* Auteur : Jonas Pilloud
* Date de création : 25 mai 2018
*/

package Vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Gui.BoutonBase;
import Gui.PanelImage;
import Gui.PanelStatus;
import Gui.PanelVerrouillage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class FenetrePrincipale extends JFrame {
	
	//panel téléphone
	private PanelImage panelTel = new PanelImage(new ImageIcon("photo/BG/formeSmartphone.png")) ;
	// Panel principal
	private Accueil PanelPrinc = new Accueil(new ImageIcon("photo/BG/fondEcran.png"));
	//Panel verrouillage
	private PanelVerrouillage verrouPanel = new PanelVerrouillage();
	// Panel de statut (heure, date, batterie)
	private PanelStatus panelStatus = new PanelStatus();
	
	// Panel du bouton home
	private JPanel homePanel = new JPanel();
	
	//Gestion panel
	private CardLayout cardLayout = new CardLayout();
	private JPanel panelContenu = new JPanel(cardLayout);
	
	private JPanel PanelApplication = new JPanel(new GridBagLayout());
	private GridBagConstraints c = new GridBagConstraints();
	

	//boutons
	private BoutonBase home = new BoutonBase(new ImageIcon("photo/Icones/Bhome.png"));
	
	
	// Verrou
		private boolean lock = true;
	
	

	public FenetrePrincipale() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480, 860);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setUndecorated(true); // Ne pas afficher les boutons de la frame
		setBackground(new Color(0, 0, 0, 0)); // Fond transparent Rouge,Bleu,Vert,Opacite
		
		setContentPane(panelTel);
		panelTel.setOpaque(false);
		panelTel.setLayout(new BorderLayout());
		panelTel.add(panelContenu, BorderLayout.CENTER);
		
		panelContenu.add(verrouPanel, "Verrou");
		verrouPanel.getVerrou().addActionListener(new UnlockClick());
		
		panelContenu.add(PanelPrinc, "PanelPrincipal");
		PanelPrinc.getVerrouiller().addActionListener(new ExitClick());
		
		PanelPrinc.getExit().addActionListener(new OffClick());
		
		
			
				// Panel du bouton home
				panelTel.add(homePanel, BorderLayout.SOUTH);
				homePanel.setOpaque(false);
				homePanel.setPreferredSize(new Dimension(480, 60));
				homePanel.add(home);
				home.setBorderPainted(false);
				home.setPreferredSize(new Dimension(50, 50));
				home.addActionListener(new HomeClick());
				
				// Panel de status
				panelTel.add(panelStatus, BorderLayout.NORTH);
				panelStatus.setOpaque(false);
				panelStatus.setPreferredSize(new Dimension(480, 55));
		
	}
	
	
	class UnlockClick implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			lock = false;
			cardLayout.show(panelContenu, "PanelPrincipal");
		}
	}
	
	// Getters & Setters
	public CardLayout getCardLayout() 
	{
		return cardLayout;
	}

	public JPanel getContentPanel() 
	{
		return panelContenu;
	}
	
	
	
	class HomeClick implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (lock == false)
				cardLayout.show(panelContenu, "MainPanel");
		}
	}
	
	class ExitClick implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			lock = true;
			cardLayout.show(panelContenu, "Verrou");
		}
	}
	class OffClick implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
//			photoApp.serializeObject();
//			contactApp.serializeObject();
//			musiqueApp.serializeObject();
			dispose();
			System.exit(0);
		}
	}

}
