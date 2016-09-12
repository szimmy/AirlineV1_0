package Airline.GUIClasses;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Airline.Airplane.Airplane;
import Airline.Passenger.Passenger;

public class AirplanePanel extends JPanel {

	private JLabel image;
	private JButton information;
	private Passenger passenger;
	
	public AirplanePanel(Airplane plane, String image, Passenger passenger) {
		
		this.image = new JLabel(new ImageIcon(image));
		this.information = new JButton(plane.getRoute().getFormattedInformation());
		information.addActionListener(new SelectPlaneListener(plane, passenger));
		
		this.setLayout(new GridLayout(1,2));
		
		this.add(this.image);
		this.add(this.information);
	}
	
	private class SelectPlaneListener implements ActionListener {

		private Airplane plane;
		private Passenger passenger;
		
		public SelectPlaneListener(Airplane plane, Passenger passenger) {
			this.plane = plane;
			this.passenger = passenger;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			new AirplaneFrame(plane, passenger);
			
		}
		
	}

}
