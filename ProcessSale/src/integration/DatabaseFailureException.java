package integration;

/**
 * This exception is thrown when our hardcoded failure happens to the database.
 */
public class DatabaseFailureException extends RuntimeException{

    /**
     * This is the constructor of the exception.
     * @param errorMessage This is the message printed when the exception occurs.
     */
    public DatabaseFailureException(String errorMessage){
        super(errorMessage);
    }
}
