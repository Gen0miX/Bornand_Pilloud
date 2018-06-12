/*
* ContactFormulaire.java
*Auteur : Mathias Bornand
*Date de création : 11 juin 2018
*/


package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Gui.BoutonBase;
import Gui.TextBase;



public class ContactFormulaire extends JPanel {

	private JLabel nom = new JLabel("Nom");
	private JLabel prenom = new JLabel("Prenom");
	private JLabel phone = new JLabel("Téléphone");
	private JLabel adresse = new JLabel("Adresse");
	private JLabel mail = new JLabel("Mail");

	private TextBase tnom;
	private TextBase tprenom;
	private TextBase tphone;
	private TextBase tadresse;
	private TextBase tmail;

	private BoutonBase delete = new BoutonBase(new ImageIcon("photo/Icones/supprimer.png"), 480, 40, new Color(11, 11, 11));

	private GridLayout gridLayout = new GridLayout(5, 2, 10, 10) ;
	
	private FlowLayout flowLayout = new FlowLayout();
	private JPanel gridPanel = new JPanel(flowLayout);
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel formulairePanel = new JPanel(gridLayout);
	private JPanel bottomPanel = new JPanel(new GridLayout(1, 1, 10, 10));

	private Dimension dimension = new Dimension(400, 200); // dimension des panels et textfield
	private Font font22 = new Font("Montserrat", Font.BOLD, 22); // Changement police en Montserrat, Gras,22px
	private Font font18 = new Font("Montserrat", Font.BOLD, 18);

	private JButton profilePhoto;
	private Images image;
	private Contact contact;
	private boolean editable;

	/**
	 * Constructeur du formulaire editable
	 * @param editable
	 */
	public ContactFormulaire(boolean editable) 
	{
		this.editable = editable;
		this.profilePhoto = new BoutonBase(new ImageIcon("photo/Icones/ContactGrand.png"), 480, 300);
		paintPanel();
	}
	/**
	 * Constructeur du formulaire de modification non editable
	 * @param contact
	 * @param editable
	 */

	public ContactFormulaire(Contact contact, boolean editable) 
	{
		this.contact = contact;
		this.editable = editable;
		this.profilePhoto = new BoutonBase(contact.getProfilePhoto(), 480, 300);
		paintPanel();
		setFieldsText();
		gridPanel.add(bottomPanel);
		bottomPanel.setPreferredSize(new Dimension(400, 60));
		bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
//		bottomPanel.setOpaque(false);
	}
	/**
	 * Méthode de dessin du panel
	 */

	private void paintPanel() 
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(168,247,171));
		
		tnom = new TextBase(editable, Color.WHITE, Color.BLACK);
		tprenom = new TextBase(editable, Color.WHITE, Color.BLACK);
		tphone = new TextBase(editable, Color.WHITE, Color.BLACK);
		tadresse = new TextBase(editable, Color.WHITE, Color.BLACK);
		tmail = new TextBase(editable, Color.WHITE, Color.BLACK);

//		add(gridPanel);
//		bottomPanel.add(delete);
		this.add(topPanel, BorderLayout.NORTH);
//		this.add(bottomPanel, BorderLayout.SOUTH);
		
		flowLayout.setHgap(0);
		topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		topPanel.setPreferredSize(new Dimension(480, 300));
		topPanel.add(profilePhoto);

		this.add(formulairePanel, BorderLayout.CENTER );
		formulairePanel.setPreferredSize(dimension);
		
		gridPanel.setOpaque(true);
		topPanel.setOpaque(false);
		formulairePanel.setOpaque(false);
		gridPanel.setBackground(new Color(168,247,171)); // créer sa propre couleur
		
		gridLayout.setHgap(10);
		
		formulairePanel.add(prenom);
		prenom.setFont(font22);
		prenom.setForeground(Color.WHITE);
		formulairePanel.add(tprenom);

		formulairePanel.add(nom);
		nom.setFont(font22);
		nom.setForeground(Color.WHITE);
		formulairePanel.add(tnom);

		formulairePanel.add(phone);
		phone.setFont(font22);
		phone.setForeground(Color.WHITE);
		formulairePanel.add(tphone);

		formulairePanel.add(adresse);
		adresse.setFont(font22);
		adresse.setForeground(Color.WHITE);
		formulairePanel.add(tadresse);

		formulairePanel.add(mail);
		mail.setFont(font22);
		mail.setForeground(Color.WHITE);
		formulairePanel.add(tmail);
	}
	/**
	 * Méthode d'écriture dans les textfields
	 */

	private void setFieldsText() 
	{
		tprenom.setText(contact.getPrenom());
		tnom.setText(contact.getNom());
		tphone.setText(contact.getTelephone());
		tadresse.setText(contact.getAdresse());
		tmail.setText(contact.getMail());
	}
	/**
	 * Méthode du reset des textfields
	 */

	public void resetTextField()
	{
		tnom.setText(null);
		tprenom.setText(null);
		tphone.setText(null);
		tadresse.setText(null);
		tmail.setText(null);
	}
	/**
	 * Méthode de modificatio du mode editable des textfields
	 */

	public void changeEditable() 
	{
		editable = !editable;
		tnom.changeEditable();
		tprenom.changeEditable();
		tphone.changeEditable();
		tadresse.changeEditable();
		tmail.changeEditable();

		if (editable) 
		{
			bottomPanel.add(delete);
			updateUI();
		}

		if (editable == false) 
		{
			bottomPanel.removeAll();
			updateUI();
		}
	}
	/**
	 * Méthode de validation
	 * @return valide/nonvalide
	 */

	public boolean validation() 
	{
		boolean validated;
		ValidText validate = new ValidText();
		validated = validate.isNotEmpty(tprenom);
		if(!validated)
			return validated;
		validated = validate.isNotEmpty(tphone);
		if(!validated)
			return validated;
		validated = validate.phoneValide(tphone);
		if(!validated)
			return validated;
		return validated;
	}
	/**
	 * Méthode qui retourn le contact modifié
	 * @return contact modifié
	 */

	public Contact getModifiedContact() 
	{
		contact.setPrenom(tprenom.getText());
		contact.setNom(tnom.getText());
		contact.setTelephone(tphone.getText());
		contact.setAdresse(tadresse.getText());
		contact.setMail(tmail.getText());
		
		if (image != null)
			contact.setImages(image);

		return contact;
	}
	/**
	 * Méthode qui retourne un nouveau contact
	 * @param id
	 * @return nouveau contact
	 */

	public Contact getNewContact(int id) 
	{
		if (image != null)
			return new Contact(id, tprenom.getText(), tnom.getText(), tphone.getText(), tadresse.getText(),
					tmail.getText(), image);
		
		return new Contact(id, tprenom.getText(), tnom.getText(), tphone.getText(), tadresse.getText(),
				tmail.getText());
	}
	/**
	 * Met à jour la photo
	 * @param photo
	 */

	public void updatePhoto(Images image) 
	{
		this.image = image;
		topPanel.remove(profilePhoto);
		profilePhoto = new BoutonBase(image.getThumbnail480300(), 480, 300);
		topPanel.add(profilePhoto);
		updateUI();
	}
	public BoutonBase getDelete() {
		return delete;
	}
	/**
	 * Listener sur le clique d'ajout d'une photo
	 * @param actionListener
	 */

	public void AddPhotoClick(ActionListener actionListener) 
	{
		profilePhoto.addActionListener(actionListener);
	}
	/**
	 * Listener sur le cliquer de suppression d'un contact
	 * @param actionListener
	 */

	public void AddDeleteClick(ActionListener actionListener) 
	{
		delete.addActionListener(actionListener);
	}
}
