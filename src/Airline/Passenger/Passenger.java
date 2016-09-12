/**
 * Passenger.java is a class which represents a Passenger on an airline.
 * Includes static methods for validating an email and password to be used by the Passenger. 
 * Passenger is also Serializable.
 * @author Sean Zimmerman, Matthew Gimbut, Phong Tran
 * @version 2015.11.20
 */

package Airline.Passenger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.util.HashMap;

import Airline.Airplane.Airplane;
import Airline.CustomExceptions.CannotUpgradeMembershipException;
import Airline.Seat.Seat;

public class Passenger implements Serializable {

	//Fields
	private HashMap<Seat, Airplane> currentSeats;
	private int id;
	private MemberPassenger membership;
	private String firstName;
	private String lastName;
	private String middleName = "";
	private String email;
	private String password;
	private final String FILE_NAME = "Files\\Passengers\\passenger" + highestId + ".ser";
	private final boolean APPEND_TO_FILE = true;
	private final boolean DO_NOT_APPEND = false;
	private final static String CSV_SEPERATOR = ",";
	
	private static int highestId = 0;
	private static final String ID_FILE_NAME = "FIles\\Passengers\\current_highest_id.txt";
	
	//Default generated serialVersionUID so we can save Passengers
	private static final long serialVersionUID = 9093424143146341090L;
	
	 /**
	  * A constructor for the Passenger class which takes in all fields 
	  * @param id The id of the passenger
	  * @param member The membership of the person
	  * @param firstName The passenger's first name
	  * @param middleName The passenger's last name
	  * @param lastName The passenger's middle name
	  * @param email The passenger's email
	  * @param password The passenger's password
	  */
	public Passenger(int id, MemberPassenger membership, String firstName, String middleName, String lastName, String email, String password) {
		this.currentSeats = new HashMap<Seat, Airplane>();
		this.id = id;
		this.membership = membership;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.email = email;
		this.password = password;
	}
	
	/**
	  * A constructor for the Passenger class which takes in all fields except middle name
	  * @param id The id of the passenger
	  * @param member The membership of the person
	  * @param firstName The passenger's first name
	  * @param lastName The passenger's last name
	  * @param email The passenger's email
	  * @param password The passenger's password
	  */
	public Passenger(int id, MemberPassenger membership, String firstName, String lastName, String email, String password) {
		this.currentSeats = new HashMap<Seat, Airplane>();
		this.id = id;
		this.membership = membership;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.middleName = "";
	}
	
	/*
	 * Default constructor just to initialize Passenger objects before deserializing.
	 */
	public Passenger() {
		
	}
	
	/**
	 * A method which puts a new seat and airplane into the Passengers currentSeats field
	 * @param seat The seat to add (Key)
	 * @param airplane The airplane to add (Value)
	 */
	public void addSeat(Seat seat, Airplane airplane) {
		this.currentSeats.put(seat, airplane);
	}
	
	/**
	 * A method which removes a seat airplane pair from the Passengers cuurrentSeats field
	 * @param seat The seat to remove
	 */
	public void removeSeat(Seat seat) {
		this.currentSeats.remove(seat);
	}
	
	/**
	 * An accessor method for the private HashMap field currentSeats
	 * @return The currentSeats of the person
	 */
	public HashMap<Seat, Airplane> getCurrentSeats() {
		return this.currentSeats;
	}
	
	/**
	 * An accessor method for the private int field id
	 * @return The id of the person
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * An accessor method for the private MemberPassenger field membership
	 * @return The membership of the person
	 */
	public MemberPassenger getMembership() {
		return this.membership;
	}
	
	/**
	 * An accessor method for the private String field firstName
	 * @return The first name of the person
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * An accessor method for the private String field lastName
	 * @return The last name of the person
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * An accessor method for the private String field middleName
	 * @return The middle name of the person
	 */
	public String getMiddleName() {
		return this.middleName;
	}
	
	/**
	 * An accessor method for the private String field email
	 * @return The email of the person
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * An accessor method for the private String field password
	 * @return The password of the person
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * An accessor method for the private static int field highestId
	 * @return The highest ID a passenger object has had
	 */
	public static int getHighestId() {
		return highestId;
	}
	
