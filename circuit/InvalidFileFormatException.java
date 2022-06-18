/**
 * Indicates a file formatting exception.
 * @author mvail AntonLeslie
 */
@SuppressWarnings("serial")
public class InvalidFileFormatException extends RuntimeException {
	public InvalidFileFormatException(String msg) {
		super(msg);
	}
}
