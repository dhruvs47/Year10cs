package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Badguy {

	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Image img;
	
	public Badguy() {
		setxCoord(40);
		setyCoord(40);
		setWidth(70);
		setHeight(90);
		setImg("files/book2.png");
	}
	
	public Badguy(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	 
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	} 
	 
	
	

}
