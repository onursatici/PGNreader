package PGNreader;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VisualizationThree extends Visualization {

	public VisualizationThree(PieceList P, String vizName, String vizNumber, String vizType) {
		super(P);
		this.name= vizName;
		this.number= vizNumber;
		this.type= vizType;

		setData(P);
	}

	public void setData(PieceList P) {

		HashMap<String,Vector<String[]>> pieceMap = this.constructMap();		

		for (Piece piece: P.allPieces){

			for(int i=1; i<100; i++){
				double movesOnTurn=0;
				double totalGames=0;
				for (String[] s: piece.moveHistory){
					if (s[0].equals(""+i)) {
						movesOnTurn++;
					}
					if (s[0].equals(""+0)) {
						totalGames++;
					}
				}
				String[] string = new String[2];
				string[0] = "move #"+i;
				string[1] = (movesOnTurn/totalGames)+"";
				Vector<String[]> val = pieceMap.get(piece.getOrigin());
				val.add(string);
			}	
		}
	}
}
