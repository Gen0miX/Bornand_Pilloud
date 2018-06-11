/*
 * ContactApp.java
 *Auteur : Mathias Bornand
 *Date de création : 11 juin 2018
 */


package Vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Gui.BoutonBase;
import Gui.PanelAjout;
import Gui.PanelListApplication;
import Gui.PanelModifApplication;
import Gui.PanelWidget;



public class ContactApp extends JPanel {
	
	private FenetrePrincipale fenPrincipale;
	private ArrayList<Contact> contacts = new ArrayList<Contact>();

	private CardLayout cardLayoutContact = new CardLayout();
	private JPanel contentPanelContact = new JPanel(cardLayoutContact);

	private ContactList contactList;
	private ContactModify contactModify;
	private ContactAdd contactAdd;

	/**
	 * Constructeur du l'application ContactApp
	 * @author Mathias
	 * @param fenPrincipale
	 */
	public ContactApp(FenetrePrincipale mainFrame) 
	{
		this.fenPrincipale = fenPrincipale;
		deSerializeObject();
		contactList = new ContactList();
		setLayout(new BorderLayout());
		add(contentPanelContact);
		contentPanelContact.add(contactList, "ListeContact");
		cardLayoutContact.show(contentPanelContact, "ListeContact");
	}

	/**
	 * Sauvegarde des contacts
	 * @author Mathias
	 */
	public void serializeObject() 
	{
		try 
		{
			FileOutputStream fichier = new FileOutputStream("serials/contacts.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(contacts);
			oos.flush();
			oos.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Chargement des contacts
	 * @author Mathias
	 */

	@SuppressWarnings("unchecked")
	public void deSerializeObject() 
	{
		try 
		{
			FileInputStream fichier = new FileInputStream("serials/contacts.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			contacts = (ArrayList<Contact>) ois.readObject();
			ois.close();
		} 
		catch (IOException e) 
		{
			contacts = new ArrayList<Contact>();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();

		}
	}
	/**
	 * @author Mathias
	 * @param contact
	 */

	private void modifyContact(Contact contact) {
		contacts.set(contacts.indexOf(contact), contact);
		contactList.update();
	}

	/**
	 * @author Mathias
	 * @param contact
	 */

	private void removeContact(Contact contact) {
		contacts.remove(contact);
		contactList.update();
	}

	/**
	 * @author Mathias
	 * @param contact
	 */

	private void addContact(Contact contact) {
		contacts.add(contact);
		contactList.update();
	}

	/**
	 * @author Mathias
	 * @return l'id du prochain contact après ajout.
	 */
	private int getNextId()
	{
		if(contacts.size() == 0)
			return 0;
		return contacts.get(contacts.size()-1).getID()+1;
	}

	/**
	 * Liste des contacts
	 * @author Mathias
	 *
	 */
	class ContactList extends PanelListApplication {
		private JPanel panelListe = new JPanel();
		private JScrollPane scrollPane = new JScrollPane(panelListe);

		/**
		 * Constructeur de la liste des contacts
		 * @author Mathias
		 */
		public ContactList() 
		{
			super("contact", Color.CYAN.darker());

			//BoxLayout permet d'afficher uniquement le scrollpane en cas de besoin
			panelListe.setLayout(new BoxLayout(panelListe, BoxLayout.Y_AXIS));
			panelListe.setBackground(new Color(162,222,208));

			afficheContacts();

			super.getAjout().addActionListener(new AddClick());
			super.getRetour().addActionListener(new BackClick());


			//Suppression des bordures automatiques du scrollpane
			scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			add(scrollPane, BorderLayout.CENTER);
		}

		/**
		 * Affiche les contacts avec action listener associés
		 * @author Mathias
		 */
		public void afficheContacts() 
		{
			ArrayList<Contact> contactsSorted = (ArrayList<Contact>) contacts.clone();
			contactsSorted = sort(contactsSorted);
			BoutonBase buttonTemp;
			for (int i = 0; i < contacts.size(); i++) {
				if (i % 2 == 0)
					buttonTemp = new BoutonBase(contactsSorted.get(i).getPrenom() + " " + contactsSorted.get(i).getNom(),
							contactsSorted.get(i).getMiniature(), Color.WHITE, Color.CYAN.darker(), 16);
				else
					buttonTemp = new BoutonBase(contactsSorted.get(i).getPrenom() + " " + contactsSorted.get(i).getNom(),
							contactsSorted.get(i).getMiniature(), new Color(247, 247, 247), Color.CYAN.darker(), 16);
				panelListe.add(buttonTemp);
				buttonTemp.addActionListener(new ContactClick(contactsSorted.get(i)));
				contactModify = new ContactModify(contactsSorted.get(i));
				contentPanelContact.add(contactModify, "" + contactsSorted.get(i).getID());
			}
		}

		/**
		 * Trie les contacts par ordre alphabétique
		 * @param contacts
		 * @return contacts triés
		 */
		public ArrayList<Contact> sort(ArrayList<Contact> contacts) 
		{

			Collections.sort(contacts, new Comparator<Contact>() 
			{
				@Override
				public int compare(Contact c1, Contact c2) 
				{
					return c1.getPrenom().toLowerCase().compareTo(c2.getPrenom().toLowerCase());
				}
			});
			return contacts;
		}

		/**
		 * Mise à jour des contacts
		 * @author Mathias
		 */
		public void update() 
		{
			panelListe.removeAll();
			afficheContacts();
			updateUI();
		}
		/**
		 * Listener après le click sur un contact
		 * @author Mathias
		 *
		 */

		class ContactClick implements ActionListener 
		{
			Contact contact;

			public ContactClick(Contact contact) 
			{
				this.contact = contact;
			}

			public void actionPerformed(ActionEvent e) 
			{
				cardLayoutContact.show(contentPanelContact, "" + contact.getID());
			}
		}
		/**
		 * Listener après le click sur ajouter un contact
		 * @author Mathias
		 *
		 */

		class AddClick implements ActionListener 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(contactAdd != null)
					contentPanelContact.remove(contactAdd);

				contactAdd = new ContactAdd();
				contentPanelContact.add(contactAdd,"AddContact");
				cardLayoutContact.show(contentPanelContact, "AddContact");
			}
		}

		/**
		 * Listener après le click sur retour
		 * @author Mathias
		 *
		 */
		class BackClick implements ActionListener 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fenPrincipale.getCardLayout().show(fenPrincipale.getContentPanel(), "MainPanel");
			}
		}
	}
	/**
	 * Panel de modification d'un contact
	 * @author Mathias
	 *
	 */

