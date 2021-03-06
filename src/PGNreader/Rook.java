package PGNreader;

public class Rook extends Piece {
	public Rook(String startingLoc, String color){
		this.setLocation(startingLoc);
		this.setColor(color);
		this.setOrigin(startingLoc);
		this.setPiece(this.getClass().getSimpleName());
	
	}

	public boolean canMove(String loc){
		char destinationRank =loc.charAt(1);
		char destinationColumn = loc.charAt(0);
		char currentRank=this.getLocation().charAt(1);
		char currentColumn=this.getLocation().charAt(0);
		
		return (destinationRank==currentRank || destinationColumn==currentColumn);
	}
	
	public static void main(String[] argv){
		Rook r= new Rook("a7","black");
		
		System.out.println(r.canMove("c8"));
	}
}
