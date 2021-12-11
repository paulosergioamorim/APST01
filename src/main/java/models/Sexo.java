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
        for (Sexo sexo : Sexo.values())
            map.put(sexo.value, sexo);
    }

    private final char value;

    Sexo(Character value) { this.value = value; }

    public static Sexo getSexo(Character value) { return map.get(value); }

    public static Character valueOf(@NotNull Sexo sexo) { return sexo.getValue(); }

    public Character getValue() { return value; }

    @Override
    public @NotNull String toString() {
        if (this == MASCULINO)
            return "Masculino";
        if (this == FEMININO)
            return "Feminino";
        return "";
    }
}
