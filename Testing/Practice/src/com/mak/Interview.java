package com.mak;

import java.util.ArrayList;
import java.util.Iterator;

public class Interview {

	public static void main(String[] args) {
		ArrayList al1 = new ArrayList<>();
		al1.add(100);
		al1.add("AMAN");
		al1.add(212);
		System.out.println(al1);
		System.out.println(al1.get(0));
		System.out.println(al1.set(0,"Rajat"));
		System.out.println(al1.size());System.out.println(al1);
		Iterator itr = al1.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next()+" ");
		}
		ArrayList al2 = new ArrayList<>(al1);
		al2.add("Patanjali");
		al2.add("Raghav");
		System.out.println(al2);
		Iterator itr1 = al2.iterator();
		while(itr1.hasNext()) {
			System.out.println(itr1.next()+" ");
		}
		
	}

}
