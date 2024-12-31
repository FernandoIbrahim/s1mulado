package com.example.S1mulado.util;

import java.lang.reflect.Field;
import java.util.Arrays;

public class PropertiesUtils {

    /**
     * Retorna os nomes das propriedades que possuem valor null em um objeto.
     *
     * @param source Objeto de origem.
     * @return Array de strings com os nomes das propriedades nulas.
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
