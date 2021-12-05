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
        for (Estado estado : Estado.values())
            map.put(estado.value, estado);
    }

    Estado(String value) { this.value = value; }

    public String getValue() { return value; }

    public static Estado getEstado(String value) { return map.get(value); }

    public static String valueOf(@NotNull Estado value) { return value.getValue(); }
}
