package error;

public class ImportException extends RuntimeException {
    public ImportException(String message, Exception exception) {
        super(message, exception);
    }
}
