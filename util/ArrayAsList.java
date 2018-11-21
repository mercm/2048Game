package util;

import java.util.Random;

import pr1.Position;


public class ArrayAsList {
	private Object[] arrayAsList;
	private int index;
	
	public ArrayAsList(int size){
		this.arrayAsList = new Position[size*size];
		this.index = 0;
	}
	
	public int size(){
		return this.index;
	}
	
	public Object get(Integer i){
		return this.arrayAsList[i];
	}
	
	public void addPosition(Object pos){
		this.arrayAsList[this.index] = pos;
		this.index++;
	}
	
	public void resetIndex(){
		this.index = 0;
	}
	
	public boolean isEmpty(){
		if(this.index == 0){
			return true;
		} else {
			return false;
		}
	}

	// The rest of the code for the ArrayAsList class is to be added here.
	// The other methods of this class will not be static .
	// This method is static in order to be similar to the "shuffle () "
	// method of the standard library class "Collections ".
	public static void shuffle(ArrayAsList list, Random random) {
		for (int i = list.size(); i > 1; i--) {
			swap(list.arrayAsList, i - 1, random.nextInt(i));
		}
	}
	
	// This method is static in order to be similar to the "shuffle () " method.
	public static Object choice(ArrayAsList list, Random random) {
		return list.get(random.nextInt(list.size()));
	}
	
	private static void swap(Object[] anArray, int i, int j) {
		Object temp = anArray[i];
		anArray[i] = anArray[j];
		anArray[j] = temp;
	}

}
