package jbehave.core.parameterconverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import org.jbehave.core.steps.ParameterConverters;

import domain.models.json.JsonModel;

/**
 * Created by Someone on 15.01.2017.
 */
public class JsonParameterConverter implements ParameterConverters.ParameterConverter {

    private String examplesLocation = System.getProperty("examples.location");

    @Override
    public boolean accept(Type type) {
        return JsonModel.class.isAssignableFrom((Class) type);
    }

    @Override
    public Object convertValue(String value, Type type) {
        try {
            return new Gson().fromJson(new String(Files.readAllBytes(Paths.get(examplesLocation + value))), type);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file " + value, e);
        }
    }
}
