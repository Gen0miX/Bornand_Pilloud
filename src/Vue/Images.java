/*
* Image.java
* Auteur : Jonas Pilloud
* Date de création : 2 juin 2018
*/

package Vue;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Images implements Serializable {
	
	private int ID;
	private transient BufferedImage image;
	private ImageIcon thumbnailFull;
	private ImageIcon thumbnail143143;
	private ImageIcon thumbnail480300;
	private ImageIcon thumbnail480480;
	private ImageIcon thumbnail5555;
	private String nom;
	private String url;
	private String format;
	private double width;
	private double height;

	/**
	 * Constructeur photo
	 * @param ID
	 * @param nom
	 * @param file
	 */
	public Images(int ID, String nom, File file) 
	{
		this.ID = ID;
		this.nom = nom;
		this.url = file.getPath();
		this.format = getFileExtension(file);

		try 
		{
			this.image = ImageIO.read(file);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		this.width = image.getWidth();
		this.height = image.getHeight();
		this.thumbnailFull = scaledImage(480, 690);
		this.thumbnail143143 = cropedImage(143, 143);
		this.thumbnail480300 = cropedImage(480, 300);
		this.thumbnail480480 = cropedImage(480, 480);
		this.thumbnail5555 = cropedImage(55, 55);
		
		
	}
	/**
	 * Rezize la photo
	 * @param scaledWidth
	 * @param scaledHeight
	 * @return
	 */

	private ImageIcon scaledImage(double scaledWidth, double scaledHeight) 
	{
		Image scaledImage;
		double ratio = Math.min((scaledWidth)/width, (scaledHeight)/height);
		scaledWidth = width*ratio;
		scaledHeight = height*ratio;;
		scaledImage = image.getScaledInstance((int) scaledWidth, (int) scaledHeight, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}

	/**
	 * Croped la photo
	 * @param desiredWidth
	 * @param desiredHeight
	 * @return
	 */
	
	private ImageIcon cropedImage(double desiredWidth, double desiredHeight)
	{

		BufferedImage scaledImage;
		Image cropedImage;
		double ratio = Math.min((3*desiredWidth)/width, (3*desiredHeight)/height);
		double scaledWidth = width*ratio;
		double scaledHeight = height*ratio;;

		scaledImage = new BufferedImage((int) scaledWidth, (int) scaledHeight, Image.SCALE_SMOOTH);
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(image, 0, 0, (int) scaledWidth, (int) scaledHeight, null);
		g2d.dispose();
		
		cropedImage = scaledImage.getSubimage((int) (scaledWidth-desiredWidth)/2, (int) (scaledHeight-desiredHeight)/2, (int) desiredWidth, (int) desiredHeight);
		
		return new ImageIcon(cropedImage);		
	}
	/**
	 * Retourne l'extension de la photo
	 * @param file
	 * @return
	 */

	private String getFileExtension(File file) 
	{
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public int getID() {
		return ID;
	}

	public ImageIcon getThumbnailFull() {
		return thumbnailFull;
	}

	public void setThumbnailFull(ImageIcon thumbnailFull) {
		this.thumbnailFull = thumbnailFull;
	}

	public ImageIcon getThumbnail143143() {
		return thumbnail143143;
	}

	public void setThumbnail143143(ImageIcon thumbnail143143) {
		this.thumbnail143143 = thumbnail143143;
	}

	public ImageIcon getThumbnail480300() {
		return thumbnail480300;
	}

	public void setThumbnail480300(ImageIcon thumbnail480300) {
		this.thumbnail480300 = thumbnail480300;
	}

	public ImageIcon getThumbnail480480() {
		return thumbnail480480;
	}

	public void setThumbnail480480(ImageIcon thumbnail480480) {
		this.thumbnail480480 = thumbnail480480;
	}

	public ImageIcon getThumbnail5555() {
		return thumbnail5555;
	}

	public void setThumbnail5555(ImageIcon thumbnail5555) {
		this.thumbnail5555 = thumbnail5555;
	}

	public String getNom() 
	{
		return nom;
	}

	public String getUrl() 
	{
		return url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}

	private void writeObject(ObjectOutputStream out) throws IOException 
	{
		out.writeInt(ID);
		out.writeObject(thumbnail143143);
		out.writeObject(thumbnail480300);
		out.writeObject(thumbnail480480);
		out.writeObject(thumbnail5555);
		out.writeObject(thumbnailFull);
		out.writeObject(format);
		out.writeObject(nom);
		out.writeObject(url);
		out.writeDouble(width);
		out.writeDouble(height);
		ImageIO.write(image,format,ImageIO.createImageOutputStream(out));
		
	}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException 
    {
    	ID = in.readInt();
    	thumbnail143143 = (ImageIcon) in.readObject();
    	thumbnail480300 = (ImageIcon) in.readObject();
    	thumbnail480480 = (ImageIcon) in.readObject();
    	thumbnail5555 = (ImageIcon) in.readObject();
    	thumbnailFull = (ImageIcon) in.readObject();
    	format = (String) in.readObject();
    	nom = (String) in.readObject();
    	url = (String) in.readObject();
    	width = in.readDouble();
    	height = in.readDouble();
        image=ImageIO.read(ImageIO.createImageInputStream(in));
    }
}


