package PGNreader;

import java.util.Vector;


public class Piece {
	private String location = "";
	private String color ="";
	public Vector<String[]> moveHistory=new Vector<String[]>(10);
	public Vector<String> checkHistory=new Vector<String>(5);
	public Vector<String[]> captureHistory=new Vector<String[]>(5);
	public String[] killedBy= new String[2];
	
	
	public void setLocation(String loc){
		this.location= loc;
	}
	
	public String getLocation(){
		return this.location;
	}
	public void setColor(String color){
		this.color = color;
	}
	public String getColor(){
		return this.color;
	}
	
	public static void main(String[] argv){
		Piece p =new Piece();
		String[] a= {"1", "e5"};
		String[] b= {"5", "f5"};
		System.out.println(p.moveHistory);
		p.moveHistory.add(a);
		System.out.println(p.moveHistory.get(0)[0]+" "+ p.moveHistory.get(0)[1]);
		p.moveHistory.add(b);
		System.out.println(p.moveHistory.get(1)[0]+" "+ p.moveHistory.get(1)[1]);
	}
}
