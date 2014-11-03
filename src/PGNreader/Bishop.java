package PGNreader;

public class Bishop extends Piece {
	public Bishop(String startingLoc, String color){
		this.setLocation(startingLoc);
		this.setColor(color);
	}
	public boolean canMove(String loc){
		char destinationRank =loc.charAt(1);
		char destinationColumn = loc.charAt(0);
		char currentRank=this.getLocation().charAt(1);
		char currentColumn=this.getLocation().charAt(0);
		
		return (Math.abs(destinationRank-currentRank) == Math.abs(destinationColumn-currentColumn));
	}
	
}
