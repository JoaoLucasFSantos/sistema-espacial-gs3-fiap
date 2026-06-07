package br.edu.missoesespaciais.enums;

public enum AreaImpacto {

    AGRICULTURA("Agricultura"),
    CLIMA("Clima"),
    DESASTRES_NATURAIS("Desastres Naturais"),
    SAUDE("Saúde"),
    CONECTIVIDADE("Conectividade"),
    RECURSOS_NATURAIS("Recursos Naturais"),
    EXPLORACAO_ESPACIAL("Exploração Espacial");

    private final String descricao;

    AreaImpacto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static void listarOpcoes() {
        for (AreaImpacto area : values()) {
            System.out.printf("  %d - %s%n", area.ordinal() + 1, area.descricao);
        }
    }
}