package models.converters;

import models.Estado;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;

@Converter(autoApply = true)
public class EstadoConverter implements AttributeConverter<Estado, String> {

    @Override
    public String convertToDatabaseColumn(@NotNull Estado estado) { return Estado.valueOf(estado); }

    @Override
    public Estado convertToEntityAttribute(String string) {
        if (Arrays.stream(Estado.values())
                .noneMatch(e -> e.getValue().equals(string))
        ) {
            throw new IllegalArgumentException("String inválida");
        }
        return Estado.getEstado(string);
    }
}
