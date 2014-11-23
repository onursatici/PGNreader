package PGNreader;

public class Pawn extends Piece {
	
	public Pawn(String startingLoc, String color){
		this.setLocation(startingLoc);
		this.setColor(color);
		this.setOrigin(startingLoc);
		this.setPiece(this.getClass().getSimpleName());
		String[] moveEntry = {"0", startingLoc};
		this.moveHistory.add(moveEntry);
	}
	
	public boolean canMove(String loc,boolean captures){
		char destinationRank =loc.charAt(1);
		char destinationColumn = loc.charAt(0);
		char currentRank=this.getLocation().charAt(1);
		char currentColumn=this.getLocation().charAt(0);
		
		if(this.getColor() =="white"){
			//System.out.println("currentLoc: "+currentColumn+currentRank+" destinationLoc: "+destinationColumn+destinationRank);
			if(captures){
				if(((destinationColumn-currentColumn) == 1 || 
						(destinationColumn-currentColumn) == -1) &&
							(destinationRank-currentRank) == 1){
					return true;
				}
			return false;
			}else if(destinationColumn==currentColumn && 
					(((destinationRank-currentRank)==2 && currentRank == '2') || 
							(destinationRank-currentRank)==1)){
				return true;
			}
			
		}
		
		else{
			if(captures){
				if(((destinationColumn-currentColumn) == 1 || 
						(destinationColumn-currentColumn) == -1) &&
							(destinationRank-currentRank) == -1){
					return true;
				}
			return false;
			}else if(destinationColumn==currentColumn && 
					(((destinationRank-currentRank)==-2 && currentRank == '7') || 
							(destinationRank-currentRank)==-1)){
				return true;
			}
			
		}
		return false;
	}
	
	public static void main(String[] argv){
		 Pawn p = new Pawn("a2","white");
		 Knight k = new Knight("c1","white");
		 Bishop b = new Bishop("b1","white");
		 Piece[] list ={p,k,b};
		 
		 p.setLocation("b3");
		 for(Piece a : list){
			 if(a.getLocation() == "c1"){
				 a.checkHistory.add("3");
			 }
		 }
		 System.out.println(k.checkHistory.get(0));
		 
	}
	
	
}
