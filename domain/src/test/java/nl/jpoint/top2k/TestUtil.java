package nl.jpoint.top2k;

import org.junit.Ignore;

import java.lang.reflect.Field;

/** Test util. */
@Ignore
public final class TestUtil {

    /**
     * Gets the private field from the given object.
     * @param object    The object.
     * @param fieldName The field name.
     * @return object The private field value.
     */
    public static Object getPrivateFieldValue(final Object object, final String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new RuntimeException("Could not get private field.", e);
        }
    }
}
