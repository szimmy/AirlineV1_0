/**
 * Seat.java is an abstract class which represents a Seat to be contained in an Airplane object, 
 * is a parent class to FirstClassSeat.java and CoachClassSeat.java. Seat is also Serializable.
 * @author Matthew Gimbut, Sean Zimmerman, Phong Tran
 * @version 2015.11.21
 */

package Airline.Seat;

import java.io.Serializable;

import javax.swing.JOptionPane;

import Airline.Airplane.Airplane;
import Airline.Passenger.Passenger;


public abstract class Seat implements Serializable {
	
	//Fields
	private int id;
	private Passenger passenger;
	private boolean isExitSeat;
	
	//Default generated serialVersionUID so we can save Seats
	private static final long serialVersionUID = 2143376160512288039L;

	/**
	 * Alternate Constructor for seats with no passenger
	 * @param id
	 * @param isExitSeat
	 * @param airplane
	 */
	public Seat(int id, boolean isExitSeat, Airplane airplane) {
		this.id = id;
		this.isExitSeat = isExitSeat;
	}
	
	/**
	 * An accessor method for the private int field id
	 * @return The id of the Seat
	 */
	public int getId() {
		return id;
	}

	/**
	 * A mutator method for the private int field id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * An accessor method for the private Passenger field
	 * @return The Passenger of the Seat
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * A mutator method for the private Passenger field 
	 * @param passenger the passenger to set
	 */
	
	public void setPassenger(Passenger passenger) {
		if (this.passenger == null){
			this.passenger = passenger;
		}
		else {
			JOptionPane.showMessageDialog(null, "this is a temp error message so i can feel cool about using error messages.");
		}
	}

	/**
	 * An accessor method for the private boolean field isExitSeat
	 * @return If the Seat is an exit seat
	 */
	public boolean isExitSeat() {
		return isExitSeat;
	}

	/**
	 * A mutator method for the private boolean field isExitSeat
	 * @param isExitSeat If the seat is an exit seat
	 */
	public void setExitSeat(boolean isExitSeat) {
		this.isExitSeat = isExitSeat;
	}
	
	/**
	 * Checks to see if the seat is currently available
	 * @return Whether or not the seat is available
	 */
	public boolean isAvailable() {
		if (this.passenger == null){
			return true;
		}
		else {
			return false;
		}		
	}
	
	public abstract double getPriceFaction();
	
}
