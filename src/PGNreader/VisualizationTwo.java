package PGNreader;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VisualizationTwo extends Visualization {

	public VisualizationTwo(PieceList P, String vizName, String vizNumber, String vizType) {
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
			for(int i=1; i<100; i++){
				for (String[] s: piece.moveHistory){
					if (s[0].equals(""+i)) {
						if (map.containsKey(s[1])){
							map.put(s[1], map.get(s[1])+1);
						}
						else {
							map.put(s[1], 1);
						}				
					}
				}
				int count = 0;
				String[] string = new String[map.entrySet().size()*2];
				for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {

					
					string[count] = mapEntry.getKey();
					count++;
					string[count] = ""+mapEntry.getValue();
					count++;
				}
				if (string.length >0){
					Vector<String[]> val = pieceMap.get(piece.getOrigin());
					val.add(string);
				}	
				map.clear();
			}
		}
	}
}
