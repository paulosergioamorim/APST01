package models.converters;

import models.Estado;
import org.jetbrains.annotations.NotNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class EstadoConverter implements AttributeConverter<Estado, String> {

    @Override
    public String convertToDatabaseColumn(@NotNull final Estado estado) { return Estado.valueOf(estado); }

    @Override
    public Estado convertToEntityAttribute(final String string) {
        if (Arrays.stream(Estado.values()).noneMatch(e -> e.getValue().equals(string))) {
            throw new IllegalArgumentException("String inválida");
        }
        return Estado.getState(string);
    }
}
