package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Goodguy {
	
	private int xCoord = 0;
	private int yCoord = 0;
	private int width = 40;
	private int height = 40;
	private Image img;
	
	/**
	 * Goodguy default constructor
	 */
	public Goodguy() {
		setxCoord(10) ;
		setyCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("files/left.png");
		
	}
	
	public Goodguy(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
		
	}
	
	public void moveIt(int direction, int w, int h) { 
		int speed = 20;
		int x = getxCoord();
		int y = getyCoord();
		
		if (direction == 39) {
			x = x + speed; 
			if (x > w) { x = x - speed * 3; } //check to see if user has moved off game area
			setxCoord(x);
			setImg("files/left.png");//right
		} else if (direction == 37) {
			if (x < 0) { x = x + speed * 3; } // check to see if user has moved off the game area
			x = x - speed;
			setxCoord(x);
			setImg("files/walle.png");//left
		} else if (direction == 38) {
			if (y < 0) { y = y + speed * 3; } // check to see if user has moved off the game area
			y = y - speed;
			setyCoord(y);
			setImg("files/left.png");//up
		} else if (direction == 40) {
			if (y > h - 10) { y = y - speed * 3; } //check to see if user has moved off the game area]
			y = y + speed;
			setyCoord(y);
			setImg("files/walle.png");
		
		}
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
