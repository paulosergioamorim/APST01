package models;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum State {
    FECHADA("Fechada"), AULAS_ENCERRADAS("Aulas Encerradas"), MATRICULAS_ENCERRADAS("Matriculas Encerradas"), EM_ANDAMENTO("Em Andamento"), MATRICULAS_ABERTAS("Matriculas Abertas");

    private final String value;

    public static final Map<String, State> map;

    static {
        map = new HashMap<>();
        for (State state : State.values())
            map.put(state.value, state);
    }

    State(String value) { this.value = value; }

    public String getValue() { return value; }

    public static State getState(String value) { return map.get(value); }

    public static String valueOf(@NotNull State value) { return value.getValue(); }
}
