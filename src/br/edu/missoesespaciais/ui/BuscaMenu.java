package br.edu.missoesespaciais.ui;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.model.Missao;
import br.edu.missoesespaciais.service.MissaoService;

import java.util.List;
import java.util.Scanner;

/**
 * Menu de consultas e alteração de status de missões.
 *
 * @author 200%Java
 * @version 1.0
 */
public class BuscaMenu {

    private final Scanner scanner;
    private final MissaoService missaoService;

    /**
     * @param scanner       scanner compartilhado com o menu principal
     * @param missaoService serviço de missões
     */
    public BuscaMenu(Scanner scanner, MissaoService missaoService) {
        this.scanner       = scanner;
        this.missaoService = missaoService;
    }

    /** Exibe o submenu de busca (por nome ou por área). */
    public void exibir() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("          BUSCA DE MISSÕES");
        System.out.println("=".repeat(45));
        System.out.println("  1 - Buscar por nome");
        System.out.println("  2 - Buscar por área de impacto");
        System.out.println("  0 - Voltar");
        System.out.print("  Opção: ");

        switch (scanner.nextLine().trim()) {
            case "1": buscarPorNome(); break;
            case "2": buscarPorArea(); break;
            case "0":                  break;
            default:
                System.out.println("  Opção inválida.");
        }
    }

    /** Lista todas as missões cadastradas. */
    public void listarTodas() {
        List<Missao> missoes = missaoService.listarMissoes();

        System.out.println("\n" + "=".repeat(45));
        System.out.println("        TODAS AS MISSÕES");
        System.out.println("=".repeat(45));

        if (missoes.isEmpty()) {
            System.out.println("  Nenhuma missão cadastrada.");
            return;
        }
        for (Missao m : missoes) {
            System.out.println("  " + m);
        }
        System.out.printf("%n  Total: %d missão(ões).%n", missoes.size());
    }

    /** Exibe formulário para alterar o status de uma missão pelo ID. */
    public void alterarStatus() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("       ALTERAR STATUS DE MISSÃO");
        System.out.println("=".repeat(45));

        listarTodas();
        if (missaoService.quantidadeMissoes() == 0) return;

        System.out.print("\n  ID da missão: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  ID inválido.");
            return;
        }

        Missao missao = missaoService.buscarPorId(id);
        if (missao == null) {
            System.out.println("  Missão não encontrada.");
            return;
        }

        System.out.printf("  Missão      : %s%n", missao.getNome());
        System.out.printf("  Status atual: %s%n%n", missao.getStatus().getDescricao());
        System.out.println("  Novo status:");
        StatusMissao.listarOpcoes();
        System.out.print("  Opção: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            if (opcao < 1 || opcao > StatusMissao.values().length) {
                System.out.println("  Opção inválida.");
                return;
            }
            StatusMissao novoStatus = StatusMissao.values()[opcao - 1];
            System.out.println("\n  " + missaoService.alterarStatus(id, novoStatus));
        } catch (NumberFormatException e) {
            System.out.println("  Entrada inválida.");
        }
    }

    // -------------------------------------------------------------------------
    // Buscas
    // -------------------------------------------------------------------------

    private void buscarPorNome() {
        System.out.print("\n  Nome (parcial ou completo): ");
        exibirResultados(missaoService.buscarPorNome(scanner.nextLine().trim()));
    }

    private void buscarPorArea() {
        System.out.println("\n  Área de impacto:");
        AreaImpacto.listarOpcoes();
        System.out.print("  Opção: ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            if (opcao < 1 || opcao > AreaImpacto.values().length) {
                System.out.println("  Opção inválida.");
                return;
            }
            exibirResultados(missaoService.buscarPorArea(AreaImpacto.values()[opcao - 1]));
        } catch (NumberFormatException e) {
            System.out.println("  Entrada inválida.");
        }
    }

    private void exibirResultados(List<Missao> missoes) {
        System.out.println();
        if (missoes.isEmpty()) {
            System.out.println("  Nenhuma missão encontrada.");
            return;
        }
        for (Missao m : missoes) {
            System.out.println("  " + m.gerarResumo());
        }
        System.out.printf("%n  %d missão(ões) encontrada(s).%n", missoes.size());
    }
}
