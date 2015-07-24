package hadoop.whiffet.internal.api.model;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class EchartsModel<T> {

	private T signX;
	private T signY;
	private List<T> axesDataX=new ArrayList<T>();
	private List<T> axesDataY=new ArrayList<T>();
	
	
	
	public T getSignX() {
		return signX;
	}



	public void setSignX(T signX) {
		this.signX = signX;
	}



	public T getSignY() {
		return signY;
	}



	public void setSignY(T signY) {
		this.signY = signY;
	}



	public List<T> getAxesDataX() {
		return axesDataX;
	}



	public void setAxesDataX(List<T> axesDataX) {
		this.axesDataX = axesDataX;
	}



	public List<T> getAxesDataY() {
		return axesDataY;
	}



	public void setAxesDataY(List<T> axesDataY) {
		this.axesDataY = axesDataY;
	}


//
//	public  Map<T,List<T>> fillData(Map<T,T> map){
//		
//		Iterator<T> it=map.keySet().iterator();
//		
//		
//		while(it.hasNext()){
//			T key=it.next();
//			
//			axesDataX.add(key);
//			axesDataY.add(map.get(key));
//		}
//		Map<T,List<T>> resultMap=new HashMap<T,List<T>>();
//		resultMap.put(signX, axesDataX);
//		resultMap.put(signY, axesDataY);
//		return	resultMap;
//	}

public void fillData(Map<T,T> map){
	 Map<T, T> sortedMap = new TreeMap<T, T>(map);
		Iterator<T> it=sortedMap.keySet().iterator();	
		while(it.hasNext()){
			T key=it.next();
			
			axesDataX.add(key);
			axesDataY.add(sortedMap.get(key));
		}
		
	}


	public EchartsModel() {
		super();
	}



	public EchartsModel(T signX, T signY,Map<T,T> map) {
		super();
		this.signX = signX;
		this.signY = signY;
		fillData(map);
	}
	
	
	
	

}