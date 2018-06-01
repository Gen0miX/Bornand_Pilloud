/*
* PanelVerrouillage.java
* Auteur : Jonas Pilloud
* Date de création : 1 juin 2018
*/

package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;





public class PanelVerrouillage extends JPanel {

	private PanelImage background = new PanelImage(new ImageIcon("photo/BG/fondEcran.png"));
	private PanelImage calque = new PanelImage(new ImageIcon("photo/BG/EcranVerr.png"));
	private BoutonBase verrou = new BoutonBase(new ImageIcon("photo/Icones/deverr.png"));

	private JPanel panel = new JPanel(new GridBagLayout()); // Centre le contenu
	private JPanel panelBorder = new JPanel(new BorderLayout());

	// Heure
	private JLabel heure = new JLabel();
	final private DateFormat DATEFORMATHEURE = new SimpleDateFormat("HH:mm");
	private Timer timer = new Timer(0, new CurrentTime());

	// Date
	private JLabel date = new JLabel();
	final private DateFormat DATEFORMATDATE = new SimpleDateFormat("EEEE dd MMM");
	private Timer timerDate = new Timer(0, new CurrentDate());
	
	//ajout nouvelle police
	
	
	public PanelVerrouillage() 
	{
		setLayout(new BorderLayout());
		add(background);
		background.setLayout(new BorderLayout());
		background.add(calque);
		calque.setOpaque(false);
		calque.setLayout(new BorderLayout());
		calque.add(verrou, BorderLayout.SOUTH);
		verrou.setBorder(new EmptyBorder(0, 0, 150, 0));

		// panel Heure et Date
		calque.add(panel);
		panel.setOpaque(false);
		panel.add(panelBorder);
		panelBorder.setOpaque(false);

		// Heure
		timer.start();
		panelBorder.add(heure, BorderLayout.NORTH);
		heure.setHorizontalAlignment(JLabel.CENTER);
		heure.setForeground(Color.WHITE);
        heure.setFont(new Font("Montserrat", Font.BOLD, 70));
	
		// Date
		timerDate.start();
		panelBorder.add(date, BorderLayout.SOUTH);
		date.setHorizontalAlignment(JLabel.CENTER);
		date.setForeground(Color.WHITE);
		date.setFont(new Font("Montserrat", Font.BOLD, 30));
	}

	public BoutonBase getVerrou() 
	{
		return verrou;
	}

	class CurrentTime implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Calendar now = Calendar.getInstance();
			heure.setText(DATEFORMATHEURE.format(now.getTime()));
		}
	}

	class CurrentDate implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Calendar now = Calendar.getInstance();
			date.setText(DATEFORMATDATE.format(now.getTime()));
		}
	}
}


