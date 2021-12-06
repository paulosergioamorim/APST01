package models.converters;

import models.State;
import org.jetbrains.annotations.NotNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<State, String> {

    @Override
    public String convertToDatabaseColumn(@NotNull State state) { return State.valueOf(state); }

    @Override
    public State convertToEntityAttribute(String string) {
        if (Arrays.stream(State.values()).noneMatch(e -> e.getValue().equals(string))) {
            throw new IllegalArgumentException("String inválida");
        }
        return State.getState(string);
    }
}
