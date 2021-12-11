package models;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum Sexo {
    MASCULINO('m'),
    FEMININO('f');

    public static final Map<Character, Sexo> map;

    static {
        map = new HashMap<>();
        for (final Sexo sexo : values())
            map.put(sexo.value, sexo);
    }

    private final char value;

    Sexo(final Character character) { this.value = character; }

    public static Sexo getSexo(final Character value) { return map.get(value); }

    public static Character valueOf(@NotNull final Sexo sexo) { return sexo.getValue(); }

    public Character getValue() { return this.value; }

    @Override
    public @NotNull String toString() {
        if (this == MASCULINO)
            return "Masculino";
        if (this == FEMININO)
            return "Feminino";
        return "";
    }
}
