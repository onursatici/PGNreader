package PGNreader;

import java.util.ArrayList;

public class PieceList {
	Pawn wA = new Pawn("a2","white");
	Pawn wB = new Pawn("b2","white");
	Pawn wC = new Pawn("c2","white");
	Pawn wD = new Pawn("d2","white");
	Pawn wE = new Pawn("e2","white");
	Pawn wF = new Pawn("f2","white");
	Pawn wG = new Pawn("g2","white");
	Pawn wH = new Pawn("h2","white");
	
	Pawn[] wP = {wA, wB, wC, wD, wE, wF, wG, wH};
	
	Rook wRQ = new Rook("a1","white");
	Knight wNQ = new Knight("b1","white");
	Bishop wBQ = new Bishop("c1","white");
	Queen wQ = new Queen("d1","white");
	King wK = new King("e1","white");
	Bishop wBK = new Bishop("f1","white");
	Knight wNK = new Knight("g1","white");
	Rook wRK = new Rook("h1","white");
	
	Pawn bA = new Pawn("a7","black");
	Pawn bB = new Pawn("b7","black");
	Pawn bC = new Pawn("c7","black");
	Pawn bD = new Pawn("d7","black");
	Pawn bE = new Pawn("e7","black");
	Pawn bF = new Pawn("f7","black");
	Pawn bG = new Pawn("g7","black");
	Pawn bH = new Pawn("h7","black");
	
	Pawn[] bP = {bA, bB, bC, bD, bE, bF, bG, bH};
	
	Rook bRQ = new Rook("a8","black");
	Knight bNQ = new Knight("b8","black");
	Bishop bBQ = new Bishop("c8","black");
	Queen bQ = new Queen("d8","black");
	King bK = new King("e8","black");
	Bishop bBK = new Bishop("f8","black");
	Knight bNK = new Knight("g8","black");
	Rook bRK = new Rook("h8","black");
	
	Piece[] allPieces = {wA,wB,wC,wD,wE,wF,wG,wH,wRQ,wNQ,wBQ,wQ,wK,wBK,wNK,wRK,bA,bB,bC,bD,bE,bF,bG,bH,bRQ,bNQ,bBQ,bQ,bK,bBK,bNK,bRK};
	
	ArrayList<String> wELO= new ArrayList<String>();
	ArrayList<String> bELO= new ArrayList<String>();
	
	public static void main(String[] argv){
		PieceList p = new PieceList();
		System.out.print(p.bP[2].getLocation());
	}
}
