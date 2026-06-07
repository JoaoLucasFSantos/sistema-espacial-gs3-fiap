package br.edu.missoesespaciais;

import br.edu.missoesespaciais.repository.MissaoRepository;
import br.edu.missoesespaciais.service.MissaoService;
import br.edu.missoesespaciais.service.RelatorioService;
import br.edu.missoesespaciais.ui.MenuPrincipal;

/**
 * Ponto de entrada da aplicação.
 * Instancia as dependências e inicia o sistema.
 */
public class Main {

    public static void main(String[] args) {
        MissaoRepository repository = new MissaoRepository();
        MissaoService    missaoService    = new MissaoService(repository);
        RelatorioService relatorioService = new RelatorioService(repository);

        MenuPrincipal menu = new MenuPrincipal(missaoService, relatorioService);
        menu.iniciar();
    }
}
