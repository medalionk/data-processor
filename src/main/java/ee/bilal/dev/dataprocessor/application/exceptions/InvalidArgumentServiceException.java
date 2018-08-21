package ee.bilal.dev.dataprocessor.application.exceptions;

/**
 * Created by bilal90 on 8/19/2018.
 */
public class InvalidArgumentServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;
    private final String fieldName;
    private final String errorMessage;

    public InvalidArgumentServiceException(String fieldName, String errorMessage) {
      super("invalidArgument: field=" + fieldName + " error=" + errorMessage);

      this.fieldName = fieldName;
      this.errorMessage = errorMessage;
    }

    public String getFieldName() {
      return fieldName;
    }

    public String getErrorMessage() {
    return errorMessage;
  }

}
