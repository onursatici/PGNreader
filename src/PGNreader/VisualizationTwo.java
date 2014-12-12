package PGNreader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
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
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i=1; i<100; i++){
			for (String[] s: P.wA.moveHistory){
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
			String[] string = new String[map.entrySet().size()*3];
			for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
				
				string[count] = "move #"+i;
				count++;
				string[count] = mapEntry.getKey();
				count++;
				string[count] = ""+mapEntry.getValue();
				count++;
				
			   //a2.add(new String[]{"move #"+i,mapEntry.getKey(),""+mapEntry.getValue()});
			   
			}
			if (string.length >0){
				a2.add(string);
			}	
			map.clear();
		}
		

	    //field.set(this, value);

		
		//var mapArray = map.entrySet().toArray();
		
		map = new HashMap<String, Integer>();
		for(int i=1; i<100; i++){
			for (String[] s: P.wB.moveHistory){
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
			String[] string = new String[map.entrySet().size()*3]; //*2 instead?
			for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
				
				string[count] = "move #"+i;
				count++;
				string[count] = mapEntry.getKey();
				count++;
				string[count] = ""+mapEntry.getValue();
				count++;
				
			   //a2.add(new String[]{"move #"+i,mapEntry.getKey(),""+mapEntry.getValue()});
			   
			}
			//piece.getOrigin();
			if (string.length >0){
				b2.add(string);
			}		
			map.clear();
		}
		
		map = new HashMap<String, Integer>();
		for(int i=1; i<100; i++){
			for (String[] s: P.wBK.moveHistory){
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
			String[] string = new String[map.entrySet().size()*3];
			for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
				
				string[count] = "move #"+i;
				count++;
				string[count] = mapEntry.getKey();
				count++;
				string[count] = ""+mapEntry.getValue();
				count++;
			
			   //a2.add(new String[]{"move #"+i,mapEntry.getKey(),""+mapEntry.getValue()});
			   
			}
			if (string.length >0){
				f1.add(string);
				
				/*
				try {
					Visualization obj = new Visualization(P);
					 Field field;
					field = obj.getClass().getField("f1");
					Object list = ((Vector<String[]>) field.getType().newInstance());

					Method add = Vector.class.getDeclaredMethod("add",Object.class);

					add.invoke(list, string); //???
					
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
				
				
				
				*/
			}	
			map.clear();
		}
	
		
		

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
