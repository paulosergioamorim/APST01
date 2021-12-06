package models.converters;

import org.jetbrains.annotations.NotNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class EstadoConverter implements AttributeConverter<models.Estado, String> {

    @Override
    public String convertToDatabaseColumn(@NotNull models.Estado estado) { return models.Estado.valueOf(estado); }

    @Override
    public models.Estado convertToEntityAttribute(String string) {
        if (Arrays.stream(models.Estado.values()).noneMatch(e -> e.getValue().equals(string))) {
            throw new IllegalArgumentException("String inválida");
        }
        return models.Estado.getState(string);
    }
}
