/**
 * Route.java is a class which represents the details of an Airplane Route.
 * Includes fields for the departure location, destination, approximate departure and arrival times, and the base price of the route.
 * @author Sean Zimmerman, Matthew Gimbut, Phong Tran
 * @version 2015.10.23
 */

package Airline.Airplane;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Route implements Serializable {

	//Fields
	private double price;
	private String departure;
	private String destination;
	private LocalDateTime departTime;
	private LocalDateTime landingTime;
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("E MMM dd yyyy"); 
	private static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");	
	
	//Default generated serialVersionUID so we can save Routes
	private static final long serialVersionUID = 5864046047982946697L;

	 /**
	  * A constructor for the Route class which takes in all fields 
	  * @param departure The departure location of the Route
	  * @param destination The destination of the Route
	  * @param departTime The departure time of the Route
	  * @param landingTime The approximate landing time of the Route
	  * @param price The base price of the Route
	  */
	public Route(double price, String departure, String destination, LocalDateTime departTime, LocalDateTime landingTime) {
		this.price = price;
		this.departure = departure;
		this.destination = destination;
		this.departTime = departTime;
		this.landingTime = landingTime;
	}
	
	/**
	 * An accessor method for the private String field departure
	 * @return The departure location of the Route
	 */
	public String getDeparture() {
		return departure;
	}
	
	/**
	 * An accessor method for the private String field destination
	 * @return The destination of the Route
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * An accessor method for the private String field departTime
	 * @return The departure time of the Route
	 */
	public LocalDateTime getDepartTime() {
		return departTime;
	}
	
	public String getFormattedDepartTime() {
		return departTime.format(TIME_FORMAT);
	}
	
	/**
	 * An accessor method for the private String field landingTime
	 * @return The approximate landing time of the Route
	 */
	public LocalDateTime getLandingTime() {
		return landingTime;
	}
	
	public String getFormattedLandingTime() {
		return landingTime.format(TIME_FORMAT);
	}
	
	/**
	 * An accessor method for the private double field price
	 * @return The base price of the Route
	 */
	public double getPrice() {
		return price;
	}

	public LocalDate getFlightDate(){
		return this.getDepartTime().toLocalDate();
	}
	
	public String getFormattedInformation() {
		return "<html>"
				+ "From: " + departure 
				+ "<p>"+ "To: " + destination 
				+ "<p>"+ "Leaving: " + this.getDepartTime().format(TIME_FORMAT) 
				+ "<p>"+ "Arriving: " + this.getLandingTime().format(TIME_FORMAT) 
				+ "<p>"+ "On: " + this.getDepartTime().format(DATE_FORMAT)
				+ "</html>";
	}
	public String toString(){
		return  "From: " + departure 
				+ "\n"+ "To: " + destination 
				+ "<\n>"+ "Leaving: " + this.getDepartTime().format(TIME_FORMAT) 
				+ "<\n>"+ "Arriving: " + this.getLandingTime().format(TIME_FORMAT) 
				+ "<\n>"+ "On: " + this.getDepartTime().format(DATE_FORMAT);
				
	}
}
