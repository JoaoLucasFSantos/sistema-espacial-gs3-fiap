package br.edu.missoesespaciais.ui;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.Ods;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.enums.TipoTecnologia;
import br.edu.missoesespaciais.service.MissaoService;

import java.util.Scanner;

/**
 * Coleta os dados necessários para cadastrar uma missão espacial.
 *
 * @author 200%Java
 * @version 1.0
 */
public class CadastroMenu {

    private final Scanner scanner;
    private final MissaoService missaoService;

    /**
     * @param scanner       scanner compartilhado com o menu principal
     * @param missaoService serviço de missões
     */
    public CadastroMenu(Scanner scanner, MissaoService missaoService) {
        this.scanner       = scanner;
        this.missaoService = missaoService;
    }

    /** Exibe o formulário de cadastro e envia os dados ao serviço. */
    public void exibir() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("         CADASTRO DE MISSÃO");
        System.out.println("=".repeat(45));

        String nome      = lerTexto("  Nome da missão     : ");
        String objetivo  = lerTexto("  Objetivo           : ");
        String agencia   = lerTexto("  Agência responsável: ");
        String local     = lerTexto("  Local de operação  : ");

        AreaImpacto    area      = lerAreaImpacto();
        Ods            ods       = lerOds();
        TipoTecnologia tecnologia = lerTipoTecnologia();
        StatusMissao   status    = lerStatus();
        int            prioridade = lerPrioridade();
        int            duracao   = lerInteiro("  Duração (meses)    : ");
        double         custo     = lerDecimal("  Custo est. (milhões R$): ");

        String resultado = missaoService.cadastrarMissao(
                nome, objetivo, area, ods, tecnologia,
                prioridade, status, agencia, local, duracao, custo
        );
        System.out.println("\n  " + resultado);
    }

    // -------------------------------------------------------------------------
    // Leitura de enums
    // -------------------------------------------------------------------------

    private AreaImpacto lerAreaImpacto() {
        System.out.println("\n  Área de impacto:");
        AreaImpacto.listarOpcoes();
        return AreaImpacto.values()[lerOpcaoEnum("  Opção: ", AreaImpacto.values().length) - 1];
    }

    private Ods lerOds() {
        System.out.println("\n  ODS relacionado:");
        Ods.listarOpcoes();
        return Ods.values()[lerOpcaoEnum("  Opção: ", Ods.values().length) - 1];
    }

    private TipoTecnologia lerTipoTecnologia() {
        System.out.println("\n  Tipo de tecnologia:");
        TipoTecnologia.listarOpcoes();
        return TipoTecnologia.values()[lerOpcaoEnum("  Opção: ", TipoTecnologia.values().length) - 1];
    }

    private StatusMissao lerStatus() {
        System.out.println("\n  Status inicial:");
        StatusMissao.listarOpcoes();
        return StatusMissao.values()[lerOpcaoEnum("  Opção: ", StatusMissao.values().length) - 1];
    }

    private int lerPrioridade() {
        System.out.println("\n  Prioridade (1 = mais alta / 5 = mais baixa):");
        return lerOpcaoEnum("  Opção: ", 5);
    }

    // -------------------------------------------------------------------------
    // Leitura de tipos primitivos
    // -------------------------------------------------------------------------

    private String lerTexto(String mensagem) {
        String valor;
        do {
            System.out.print(mensagem);
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) System.out.println("  Campo obrigatório.");
        } while (valor.isEmpty());
        return valor;
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= 0) return valor;
                System.out.println("  Informe um valor positivo.");
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private double lerDecimal(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                if (valor >= 0) return valor;
                System.out.println("  Informe um valor positivo.");
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida. Digite um número.");
            }
        }
    }

    /**
     * Lê uma opção numérica no intervalo [1, max], repetindo até entrada válida.
     *
     * @param mensagem prompt exibido ao usuário
     * @param max      valor máximo aceito
     * @return opção válida escolhida pelo usuário
     */
    private int lerOpcaoEnum(String mensagem, int max) {
        while (true) {
            System.out.print(mensagem);
            try {
                int opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= 1 && opcao <= max) return opcao;
                System.out.printf("  Informe um número entre 1 e %d.%n", max);
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida. Digite um número.");
            }
        }
    }
}