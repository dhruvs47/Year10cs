package game;

import javax.swing.JFrame;
import java.awt.BorderLayout;

//inheriting this code
public class MyScreen extends JFrame {
	//J frame is the frame that holds the canvas and you can't draw on it
	//inheriting this and adding to it

	public MyScreen() {
		//these are constructors
		this.setSize(2000,1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}//runs the code (main method) and it is required or else the code won't run
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//Adds canvas onto screen and runs canvas code
			MyScreen screen = new MyScreen();
			MyCanvas canvas = new MyCanvas();
			screen.getContentPane().add(canvas);
	}

}
