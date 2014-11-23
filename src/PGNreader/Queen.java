package PGNreader;

public class Queen extends Piece {
	public Queen(String startingLoc, String color){
		this.setLocation(startingLoc);
		this.setColor(color);
		this.setOrigin(startingLoc);
		this.setPiece(this.getClass().getSimpleName());
		String[] moveEntry = {"0", startingLoc};
		this.moveHistory.add(moveEntry);
	}
	public boolean canMove(String loc){
		char destinationRank =loc.charAt(1);
		char destinationColumn = loc.charAt(0);
		char currentRank=this.getLocation().charAt(1);
		char currentColumn=this.getLocation().charAt(0);
		
		return ((Math.abs(destinationRank-currentRank) == Math.abs(destinationColumn-currentColumn)) ||
				(destinationRank==currentRank || destinationColumn==currentColumn));
	}
	
	public static void main(String[] argv){
		Queen q = new Queen("e5","white");
		
		System.out.println(q.canMove("a2"));
	}
}
