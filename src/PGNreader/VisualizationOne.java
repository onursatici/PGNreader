package PGNreader;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VisualizationOne extends Visualization {

	public VisualizationOne(PieceList P, String vizName, String vizNumber, String vizType) {
		super(P);
		this.name= vizName;
		this.number= vizNumber;
		this.type= vizType;

		setData(P);
	}

	public void setData(PieceList P) {

		HashMap<String,Vector<String[]>> pieceMap = this.constructMap();		

		for (Piece piece: P.allPieces){
			Map<String, Integer> map = new HashMap<String, Integer>();

			for (int i=0; i< piece.moveHistory.size()-2; i++){
				String[] s = piece.moveHistory.get(i);
				String[] next = piece.moveHistory.get(i+1);
				if(!(next[0].equals(""+0))){
					if (map.containsKey(s[1] +", "+ next[1])){
						map.put((s[1] +", "+ next[1]), map.get((s[1] +", "+ next[1]))+1);
						
					}
					else {
						map.put((s[1] +", "+ next[1]), 1);
						if(s[1].equals("a7") && next[1].equals("c8")) System.out.println(piece.getOrigin()+" "+s[0]+" "+s[1]+" "+next[0]+" "+next[1]);
					}	
				}
			}
			Vector<String[]> val = pieceMap.get(piece.getOrigin());
			
			for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
				String[] string = new String[2];
				string[0] = mapEntry.getKey();
				string[1] = ""+mapEntry.getValue();
				val.add(string);
			}
			map.clear();
		}
	}
	public static void main(String[] agrv){
		
	}
}
