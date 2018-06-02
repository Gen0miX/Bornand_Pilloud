/*
* panelStatus.java
* Auteur : Jonas Pilloud
* Date de création : 2 juin 2018
*/

package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class PanelStatus extends JPanel {
	
	private BoutonBase res = new BoutonBase(new ImageIcon("photo/Icones/reseau.png")) ;
	private BoutonBase batterie = new BoutonBase(new ImageIcon("photo/Icones/batterie.png")) ;
	
	//Heure
		private JLabel heure = new JLabel();
		final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm");
		private Timer timer = new Timer(0, new CurrentTime());
	
		public PanelStatus() {
			super();
			GridLayout grid = new GridLayout() ;
			this.setLayout(grid) ;
			this.add(new BarGauche()) ;
			this.add(new BarCentre()) ;
			this.add(new BarDroite()) ;
		}
		
	public class BarGauche extends JPanel {
		public BarGauche() {
			super() ;
			FlowLayout flow = new FlowLayout() ;
			flow.setAlignment(FlowLayout.LEFT);
			flow.setHgap(25);
			flow.setVgap(20);
			this.setLayout(flow);
			this.add(res);
			this.setOpaque(false);
		}
	}
	public class BarCentre extends JPanel {
		public BarCentre() {
			super() ;
			FlowLayout flow = new FlowLayout() ;
			
			this.setLayout(flow);
			// Heure
			timer.start();
			this.add(heure);
			this.setOpaque(false);
			heure.setForeground(Color.WHITE);
			heure.setPreferredSize(new Dimension(40, 55));
			
		}
	}
	
	public class BarDroite extends JPanel{
		public BarDroite() {
		super();
		FlowLayout flow = new FlowLayout() ;
		flow.setAlignment(FlowLayout.RIGHT);
		flow.setHgap(25);
		flow.setVgap(20);
		this.setLayout(flow);
		this.add(batterie) ;
		this.setOpaque(false);
		
		}
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