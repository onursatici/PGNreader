package PGNreader;

public class King extends Piece {
	public King(String startingLoc, String color){
		this.setLocation(startingLoc);
		this.setColor(color);
		this.setOrigin(startingLoc);
		this.setPiece(this.getClass().getSimpleName());
		String[] moveEntry = {"0", startingLoc};
		this.moveHistory.add(moveEntry);
	}
}
