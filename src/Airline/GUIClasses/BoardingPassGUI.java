package Airline.GUIClasses;

import javax.swing.*;
import Airline.Airplane.Airplane;
import Airline.Passenger.Passenger;
import Airline.Seat.Seat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardingPassGUI extends JFrame {
	
	private JPanel centerPanel;
	private JPanel spacing;
	private JPanel southPanel;
	private JTextArea boardingInfo;
	private JScrollPane scrollPane;
	private JButton close;
	private Timer t;
	private final int INTERVAL = 1;
	private JProgressBar printBar;
	private int percent = 0;
	
	public BoardingPassGUI(Passenger passenger, Seat seat, Airplane airplane, String cost) {
		this.setLayout(new BorderLayout());
		centerPanel = new JPanel();
		southPanel = new JPanel();
		spacing = new JPanel();
		boardingInfo = new JTextArea(passenger.getLastName() + ", " + passenger.getFirstName() + " " + passenger.getMiddleName() + "\n\n");
		boardingInfo.setEditable(false);
		printBar = new JProgressBar(0,100);
		printBar.setValue(0);
		printBar.setStringPainted(true);
		close = new JButton("Close");
		
		t = new Timer(INTERVAL, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				t.setDelay(75);
				if(percent==100) {
					t.stop();
					close.setEnabled(true);
					printBar.setValue(percent);
					printBar.setString("Printing complete!");
				}
				else {
					percent++;
					printBar.setValue(percent);
					printBar.setString("Printing is " + percent + "% complete.");
				}
			}
		});
		
		close.setEnabled(false);
		close.addActionListener(event -> this.dispose());
		southPanel.setLayout(new GridLayout(0,1));
		

		boardingInfo.append("----------------------------------------\n");
		boardingInfo.append("Airplane " + airplane.getFlightNo() + "\n");
		boardingInfo.append("From: " + airplane.getRoute().getDeparture() + "\n");
		boardingInfo.append("To: " + airplane.getRoute().getDestination() + "\n");
		boardingInfo.append("Departure time: " + airplane.getRoute().getDepartTime() + "\n");
		boardingInfo.append("Landing time: " + airplane.getRoute().getLandingTime() + "\n");
		boardingInfo.append(seat  + "\n");
		boardingInfo.append("Seat " + seat.getId() + "\n");
		boardingInfo.append(cost + "\n");
		boardingInfo.append("----------------------------------------\n\n");
			
		scrollPane = new JScrollPane(boardingInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(scrollPane);
		
		southPanel.add(printBar);
		southPanel.add(close);
				
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(spacing, BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(300, 475);
		this.setMinimumSize(new Dimension(300, 400));
		this.setMaximumSize(new Dimension(300, 550));
		this.pack();
		this.setTitle("Boarding Pass");
		this.setVisible(true);
		
		t.start();		
	}
	
	

}
