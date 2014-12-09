package PGNreader;

import java.util.Vector;

public class Visualization {
	
	public String name = "";
	public String number ="";
	public String type = "";
	
	public Vector<String[]> a2=new Vector<String[]>(10);
	public Vector<String[]> b2=new Vector<String[]>(10);
	public Vector<String[]> c2=new Vector<String[]>(10);
	public Vector<String[]> d2=new Vector<String[]>(10);
	public Vector<String[]> e2=new Vector<String[]>(10);
	public Vector<String[]> f2=new Vector<String[]>(10);
	public Vector<String[]> g2=new Vector<String[]>(10);
	public Vector<String[]> h2=new Vector<String[]>(10);
	
	public Vector<String[]> a7=new Vector<String[]>(10);
	public Vector<String[]> b7=new Vector<String[]>(10);
	public Vector<String[]> c7=new Vector<String[]>(10);
	public Vector<String[]> d7=new Vector<String[]>(10);
	public Vector<String[]> e7=new Vector<String[]>(10);
	public Vector<String[]> f7=new Vector<String[]>(10);
	public Vector<String[]> g7=new Vector<String[]>(10);
	public Vector<String[]> h7=new Vector<String[]>(10);
	
	public Vector<String[]> a1=new Vector<String[]>(10);
	public Vector<String[]> b1=new Vector<String[]>(10);
	public Vector<String[]> c1=new Vector<String[]>(10);
	public Vector<String[]> d1=new Vector<String[]>(10);
	public Vector<String[]> e1=new Vector<String[]>(10);
	public Vector<String[]> f1=new Vector<String[]>(10);
	public Vector<String[]> g1=new Vector<String[]>(10);
	public Vector<String[]> h1=new Vector<String[]>(10);
	
	public Vector<String[]> a8=new Vector<String[]>(10);
	public Vector<String[]> b8=new Vector<String[]>(10);
	public Vector<String[]> c8=new Vector<String[]>(10);
	public Vector<String[]> d8=new Vector<String[]>(10);
	public Vector<String[]> e8=new Vector<String[]>(10);
	public Vector<String[]> f8=new Vector<String[]>(10);
	public Vector<String[]> g8=new Vector<String[]>(10);
	public Vector<String[]> h8=new Vector<String[]>(10);

	
	public Visualization(PieceList P){
		
		/*
		for (Piece piece: P.allPieces){
			String[] dataEntry={""+piece.moveHistory.size(),""+piece.captureHistory.size()};
			try {
				Visualization v = new Visualization(P);
				Object val = Visualization.getInstanceField(v, piece.getOrigin());
				((Vector<String[]>) val).add(dataEntry);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
	}
	
	/*
	 public static Object getInstanceField(Object instance, String fieldName) throws Throwable {
	        Field field = instance.getClass().getDeclaredField(fieldName);
	        field.setAccessible(true);
	        return field.get(instance);
	    }
	    */
	
	public static void main(String[] argv){

	}

}
