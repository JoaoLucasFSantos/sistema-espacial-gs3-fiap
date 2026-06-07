package br.edu.missoesespaciais.enums;

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

    public String getDescricao() {
        return descricao;
    }

    public static void listarOpcoes() {
        for (StatusMissao status : values()) {
            System.out.printf("  %d - %s%n", status.ordinal() + 1, status.descricao);
        }
    }
}
