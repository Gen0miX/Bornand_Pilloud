/*
 * ValidText.java
 *Auteur : Mathias Bornand
 *Date de création : 11 juin 2018
 */


package Vue;

import java.awt.Color;

import Gui.TextBase;


public class ValidText {


		/**
		 * Méthode qui controle si le textfield est vide ou pas
		 * @param textField
		 * @return
		 */
		public boolean isNotEmpty(TextBase textField)
		{
			if (textField.getText().isEmpty()) 
			{
				textField.setBackground(new Color(222, 44, 60));
				textField.setText("Champ obligatoire");
				textField.setForeground(Color.WHITE);
				return false;
			}
			if(textField.getBackground().equals(new Color(222, 44, 60)))
				return false;
			return true;
		}
		/**
		 * Méthode qui verifie si le format du num de téléphone est valide
		 * @param textField
		 * @return
		 */

		public boolean phoneValide(TextBase textField)
		{
			String phonePattern = "(\\+41)?(\\s)?(0)?\\d{2}(\\s)?\\d{3}(\\s)?\\d{2}(\\s)?\\d{2}";
			String phonePattern2 = "(0041)?(\\s)?(0)?\\d{2}(\\s)?\\d{3}(\\s)?\\d{2}(\\s)?\\d{2}";
			String phonePatternHotline = "(0800)(\\s)?\\d{3}(\\s)?\\d{3}";
			String phonePatternUrgence = "\\d{3}";

			if (textField.getText().matches(phonePattern) == false && textField.getText().matches(phonePattern2) == false
					&& textField.getText().matches(phonePatternHotline) == false
					&& textField.getText().matches(phonePatternUrgence) == false) 
			{
				textField.setBackground(new Color(222, 44, 60));
				textField.setText("Format du numéro invalide");
				textField.setForeground(Color.WHITE);
				return false;
			}
			if(textField.getBackground().equals(new Color(222, 44, 60)))
				return false;
			return true;
		}

	}

