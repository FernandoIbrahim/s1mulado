package com.example.S1mulado.util;

import java.lang.reflect.Field;
import java.util.Arrays;

public class PropertiesUtils {

    /**
     * Returns the names of the properties that have a null value in an object.
     *
     * @param source Origen object.
     * @return an Array of strings with the same name of the null properties.
     */
    public static String[] getNullPropertyNames(Object source) {
        return Arrays.stream(source.getClass().getDeclaredFields())
                .filter(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(source) == null;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .map(Field::getName)
                .toArray(String[]::new);
    }

}
