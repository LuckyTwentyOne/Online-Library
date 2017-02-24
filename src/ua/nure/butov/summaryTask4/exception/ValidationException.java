package ua.nure.butov.summaryTask4.exception;


/**
 * {@code ValidationException} is thrown when the the 
 * error is caused by user's incorrect input.
 * 
 * @author V.Butov
 */
public class ValidationException extends Exception {
	private static final long serialVersionUID = -2118427909075744449L;

	/**
	 * Creates a new {@code UsersException} object with a specified message.
	 *
	 * @param message
	 *            message of the exception
	 */
	public ValidationException(String message) {
		super(message);
	}
}
