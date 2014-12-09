package PGNreader;

public class VisualizationOne extends Visualization {

	public VisualizationOne(PieceList P, String vizName, String vizNumber, String vizType) {
		super(P);
		this.name= vizName;
		this.number= vizNumber;
		this.type= vizType;
		
		setData(P);
	}
	
	public void setData(PieceList P) {
		
		a2.add(new String[]{""+P.wA.moveHistory.size(),""+P.wA.captureHistory.size()});
		b2.add(new String[]{""+P.wB.moveHistory.size(),""+P.wB.captureHistory.size()});
		c2.add(new String[]{""+P.wC.moveHistory.size(),""+P.wC.captureHistory.size()});
		d2.add(new String[]{""+P.wD.moveHistory.size(),""+P.wD.captureHistory.size()});
		e2.add(new String[]{""+P.wE.moveHistory.size(),""+P.wE.captureHistory.size()});
		f2.add(new String[]{""+P.wF.moveHistory.size(),""+P.wF.captureHistory.size()});
		g2.add(new String[]{""+P.wG.moveHistory.size(),""+P.wG.captureHistory.size()});
		h2.add(new String[]{""+P.wH.moveHistory.size(),""+P.wH.captureHistory.size()});
		
		a7.add(new String[]{""+P.bA.moveHistory.size(),""+P.bA.captureHistory.size()});
		b7.add(new String[]{""+P.bB.moveHistory.size(),""+P.bB.captureHistory.size()});
		c7.add(new String[]{""+P.bC.moveHistory.size(),""+P.bC.captureHistory.size()});
		d7.add(new String[]{""+P.bD.moveHistory.size(),""+P.bD.captureHistory.size()});
		e7.add(new String[]{""+P.bE.moveHistory.size(),""+P.bE.captureHistory.size()});
		f7.add(new String[]{""+P.bF.moveHistory.size(),""+P.bF.captureHistory.size()});
		g7.add(new String[]{""+P.bG.moveHistory.size(),""+P.bG.captureHistory.size()});
		h7.add(new String[]{""+P.bH.moveHistory.size(),""+P.bH.captureHistory.size()});
		
		a1.add(new String[]{""+P.wRQ.moveHistory.size(),""+P.wRQ.captureHistory.size()});
		b1.add(new String[]{""+P.wNQ.moveHistory.size(),""+P.wNQ.captureHistory.size()});
		c1.add(new String[]{""+P.wBQ.moveHistory.size(),""+P.wBQ.captureHistory.size()});
		d1.add(new String[]{""+P.wQ.moveHistory.size(),""+P.wQ.captureHistory.size()});
		e1.add(new String[]{""+P.wK.moveHistory.size(),""+P.wK.captureHistory.size()});
		f1.add(new String[]{""+P.wBK.moveHistory.size(),""+P.wBK.captureHistory.size()});
		g1.add(new String[]{""+P.wNK.moveHistory.size(),""+P.wNK.captureHistory.size()});
		h1.add(new String[]{""+P.wRK.moveHistory.size(),""+P.wRK.captureHistory.size()});
		
		a8.add(new String[]{""+P.bRQ.moveHistory.size(),""+P.bRQ.captureHistory.size()});
		b8.add(new String[]{""+P.bNQ.moveHistory.size(),""+P.bNQ.captureHistory.size()});
		c8.add(new String[]{""+P.bBQ.moveHistory.size(),""+P.bBQ.captureHistory.size()});
		d8.add(new String[]{""+P.bQ.moveHistory.size(),""+P.bQ.captureHistory.size()});
		e8.add(new String[]{""+P.bK.moveHistory.size(),""+P.bK.captureHistory.size()});
		f8.add(new String[]{""+P.bBK.moveHistory.size(),""+P.bBK.captureHistory.size()});
		g8.add(new String[]{""+P.bNK.moveHistory.size(),""+P.bNK.captureHistory.size()});
		h8.add(new String[]{""+P.bRK.moveHistory.size(),""+P.bRK.captureHistory.size()});
		
	}

}
