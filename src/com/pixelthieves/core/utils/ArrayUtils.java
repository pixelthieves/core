package com.pixelthieves.core.utils;

import java.lang.reflect.Array;

/**
 * Created by Tomas on 9/23/13.
 */
public class ArrayUtils {

    public static void copyArray(Object source, Object dest) {
        if (source.getClass().isArray() && dest.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(source); i++) {
                if (Array.get(source, i) != null && Array.get(source, i).getClass().isArray()) {
                    copyArray(Array.get(source, i), Array.get(dest, i));
                } else {
                    Array.set(dest, i, Array.get(source, i));
                }
            }
        }
    }

    public <E> E copyArray(E source) {
        // TODO look into this
        E dest = null;
       /* try {
            dest = new SomeContainer<E>().createInstance(source.getClass());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }                     */
        if (source.getClass().isArray() && dest.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(source); i++) {
                if (Array.get(source, i) != null && Array.get(source, i).getClass().isArray()) {
                    copyArray(Array.get(source, i), Array.get(dest, i));
                } else {
                    Array.set(dest, i, Array.get(source, i));
                }
            }
        }
        return null;
    }

    private static class SomeContainer<E> {
        E createInstance(Class<E> clazz) throws IllegalAccessException, InstantiationException {
            return clazz.newInstance();
        }
    }
}
