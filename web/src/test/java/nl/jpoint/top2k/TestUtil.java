package nl.jpoint.top2k;

import org.junit.Ignore;

import java.lang.reflect.Field;

/** Test util. */
@Ignore
public final class TestUtil {

    /**
     * Insert a value into an object.
     * @param object    The object.
     * @param fieldName The name of the field.
     * @param value     The value.
     * @return <code>true</code> if inserted, else <code>false</code>.
     */
    public static void insert(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException("Could not insert object for field.", e);
        }
    }
}
