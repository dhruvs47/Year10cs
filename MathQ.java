package game;

import java.util.Random;

public class MathQ {
	
	private int a;
	private int b;
	private int c;

	public MathQ() {
		//Random rand  = new Random();
		setA(1);
		setB(1);
		setC(getA()*getB());//(q.getA() * q.getB());
	}
	
	public void setA(int a) {
		//Random rand = new Random();
		//this.a = rand.nextInt(15);
		this.a = a;
	}
	public int getA() {
		return a;
	}
	
	public void setB(int b) {
		//Random rand = new Random();
		//this.b = rand.nextInt(15);
		this.b = b;
	}
	public int getB() {
		return b;
	}
	
	public void setC(int c) {
		this.c = c;
	}
	public int getC() {
		return c;
	}
 
} 
