package au.com.anthonybruno.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {

    @SuppressWarnings({"unchecked"})
    public static <T> T buildWithNoArgConstructor(Class<T> c) {
        for (Constructor<?> constructor : c.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 0)  {
                try {
                    return (T) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Couldn't construct a new object of type: " + c + " using it's 0 args constructor!", e);
                }
            }
        }

        throw new IllegalArgumentException("No 0 args constructor found in class: " + c);
    }
}
