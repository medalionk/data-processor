package ee.bilal.dev.dataprocessor.application.exceptions;

/**
 * Created by bilal90 on 8/19/2018.
 */
public class ConfigurationException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConfigurationException() {
        super("Configuration error!");
    }

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
