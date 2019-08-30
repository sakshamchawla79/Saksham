package assignment1_201917;

import java.util.ArrayList;

public abstract class Newvetenarian {

	public static <E> boolean isEmpty(ArrayList<E> arrayList) {
		return arrayList.isEmpty();
	}

	public abstract String toString();

	protected String specialisation;
	public ArrayList<Animal> listOfAnimals;
	public int maxNumOfAnimals;
	protected double hourlyRate;
	private static int vetIdNo = 1000;
	public String name;
	protected int age;
	protected String identifier;

	public Newvetenarian() {
		super();
	}

	public final boolean HasAnimals() {  // does the Vet have any animals registered for care?
		return !Veterinarian.isEmpty(listOfAnimals);
	}

	public final double getHourlyRate() {
		return this.hourlyRate;
	}

	public final String printListOfAnimals() {
		String temp = "";
		temp += "\nList of Animals registered for Dr " + this.name + "\n\n";
		for (int i = 0; i < this.listOfAnimals.size(); i++){
			temp += ((Animal) this.listOfAnimals.get(i)) + "\n";
		}
		return temp;
	}

	public final void setIdentifier() {
		this.identifier = "V" + String.valueOf(vetIdNo++);
	}

}