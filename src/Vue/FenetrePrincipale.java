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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Gui.PanelImage;
import Gui.PanelVerrouillage;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class FenetrePrincipale extends JFrame {
	
	//panel téléphone
	private PanelImage panelTel = new PanelImage(new ImageIcon("photo/BG/formeSmartphone.png")) ;
	// Panel prncipal
	private PanelImage PanelPrinc = new PanelImage(new ImageIcon("photo/BG/fondEcran.png"));
	//Panel verrouillage
	private PanelVerrouillage verrouPanel = new PanelVerrouillage();
	// Panel de statut (heure, date, batterie)
		private JPanel panelStatus = new JPanel();
	
	//Gestion panel
	private CardLayout cardLayout = new CardLayout();
	private JPanel contentPanel = new JPanel(cardLayout);
	
	private JPanel PanelApplication = new JPanel(new GridBagLayout());
	private GridBagConstraints c = new GridBagConstraints();
	
	//Heure
		private JLabel heure = new JLabel();
		final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm");
		private Timer timer = new Timer(0, new CurrentTime());
	
	// Verrou
		private boolean lock = true;
	
	public FenetrePrincipale() {
		
		// Heure
		timer.start();
		panelStatus.add(heure);
		heure.setForeground(Color.WHITE);
		heure.setPreferredSize(new Dimension(31, 45));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480, 860);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setUndecorated(true); // Ne pas afficher les boutons de la frame
		setBackground(new Color(0, 0, 0, 0)); // Fond transparent Rouge,Bleu,Vert,Opacite
		
		setContentPane(panelTel);
		panelTel.setOpaque(false);
		panelTel.setLayout(new BorderLayout());
		panelTel.add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.add(verrouPanel, "Verrou");
		verrouPanel.getVerrou().addActionListener(new UnlockClick());
		
		contentPanel.add(PanelPrinc, "PanelPrincipal");
		
		
		PanelPrinc.setLayout(new BorderLayout());
		
		
		
		// Le parametre fill sert à définir comment le composant sera rempli GridBagConstraints.BOTH permet d'occuper tout l'espace disponible
				c.fill = GridBagConstraints.BOTH;
				
				// Insets définir la marge entre les composant new Insets(margeSupérieure, margeGauche, margeInférieur, margeDroite) */
				c.insets = new Insets(7, 7, 7, 7);

				// Ipady permet de savoir où on place le composant s'il n'occupe pas la totalité de l'espace disponnible
				c.ipady = c.anchor = GridBagConstraints.CENTER;

				// Weightx définit le nombre de cases en abscisse -> lignes
				c.weightx = 4;

				// Weighty définit le nombre de cases en ordonnée -> colonne
				c.weighty = 3;
				
				// pour dire qu'on ajoute un composant en position (i, j), on définit gridx=i et gridy=j gridx et gridy par défaut à 0

				c.gridx = 0;// première colonne
				c.gridy = 0;// première ligne
				c.gridwidth = 2; // nbre de colonnes que le composant va utiliser dans la grille
				c.gridheight = 1; // nbre de lignes que le composant va utiliser dans la  grille
				
	}
	
	class UnlockClick implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			lock = false;
			cardLayout.show(contentPanel, "MainPanel");
		}
	}
	
	// Getters & Setters
	public CardLayout getCardLayout() 
	{
		return cardLayout;
	}

	public JPanel getContentPanel() 
	{
		return contentPanel;
	}
	
	class CurrentTime implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Calendar now = Calendar.getInstance();
			heure.setText(DATEFORMAT.format(now.getTime()));
		}
	}

}
