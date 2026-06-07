package br.edu.missoesespaciais.ui;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.Ods;
import br.edu.missoesespaciais.model.Missao;
import br.edu.missoesespaciais.service.RelatorioService;

import java.util.Scanner;

/**
 * Menu de relatórios e estatísticas do sistema.
 */
public class RelatorioMenu {

    private final Scanner scanner;
    private final RelatorioService relatorioService;

    public RelatorioMenu(RelatorioService relatorioService) {
        this.scanner          = new Scanner(System.in);
        this.relatorioService = relatorioService;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n" + "=".repeat(45));
            System.out.println("            RELATÓRIOS");
            System.out.println("=".repeat(45));
            System.out.println("  1 - Total de missões");
            System.out.println("  2 - Missão mais prioritária");
            System.out.println("  3 - Quantidade por status");
            System.out.println("  4 - Área mais utilizada");
            System.out.println("  5 - ODS mais utilizado");
            System.out.println("  6 - Resumo geral");
            System.out.println("  0 - Voltar");
            System.out.println("=".repeat(45));
            System.out.print("  Opção: ");

            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    private void processarOpcao(int opcao) {
        System.out.println();
        switch (opcao) {
            case 1:
                System.out.printf("  Total de missões cadastradas: %d%n",
                        relatorioService.totalMissoes());
                break;

            case 2:
                Missao prioritaria = relatorioService.missaoMaisPrioritaria();
                if (prioritaria != null) {
                    System.out.println("  Missão mais prioritária:");
                    System.out.println("  " + prioritaria.gerarResumo());
                } else {
                    System.out.println("  Nenhuma missão cadastrada.");
                }
                break;

            case 3:
                relatorioService.quantidadePorStatus();
                break;

            case 4:
                AreaImpacto area = relatorioService.areaMaisUtilizada();
                System.out.printf("  Área mais utilizada: %s%n",
                        area != null ? area.getDescricao() : "nenhuma missão cadastrada");
                break;

            case 5:
                Ods ods = relatorioService.odsMaisUtilizado();
                System.out.printf("  ODS mais utilizado: %s%n",
                        ods != null ? ods.getDescricao() : "nenhuma missão cadastrada");
                break;

            case 6:
                relatorioService.resumoGeral();
                break;

            case 0:
                break;

            default:
                System.out.println("  Opção inválida.");
        }
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
