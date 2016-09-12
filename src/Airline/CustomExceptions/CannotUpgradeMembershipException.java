package Airline.CustomExceptions;

public class CannotUpgradeMembershipException extends Exception {

	//Fields
	private String message;
	
	/**
	 * Alternate constructor, initializes the field
	 * @param message The message of the exception
	 */
	public CannotUpgradeMembershipException(String message) {
		this.message = message;
	}
	
	/**
	 * An accessor method for the private String field message
	 * @return The message of the exception
	 */
	public String getMessage() {
		return this.message;
	}
}
