package edu.mass.doe.cap.file.io;



/**
 * The Class StorageException.
 */
public class StorageException extends RuntimeException {

    /**
     * Instantiates a new storage exception.
     *
     * @param message the message
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * Instantiates a new storage exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
