
package PGNreader;

public class Knight extends Piece {
	public Knight(String startingLoc, String color){
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
		
		if(Math.abs(currentRank-destinationRank)==1){
			return (Math.abs(currentColumn-destinationColumn) == 2);
		}
		if(Math.abs(currentColumn-destinationColumn)==1){
			return (Math.abs(currentRank-destinationRank) == 2);
		}
		return false;
	}
	
	public static void main(String[] argv){
		Knight k = new Knight("d5","white");
		
		System.out.println(k.canMove("c3"));
	}
}
