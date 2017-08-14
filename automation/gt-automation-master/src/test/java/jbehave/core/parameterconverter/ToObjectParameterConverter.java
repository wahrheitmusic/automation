package jbehave.core.parameterconverter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.ParameterConverters;

import domain.parameters.ParameterObj;

/**
 * Created by Someone on 29.01.2017.
 */
public class ToObjectParameterConverter implements ParameterConverters.ParameterConverter {
    @Override
    public boolean accept(Type type) {
        return ParameterObj.class.isAssignableFrom((Class<?>) type);
    }

    @Override
    public Object convertValue(String value, Type type) {
        ExamplesTable et = (ExamplesTable) new ParameterConverters.ExamplesTableConverter().convertValue(value,
                ExamplesTable.class);
        List<String> headers = et.getHeaders();
        Class clazz = (Class) type;
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String header : headers) {
            Optional<Field> fieldOpt = Stream.of(clazz.getDeclaredFields())
                    .filter(f -> f.getName().replace("_", "").equalsIgnoreCase(header.replace("_", ""))).findFirst();
            if (fieldOpt.isPresent()) {
                    Field field = fieldOpt.get();
                    field.setAccessible(true);
                try {
                    field.set(instance, et.getRow(0).get(header));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }
}
