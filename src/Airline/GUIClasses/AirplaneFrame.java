/**
 * AirplaneFrame.java is a subclass of JFrame which is to display the route information and the seating 
 * arrangement for a given Airplane Object using a BorderLayout. This uses RouteInfoPanel and SeatPanel objects
 * @author Sean Zimmerman, Matthew Gimbut, Phong Tran
 * @version 2015.11.21
 */

package Airline.GUIClasses;

import javax.swing.*;
import java.awt.*;

import Airline.Airplane.Airplane;
import Airline.Passenger.Passenger;

public class AirplaneFrame extends JFrame {
	
	//Fields
	private JLabel airplaneName;
	private RouteInfoPanel departurePanel;
	private RouteInfoPanel destinationPanel;
	private SeatPanel seatPanel;
	private JPanel spacing;
	private Dimension minSize;
	private Passenger passenger;
	
	public AirplaneFrame (Airplane airplane, Passenger passenger) {
		
		//Construct Panels and add them to the frame
		this.passenger = passenger;
		departurePanel = new RouteInfoPanel(airplane.getRoute().getDeparture(), airplane.getRoute().getFormattedDepartTime());
		destinationPanel = new RouteInfoPanel(airplane.getRoute().getDestination(), airplane.getRoute().getFormattedLandingTime());
		seatPanel = new SeatPanel(airplane, passenger);
		minSize = new Dimension(500,500);
		spacing = new JPanel();
		spacing.add(new JLabel("\t"));
		
		this.airplaneName = new JLabel("Displaying information for airplane: " + airplane.getFlightNo() + ". Black seats are taken.");
		
		this.setTitle("Seat display for flight: " + airplane.getFlightNo());
	
		this.setLayout(new BorderLayout());
		
		this.add(this.airplaneName,BorderLayout.NORTH);
		this.add(this.destinationPanel, BorderLayout.EAST);
		this.add(this.seatPanel, BorderLayout.CENTER);
		this.add(this.departurePanel, BorderLayout.WEST);
		
		this.pack();
		this.setVisible(true);
		this.setSize(minSize);
		this.setMinimumSize(minSize);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
