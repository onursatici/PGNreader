package PGNreader;

import java.util.HashMap;
import java.util.Vector;

public class VisualizationEleven extends Visualization {

	public VisualizationEleven(PieceList P, String vizName, String vizNumber, String vizType) {
		super(P);
		this.name= vizName;
		this.number= vizNumber;
		this.type= vizType;

		setData(P);
	}

	public void setData(PieceList P) {

		HashMap<String,Vector<String[]>> pieceMap = this.constructMap();		
		int totalGames = P.bELO.size();
		for (Piece piece: P.allPieces){
			Vector<String[]> val = pieceMap.get(piece.getOrigin());
			val.add(new String[]{""+((double) (totalGames-piece.captureHistory.size())/totalGames)});		
		}
	}
}