	/**
	 * A mutator method for the private static int field highestId which increments it by 1 and writes 
	 * the new value into the current_highest_id file
	 */
	public static void increaseId() {
		highestId++;
		try(BufferedWriter idFile = new BufferedWriter(new FileWriter(ID_FILE_NAME));) {
			idFile.write(String.valueOf(highestId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * A mutator method for the private static int field highestid
	 */
	public static void setHighestId() {
		try(BufferedReader input = new BufferedReader(new FileReader(ID_FILE_NAME));) {
			//Sets the highestId to the ID last written to the file on last run
			highestId = Integer.parseInt(input.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Override of the toString method to return the formatted full name of the person
	 * @return The full name of the person
	 */
	public String toString() {
		if(this.middleName.trim().length() > 0) {
			return this.firstName + " " + this.middleName + " " + this.lastName;
		}
		return this.firstName + " " + this.lastName;
	}
	
	/**
	 * A mutator method for the private MemberPassenger field membership
	 * @param membership The membership of the person
	 */
	public void setMembership(MemberPassenger membership) {
		this.membership = membership;
	}
	
	/**
	 * A mutator method for the private String field firstName
	 * @param firstName The first name of the person
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * A mutator method for the private String field lastName
	 * @param lastName The last name of the person
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * A mutator method for the private String field middleName
	 * @param middleName The middle name of the person
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * A mutator method for the private String field email
	 * @param email The new email for the person
	 */
	public void setEmail(String email) {
		if(validateEmail(email)) {
			this.email = email;
		}
	}
	
	/**
	 * A mutator method for the private String field password
	 * @param email The new password for the person
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * A method which calls the updateMembership method of the membership field which 
	 * upgrades the membership of the passenger
	 */
	public void upgradeMembership() {
		try {
			this.membership.updateMembership(this);
		} catch (CannotUpgradeMembershipException e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * A method which calls the canRegister method of the membership field which 
	 * determines if the Passenger can register for this plane
	 * @param plane The Airplane to check if the passenger can register for it
	 * @return If the passenger can register
	 */
	public boolean canRegister(Airplane plane) {
		return this.membership.canRegister(plane);
	}
	
	/**
	 * The three conditions that have to be met for an email to be valid are:
	 * At least one '@' and '.'
	 * At least one of the '.' most proceed the '@'
	 * There must only be one '@'
	 * @param email The email address to be tested
	 * @return If the email is valid or not
	 */
	public static boolean validateEmail(String email) {
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * The three conditions that have to be met for a password to be valid are:
	 * At least 6 characters no more than 20
	 * At least one capital letter
	 * At least one digit
	 * @param password The password to be tested
	 * @return If the password is valid or not
	 */
	public static boolean validatePassword(String password) {
		Pattern p = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}");
		Matcher m = p.matcher(password);
		return m.matches();
	}
	
	/**
	 * Serialization method to save passenger objects to file
	 */
	public void serialize() {
		try(FileOutputStream fs = new FileOutputStream(FILE_NAME, DO_NOT_APPEND);
				ObjectOutputStream os = new ObjectOutputStream(fs)) {
			os.writeObject(this);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deserialization method to get passenger objects from file
	 */
	public static Passenger deserialize(int id) {
		try(FileInputStream fs = new FileInputStream("Files\\Passengers\\passenger" + id + ".ser");
				ObjectInputStream os = new ObjectInputStream(fs)) {
			return (Passenger) os.readObject();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//If the deserialize fails return null
		return null;
	}
	
	/**
	 * Method to write new passenger accounts to CSV file
	 */
	public void writeToCSV() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Files\\accounts.txt", APPEND_TO_FILE));
			StringBuffer line = new StringBuffer();
			
			//Put the passenger information in a csv for easy access
			line.append(id);
			line.append(CSV_SEPERATOR);
			line.append(email);
			line.append(CSV_SEPERATOR);
			line.append(password);			
			line.append(CSV_SEPERATOR);
			
			bw.append(line.toString());
			bw.newLine();
			bw.flush();
			bw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
