package br.edu.missoesespaciais.enums;

/**
 * Objetivos de Desenvolvimento Sustentável da ONU relacionados às missões.
 *
 * @author Equipe 200%Java
 * @version 1.0
 */
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
        this.numero    = numero;
        this.descricao = descricao;
    }

    /** @return número oficial do ODS */
    public int getNumero() {
        return numero;
    }

    /** @return descrição formatada no padrão "ODS N — Descrição" */
    public String getDescricao() {
        return String.format("ODS %d — %s", numero, descricao);
    }

    /** Imprime todas as opções numeradas para uso em menus. */
    public static void listarOpcoes() {
        for (Ods ods : values()) {
            System.out.printf("  %d - ODS %-2d : %s%n", ods.ordinal() + 1, ods.numero, ods.descricao);
        }
    }
}