package br.edu.missoesespaciais.ui;

import br.edu.missoesespaciais.service.MissaoService;
import br.edu.missoesespaciais.service.RelatorioService;

import java.util.Scanner;

/**
 * Ponto de entrada da interface. Controla o fluxo geral do sistema.
 *
 * @author 200%Java
 * @version 1.0
 */
public class MenuPrincipal {

    private final Scanner scanner;
    private final CadastroMenu cadastroMenu;
    private final BuscaMenu buscaMenu;
    private final RelatorioMenu relatorioMenu;

    /**
     * @param missaoService    serviço de missões
     * @param relatorioService serviço de relatórios
     */
    public MenuPrincipal(MissaoService missaoService, RelatorioService relatorioService) {
        this.scanner       = new Scanner(System.in);
        this.cadastroMenu  = new CadastroMenu(scanner, missaoService);
        this.buscaMenu     = new BuscaMenu(scanner, missaoService);
        this.relatorioMenu = new RelatorioMenu(relatorioService);
    }

    /** Inicia o loop principal do sistema. Encerra quando o usuário escolhe 0. */
    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        scanner.close();
        System.out.println("\nSistema encerrado. Até logo!");
    }

    private void exibirMenu() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("     SISTEMA DE MISSÕES ESPACIAIS");
        System.out.println("=".repeat(45));
        System.out.println("  1 - Cadastrar missão");
        System.out.println("  2 - Listar missões");
        System.out.println("  3 - Buscar missão");
        System.out.println("  4 - Alterar status de missão");
        System.out.println("  5 - Relatórios");
        System.out.println("  0 - Sair");
        System.out.println("=".repeat(45));
        System.out.print("  Opção: ");
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastroMenu.exibir();     break;
            case 2: buscaMenu.listarTodas();   break;
            case 3: buscaMenu.exibir();        break;
            case 4: buscaMenu.alterarStatus(); break;
            case 5: relatorioMenu.exibir();    break;
            case 0:                            break;
            default:
                System.out.println("  Opção inválida. Tente novamente.");
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
