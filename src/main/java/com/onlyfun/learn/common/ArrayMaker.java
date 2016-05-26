package com.onlyfun.learn.common;

import java.lang.reflect.Array;

public class ArrayMaker<T> {
	private Class<T> kind;
	public ArrayMaker(Class<T> kind) {
		this.kind = kind;
	}
	
	@SuppressWarnings("unchecked")
	T[] create(int size){
		return (T[]) Array.newInstance(kind, size);
	}

	public static void main(String[] args) {
		
	}

}
