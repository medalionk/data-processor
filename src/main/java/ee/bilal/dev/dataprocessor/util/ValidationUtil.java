package ee.bilal.dev.dataprocessor.util;

import ee.bilal.dev.dataprocessor.application.exceptions.ConfigurationException;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
public final class ValidationUtil {
    private ValidationUtil() {
        throw new AssertionError();
    }

    public static <T> void validateConfigPropertyNotNull(T property, String name) throws ConfigurationException {
        if (property == null)
        {
            throw new ConfigurationException(String.format("Configurable property '%s' cannot be null.", name));
        }
    }

    public static <T> void validatePropertyNotNull(T t, String name) {
        if (t == null) {
            throw new IllegalArgumentException(String.format("Parameter '%s' cannot be null", name));
        }
    }

    public static <T> void validateEntity(T entity)
    {
        validatePropertyNotNull(entity, "entity");
    }

    public static void validateIdentity(String id)
    {
        validateStringNotNullOrEmpty(id, "Id");
    }

    public static <E> void validateListNotNullOrEmpty(List<E> property, String name) {
        validatePropertyNotNull(property, name);

        if (property.isEmpty()){
            throw new IllegalArgumentException(String.format("Parameter '%s' cannot be empty", name));
        }
    }

    public static void validateStringNotNullOrEmpty(String property, String name) {
        validatePropertyNotNull(property, name);

        if (property.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("Parameter '%s' cannot be empty", name));
        }
    }
}
