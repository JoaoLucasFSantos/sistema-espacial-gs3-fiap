package br.edu.missoesespaciais.enums;

/**
 * Representa o ciclo de vida de uma missão espacial.
 *
 * @author Equipe 200%Java
 * @version 1.0
 */
public enum StatusMissao {

    PLANEJADA("Planejada"),
    EM_ANALISE("Em análise"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada");

    private final String descricao;

    StatusMissao(String descricao) {
        this.descricao = descricao;
    }

    /** @return descrição amigável do status */
    public String getDescricao() {
        return descricao;
    }

    /** Imprime todas as opções numeradas para uso em menus. */
    public static void listarOpcoes() {
        for (StatusMissao status : values()) {
            System.out.printf("  %d - %s%n", status.ordinal() + 1, status.descricao);
        }
    }
}
