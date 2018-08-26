package ee.bilal.dev.dataprocessor.util;

/**
 * Created by bilal90 on 8/19/2018.
 */
public final class StringUtil {

    private StringUtil() {
        throw new AssertionError();
    }

    /**
     * Check if string property is null or empty
     * @param property to validate
     * @return true if null or empty
     */
    public static boolean isNullOrEmpty(String property){
        return property == null || property.trim().isEmpty();
    }

}
