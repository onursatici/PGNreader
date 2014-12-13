package PGNreader;

import java.util.HashMap;
import java.util.Vector;

public class VisualizationFive extends Visualization {

	public VisualizationFive(PieceList P, String vizName, String vizNumber, String vizType) {
		super(P);
		this.name= vizName;
		this.number= vizNumber;
		this.type= vizType;

		setData(P);
	}

	public void setData(PieceList P) {

		HashMap<String,Vector<String[]>> pieceMap = this.constructMap();		

		for (Piece piece: P.allPieces){
			Vector<String[]> val = pieceMap.get(piece.getOrigin());
			val.add(new String[]{""+piece.moveHistory.size(),""+piece.captureHistory.size()});		
		}
	}
}
