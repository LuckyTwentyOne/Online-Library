package ua.nure.butov.summaryTask4.exception;


/**
 * {@code UsersException} is thrown when the the error is caused by User.
 */
public class UsersException extends Exception {
	private static final long serialVersionUID = -7299067858182536733L;

	/**
	 * Creates a new {@code UsersException} object with a specified message.
	 *
	 * @param message
	 *            message of the exception
	 */
	public UsersException(String message) {
		super(message);
	}
}
