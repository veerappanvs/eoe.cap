package edu.mass.doe.cap.file.io;


/**
 * The Class StorageFileNotFoundException.
 */
public class StorageFileNotFoundException extends StorageException {

    /**
     * Instantiates a new storage file not found exception.
     *
     * @param message the message
     */
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new storage file not found exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}