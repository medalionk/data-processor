package ee.bilal.dev.dataprocessor.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlUtil.class);
    private static final String[] DEFAULT_SCHEMES = {"http","https"};

    public static boolean isValidUrl(final String url, final String[] schemes){
        final UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

    public static boolean isValidUrl(final String url){
        return isValidUrl(url, DEFAULT_SCHEMES);
    }
}
