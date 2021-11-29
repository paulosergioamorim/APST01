package models;

import java.util.Map;

public enum Sexo {
    MASCULINO(0, "Masculino"),
    FEMININO(1, "Feminino");

    private final int value;
    private final String description;

    public static final Map<Integer, Sexo> map;

    static {
        map = new java.util.HashMap<>();
        for (Sexo sexo : Sexo.values()) {
            map.put(sexo.getValue(), sexo);
        }
    }

    Sexo(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() { return value; }

    public String getDescription() { return description; }

    public static Sexo getSexo(int value) { return map.get(value); }

    public static int valueOf(Sexo sexo) { return sexo.value; }

    public static String descriptionOf(Sexo sexo) { return sexo.description; }
}
