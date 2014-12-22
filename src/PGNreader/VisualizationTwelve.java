package PGNreader;

import java.util.HashMap;
import java.util.Vector;

public class VisualizationTwelve extends Visualization {

	public VisualizationTwelve(PieceList P, String vizName, String vizNumber, String vizType) {
		super(P);
		this.name= vizName;
		this.number= vizNumber;
		this.type= vizType;

		setData(P);
	}

	public void setData(PieceList P) {

		HashMap<String,Vector<String[]>> pieceMap = this.constructMap();		
		double totalGames = (double) P.bELO.size();
		for (Piece piece: P.allPieces){

			for(int i=1; i<100; i++){
				double capturedOnTurn=0;
				for (String[] s: piece.killedBy){
					if (s[1].equals(""+i)) {
						capturedOnTurn++;
					}
				}
				String[] string = new String[2];
				string[0] = "move #"+i;
				string[1] = (capturedOnTurn/totalGames)+"";
				Vector<String[]> val = pieceMap.get(piece.getOrigin());
				val.add(string);
			}	
		}
	}
	
}
