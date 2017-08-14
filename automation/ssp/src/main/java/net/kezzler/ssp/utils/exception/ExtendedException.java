package net.kezzler.ssp.utils.exception;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.assertj.core.error.MessageFormatter;

import static java.util.Optional.ofNullable;

public abstract class ExtendedException extends RuntimeException {
    private static final MessageFormatter formatter = MessageFormatter.instance();
    List<String> collectedErrors;

    public ExtendedException() {

    }

    public ExtendedException(String message) {
        super(message);

    }

    public ExtendedException(Throwable cause) {
        super(cause);

    }

    public ExtendedException(String message, Throwable cause) {
        super(message, cause);

    }

    public ExtendedException(String msg, String... args) {
        super(String.format(msg, args));
    }

    public List<String> getCollectedErrors() {
        return collectedErrors;
    }

    public void collectFailedCondition(final boolean conditionResult, final String message) {
        if (!conditionResult) {
            this.collectedErrors.add(message);
        }
    }

    public void throwIf(final boolean conditionResult) {
        if (conditionResult) {
            throw this;
        }
    }

    public void throwAllCollectedErrors() {
        if (!this.getCollectedErrors().isEmpty()) {
            setNewFieldValue("detailMessage", createMessage());
            throw this;
        }
    }

    private String createMessage() {
        StringBuilder msg = new StringBuilder("%nThe following ");
        int size = collectedErrors.size();
        msg = size == 1 ? msg.append("assertion") : msg.append(size).append(" assertions");
        msg.append(" failed:%n");
        for (int i = 0; i < size; i++) {
            msg.append(i + 1).append(") ").append(collectedErrors.get(i)).append("%n");
        }
        return formatter.format(null, null, msg.toString());
    }

    private void setNewFieldValue(final String fieldName, final String value) {
        List<Field> fieldList = new ArrayList<>();
        addDeclaredAndInheritedFields(this.getClass(), fieldList);
        Field expectedField = ofNullable(
                fieldList.stream().filter(field -> field.getName().equals(fieldName)).findFirst())
                        .orElseThrow(() -> new IncompatibleDataException(
                                String.format("Field %s is not present in class %s and in its parent classes ",
                                        fieldName, this.getClass().getName())))
                        .get();
        expectedField.setAccessible(true);
        try {
            expectedField.set(this, value);
        } catch (IllegalAccessException e) {
            throw new IncompatibleDataException(String.format("% field is not accessible", expectedField));
        }
    }

    private void addDeclaredAndInheritedFields(final Class<?> c, final Collection<Field> fields) {
        fields.addAll(Arrays.asList(c.getDeclaredFields()));
        Class<?> superClass = c.getSuperclass();
        if (superClass != null) {
            addDeclaredAndInheritedFields(superClass, fields);
        }
    }

}
