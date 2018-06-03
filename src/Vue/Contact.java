/*
* Contact.java
* Auteur : Jonas Pilloud
* Date de création : 2 juin 2018
*/

package Vue;

import java.io.Serializable;

import javax.swing.ImageIcon;



public class Contact implements Serializable {
	private int ID;
	private String nom;
	private String prenom;
	private String telephone;
	private String adresse;
	private String mail;
	private Images photo;
	private ImageIcon profileImage = new ImageIcon("image/icons/photoprofile.png");
	private ImageIcon miniature = new ImageIcon("image/icons/contactprofile.png");
	
	/**
	 Constructeur de contact 
	 * @param ID
	 * @param prenom
	 * @param nom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 * @author Jonas
	 */
	public Contact(int ID, String prenom, String nom, String telephone, String adresse, String mail)
	{	
		this.ID = ID;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.mail = mail;
	}

	/**
	 * Constructeur de contact avec photo
	 * @param ID
	 * @param prenom
	 * @param nom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 * @param photo
	 * @author Jonas
	 */
	public Contact(int ID, String prenom, String nom, String telephone, String adresse, String mail, Images photo)
	{	
		this.ID = ID;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.mail = mail;
		setImages(photo);
	}

	public int getID() 
	{
		return ID;
	}
	/**
	 * 
	 * @param photo
	 */
	public void setImages(Images photo)
	{
		this.photo = photo;
		profileImage = photo.getThumbnail480300();
		miniature = photo.getThumbnail5555();
	}
	
	public ImageIcon getProfilePhoto() 
	{
		return profileImage;
	}

	public ImageIcon getMiniature() 
	{
		return miniature;
	}

	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	public String getPrenom() 
	{
		return prenom;
	}

	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}

	public String getTelephone() 
	{
		return telephone;
	}

	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}

	public String getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(String adresse) 
	{
		this.adresse = adresse;
	}

	public String getMail() 
	{
		return mail;
	}

	public void setMail(String mail) 
	{
		this.mail = mail;
	}

	
}
