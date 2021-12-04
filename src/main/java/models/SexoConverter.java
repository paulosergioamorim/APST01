package models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Character> {

    @Override
    public Character convertToDatabaseColumn(@NotNull Sexo sexo) {
        if (Arrays.stream(Sexo.values()).noneMatch(s -> s.equals(sexo)))
            throw new IllegalArgumentException("Sexo inválido");
        return Sexo.valueOf(sexo);
    }

    @Override
    public Sexo convertToEntityAttribute(@NotNull Character aChar) {
        if (Arrays.stream(Sexo.values()).noneMatch(s -> s.getValue().equals(aChar)))
            throw new IllegalArgumentException("Caractere inválido");
        return Sexo.getSexo(aChar);
    }
}
