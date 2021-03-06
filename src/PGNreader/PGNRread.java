package PGNreader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; 

public class PGNRread {

	public static void parsePGN(){
	
	//  [0-9]+\..+?(?=\s[0-9]+\.)  Regex to read all moves, except the last move

		BufferedReader br = null;
		/** P has every piece in it. Every piece has their own capture, check, and move history. After the buffered
		 * reader is done, a piece will have concatenated Move Histories for adjacent games in the PGN file. A single
		 * output JSON file will include the move, capture and check data of all the games in a PGN file. **/
		PieceList P = new PieceList(); 

		try{
			String currentLine;
			br=new BufferedReader(new FileReader( //PGN to read
					"/Users/ethangottlieb/Documents/PGNreader/ficsgamesdb_201301_standard_nomovetimes_1161103.pgn")); //"/Users/onursatici/Documents/workspace/PGNreader/ficsgamesdb_201301_standard_nomovetimes_1161103.pgn"));
			//iterate through every line
			while((currentLine = br.readLine()) != null){

				//get ELOs and change ELO fields in PieceList
				if(currentLine.length()>7 && currentLine.substring(0,7).equals("[WhiteE")){
					if(currentLine.substring(14,15).equals("\""))
						P.wELO.add(currentLine.substring(11,14));
					else P.wELO.add(currentLine.substring(11,15));
				}
				if(currentLine.length()>7 && currentLine.substring(0,7).equals("[BlackE")){
					if(currentLine.substring(14,15).equals("\""))
						P.bELO.add(currentLine.substring(11,14));
					else P.bELO.add(currentLine.substring(11,15));
				}

				//get the move line of a game and extract moves from it. Append the moves into the PieceList P.
				if(currentLine.length()>1 && !currentLine.substring(0,1).equals("[")){
					extractMoves(currentLine, P);
				}
				
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		printPiecesToJSON(P);
	}
	
	public static void printPiecesToJSON(PieceList P){
		ObjectMapper mapper = new ObjectMapper(); //form output writer to write the Pieces into a JSON file
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("JSONoutput.txt", false)));
			out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(P.wELO)); 
			out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(P.bELO));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		for (Piece piece :  P.allPieces){ //iterate through all pieces
			//Enumeration<String[]> e = piece.moveHistory.elements();

			try
			{
				out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(piece)); //print all instance variables of the piece   	  
			} catch (JsonGenerationException a)
			{
				a.printStackTrace();
			} catch (JsonMappingException a)
			{
				a.printStackTrace();
			} catch (IOException a)
			{
				a.printStackTrace();
			}

			/**while(e.hasMoreElements()){
				System.out.print(Arrays.toString(e.nextElement()) + " ");
			}
			System.out.println();**/
		} 
		out.close();	
		printVisualizationDataToJSON(P);
	}
	
	public static void printVisualizationDataToJSON(PieceList P){

		// Create Visualization Objects
		Visualization one = new VisualizationOne(P, "Piece Flow (Static)", "1", "Board Flow");
		Visualization two = new VisualizationTwo(P, "Piece Movement Map (Time)", "2", "Board Heat");
		Visualization three = new VisualizationThree(P, "Piece Movement Probability (Time)", "3", "Bar Graph");
		Visualization four = new VisualizationFour(P, "Piece Movement Probability (Static)", "4", "Line Graph");
		Visualization five = new VisualizationFive(P, "Avg Moves/Captures Per Piece (Static)", "5", "Bar Graph");
		
		Visualization eleven = new VisualizationEleven(P, "Survival Probability Per Piece (Static)", "11", "Bar Graph");
		Visualization twelve = new VisualizationTwelve(P, "Survival Probability Over Time Per Piece (Static)", "12", "Line Graph");
		
		Visualization[] allVisualizations = {one, two, three, four, five, eleven, twelve};
		
		ObjectMapper mapper = new ObjectMapper(); //form output writer to write the Visualization into a JSON file
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("JSONvisualizations.txt", false)));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		for (Visualization viz :  allVisualizations){ //iterate through all pieces

			try
			{
				out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(viz)); //print all instance variables of the visualization 	  
			} catch (JsonGenerationException a)
			{
				a.printStackTrace();
			} catch (JsonMappingException a)
			{
				a.printStackTrace();
			} catch (IOException a)
			{
				a.printStackTrace();
			}

			
		} 
		out.close();
		
	}

	public static void extractMoves(String movesString, PieceList P) throws IOException{
		Pattern pattern = Pattern.compile("[0-9]+\\..+?(?=((\\s[0-9]+\\.)|(\\{)))"); // regex to extract each move e.g "1. d4 d6"  \\s[0-9]+
		//add regex for last move   [0-9]+\\..+?(?=\\s[0-9]+\\.
		Matcher matcher = pattern.matcher(movesString);
		String[] hit = new String[3];
		String whiteMove = null;
		String blackMove = null;

		for(Piece piece : P.allPieces){
			String[] moveEntry = {"0", piece.getOrigin()};
			piece.moveHistory.add(moveEntry);	
			piece.setLocation(piece.getOrigin());
		}
		while(matcher.find()){
			//hit[0] includes the move number, hit[1] -> white move hit[2] -> black move
			hit=matcher.group().split(" ");
			
			String moveNumber= hit[0].substring(0,(hit[0].length()-1));
			if(hit.length >= 2){
				whiteMove = hit[1];
			}
			if(hit.length >= 3){
				blackMove = hit[2];
			} //TODO make sure that games where a resign happens before black completes their move works. 
			//System.out.println(hit[0]+" "+hit[1]+" "+hit[2]);
			
			String whiteMoveLoc="";
			String blackMoveLoc="";
			Piece lastMovedWhitePiece = null;
			Piece lastMovedBlackPiece = null;

			lastMovedWhitePiece=checkPawnMove(whiteMove,whiteMoveLoc,moveNumber,true,P);
			if(lastMovedWhitePiece == null){
				lastMovedWhitePiece=checkKnightMove(whiteMove,whiteMoveLoc,moveNumber,true,P);
				if(lastMovedWhitePiece == null){
					lastMovedWhitePiece=checkBishopMove(whiteMove,whiteMoveLoc,moveNumber,true,P);
					if(lastMovedWhitePiece == null){
						lastMovedWhitePiece=checkRookMove(whiteMove,whiteMoveLoc,moveNumber,true,P);
						if(lastMovedWhitePiece == null){
							lastMovedWhitePiece=checkQueenMove(whiteMove,whiteMoveLoc,moveNumber,true,P);
							if(lastMovedWhitePiece == null){
								lastMovedWhitePiece=checkKingMove(whiteMove,whiteMoveLoc,moveNumber,true,P);
								if(lastMovedWhitePiece == null){
									checkCastling(whiteMove,moveNumber,true,P);
								}	
							}
											
						}
					}
				}
			}
			lastMovedBlackPiece=checkPawnMove(blackMove,blackMoveLoc,moveNumber,false,P);
			if(lastMovedBlackPiece == null){
				lastMovedBlackPiece=checkKnightMove(blackMove,blackMoveLoc,moveNumber,false,P);
				if(lastMovedBlackPiece == null){
					lastMovedBlackPiece=checkBishopMove(blackMove,blackMoveLoc,moveNumber,false,P);
					if(lastMovedBlackPiece == null){
						lastMovedBlackPiece=checkRookMove(blackMove,blackMoveLoc,moveNumber,false,P);
						if(lastMovedBlackPiece == null){
							lastMovedBlackPiece=checkQueenMove(blackMove,blackMoveLoc,moveNumber,false,P);
							if(lastMovedBlackPiece == null){
								lastMovedBlackPiece=checkKingMove(blackMove,blackMoveLoc,moveNumber,false,P);
								if(lastMovedBlackPiece == null){
									checkCastling(blackMove,moveNumber,false,P);
								}
							}
						}
					}
				}
			}
			//TODO Check for edge cases like en-passant or promotion
			checkChecks(whiteMove,moveNumber,true,lastMovedWhitePiece);
			checkChecks(blackMove,moveNumber,false,lastMovedBlackPiece);
		} 
		//Add here for checkmate / elo/ move number
	}
	
	private static void checkChecks(String move,String moveNumber, boolean isWhite, Piece lastMovedPiece){
		if(move != null)
		if(move.charAt(move.length()-1)== '+' && lastMovedPiece != null){
			lastMovedPiece.checkHistory.add(moveNumber);
		}
	}
	
	private static void checkCastling(String move,String moveNumber ,boolean isWhite, PieceList P){
		if(move != null)
		if(move.charAt(0)=='O'){ // check if castling and update the move histories of different pieces.
			if(move.length()>4){
				if(isWhite){
					addMove(P,P.wK,"c1",moveNumber);
					addMove(P,P.wRQ,"d1",moveNumber);
				}else{
					addMove(P,P.bK,"c8",moveNumber);
					addMove(P,P.bRQ,"d8",moveNumber);
				}
			}else{
				if(isWhite){
					addMove(P,P.wK,"g1",moveNumber);
					addMove(P,P.wRK,"f1",moveNumber);
				}else{
					addMove(P,P.bK,"g8",moveNumber);
					addMove(P,P.bRK,"f8",moveNumber);
				}
			}
		}
	}
	
	private static King checkKingMove(String move, String moveLoc, String moveNumber, boolean isWhite, PieceList P){
		boolean capture =false;
		if(move != null)
		if(move.charAt(0)=='K'){
			moveLoc=move.substring(1,3);
			if(move.charAt(1)=='x'){
				capture=true;
				moveLoc=move.substring(2,4);
			}
			if(isWhite){
				if (capture) addCapture(moveLoc, moveNumber, P, P.wK);
				addMove(P,P.wK,moveLoc,moveNumber);
				return P.wK;
			}else{
				if (capture) addCapture(moveLoc, moveNumber, P, P.bK);
				addMove(P,P.bK,moveLoc,moveNumber);
				return P.bK;
			}
		}
		return null;
	}

	private static void addCapture(String moveLoc, String moveNumber, PieceList P, Piece movedPiece) {
		String capturedPiece = "";

		for (Piece piece :  P.allPieces){
			if (piece.getLocation().equals(moveLoc)){
				capturedPiece = piece.getOrigin();
				piece.setLocation("----captured at " + moveLoc + " by " +  movedPiece.getOrigin()+ movedPiece.getPiece() + " on move "+ moveNumber);
				String[] killedString = {movedPiece.getOrigin() + movedPiece.getPiece(),moveNumber};
				
				piece.killedBy.add(killedString);
			}
		}
		String[] captureEntry = {moveNumber,capturedPiece};
		movedPiece.captureHistory.add(captureEntry);
	}
	
	private static Queen checkQueenMove(String move, String moveLoc,String moveNumber, boolean isWhite, PieceList P){
		boolean capture = false;
		if(move != null)
		if(move.charAt(0)=='Q'){
			char indicator = 'o';
			moveLoc=move.substring(1,3);
			if(move.charAt(1)=='x'){
				moveLoc=move.substring(2,4);
				capture = true;
			}else if( Math.abs(move.charAt(1)-move.charAt(2))<9){ //Rab8
				moveLoc=move.substring(2,4);
				indicator = move.charAt(1); 
			}
			if(move.charAt(2)=='x'){
				moveLoc=move.substring(3,5);
			capture = true;
			indicator = move.charAt(1);
			}
			if(isWhite){
				if(P.wQ.canMove(moveLoc) && (indicator == 'o' || indicator==P.wQ.getLocation().charAt(0))){ //System.out.println("wQ can move: "+P.wQ.getLocation()+" to "+moveLoc);
					if (capture) addCapture(moveLoc, moveNumber, P, P.wQ);
					addMove(P,P.wQ,moveLoc,moveNumber);
					return P.wQ;
				}
			}else{
				if(P.bQ.canMove(moveLoc) && (indicator == 'o' || indicator==P.bQ.getLocation().charAt(0))){
					if (capture) addCapture(moveLoc, moveNumber, P, P.bQ);
					addMove(P,P.bQ,moveLoc,moveNumber);
					return P.bQ;
				}
			}	
		}
		return null;
	}

	
	private static Knight checkKnightMove(String move, String moveLoc, String moveNumber, boolean isWhite, PieceList P) {
		boolean capture = false; //TODO for knights and rooks, ad cases like Rab8
		if(move != null)
		if(move.charAt(0)=='N'){
				char indicator = 'o';
				moveLoc=move.substring(1,3);
				if(move.charAt(1)=='x'){
					moveLoc=move.substring(2,4);
					capture = true;
				}else if( Math.abs(move.charAt(1)-move.charAt(2))<9){ //Rab8
					moveLoc=move.substring(2,4);
					indicator = move.charAt(1); 
				}
				if(move.charAt(2)=='x'){
					moveLoc=move.substring(3,5);
				capture = true;
				indicator = move.charAt(1);
				}
			if(isWhite){
				boolean Q=true; boolean K=true;
				if(indicator != 'o'){
					if(P.wRQ.getLocation().charAt(0)==indicator) K=false;
					if(P.wRK.getLocation().charAt(0)==indicator) Q=false;
				}
				if(P.wNQ.canMove(moveLoc) && Q){
					if (capture) addCapture(moveLoc, moveNumber, P, P.wNQ);
					addMove(P,P.wNQ,moveLoc,moveNumber);
					return P.wNQ;
				}
				if(P.wNK.canMove(moveLoc) && K){
					if (capture) addCapture(moveLoc, moveNumber, P, P.wNK);
					addMove(P,P.wNK,moveLoc,moveNumber);
					return P.wNK;
				}
			}else{
				boolean Q=true; boolean K=true;
				if(indicator != 'o'){
					if(P.wRQ.getLocation().charAt(0)==indicator) K=false;
					if(P.wRK.getLocation().charAt(0)==indicator) Q=false;
				}
				if(P.bNQ.canMove(moveLoc) && Q){
					if (capture) addCapture(moveLoc, moveNumber, P, P.bNQ);
					addMove(P,P.bNQ,moveLoc,moveNumber);
					return P.bNQ;
				}
				if(P.bNK.canMove(moveLoc) && K){
					if (capture) addCapture(moveLoc, moveNumber, P, P.bNK);
					addMove(P,P.bNK,moveLoc,moveNumber);
					return P.bNK;
				}
			}
		}
		return null;

	}

	private static Bishop checkBishopMove(String move, String moveLoc, String moveNumber, boolean isWhite, PieceList P){
		boolean capture = false;
		if(move != null)
		if(move.charAt(0)=='B'){
				moveLoc=move.substring(1,3);
			if(move.charAt(1)=='x'){
				moveLoc=move.substring(2,4);
				capture=true;
			}
			if(move.charAt(2)=='x'){
				moveLoc=move.substring(3,5);
				capture=true;
			}
			if(isWhite){
				if(P.wBQ.canMove(moveLoc)){
					if (capture) addCapture(moveLoc, moveNumber, P, P.wBQ);	
					addMove(P,P.wBQ,moveLoc,moveNumber);
					return P.wBQ;
				}
				if(P.wBK.canMove(moveLoc)){
					if (capture) addCapture(moveLoc, moveNumber, P, P.wBK);
					addMove(P,P.wBK,moveLoc,moveNumber);
					return P.wBK;
				}
			}else{
				if(P.bBQ.canMove(moveLoc)){
					if (capture) addCapture(moveLoc, moveNumber, P, P.bBQ);
					addMove(P,P.bBQ,moveLoc,moveNumber);
					return P.bBQ;
				}
				if(P.bBK.canMove(moveLoc)){
					if (capture) addCapture(moveLoc, moveNumber, P, P.bBK);
					addMove(P,P.bBK,moveLoc,moveNumber);
					return P.bBK;
				}
			}
		}
		return null;
	}

	private static Rook checkRookMove(String move, String moveLoc, String moveNumber, boolean isWhite, PieceList P){
		boolean capture = false;
		if(move != null)
		if(move.charAt(0)=='R'){ //System.out.println(move);
			char indicator = 'o';
				moveLoc=move.substring(1,3);
			if(move.charAt(1)=='x'){
				moveLoc=move.substring(2,4);
				capture = true;
			}else if( Math.abs(move.charAt(1)-move.charAt(2))<9){ //Rab8
				moveLoc=move.substring(2,4);
				indicator = move.charAt(1); 
			}
			if(move.charAt(2)=='x'){
				moveLoc=move.substring(3,5);
				capture = true;
				indicator = move.charAt(1);
			}
			//System.out.println(indicator + " " +moveLoc);
			if(isWhite){
				boolean Q=true; boolean K=true;
				if(indicator != 'o'){
					if(P.wRQ.getLocation().charAt(0)==indicator) K=false;
					if(P.wRK.getLocation().charAt(0)==indicator) Q=false;
				}
				if(P.wRQ.canMove(moveLoc) && isEmptyBetween(P,moveLoc,P.wRQ.getLocation()) && Q){
					if (capture) addCapture(moveLoc, moveNumber, P, P.wRQ); 
					addMove(P,P.wRQ,moveLoc,moveNumber); 
					return P.wRQ;
				}
				if(P.wRK.canMove(moveLoc) && isEmptyBetween(P,moveLoc,P.wRK.getLocation()) && K){
					if (capture) addCapture(moveLoc, moveNumber, P, P.wRK);
					addMove(P,P.wRK,moveLoc,moveNumber); 
					return P.wRK;
				}
			}else{
				boolean Q=true; boolean K=true;
				if(indicator != 'o'){
					if(P.bRQ.getLocation().charAt(0)==indicator) K=false;
					if(P.bRK.getLocation().charAt(0)==indicator) Q=false;
				}
				if(P.bRQ.canMove(moveLoc) && isEmptyBetween(P,moveLoc,P.bRQ.getLocation()) && Q){
					if (capture) addCapture(moveLoc, moveNumber, P, P.bRQ);
					addMove(P,P.bRQ,moveLoc,moveNumber);
					return P.bRQ;
				}
				if(P.bRK.canMove(moveLoc) && isEmptyBetween(P,moveLoc,P.bRK.getLocation()) && K){
					if (capture) addCapture(moveLoc, moveNumber, P, P.bRK);
					addMove(P,P.bRK,moveLoc,moveNumber);
					return P.bRK;
				}
			}
		}
		return null;
	}
	private static void addMove(PieceList P,Piece piece, String moveLoc, String moveNumber){
		piece.setLocation(moveLoc);
		String[] moveEntry = {moveNumber, moveLoc};
		piece.moveHistory.add(moveEntry);
	}

	private static char max(char a, char b){
		if(a>b){
			return a;
		}
		return b;
	}
	private static char min(char a, char b){
		if(a>b){
			return b;
		}
		return a;
	}

	private static boolean isEmptyBetween(PieceList P, String destinationLocation, String currentLocation){ 
		char currentColumn = currentLocation.charAt(0);
		char currentRank = currentLocation.charAt(1);
		char destinationColumn = destinationLocation.charAt(0);
		char destinationRank = destinationLocation.charAt(1);
		//System.out.println(currentColumn+""+currentRank+""+destinationColumn+""+destinationRank);

		for(Piece piece : P.allPieces){
			char locColumn = piece.getLocation().charAt(0);
			char locRank = piece.getLocation().charAt(1);
			//System.out.println(locColumn +""+locRank);

			if(currentRank == destinationRank && currentRank==locRank){
				//System.out.println(min(currentColumn,destinationColumn));
				if(locColumn<max(currentColumn,destinationColumn) && locColumn>min(currentColumn,destinationColumn))
					return false;
			}else
			if(currentColumn == destinationColumn && currentColumn==locColumn){
				if(locRank<max(currentRank,destinationRank) && locRank>min(currentRank,destinationRank))
					return false;
			}
		}
		return true;
	}
	
	/*
	private static boolean isEmptyBetweenDiagonal(PieceList P, String destinationLocation, String currentLocation){ 
		char currentColumn = currentLocation.charAt(0);
		char currentRank = currentLocation.charAt(1);
		char destinationColumn = destinationLocation.charAt(0);
		char destinationRank = destinationLocation.charAt(1);
		//System.out.println(currentColumn+""+currentRank+""+destinationColumn+""+destinationRank);

		for(Piece piece : P.allPieces){
			char locColumn = piece.getLocation().charAt(0);
			char locRank = piece.getLocation().charAt(1);
			//System.out.println(locColumn +""+locRank);

			if(currentRank == destinationRank && currentRank==locRank){
				//System.out.println(min(currentColumn,destinationColumn));
				if(locColumn<max(currentColumn,destinationColumn) && locColumn>min(currentColumn,destinationColumn))
					return false;
			}else
			if(currentColumn == destinationColumn && currentColumn==locColumn){
				if(locRank<max(currentRank,destinationRank) && locRank>min(currentRank,destinationRank))
					return false;
			}
		}
		return true;
	}
*/

	private static Pawn checkPawnMove(String move, String moveLoc,String moveNumber, boolean isWhite, PieceList P){
		if(move != null)
		if(move.charAt(0)=='a' || move.charAt(0)=='b' || move.charAt(0)=='c' || move.charAt(0)=='d' ||
				move.charAt(0)=='e' || move.charAt(0)=='f' || move.charAt(0)=='g' || move.charAt(0)=='h'){
			char row = move.charAt(0);
			boolean captured=false;
			if(move.substring(1,2).equals("x")){
				captured = true;
				moveLoc=move.substring(2,4);
				//System.out.println("Captured: "+move.substring(1,2) +" location: "+moveLoc);
			}else moveLoc=move;
			//System.out.println("location: "+moveLoc);
			for(Pawn pawn : (isWhite ? P.wP : P.bP)){
				if (!(pawn.getLocation().charAt(0)==row)){
					continue;
				}
				if(pawn.canMove(moveLoc.substring(0,2), captured)){
					if (captured){
						addCapture(moveLoc, moveNumber, P, pawn);
					}
					//System.out.println("pawn at: "+pawn.getLocation()+" can move to "+moveLoc.substring(0,2));
					//System.out.println();
					pawn.setLocation(moveLoc);
					String[] moveEntry = {moveNumber,moveLoc};
					pawn.moveHistory.add(moveEntry);
					return pawn;
				}
			}
		}
		return null;
	}

	public static void main(String[] argv){
		parsePGN();
		//Piece p = new Piece();
		//System.out.println(p.getClass().getSimpleName().equals("Piece"));
		//PieceList p =new PieceList();
		//System.out.println(isEmptyBetween(p, "c3", "e3"));

	}
}






