package PGNreader;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
		
	}
	
	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}
		return fields;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String,Vector<String[]>> constructMap(){
		
		List<Field> fields =getAllFields(new LinkedList<Field>(), this.getClass());

		HashMap<String,Vector<String[]>> pieceMap = new HashMap<String,Vector<String[]>>();
		
		for (Field f : fields) {
			if (f.getType() == java.util.Vector.class) {
				try {
					pieceMap.put(f.getName(), ((Vector<String[]>) f.get(this)));

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		return pieceMap;
	}

	
	public static void main(String[] argv){
		PieceList p = new PieceList();
		Visualization a = new Visualization(p);
		a.constructMap();
		
	}

}
