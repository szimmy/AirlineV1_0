/**
 * MemberPassenger.java is an interface which applies the State Pattern to Passenger.java which 
 * gives MemberPassenger and its implemented classes functionality of if the Passenger can register 
 * for a flight and updating the existing membership. Memberships come in Platinum, Gold, 
 * Silver, and None. MemberPassenger is a sub-interface of Serializable so we can ensure that 
 * classes which implements MemberPassenger can be serialized. This is needed since Passengers 
 * contain MemberPassenger objects and Passengers are serializable. 
 * @author Sean Zimmerman, Matthew Gimbut, Phong Tran
 * @version 2015.11.20
 */

package Airline.Passenger;

import java.io.Serializable;

import Airline.Airplane.Airplane;
import Airline.CustomExceptions.CannotUpgradeMembershipException;

public interface MemberPassenger extends Serializable {

	//Interface Constants, refer to the amount of days in advance you can register for the different members
	public static final int PLATINUM_REGISTRATION_DAYS = 30;
	public static final int GOLD_REGISTRATION_DAYS = 15;
	public static final int SILVER_REGISTRATION_DAYS = 7;
	public static final int NORMAL_REGISTRATION_DAYS = 0;
	
	//Interface Constant, the price to upgade your membership to the next level
	public static final int UPGRADE_COST = 150;
	
	//Define abstract methods
	public abstract boolean canRegister(Airplane plane);
	
	public abstract void updateMembership(Passenger passenger) throws CannotUpgradeMembershipException;
	
	public abstract double getPriceReduction();
}
