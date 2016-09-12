/**
 * RouteInfoPanel.java is a subclass of JPanel which includes 2 JLabels put into a GridLayout to 
 * help display the information of a Route which belongs to an Airplane.
 * @author Sean Zimmerman, Matthew Gimbut, Phong Tran
 * @version 2015.11.21
 */

package Airline.GUIClasses;

import java.awt.GridLayout;
import java.time.LocalDateTime;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class RouteInfoPanel extends JPanel {

	//Fields
	private JLabel locationInfo;
	private JLabel timeInfo;
	
	/**
	 * Alternate Constructor which defines all the fields
	 * @param locationInfo The String to be set as the text of locationInfo
	 * @param timeInfo The String to be set as the text of timeInfo
	 */
	public RouteInfoPanel(String locationInfo, String timeInfo) {
		//Initiate the fields
		this.locationInfo = new JLabel(locationInfo);
		this.timeInfo = new JLabel(timeInfo);
		
		//Add them to the Panel
		this.setLayout(new GridLayout(2,1));
		
		this.add(this.locationInfo);
		this.add(this.timeInfo);
	}
}
