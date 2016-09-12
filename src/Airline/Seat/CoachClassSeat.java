
/**
 * 
 * CoachClassSeat subclass of Seat
 * @author Matthew Gimbut
 * @author Sean Zimmerman
 * @author Phong Tran
 *
 */

package Airline.Seat;

import Airline.Airplane.Airplane;

public class CoachClassSeat extends Seat{
	private final double PRICE_FACTION = 1.0;

	public CoachClassSeat(int id, boolean isExitSeat, Airplane airplane) {
		super(id, isExitSeat, airplane);
	}
	
	/**
	 * @return the priceFaction
	 */
	public double getPriceFaction() {
		return PRICE_FACTION;
	}

	public String toString() {
		return "Coach class";
	}

}