	class ContactModify extends PanelModifApplication {
		private ContactPhoto contactPhoto;
		private ContactFormulaire formulaire;
		private Contact contact;
		private boolean editFlag = false;

		/**
		 * Constructeur du panel de modification d'un contact
		 * @author Mathias
		 * @param contact
		 */
		public ContactModify(Contact contact) 
		{
			super("Modifier un contact", Color.CYAN.darker());
			this.contact = contact;
			this.formulaire = new ContactFormulaire(contact, false);
			add(formulaire);
			super.getRetour().addActionListener(new CancelClick());
			super.getModifier().addActionListener(new EditClick());
			super.getSauvegarde().addActionListener(new SaveClick());
			formulaire.AddDeleteClick(new DeleteClick());
			formulaire.AddPhotoClick(new PhotoClick());
		}

		/**
		 * Met à jour la photo
		 * @author Mathias
		 * @param photo
		 */
		public void updatePhoto(Images image) 
		{
			formulaire.updatePhoto(image);
		}
		/**
		 * Listener cliquer sur annuler modification
		 * @author Mathias
		 *
		 */

		class CancelClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				contactList.update();
				cardLayoutContact.show(contentPanelContact, "ListeContact");
			}
		}
		/**
		 * Listener cliquer sur modifier un contact
		 * @author Mathias
		 *
		 */

		class EditClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				formulaire.changeEditable();
				editFlag = true;
				getRetour().setEnabled(false);
				edit();
			}
		}
		/**
		 * Listener cliquer sur sauvegarder
		 * @author Mathias
		 *
		 */

		class SaveClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (formulaire.validation()) 
				{
					formulaire.changeEditable();
					modifyContact(formulaire.getModifiedContact());
					editFlag = false;
					getRetour().setEnabled(true);
					requestFocusInWindow();
					save();
					contactList.update();
				}
			}
		}
		/**
		 * Listener cliquer sur supprimer un contact
		 * @author Mathias
		 *
		 */

		class DeleteClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				removeContact(contact);
				contactList.update();
				cardLayoutContact.show(contentPanelContact, "ListeContact");

			}
		}
		/**
		 * Listener cliquer sur la photo
		 * @author Mathias
		 *
		 */

		class PhotoClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if (contactPhoto != null)
					contentPanelContact.remove(contactPhoto);
				if (editFlag) {
					contactPhoto = new ContactPhoto(ContactModify.this, contact);
					contentPanelContact.add(contactPhoto, "PhotoContact");
					cardLayoutContact.show(contentPanelContact, "PhotoContact");
				}
			}
		}
	}
	/**
	 * Panel d'ajout d'un contact
	 * @author Mathias
	 *
	 */

	class ContactAdd extends PanelAjout {
		private ContactPhoto contactPhoto;
		private ContactFormulaire formulaire;
		private Contact contact;
		private Images image;

		/**
		 * Constructeur d'ajout d'un contact
		 */
		public ContactAdd() 
		{
			super("Ajouter un contact", Color.CYAN.darker());

			this.formulaire = new ContactFormulaire(true);
			add(formulaire);
			formulaire.AddPhotoClick(new PhotoClick());
			super.getCancel().addActionListener(new CancelClick());
			super.getSave().addActionListener(new SaveClick());

		}
		/**
		 * Mise à jour de la photo
		 * @param photo
		 */

		public void updatePhoto(Images image) 
		{
			formulaire.updatePhoto(image);
			formulaire.AddPhotoClick(new PhotoClick());
		}
		/**
		 * Listener sauvegarde d'un contact
		 * @author Mathias
		 *
		 */

		class SaveClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (formulaire.validation()) {
					int id = getNextId();
					addContact(formulaire.getNewContact(id));
					formulaire.resetTextField();
					contactList.update();
					cardLayoutContact.show(contentPanelContact, "ListeContact");
					return;
				}
			}
		}
		/**
		 * Listener annuler l'ajout
		 * @author Mathias
		 *
		 */

		class CancelClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cardLayoutContact.show(contentPanelContact, "ListeContact");

			}
		}
		/**
		 * Listener clique sur la photo d'ajout
		 * @author Mathias
		 *
		 */

		class PhotoClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (contactPhoto != null)
					contentPanelContact.remove(contactPhoto);

				contactPhoto = new ContactPhoto();
				contentPanelContact.add(contactPhoto, "PhotoContact");
				cardLayoutContact.show(contentPanelContact, "PhotoContact");
			}
		}
	}
	/**
	 * Classe photo des contacts
	 * @author Mathias
	 *
	 */

	class ContactPhoto extends PanelWidget {
		private JPanel gridPanel = new JPanel(new GridLayout(0, 3, 7, 7));
		private JPanel photoListe = new JPanel();
		private JScrollPane scrollPane = new JScrollPane(photoListe);
		private ArrayList<Images> imgall;
		private ContactModify contactModify;
		private Contact contact;

		/**
		 * Constructeur des photos des contacts
		 */
		public ContactPhoto() 
		{
			
			super("choisir une photo", Color.CYAN.darker());
			paintPanel();
		}
		/**
		 * Constructeur des photos des contacts
		 * @param contactModify
		 * @param contact
		 */

		public ContactPhoto(ContactModify contactModify, Contact contact) 
		{
			this();
			this.contactModify = contactModify;
			this.contact = contact;
		}
		/**
		 * Dessine le panel
		 */

		//A FINIR
		public void paintPanel()
		{
			imgall = fenPrincipale.getGalerieApp().getImgall();
			photoListe.setLayout(new FlowLayout());
			photoListe.add(gridPanel);
			gridPanel.setAlignmentX(CENTER_ALIGNMENT);
			gridPanel.setOpaque(false);
			affichePhotos();
			scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			add(scrollPane);

			super.getBack().addActionListener(new BackClick());
		}
		/**
		 * Affiche les photos
		 */

		public void affichePhotos() 
		{
			BoutonBase vignette;

			for (Images photo : imgall) 
			{
				vignette = new BoutonBase(photo.getThumbnail143143(), 143, 143);
				vignette.addActionListener(new PhotoClick(photo));
				gridPanel.add(vignette);
			}
		}
		/**
		 * Listener retour
		 * @author Mathias
		 *
		 */

		class BackClick implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (contact != null)
					cardLayoutContact.show(contentPanelContact, ""+contact.getID());

				cardLayoutContact.show(contentPanelContact, "AddContact");
			}

		}
		/**
		 * Listener cliquer sur la photo
		 * @author Mathias
		 *
		 */

		class PhotoClick implements ActionListener {
			private Images photo;

			public PhotoClick(Images photo) 
			{
				this.photo = photo;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				if (contactModify != null || contact != null) {
					contactModify.updatePhoto(photo);
					cardLayoutContact.show(contentPanelContact, "" + contact.getID());
					return;
				}
				contactAdd.updatePhoto(photo);
				cardLayoutContact.show(contentPanelContact, "AddContact");
			}
		}
	}
}
