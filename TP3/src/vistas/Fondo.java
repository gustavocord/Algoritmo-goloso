package vistas;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fondo extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image imagenImage;
	private String url;
	
	
	public Fondo(String url) {
		this.url=url;
	}
	@Override
	public void paint(Graphics g) {
		imagenImage = new ImageIcon(getClass().getResource(url)).getImage();
		g.drawImage(imagenImage, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}
}
