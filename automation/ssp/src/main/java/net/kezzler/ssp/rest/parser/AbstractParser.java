package net.kezzler.ssp.rest.parser;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public abstract class AbstractParser {

    public static <T extends Object> T fromJson(final String json, final Class<T> returnType) {
        return new Gson().fromJson(json, returnType);
    }

    public static <T extends Object> T fromJson(final String json, final Type type) {
        return new Gson().fromJson(json, type);
    }

    public static String toJson(final Object o) {
        return new Gson().toJson(o);
    }

    public static String toJson(final Object o, final Type type) {
        return new Gson().toJson(o, type);
    }
}
