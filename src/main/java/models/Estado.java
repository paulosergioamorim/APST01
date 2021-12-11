package models;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum Estado {
    FECHADA("Fechada"),
    AULAS_ENCERRADAS("Aulas Encerradas"),
    MATRICULAS_ENCERRADAS("Matriculas Encerradas"),
    EM_ANDAMENTO("Em Andamento"),
    MATRICULAS_ABERTAS("Matriculas Abertas");

    private final String value;

    public static final Map<String, Estado> map;

    static {
        map = new HashMap<>();
        for (final Estado estado : values())
            map.put(estado.value, estado);
    }

    Estado(final String value) { this.value = value; }

    public String getValue() { return this.value; }

    public static Estado getState(final String value) { return map.get(value); }

    public static String valueOf(@NotNull final Estado value) { return value.getValue(); }

    @Override
    public @NotNull String toString() {
        if (this == FECHADA) return "Fechada";
        else if (this == AULAS_ENCERRADAS) return "Aulas Encerradas";
        else if (this == EM_ANDAMENTO) return "Em Andamento";
        else if (this == MATRICULAS_ENCERRADAS) return "Matriculas Encerradas";
        else if (this == MATRICULAS_ABERTAS) return "Matriculas Abertas";
        return "";
    }
}
