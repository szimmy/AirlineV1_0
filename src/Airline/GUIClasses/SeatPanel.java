/**
 * SeatPanel.java is a subclass of JPanel which contains a bunch of automatically generated 
 * SeatButton objects in a GridLayout for all of the Seats in the input Airplane Object.
 * @author Sean Zimmerman, Matthew Gimbut, Phong Tran
 * @version 2015.11.21
 */

package Airline.GUIClasses;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Airline.Airplane.Airplane;
import Airline.Passenger.Passenger;
import Airline.Seat.FirstClassSeat;
import Airline.Seat.Seat;

public class SeatPanel extends JPanel {

	private final Color GOLD = new Color(255, 215, 0);
	private final Color LIGHT_BLUE = new Color(175, 238, 238);
	/**
	 * Alternate Constructor which sets up the layout of the Panel
	 * @param airplane The Airplane the Panel is being made for
	 */
	public SeatPanel(Airplane airplane, Passenger passenger) {
		this.setLayout(new GridLayout(0, 6));
		addSeat(airplane, passenger);
	}
	
	/**
	 * A private method which automatically generates and adds SeatButton objects for every seat 
	 * in the Airplane into the Panel
	 * @param airplane The Airplane the Panel is being made for
	 */
	private void addSeat(Airplane airplane, Passenger passenger){
		ArrayList<Seat> seats = airplane.getSeats();
		for(Seat s : seats){
			
			if(s instanceof FirstClassSeat) {
				if(s.isAvailable()) {
				this.add(new JLabel("\t"));
				this.add(new SeatButton(s, GOLD, passenger, airplane));
				this.add(new JLabel("\t"));
				}
				else {
					this.add(new JLabel("\t"));
					this.add(new SeatButton(s, Color.BLACK, passenger, airplane));
					this.add(new JLabel("\t"));
				}

			}
			else {
				if(s.isAvailable()) {
					this.add(new JLabel("\t"));
					this.add(new SeatButton(s, LIGHT_BLUE, passenger, airplane));
					this.add(new JLabel("\t"));
				}
				else {
					this.add(new JLabel("\t"));
					this.add(new SeatButton(s, Color.BLACK, passenger, airplane));
					this.add(new JLabel("\t"));
				}
			}
			
		}
		
	}
}
