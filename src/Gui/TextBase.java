/*
* TextBase.java
* Auteur : Jonas Pilloud
* Date de création : 2 juin 2018
*/

package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;


public class TextBase extends JTextField {

		private Color background;
		private Color foreground;
		private Font font15 = new Font("Montserrat", Font.BOLD, 15);
		private boolean editable;

		/**
		 * Constructeur des textfields
		 * @param editable
		 * @param background
		 * @param foreground
		 */
		public TextBase(boolean editable, Color background, Color foreground) 
		{
			this.editable = editable;
			this.background = background;
			this.foreground = foreground;
			setBorder(null);
			setHorizontalAlignment(JTextField.CENTER);
			setFont(font15);
			setBackground(this.background);
			setForeground(this.foreground);
			setEditable(editable);
			addFocusListener(new FieldListener());
			addMouseListener(new TextFieldListener());
			
		}
		/**
		 * Change le mode d'édition
		 */

		public void changeEditable() 
		{
			this.editable = !editable;
			setEditable(editable);
		}
		/**
		 * Listener sur les textfields qui reset les couleurs
		 * @author Rafael Peixoto
		 *
		 */
		
		class TextFieldListener extends MouseAdapter
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Color test = new Color(222, 44, 60);
				if (isEditable() && getBackground().equals(test)) 
				{
					setText(null);
					setBackground(background);
					setForeground(foreground);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Color test = new Color(222, 44, 60);
				if (isEditable() && getBackground().equals(test)) 
				{
					setText(null);
					setBackground(background);
					setForeground(foreground);
				}
			}
		}

		/**
		 * Listner sur le focus des textfields qui reset la couleur
		 * @author Rafael Peixoto
		 *
		 */
		class FieldListener implements FocusListener 
		{
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				Color test = new Color(222, 44, 60);
				if (isEditable() && getBackground().equals(test)) 
				{
					setText(null);
					setBackground(background);
					setForeground(foreground);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) 
			{
			}
		}
	}

