package ee.bilal.dev.dataprocessor.application.exceptions;

/**
 * Created by bilal90 on 8/19/2018.
 */
public class InternalErrorServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalErrorServiceException() {
      this("Internal Error");
    }

    public InternalErrorServiceException(String message) {
      super(message);
    }

    public InternalErrorServiceException(String message, Throwable cause) {
      super(message, cause);
    }

    public InternalErrorServiceException(Throwable cause) {
      super(cause);
    }

}