/**       //older pawn move checker
if(whiteMove.charAt(0)=='a' || whiteMove.charAt(0)=='b' || whiteMove.charAt(0)=='c' || whiteMove.charAt(0)=='d' ||
		whiteMove.charAt(0)=='e' || whiteMove.charAt(0)=='f' || whiteMove.charAt(0)=='g' || whiteMove.charAt(0)=='h'){

	boolean captured=false;
	if(whiteMove.substring(1,2).equals("x")){
		captured = true;
		whiteMoveLoc=whiteMove.substring(2,4);
		System.out.println("Captured: "+whiteMove.substring(1,2) +" location: "+whiteMoveLoc);
	}else whiteMoveLoc=whiteMove;
	System.out.println("location: "+whiteMoveLoc);
	for(Pawn pawn : P.wP){
		if(pawn.canMove(whiteMoveLoc.substring(0,2), captured)){
			System.out.println("pawn at: "+pawn.getLocation()+" can move to "+whiteMoveLoc.substring(0,2));
			System.out.println();
			pawn.setLocation(whiteMoveLoc);
			String[] moveEntry = {moveNumber,whiteMoveLoc};
			pawn.moveHistory.add(moveEntry);
		}
	}
}

if(blackMove.charAt(0)=='a' || blackMove.charAt(0)=='b' || blackMove.charAt(0)=='c' || blackMove.charAt(0)=='d' ||
		blackMove.charAt(0)=='e' || blackMove.charAt(0)=='f' || blackMove.charAt(0)=='g' || blackMove.charAt(0)=='h'){

	boolean captured=false;
	if(blackMove.substring(1,2).equals("x")){
		captured = true;
		blackMoveLoc=blackMove.substring(2,4);
		System.out.println("Captured: "+blackMove.substring(1,2) +" location: "+blackMoveLoc);
	}else blackMoveLoc=blackMove;
	System.out.println("location: "+blackMoveLoc);
	for(Pawn pawn : P.bP){
		if(pawn.canMove(blackMoveLoc.substring(0,2), captured)){
			System.out.println("pawn at: "+pawn.getLocation()+" can move to "+blackMoveLoc.substring(0,2));
			System.out.println();
			pawn.setLocation(blackMoveLoc);
			String[] moveEntry = {moveNumber,blackMoveLoc};
			pawn.moveHistory.add(moveEntry);
		}
	}
} **/
