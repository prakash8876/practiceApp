package com.thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentExample {

	public static void main(String[] args) {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
//		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		
		Iterator<Integer> itr = map.keySet().iterator();
		while(itr.hasNext()) {
			Integer key = itr.next();
			System.out.println("Map Value:: " + map.get(key));
			if (key.equals(2)) {
				map.put(4, 4); 
//				this will throw java.util.ConcurrentModificationException
//				because we are modifiying map using HashMap
//				To handle this change with ConcurrentHashMap
			}
		}
		
		System.out.println("\n\nMap size :: " + map.size());
		System.out.println("\n\nMap values :: " + map.toString());
	}

}
