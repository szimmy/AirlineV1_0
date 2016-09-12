package Airline.GUIClasses;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

import Airline.Airplane.Airplane;
import Airline.Passenger.Passenger;
import Airline.Seat.Seat;

@SuppressWarnings("serial")
public class SeatButton extends JButton {
	private String seatName;
	private Passenger passenger;
	private Airplane airplane;
	private Seat seat;
	private String doubleToCurrency;
	private NumberFormat formatter;

	public SeatButton(Seat seat, Color color, Passenger passenger, Airplane airplane) {
		this.passenger = passenger;
		this.airplane = airplane;
		this.seat = seat;
		formatter = NumberFormat.getCurrencyInstance();
		doubleToCurrency = "";
		setSeatInfo();
		this.setBackground(color);
	}

	/**
	 * Made the confirmation message code it's own method to avoid repeated code
	 * @param passenger
	 * @param seat
	 * @param airplane
	 */
	public int printConfirmationMessage(Passenger passenger, Seat seat, Airplane airplane) {
		int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to register for seat " + seat.getId() + 
				" on flight " + airplane.getFlightNo() + "?\n The cost is " + doubleToCurrency + ".");
		return n;
	}

	/**
	 * Gets the passenger's initials to display instead of their entire name
	 * @return A string containing the passenger's initials
	 */
	public String getInitials(Passenger passenger) {
		if (passenger != null) {
			String firstInitial = passenger.getFirstName().substring(0, 1) + ".";
			String lastInitial = passenger.getLastName().substring(0, 1) + ".";
			return firstInitial + lastInitial; 
		}
		else {
			return "E";
		}
	}

	private void adminInfo() {
		if(passenger.getId() <= 2) {
			this.seatName = "<html>" + String.valueOf(seat.getId()) + "<p>" + getInitials(seat.getPassenger()) + "</html>";
		}
		else {
			this.seatName = "<html>" + String.valueOf(seat.getId()) + "<p>" + "</html>";
		}
	}

	/**
	 * Method to set all information of our SeatButton.
	 * Made this to prevent having duplicate code in each of the constructors. 
	 */
	public void setSeatInfo() {
		//If the seat is occupied then you cannot click the button
		if(!seat.isAvailable()) {
			this.setEnabled(false);
			adminInfo();
		}
		else {
			adminInfo();
		}

		this.setText(seatName);

		if(seat.isExitSeat()) {
			this.setForeground(Color.RED);
		}

		this.addActionListener(event -> {
			if(passenger.canRegister(airplane))
			{
				doubleToCurrency = formatter.format((seat.getPriceFaction() * airplane.getRoute().getPrice()) - passenger.getMembership().getPriceReduction());

				if (seat.isExitSeat()) {
					int n = JOptionPane.showConfirmDialog(null, "This seat is next to an emergency exit."
							+ "\nIn the event of an emergency, the crew may require your assistance.\n" +
							"Is this okay?", "Warning" , JOptionPane.WARNING_MESSAGE);
					if(n == JOptionPane.YES_OPTION){
						if (printConfirmationMessage(passenger, seat, airplane) == JOptionPane.YES_OPTION){
							seat.setPassenger(passenger);
							//The seat is unable to be clicked when somebody has it reserved
							this.setEnabled(false);
							this.seatName = "<html>" + String.valueOf(seat.getId()) + "<p>" + "</html>";
							this.setText(seatName);
							this.setBackground(Color.BLACK);
							//Serialize the Airplane of the Seat after it has been updated
							airplane.serialize();
							new BoardingPassGUI(passenger, seat, airplane, doubleToCurrency);
						}
						else {
							JOptionPane.showMessageDialog(null, "No action taken.");	
						}				

					}
				}
				else {
					if (printConfirmationMessage(passenger, seat, airplane) == JOptionPane.YES_OPTION) {
						//The seat is unable to be clicked when somebody has it reserved
						seat.setPassenger(passenger);
						this.setEnabled(false);
						adminInfo();
						this.setText(seatName);
						this.setBackground(Color.BLACK);
						//Serialize the Airplane of the Seat after it has been updated
						airplane.serialize();
						new BoardingPassGUI(passenger, seat, airplane, doubleToCurrency);
					}
					else {
						JOptionPane.showMessageDialog(null, "No action taken.");				
					}

				}
			}
			//If the passenger cannot register for the plane then give error message
			else {
				JOptionPane.showMessageDialog(null, "You cannot register for this Airplane! The date is too far away, please upgrade your membership to register for this flight.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		});
	}

}
