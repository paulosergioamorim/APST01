package models.converters;

import models.Sexo;
import org.jetbrains.annotations.NotNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Character> {

    @Override
    public Character convertToDatabaseColumn(@NotNull final Sexo sexo) {
        return Sexo.valueOf(sexo);
    }

    @Override
    public Sexo convertToEntityAttribute(@NotNull final Character aChar) {
        if (Arrays.stream(Sexo.values()).noneMatch(s -> s.getValue().equals(aChar)))
            throw new IllegalArgumentException("Caractere inválido");
        return Sexo.getSexo(aChar);
    }
}
