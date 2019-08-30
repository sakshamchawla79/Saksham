package assignment1_201917;

import java.io.File;
import java.io.IOException;
import java.util.*;

import assignment1_201917.Clinic.Clinicdata;

public class Clinic extends Newvetenarian {
	public static class Clinic {
		public Clinicdata data;

		public Clinic(Clinicdata data) {
			this.data = data;
		}
	}

	public static class Clinicdata {
		public Newclinic data;

		public Clinicdata(Newclinic data) {
			this.data = data;
		}
	}

	private Clinic data = new Clinic(new Clinicdata(new Newclinic(new ClinicData())));

	public Clinic(){
		data.data.data.data.listOfVeterinarians = new ArrayList<Veterinarian>();
		data.data.data.data.listOfVeterinarians.clear();
		data.data.data.data.listOfAnimals = new ArrayList<Animal>();
		data.data.data.data.listOfAnimals.clear();
		Clinic();
		}

	public void Clinic() {
		data.data.data.data.setInputFileName("MyInput.csv");
	}
	
	public final String run(){
		String output = "";

		System.out.println("Begin run\n");
		 // set up data for clinic
		
		try {
			File inputFile = new File(data.data.data.data.getInputFileName());
			Scanner s = new Scanner(inputFile);
			String vName,  vSpeciality, line, tokens[], aName,  aBreed, aPreferredVet, aInsuranceNo;
			int vAge, vMax, aHoursTreated, aAge;
			while (s.hasNext()) {
				line = s.nextLine();
				tokens = line.split(",");
				if (tokens[0].equalsIgnoreCase("Veterinarian")) {
					vName = tokens[1];
					vAge = Integer.valueOf(tokens[2]);
					vMax = Integer.valueOf(tokens[3]);
					vSpeciality = tokens[4];
					this.data.data.data.data.listOfVeterinarians.add(new Veterinarian(vName, vAge, vMax, vSpeciality));
				}
				if (tokens[0].equalsIgnoreCase("InsuredAnimal")) {
					aName = tokens[1];
					aAge = Integer.valueOf(tokens[2]);
					aPreferredVet = tokens[3];
					aInsuranceNo = tokens[4];
					aHoursTreated = Integer.valueOf(tokens[5]);
					aBreed = tokens[6];
					this.data.data.data.data.listOfAnimals.add(new Animal(aName, aAge, "Insured", aPreferredVet, aInsuranceNo, aHoursTreated, aBreed));
					
				}
				if (tokens[0].equalsIgnoreCase("Animal")) {
					aName = tokens[1];
					aAge = Integer.valueOf(tokens[2]);
					aHoursTreated = Integer.valueOf(tokens[3]);
					aBreed = tokens[4];
					this.data.data.data.data.listOfAnimals.add(new Animal(aName, aAge, aHoursTreated, aBreed));
				}
			}
		}
		catch ( IOException e) 
		{
			System.out.println(e.getStackTrace());
			this.data.data.data.data.listOfVeterinarians.add(new Veterinarian("Ben Casey", 32, 3, "Small Animals"));
			this.data.data.data.data.listOfVeterinarians.add(new Veterinarian("Hawkeye Pierce", 47, 3, "Large Animals"));
			this.data.data.data.data.listOfVeterinarians.add(new Veterinarian("Doogie Howser", 22, 1, "Horses"));
			// set up animal data
			this.data.data.data.data.listOfAnimals.add(new Animal("Fred Bear", 2, "Insured", "Ben Casey", "HCF236788", 10, "Cockerspaniel"));
			this.data.data.data.data.listOfAnimals.add(new Animal("Betty Bird", 3, 7, "Budgerigar"));
			this.data.data.data.data.listOfAnimals.add(new Animal("Bella Plant", 3, "Insured", "Ben Casey", "HCF265123", 23, "Rabbit"));
			this.data.data.data.data.listOfAnimals.add(new Animal("Dagwood Dog", 8, "Insured", "Doogie Howser", "HCF265988", 2, "Labrador" ) );
			this.data.data.data.data.listOfAnimals.add(new Animal("Ernie", 2, "Insured", "Ben Casey", "HCF134231", 1, "Guinea Pig" ) );
			this.data.data.data.data.listOfAnimals.add(new Animal("Tinkerbell", 4,  1, "Siamese cat") );
			this.data.data.data.data.listOfAnimals.add(new Animal("Slinky", 1, 1, "Blue Tongue Lizard") );
			this.data.data.data.data.listOfAnimals.add(new Animal("Mickey Mouse", 5, "Insured", "Ben Casey", "HCF234511", 1, "Mouse") );
		}
		
		
		// Add details of data to output string

		output += "___________________\n\nList of registered veterinarians\n___________________\n\n";
		for (int i = 0; i < this.data.data.data.data.listOfVeterinarians.size(); i++){
			Veterinarian temp = ((Veterinarian)this.data.data.data.data.listOfVeterinarians.get(i));
			output += temp.toString() + "\n";
			if (temp.HasAnimals()){
				output += temp.printListOfAnimals();
			}
			else{
				output += "No animals assigned to this veterinarian as yet";
			}
			output += "\n";
		}

		output += "\n";
		
		output += "\n___________________\n\nList of animals before veterinarians assigned\n___________________\n\n";
		for (int i = 0; i < this.data.data.data.data.listOfAnimals.size(); i++){
			output += ((Animal)this.data.data.data.data.listOfAnimals.get(i)).toString() + "\n";
		}

		// assign animals to a particular veterinarian in this clinic
		
		output += "\n___________________\n\n Assigning Veterinarians to Animals\n___________________\n";
		ArrayList<Animal> list = this.data.data.data.data.listOfAnimals;
		for (int i = 0; i < list.size(); i++){
			Animal temp = (Animal)list.get(i);
			if (this.data.data.data.data.listOfVeterinarians == null) {
				output += "- No available veterinarians\n*\n"; 
				break;  // go no further will allocations, list of Veterinarians is empty - may need to check for availability in another clinic in a later version 
			}
			if (temp.assignVeterinarian(this.data.data.data.data.listOfVeterinarians) == false){  // attempt to assign animal to a veterinarian
				output += "\n********************************************************************\n Not yet assigned animal " + temp.getName() + "- No available veterinarians\n********************************************************************\n";
				output += ((Animal)list.get(i)).mv(temp.animalType);
			}
			else{
				output += "Assigning veterinarian " + temp.assignedVeterinarian.name + " to " + temp.getName() + "\n";
			}
		}
		
		// Add new information of assignments to output string 

		output += "\n___________________\n\nList of animals after veterinarians assigned\n___________________\n";
		for (int i = 0; i < this.data.data.data.data.listOfAnimals.size(); i++){
			output += ((Animal)this.data.data.data.data.listOfAnimals.get(i)).toString() + "\n";
		}

		output += "___________________\n\nList of veterinarians after animals assigned\n___________________\n" + "\n";
		for (int i = 0; i < this.data.data.data.data.listOfVeterinarians.size(); i++){
			Veterinarian temp = ((Veterinarian)this.data.data.data.data.listOfVeterinarians.get(i));
			output += temp.toString() + "\n";
			if (temp.HasAnimals()){
				output += temp.printListOfAnimals();
			}
			else{
				output += "No animals assigned to this veterinarian as yet";
			}
			output += "\n";
		}
		
		return output;  // return string to calling method to print out
	}

	public String toString() {
		return null;
	}
}
