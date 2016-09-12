/**
 * Airplane.java is an Abstract class which represents a basic Airplane, its sub classes are 
 * SixSeatPlane and TwelveSeatPlane, these are planes to be used by Acme Airlines.
 * @author Phong Tran, Sean Zimmerman, Matthew Gimbut
 * @version 2015.11.21
 */

package Airline.Airplane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Airline.Passenger.Passenger;
import Airline.Seat.Seat;

public abstract class Airplane implements Serializable {
	
	//Fields
	private int id;
	private String flightNo;
	private ArrayList<Seat> seats;
	private Route route;
	
	//Class constant, the seats per row
	public final int NUMBER_OF_SEATS_PER_ROW = 2;
	
	//Default generated serialVersionUID so we can save Airplanes
	private static final long serialVersionUID = -1227205192035724262L;
	
	/**
	 * Alternate Constructor which defines all fields
	 * @param id The id of the Airplane
	 * @param flightNo The flightNumber of the Airplane
	 * @param route The Route of the Airplane
	 */
	public Airplane(int id, String flightNo, Route route) {
		this.id = id;
		this.flightNo = flightNo;
		this.route = route;
		seats = new ArrayList<Seat>();
	}
	
	/**
	 * An accessor method for the private Route field route
	 * @return The Route of the Airplane
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * A mutator method for the private Route field route
	 * @param route The new Route of the Airplane
	 */
	public void setRoute(Route route) {
		this.route = route;
	}
	
	/**
	 * An accessor method for the private int field id
	 * @return The id of the Airplane
	 */
	public int getId() {
		return id;
	}

	/**
	 * An accessor method for the private String field flightNo
	 * @return The flight number of the Airplane
	 */
	public String getFlightNo() {
		return flightNo;
	}
	
	/**
	 * A mutator method for the private String field flightNo
	 * @param flighNo The new flight number of the Airplane
	 */
	public void setFlightNo(String flighNo) {
		this.flightNo = flighNo;
	}

	/**
	 * An accessor method for the private ArrayList of Seat seats
	 * @return The seats of the Airplane
	 */
	public ArrayList<Seat> getSeats() {
		return seats;
	}
	
	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}


	//Define abstract methods for the subclasses to implement
	public abstract void serialize();
	public abstract void createSeats();
	public abstract int getNumberOfSeats();
	

}
