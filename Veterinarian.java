package assignment1_201917;
import java.util.*;

public class Veterinarian extends Newvetenarian {

	
		public Veterinarian(){  // constructor to set up a Veterinarian object
			specialisation = "Unknown";

			listOfAnimals = new ArrayList<Animal>();  // initially no animals registered
			listOfAnimals.clear();
			maxNumOfAnimals = 0;  // maximum number of animals that can be registered to the Vet
			hourlyRate = 100;
			this.setIdentifier();
		}

		
		public Veterinarian(String name, int age, int animals2, String specialisation){
			this.name = name;
			this.age = age;

			listOfAnimals = new ArrayList<Animal>();
			listOfAnimals.clear();
			maxNumOfAnimals = animals2;
			this.specialisation = specialisation;
			hourlyRate = 100;
			this.setIdentifier();
		}

		public Veterinarian(String name, int age, int animals2, String specialisation, double hourlyRate){
			this.name = name;
			this.age = age;

			listOfAnimals = new ArrayList<Animal>();
			listOfAnimals.clear();
			maxNumOfAnimals = animals2;
			this.specialisation = specialisation;
			this.setIdentifier();
			this.hourlyRate = hourlyRate;
		}

		@Override
		public String toString(){
			return "*******************\n Dr " + this.name + "\n\t id number: " + 
					identifier + 
					"\n\t Max Number of Patients: " + this.maxNumOfAnimals;
		}
	
		
	}
