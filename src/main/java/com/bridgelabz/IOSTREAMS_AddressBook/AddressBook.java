package com.bridgelabz.IOSTREAMS_AddressBook;

/** 
 * @author Goutham
 * <goutham.y@bridgelabz.com>
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/* *
 *  UC1 Ability to create a Contacts in Address Book with first and last names, address, city, state, zip, phone number and email.
 * Ability to add a new Contact to Address Book
 * Using the Oops concepts,collections.
 * It print the Contact details in Address Book.
 */

class Addressbook1 {

	static ArrayList<Address> arr_list = new ArrayList<>();
	Scanner sc = new Scanner(System.in);

	public void AddPreson() {
		System.out.println("Enter the First name :");
		String First_name = sc.next();
		System.out.println("Enter the Last name :");
		String Last_name = sc.next();
		System.out.println("Enter the address :");
		String address = sc.next();
		System.out.println("Enter the phone number :");
		String phoneNum = sc.next();
		System.out.println("Enter the zip code: ");
		String zip = sc.next();
		System.out.println("Enter the city name : ");
		String city = sc.next();
		System.out.println("Enter the state name : ");
		String state = sc.next();
		System.out.println("Enter the email : ");
		String email = sc.next();

		Address adress = new Address(First_name, Last_name, address, phoneNum, zip, city, state, email);

		arr_list.add(adress);
//		for (Address add : arr_list) {
//			add.display();
//		}

		try {
			addtofile(arr_list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * UC3 : Ability to edit existing contact person using their name Editing the
	 * name existing contact person in Address Book.
	 * 
	 * @param name,name1.
	 */

	public void EditePeson(String name, String name1) {
		for (Address a : arr_list) {
			if (name.equals(a.First_name)) {
				a.First_name = name1;
				a.display();
			}

		}
	}

	/**
	 * UC4 : Ability to edit existing contact person using their name Delete the
	 * contact person in Address Book .
	 * 
	 * @param name.
	 */
	public void DeletePerson(String name) {
		for (int i = 0; i < arr_list.size(); i++) {
			Address p = arr_list.get(i);
			if (name.equals(p.First_name)) {
				arr_list.remove(i);
				System.out.println("Person  details are deleted");
				System.out.println(arr_list);
			} else
				System.out.println("Person name not found");
		}
	}

	/**
	 * UC5 : Ability to add multiple person to Address Book
	 * 
	 * @return It prints the multiple persons data n Address Book.
	 */
	public void Multipleperson() {

		Scanner sc = new Scanner(System.in);
		System.out.print("How many person you want to add :");
		int Count = sc.nextInt();
		for (int i = 1; i <= Count; i++) {
			AddPreson();
		}
		for (Address p : arr_list) {
			p.display();
		}
	}

	/**
	 * Ability to search Person in a City or State across the multiple Address Book
	 * Search Result
	 * 
	 * @return It returns person data that matches with city or state in AddressBook
	 */

	public void searchcity() {

		HashMap<Integer, String> hash = new HashMap<>();
		HashMap<Integer, String> hash1 = new HashMap<>();

		String choice, city, pname;
		System.out.println(
				"search \n 1)choose City  (press 1) \n 2)Press city or state  (press 2) : \n 3)Enter the city name (press 3) \n 4)Press 4 sort by name : \n 5)Sort by state pres 5 :\n 6)(press 6)Person contact details are :");
		choice = sc.next();
		switch (choice) {

		case "1":
			System.out.println("Enter the person first name and last name without space ");
			pname = sc.next();
			List<Address> list = arr_list.stream()
					.filter(person_name -> person_name.First_name.concat(person_name.Last_name).equals(pname))
					.collect(Collectors.toList());
			for (Address addr : list) {
				System.out.println("Name of the city is : " + addr.city);
			}
			break;

		/**
		 * Ability to view Persons by City or State
		 * 
		 * @return It returns an person data match with state
		 */
		case "2":
			System.out.println("Enter the city or state name");
			city = sc.next();

			List<Address> list1 = arr_list.stream().filter(city_name -> city_name.city.equals(city))
					.collect(Collectors.toList());
			for (Address addr : list1) {
				System.out.println("the deatils of the person : " + addr.First_name + " " + addr.Last_name);
			}
			break;

		/**
		 * Ability to get number of contact persons i.e. count by City or State It
		 * returns the phone number and name in contact persons.
		 */

		case "3":
			System.out.println("Enter the city name :");
			String cityname = sc.next();
			List<Address> list2 = arr_list.stream().filter(p_number -> p_number.city.equals(cityname))
					.collect(Collectors.toList());
			for (Address addr : list2) {
				System.out.println("The person phone number : " + addr.phoneNum + " name : " + addr.First_name);

			}
			break;

		/**
		 * Ability to sort the entries in the address book alphabetically by Person’s
		 * 
		 * @return It returns the sorted names in Address Book.
		 */
		case "4":
			List<String> sorted = arr_list.stream().map(a -> a.First_name.concat(a.Last_name)).sorted()
					.collect(Collectors.toList());
			sorted.forEach(System.out::println);
			break;

		/**
		 * Ability to sort the entries in the address book by City, State, or Zip
		 * 
		 * @return It return the sorted zip in Address Book.
		 */

		case "5":
			List<String> sortedzip = arr_list.stream().map(s -> s.zip).sorted().collect(Collectors.toList());
			sortedzip.forEach(System.out::println);
			break;

		case "6":
			try {
				diplayData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Invalid user input please enter valid");
			break;
		}

	}

	/**
	 * Ability to Read or Write the Address Book with Persons Contact into a File
	 * using File IO
	 * 
	 * @throws IOException
	 * @param emplist data takes as ArrayList.
	 * @return It returns the write the CSV file data.
	 */

	public static void addtofile(ArrayList<Address> emplist) throws IOException {
		FileWriter filewriter = new FileWriter(
				"C:\\Users\\Goutham\\eclipse-workspace\\AddressBookProblems\\AddressBookcsv\\src\\output.txt");
		for (Address str : emplist) {
			filewriter.write(str + System.lineSeparator());
		}

		filewriter.close();
	}

	/**
	 * Ability to Read or Write the Address Book with Persons Contact into a File
	 * using File IO
	 * 
	 * @return It prints the data which is reading the data from the CSV file.
	 * @throws IOException
	 */

	public static void diplayData() throws IOException {
		Files.lines(
				new File("C:\\Users\\Goutham\\eclipse-workspace\\AddressBookProblems\\AddressBookcsv\\src\\output.txt")
						.toPath())
				.forEach(System.out::println);
	}

	/**
	 * Ability to Write the Address Book with Persons Contact into a File using File
	 * IO
	 * 
	 * @return It prints the data which is reading the data from the file.
	 * @throws IOException
	 */

	public void writecsvdatafile(String filename) {

		try {
			FileWriter outputfile = new FileWriter("C:\\Users\\Goutham\\CSVFiles\\AddressBookData.csv" + filename);
			CSVWriter writer = new CSVWriter(outputfile);
			ArrayList<String[]> csvdata = new ArrayList<>();
			csvdata.add(
					new String[] { "First_name", "Last_name", "address", "phoneNum", "zip", "city", "state", "email" });
			for (Address address : arr_list) {
				csvdata.add(new String[] { address.getFirst_name(), address.getLast_name(), address.getAddress(),
						address.getPhoneNum(), address.getZip(), address.getCity(), address.getState(),
						address.getEmail() });
			}
			writer.writeAll(csvdata);

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Ability to Read/Write the Address Book with Persons Contact as CSV File
	 * 
	 * @return It returns the CSV files data in Address Book.
	 * @param file .
	 */

	public static void readDataLineByLine(String file) {

		try {
			FileReader filereader = new FileReader("C:\\Users\\Goutham\\CSVFiles\\AddressBookData.csv" + file);
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;

			// we are going to read data line by line
			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ability to Read or Write the Address Book with Persons Contact as JSON File
	 * 
	 * @param It provides the filename with extension. It creates he JSON file.
	 */

	public void writejsondatafile(String filename) {

		try {
			// create a writer
			Writer writer = new FileWriter("C:\\Users\\Goutham\\JSONFiles\\" + filename);
			// convert list to JSON File
			new Gson().toJson(arr_list, writer);
			System.out.println("Done");
			// close the writer
			writer.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Ability to Read the Address Book with Persons Contact as JSON File
	 * 
	 * @param It provides the filename with extension.
	 * @purpose It returns the JSON file data.
	 */

	public void readjsondatafile(String filename) {

		Gson gson = new Gson();
		try {

			Address[] jsondata = gson.fromJson(new FileReader("C:\\Users\\Goutham\\JSONFiles\\" + filename),
					Address[].class);

			System.out.println(gson.toJson(jsondata));
		} catch (IOException e) {
			System.err.println("File not found");
		}
	}

}

/**
 * Ability to ensure there is no Duplicate Entry of the same Person in a
 * particular Address Book It prints the details of the Address book in contact
 * person and no duplicate entries.
 */

class AddressHashMap {
	HashMap<String, Address> map = new HashMap<>();

	public void AddressHashmap() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of persons to add :");
		int count = sc.nextInt();
		for (int i = 1; i <= count; i++) {
			System.out.println("Enter the First name :");
			String First_name = sc.next();
			System.out.println("Enter the Last name :");
			String Last_name = sc.next();
			System.out.println("Enter the address :");
			String address = sc.next();
			System.out.println("Enter the phone number :");
			String phoneNum = sc.next();
			System.out.println("Enter the zip code: ");
			String zip = sc.next();
			System.out.println("Enter the city name : ");
			String city = sc.next();
			System.out.println("Enter the state name : ");
			String state = sc.next();
			System.out.println("Enter the email : ");
			String email = sc.next();

			Address data = new Address(First_name, Last_name, address, phoneNum, zip, city, state, email);
			// map.put(email, data);

			if (map.containsKey(First_name.concat(Last_name))) {
				System.out
						.println("\nError : " + First_name + " " + Last_name + " already exists on this address book.");
				break;
			}
			map.put(First_name.concat(Last_name), data);
		}
		displaymapdata(map);
	}

	public void displaymapdata(HashMap<String, Address> map) {
		for (String map1 : map.keySet()) {
			System.out.println("Key: " + map1 + " Value: " + map.get(map1));
		}
	}

}

class AddressBook {

	public static void main(String[] args) {
		Address s1 = new Address("Goutham", "Y", "Banglore near RT nagar", "9866522331", "565500", "Bangalour",
				"Karnataka", "abc@gmail.com");
		s1.display();
		Addressbook1 ad = new Addressbook1();
		ad.AddPreson();
		ad.AddPreson();
		ad.EditePeson("Goutham", "goutham");
		ad.DeletePerson("goutham");
		ad.Multipleperson(); // UC5
		ad.searchcity();
		AddressHashMap add = new AddressHashMap();
		add.AddressHashmap();
		ad.writecsvdatafile("AddressBookData.csv");
		ad.readDataLineByLine("AddressBookData.csv");
		ad.writejsondatafile("userdata.json");
		ad.readjsondatafile("userdata.json");

	}
}

