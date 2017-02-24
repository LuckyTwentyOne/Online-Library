package ua.nure.butov.summaryTask4.exception;

/**
 * {@code FileException} is thrown when the the error occurs while working with
 * files.
 */
public class FileException extends RuntimeException {
	private static final long serialVersionUID = -4598436193125567448L;

	/**
	 * Creates a new {@code FileException} object with a specified message.
	 *
	 * @param message
	 *            message of the exception
	 */
	public FileException(String message) {
		super(message);
	}

	/**
	 * Creates a new {@code FileException} object with a specified message and
	 * cause.
	 *
	 * @param message
	 *            message of the exception
	 * @param cause
	 *            cause of the exception
	 */
	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates a new {@code FileException} object with a specified cause.
	 *
	 * @param cause
	 *            cause of the exception
	 */
	public FileException(Throwable cause) {
		super(cause);
	}

}
