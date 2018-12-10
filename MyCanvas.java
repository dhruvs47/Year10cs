package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextField;


import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener {

	 //global variables - accessible in all methods
	Background background = new Background(0,0,1000,1500,"files/scicity.jpg");
	Background menu = new Background(0,0,1000,1500,"files/Start1.png");
	Background end = new Background(0,0,1000, 1500,"files/gao.png");
	Goodguy robot = new Goodguy(0,750,80,80,"files/walle.png");
	
	int qans = 0;
	int qfake1 = 0;
	int qfake2 = 0;
	boolean askq = false;
	int[] qchoices = {0,0,0}; 
	int anspos = 0;
	int qans1pos = 0;
	int qans2pos = 0;
	boolean StartGame = false;
	int qfakedif1 = 0;
	int qfakedif2 = 0;
	
	boolean EndGame = false;

	LinkedList badguys = new LinkedList();

	LinkedList laser = new LinkedList();
	
	int time = 0;
	
	int score = 0;
	
	int round = 0;
	
	int y = 1;;
	
	//boolean bgameover = false;

	Font myFont = new Font("Comic Sans", 1, 80);
	
     public void run() {

            for(int i = 0; i < badguys.size(); i++) {// draw bad guys

                Badguy bg = (Badguy) badguys.get(i);

                bg.setxCoord(bg.getxCoord() + 10);

            }

            repaint();

        }

     MathQ q = new MathQ();

	//boolean inQ = false;
	

	public MyCanvas() {
		this.setSize(2000,1000); //set same size as MyScreen
		this.addKeyListener(this); //add the listener to your canvas
		playIt("files/MKsong.wav");
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i<50; i++) {
			Badguy bg = new Badguy(rand.nextInt(winwidth)+1500, rand.nextInt(winheight),70,90,"files/book2.png");
			Rectangle r = new Rectangle(100,100,30,30);
			if (r.contains(robot.getxCoord(), robot.getyCoord())) { //check to see if badguy spawns on robot
				System.out.println("badguy on top of robot");
				continue;
			
			}
			badguys.add(bg);
		}
	
	}
		@Override
		public void paint(Graphics g) {
			
			if (y%11 == 0 && y != 0) {
				
			 	Random rand = new Random();
			 	qfakedif1 = rand.nextInt(30) - 15; //different difference for answer
			 	qfakedif2 = rand.nextInt(30) - 15; //different difference for answer then fakedif2 
			 	anspos = rand.nextInt(3);
				qans1pos = rand.nextInt(3);
				while (qans1pos == anspos) {
					qans1pos = rand.nextInt(3);
				}
				qans2pos = rand.nextInt(3);
				while (qans2pos == anspos || qans2pos == qans1pos) {
					qans2pos = rand.nextInt(3);
				}
			 	q.setA(rand.nextInt(15));
				q.setB(rand.nextInt(15));
				qans = (q.getA() * q.getB()); /*q.getC();*/ 
				qfake1 = qans + qfakedif1;
				qfake2 = qans - qfakedif2;
				qchoices[anspos] = qans;
				qchoices[qans1pos] = qfake1;
				qchoices[qans2pos] = qfake2;
				askq = true;
				
				
			 }
			
			if (askq) {
				Font myFont = new Font ("Times New Roman", 1, 90);
				g.setFont (myFont); 
				g.drawImage(background.getImg(), background.getxCoord(), background.getyCoord(), background.getHeight(), background.getWidth(), this);
				g.drawString((q.getA()) + " x " + Integer.toString(q.getB()), 600, 200);
				g.drawString("1) " + Integer.toString(qchoices[0]), 200, 500);
				g.drawString("2) " + Integer.toString(qchoices[1]), 600, 500);
				g.drawString("3) " + Integer.toString(qchoices[2]), 1000, 500);
			
				
				 
			} else {
				//EndGame=false;
				//g.drawImage(background.getImg(), background.getxCoord(), background.getyCoord(), background.getHeight(), background.getWidth(), this);
				g.drawImage(end.getImg(), end.getxCoord(), end.getyCoord(), end.getHeight(), end.getWidth(), this);
				
				if (EndGame == true) {
				g.drawImage(background.getImg(), background.getxCoord(), background.getyCoord(), background.getHeight(), background.getWidth(), this);
				g.setColor(Color.blue);
				g.setFont(new Font("serif", Font.BOLD,30));
				g.drawString("Score = " + score, 100, 100);
				/*Font myFont = new Font ("Times New Roman", 1, 150);
				g.setFont (myFont);
				g.drawString ("Game Over", 500, 500);*/  
				try { // Everything in thread stops for 3 seconds
					Thread.sleep(4000); //milliseconds
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();   //I found it from my python waterloo course 
				}
				System.exit(1);
				
			}
			
			g.drawImage(menu.getImg(), menu.getxCoord(), menu.getyCoord(), menu.getHeight(), menu.getWidth(), this);
			
			if (StartGame == true) {
				g.drawImage(background.getImg(), background.getxCoord(), background.getyCoord(), background.getHeight(), background.getWidth(), this);
				
				
				if (!askq) {
					g.drawImage(background.getImg(), background.getxCoord(), background.getyCoord(), background.getHeight(), background.getWidth(), this);
					g.drawImage(robot.getImg(), robot.getxCoord(), robot.getyCoord(), robot.getHeight(), robot.getWidth(), this);
					for(int i = 0; i < badguys.size(); i++) {// draw bad guys
						Badguy bg = (Badguy) badguys.get(i);
						g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
						Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
						if (time%90==0 && !askq) {
							bg.setxCoord(bg.getxCoord()-1);
						
						}
						
						if (bg.getxCoord() < -20) {
							
							EndGame = true;
							background.setImg("files/gao.png");
							
						} if (round != 0) {
							
						}
						
						for(int j = 0; j < laser.size(); j++) {
							Projectile k = (Projectile) laser.get(j);
							if (k.getxCoord() > this.getWidth()) { laser.remove(k); }
							k.setxCoord(k.getxCoord() + 1);
							g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);
							 
							Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
							if (kr.intersects(r)) {
								badguys.remove(i);
								laser.remove(j);
								playIt("files/shout.wav");
								y++; }
							
							
						}
						
									
					  
					repaint();
					}
				}
				else {
					//draw the question
					String str = "What is " + String.valueOf(q.getA() + " x " + String.valueOf(q.getB() + " ?"));
					/*if (inputed right) {
						inQ = false;
						score++;
					
					}*/ 
					this.setFont(myFont);
					g.drawString(str, 100, 200); 
					
				}
			} }
			
		}
		public void playIt(String filename) {
		
			try {
				InputStream in = new FileInputStream(filename);
				AudioStream as = new AudioStream(in);
				AudioPlayer.player.start(as);
				
			} catch (IOException e)	{	
				System.out.println(e);
			}
		} 
	@Override
	public void keyPressed(KeyEvent e) {
		
		
		
		int key = e.getKeyCode();
		int choice = 0;
		boolean answered = false;
		
		if (askq) { // if question is being asked/answered
		
		if(key > 48 && key < 52) { // if keys in between these values pressed
			//askq = false; 
			if (key == 49) { // Keycode for key #1
				choice = 1;  // 
				answered = true;
				y = 1;
			}
			if (key == 50) { //Keycode for key #2
				choice = 2;
				answered = true; // Just answered and answer is being verified
				y = 1;
			}
			if (key == 51) { //Keycode for key #3
				choice = 3;
				answered = true;
				y = 1;  //# of badguys killed resets
			}
			
			if(answered && ((anspos + 1) == choice)) {
				score+=500;  // 500 points added to score integet
				askq = false; // Continues game
				answered = false; 
				y = 1; 
				playIt("files/correct.wav"); // sound effect for right answer
			} else {
				askq = true;
				score-=200;
				y = 11;
				round+=1;
				playIt("files/wrong.wav"); // sound effect for wrong answer
			}
			
			repaint();
				
		} 
		
		} else {
			
		//}
		
		
		
		if (key == KeyEvent.VK_S) {
			StartGame = true;     
		}
		
		if (e.getKeyCode() == 32) {
			Projectile beam = new Projectile(robot.getxCoord(), robot.getyCoord(),30,30,"files/laser.png");
			laser.add(beam);
		}
		System.out.println(e);
		
		robot.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight());// move robot in response to keypress
		
		for(int i = 0; i < badguys.size(); i++) { // check if badguys hit
			Badguy bg = (Badguy) badguys.get(i); // convert generic
			Rectangle ggr = new Rectangle(robot.getxCoord(),robot.getyCoord(),robot.getWidth(),robot.getHeight());
			Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(),bg.getWidth(),bg.getHeight());
			if (ggr.intersects(r)) {
			if (r.contains(robot.getxCoord(),robot.getyCoord())) {
				System.out.println("badguy hit by robot");
				badguys.remove(i);
				playIt("files/shout.wav");
				EndGame = true;
				background.setImg("files/gao.png");
			}
		}
		repaint();
		
		}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	}


