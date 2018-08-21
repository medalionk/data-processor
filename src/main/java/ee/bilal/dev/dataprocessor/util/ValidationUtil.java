package ee.bilal.dev.dataprocessor.util;

/**
 * Created by bilal90 on 8/19/2018.
 */
public final class ValidationUtil {

    private ValidationUtil() {
        throw new AssertionError();
    }

    /**
     * Validate that given property is not null
     * @param t property to validate
     * @param name of property
     * @param <T> property type
     */
    public static <T> void validatePropertyNotNull(T t, String name) {
        if (t == null) {
            throw new IllegalArgumentException(String.format("Parameter '%s' cannot be null", name));
        }
    }

    /**
     * Validate that given entity is not null
     * @param entity to validate
     * @param <T> entity type
     */
    public static <T> void validateEntity(T entity)
    {
        validatePropertyNotNull(entity, "entity");
    }

    /**
     * Validate that given string identity is not null or empty
     * @param id string identity
     */
    public static void validateIdentity(String id)
    {
        validateStringNotNullOrEmpty(id, "Id");
    }

    /**
     * Validate that string property is null or empty
     * @param property to validate
     * @param name of property
     */
    public static void validateStringNotNullOrEmpty(String property, String name) {
        if (StringUtil.isNullOrEmpty(property)) {
            throw new IllegalArgumentException(String.format("Parameter '%s' cannot be empty", name));
        }
    }

}
