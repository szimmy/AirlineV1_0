package Airline.GUIClasses;

import javax.swing.*;

import Airline.Driver;
import Airline.Airplane.Airplane;
import Airline.Airplane.TwelveSeatPlane;
import Airline.Passenger.GoldMember;
import Airline.Passenger.Passenger;
import Airline.Passenger.PlatinumMember;
import Airline.Passenger.SilverMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WelcomeGUI extends JFrame {

	private JPanel centerPanel;
	private BorderLayout borderLayout;
	private Dimension minimumSize;
	private ArrayList<Airplane> planes;
	private Passenger passenger;
	private JPanel southPanel;
	private JPanel northPanel;
	private JButton logout;
	private JLabel welcome;
	private JLabel membership;
	private JButton upgradeMembership;
	private JLabel spacing;
	private final String BIG_PLANE = "Images\\big_plane_small.jpg";
	private final String SMALL_PLANE = "Images\\small_plane_small.jpg";
	
	public WelcomeGUI(ArrayList<Airplane> planes, Passenger passenger) {
		
		this.planes = planes;
		this.passenger = passenger;
		
		centerPanel = new JPanel();
		southPanel = new JPanel();
		northPanel = new JPanel();
		spacing = new JLabel();
		borderLayout = new BorderLayout();
		minimumSize = new Dimension(300, 300);
		logout = new JButton("Log Out");
		logout.addActionListener(new LogOutListener());
		welcome = new JLabel("Welcome, " + passenger.getFirstName() + "! Click any button containing flight details to see seating. Current status: ");
		membership = new JLabel("" + passenger.getMembership());
		setMembershipColor();
		
		upgradeMembership = new JButton("Upgrade Membership");
		
		upgradeMembership.addActionListener(event -> {
			passenger.upgradeMembership();
			membership.setText("" + passenger.getMembership());
			setMembershipColor();
			passenger.serialize();
		});
		
		northPanel.setLayout(new FlowLayout());
		northPanel.add(welcome);
		northPanel.add(membership);
		centerPanel.setLayout(new GridLayout(4,4));
		southPanel.setLayout(new FlowLayout());
		
		for (Airplane currentPlane : planes ) {
			if (currentPlane instanceof TwelveSeatPlane) {
				centerPanel.add(new AirplanePanel(currentPlane, BIG_PLANE, passenger));
			}
			else {
				centerPanel.add(new AirplanePanel(currentPlane, SMALL_PLANE, passenger));
			}
		}
		
		southPanel.add(upgradeMembership);
		southPanel.add(logout);

		this.setLayout(borderLayout);
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(spacing, BorderLayout.EAST);
		this.add(spacing, BorderLayout.WEST);
		this.setTitle("Available Flights");
		this.pack();
		this.setVisible(true);
		this.setMinimumSize(minimumSize);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void close() {
		new LoginGUI();
		this.dispose();
	}
	
	private void setMembershipColor() {
			if(passenger.getMembership() instanceof PlatinumMember) {
				membership.setForeground(new Color(0, 191, 255));
			}
			else if(passenger.getMembership() instanceof GoldMember) {
				membership.setForeground(new Color(255, 215, 0));
			}
			else if(passenger.getMembership() instanceof SilverMember) {
				membership.setForeground(new Color(192, 192, 192));
			}
			else {
				membership.setForeground(new Color(139, 131, 120));
			}
			
	}
	
	private class LogOutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?",
					"Warning" , JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.YES_OPTION) {
				close();
			}
			else {
				System.out.println("User chose not to log out");
			}			
		}
		
	}
		
}
