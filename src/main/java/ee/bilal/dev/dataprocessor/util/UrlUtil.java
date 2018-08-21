package ee.bilal.dev.dataprocessor.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtil {

    private static final String[] DEFAULT_SCHEMES = {"http","https"};

    /**
     * Check if given string is a valid url
     * @param url to validate
     * @param schemes http schemes to validate with
     * @return true if valid and false otherwise
     */
    public static boolean isValidUrl(final String url, final String[] schemes){
        final UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

    /**
     * Check if given string is a valid url using default http schemes
     * @param url to validate
     * @return true if valid and false otherwise
     */
    public static boolean isValidUrl(final String url){
        return isValidUrl(url, DEFAULT_SCHEMES);
    }

}
