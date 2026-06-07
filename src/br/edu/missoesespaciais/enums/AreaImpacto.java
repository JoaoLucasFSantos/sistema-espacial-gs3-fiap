package br.edu.missoesespaciais.enums;

/**
 * Áreas da sociedade que podem ser beneficiadas pelas missões espaciais.
 *
 * @author Equipe 200%Java
 * @version 1.0
 */
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

    /** @return descrição amigável da área */
    public String getDescricao() {
        return descricao;
    }

    /** Imprime todas as opções numeradas para uso em menus. */
    public static void listarOpcoes() {
        for (AreaImpacto area : values()) {
            System.out.printf("  %d - %s%n", area.ordinal() + 1, area.descricao);
        }
    }
}