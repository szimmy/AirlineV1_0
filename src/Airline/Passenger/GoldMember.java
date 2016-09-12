/**
 * GoldMember.java is an implementation of the MemberPassenger interface which helps 
 * apple the State Pattern to Passenger.java which gives MemberPassenger and its implemented
 *  classes functionality of if the Passenger can register 
 * for a flight and updating the existing membership. Memberships come in Platinum, Gold, 
 * Silver, and None.
 * @author Sean Zimmerman, Matthew Gimbut, Phong Tran
 * @version 2015.11.20
 */

package Airline.Passenger;

import java.time.*;
import java.time.temporal.*;
import java.util.Date;

//import org.joda.time.Days;
//import org.joda.time.LocalDate;

import Airline.Airplane.Airplane;
import Airline.CustomExceptions.CannotUpgradeMembershipException;

public class GoldMember implements MemberPassenger {

	//Default generated serialVersionUID so we can save GoldMembers
	private static final long serialVersionUID = 1973763743722890383L;
	private final double PRICE_REDUCTION = 25.55;

	/**
	 * Default Constructor
	 */
	public GoldMember() {
		//Do nothing
	}
	
	@Override
	/**
	 * Determines if the Passenger can register for this plane
	 * @param plane The Airplane to check if the passenger can register for it
	 * @return If the passenger can register
	 */
	public boolean canRegister(Airplane plane) {
		//Using the external library Joda Time to easily get the distance between date objects in days
		//Days difference = Days.daysBetween(LocalDate.fromDateFields(plane.getRoute().getFlightDate()), LocalDate.fromDateFields(new Date()));
		//int differenceNum = difference.getDays();
		int differenceNum = LocalDate.now().until(plane.getRoute().getFlightDate()).getDays();		
		//If the dates are closer than the allowed registration day
		if(differenceNum <= MemberPassenger.GOLD_REGISTRATION_DAYS) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	/**
	 * Upgrades the membership type of the Passenger
	 * @param passenger The passenger to be upgraded
	 * @throws CannotUpgradeMembershipException The Passenger is already at the highest upgrade
	 */
	public void updateMembership(Passenger passenger) throws CannotUpgradeMembershipException {
		passenger.setMembership(new PlatinumMember());
	}
	
	public String toString() {
		return "Gold Membership";
	}

	@Override
	public double getPriceReduction() {
		return PRICE_REDUCTION;
	}
}
