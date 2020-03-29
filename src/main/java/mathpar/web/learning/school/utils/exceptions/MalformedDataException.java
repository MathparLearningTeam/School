package mathpar.web.learning.school.utils.exceptions;

public class MalformedDataException extends RuntimeException {
    public MalformedDataException() {
    }

    public MalformedDataException(String format) {
        super(format);
    }
}
