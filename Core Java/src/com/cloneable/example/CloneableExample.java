package com.cloneable.example;

public class CloneableExample implements Cloneable {
	int x = 20, y = 40;
	
	public static void main(String[] args) {
		CloneableExample obj1 = new CloneableExample();
		System.out.println("obj1");
		System.out.println(obj1.x);
		System.out.println(obj1.y);
		
		System.out.println();
		
		CloneableExample obj2;
		try {
			obj2 = (CloneableExample) obj1.clone();
			System.out.println("obj2");
			System.out.println(obj2.x);
			System.out.println(obj2.y);
			System.out.println();
			obj2.x = 33;
			obj2.y = 454;
			System.out.println("obj1");
			System.out.println(obj1.x);
			System.out.println(obj1.y);
			System.out.println();
			System.out.println("obj2");
			System.out.println(obj2.x);
			System.out.println(obj2.y);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
