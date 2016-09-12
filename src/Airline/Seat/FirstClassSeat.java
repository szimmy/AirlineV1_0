package Airline.Seat;

import Airline.Airplane.Airplane;

/**
 * 
 * FirstClassSeat subclass of Seat
 * @author Matthew Gimbut
 * @author Sean Zimmerman
 * @author Phong Tran
 *
 */
public class FirstClassSeat extends Seat{
	private final double PRICE_FACTION = 1.75;

	public FirstClassSeat(int id, boolean isExitSeat, Airplane airplane) {
		super(id, isExitSeat, airplane);
	}
	
	/**
	 * @return the priceFaction
	 */
	public double getPriceFaction() {
		return PRICE_FACTION;
	}

	
	public String toString() {
		return "First class";
	}

}
