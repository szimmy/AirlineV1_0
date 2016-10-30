package Airline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Airline.Airplane.Airplane;
import Airline.Airplane.Route;
import Airline.Airplane.SixSeatPlane;
import Airline.Airplane.TwelveSeatPlane;
import Airline.GUIClasses.LoginGUI;
import Airline.Passenger.Passenger;


public class Driver {

	public static void main(String[] args) {

		//Set the highestIds before creating anything to avoid ID overlap
		Passenger.setHighestId();
		TwelveSeatPlane.setHighestId();
		SixSeatPlane.setHighestId();
		
		//createAirplanes();
		new LoginGUI();
	}

	/**
	 * Handles the creation of planes
	 * Couldn't think of a good name, if anyone else can please do so
	 * @param plane
	 */
	private static void initialize(Airplane plane) {
		plane.createSeats();
		plane.serialize();
	}
	
	private static void createAirplanes()  {
		ArrayList<Airplane> planes = new ArrayList<Airplane>();

		planes.add(new SixSeatPlane(1, "S601", new Route( 90.0, "Chicago", "New York City",
				LocalDateTime.of(2015, 12, 04, 9, 00, 00), LocalDateTime.of(2017, 12, 04, 15, 00, 00))));
		
		SixSeatPlane.increaseId();
		
		planes.add(new SixSeatPlane(2, "S602", new Route( 70.0, "New York City", "Philadelphia",
				LocalDateTime.of(2015, 12, 31, 12, 00, 00), LocalDateTime.of(2017, 12, 31, 15, 00, 00))));
		
		SixSeatPlane.increaseId();
		

		planes.add(new SixSeatPlane(3, "S603", new Route( 130.0, "Los Angeles", "Saint Louis",
				LocalDateTime.of(2015, 12, 11, 8, 00, 00), LocalDateTime.of(2017, 12, 11, 15, 00, 00))));
		
		SixSeatPlane.increaseId();
		
		
		planes.add(new SixSeatPlane(4, "S604", new Route( 110.0, "Saint Louis", "Houston",
				LocalDateTime.of(2015, 12, 14, 8, 00, 00), LocalDateTime.of(2017, 12, 14, 12, 00, 00))));
		
		SixSeatPlane.increaseId();

		
		planes.add(new SixSeatPlane(5, "S605", new Route( 80.0, "Philadelphia", "Chicago",
				LocalDateTime.of(2015, 12, 15, 8, 00, 00), LocalDateTime.of(2017, 12, 16, 17, 00, 00))));
		
		SixSeatPlane.increaseId();

		
		planes.add(new SixSeatPlane(6, "S606", new Route( 115.0, "Houston", "New York City",
				LocalDateTime.of(2015, 12, 15, 8, 00, 00), LocalDateTime.of(2017, 12, 16, 14, 00, 00))));
		
		SixSeatPlane.increaseId();

		
		planes.add(new TwelveSeatPlane(1, "S1201", new Route( 50.0, "Chicago",
				"Philadelphia",	LocalDateTime.of(2015, 12, 17, 8, 00, 00), LocalDateTime.of(2017, 12, 17, 14, 00, 00))));
		
		TwelveSeatPlane.increaseId();

		
		planes.add(new TwelveSeatPlane(2, "S1202", new Route( 120.0, "Saint Louis",	"Dallas",
				LocalDateTime.of(2015, 12, 18, 10, 00, 00), LocalDateTime.of(2017, 12, 18, 13, 00, 00))));
		
		TwelveSeatPlane.increaseId();

		
		planes.add(new TwelveSeatPlane(3, "S1203", new Route( 90.0, "New York City", "Washington DC",
				LocalDateTime.of(2015, 12, 19, 9, 00, 00), LocalDateTime.of(2017, 12, 19, 14, 00, 00))));
		
		TwelveSeatPlane.increaseId();

		
		planes.add(new TwelveSeatPlane(4, "S1204", new Route( 100.0, "Washington DC", "Chicago",
				LocalDateTime.of(2015, 12, 20, 10, 00, 00), LocalDateTime.of(2017, 12, 20, 20, 00, 00))));
		
		TwelveSeatPlane.increaseId();


		
		planes.add(new TwelveSeatPlane(5, "S1205", new Route( 90.0, "New York City", "Chicago",
				LocalDateTime.of(2015, 12, 21, 15, 00, 00), LocalDateTime.of(2017, 12, 21, 22, 00, 00))));
		
		TwelveSeatPlane.increaseId();


		
		planes.add(new TwelveSeatPlane(6, "S1206", new Route( 115.0, "Houston", "New York City",
				LocalDateTime.of(2015, 12, 22, 17, 00, 00), LocalDateTime.of(2017, 12, 22, 23, 00, 00))));
		
		TwelveSeatPlane.increaseId();
		
		for(Airplane plane : planes) {
			initialize(plane);
		}

		
	}
	
	/**
	 * A static method which automatically deserializes all the already serialized planes
	 * @return
	 */
	public static ArrayList<Airplane> deserializePlanes() {
		ArrayList<Airplane> planes = new ArrayList<Airplane>();
		
		//Deserialize the Six Seat Planes
		for(int i = 1; i < SixSeatPlane.getHighestId(); i++) {
			planes.add(SixSeatPlane.deserialize(i));
		}
		
		//Deserialize the Twelve Seat Planes
		for(int i = 1; i < TwelveSeatPlane.getHighestId(); i++) {
			planes.add(TwelveSeatPlane.deserialize(i));
		}
		
		return planes;
	}

}
