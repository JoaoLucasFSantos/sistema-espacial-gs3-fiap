package br.edu.missoesespaciais.enums;

public enum Ods {

    ODS_2 (2,  "Fome Zero e Agricultura Sustentável"),
    ODS_3 (3,  "Saúde e Bem-Estar"),
    ODS_9 (9,  "Indústria, Inovação e Infraestrutura"),
    ODS_11(11, "Cidades e Comunidades Sustentáveis"),
    ODS_13(13, "Ação Contra a Mudança Global do Clima"),
    ODS_15(15, "Vida Terrestre");

    private final int numero;
    private final String descricao;

    Ods(int numero, String descricao) {
        this.numero   = numero;
        this.descricao = descricao;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescricao() {
        return String.format("ODS %d — %s", numero, descricao);
    }

    public static void listarOpcoes() {
        for (Ods ods : values()) {
            System.out.printf("  %d - ODS %-2d : %s%n", ods.ordinal() + 1, ods.numero, ods.descricao);
        }
    }
}