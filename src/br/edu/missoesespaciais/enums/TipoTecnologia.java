package br.edu.missoesespaciais.enums;

/**
 * Principal tecnologia utilizada em uma missão espacial.
 *
 * @author Equipe 200%Java
 * @version 1.0
 */
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

    /** @return descrição amigável da tecnologia */
    public String getDescricao() {
        return descricao;
    }

    /** Imprime todas as opções numeradas para uso em menus. */
    public static void listarOpcoes() {
        for (TipoTecnologia tipo : values()) {
            System.out.printf("  %d - %s%n", tipo.ordinal() + 1, tipo.descricao);
        }
    }
}
