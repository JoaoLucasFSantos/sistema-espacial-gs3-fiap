package br.edu.missoesespaciais.enums;

public enum TipoTecnologia {

    SATELITE("Satélite"),
    SENSOR_ORBITAL("Sensor Orbital"),
    IA_EMBARCADA("Inteligência Artificial Embarcada"),
    ESTACAO_ESPACIAL("Estação Espacial"),
    TELECOMUNICACAO("Telecomunicação"),
    DRONE_INTEGRADO("Drone Integrado");

    private final String descricao;

    TipoTecnologia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static void listarOpcoes() {
        for (TipoTecnologia tipo : values()) {
            System.out.printf("  %d - %s%n", tipo.ordinal() + 1, tipo.descricao);
        }
    }
}
