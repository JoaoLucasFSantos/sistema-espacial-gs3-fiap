package br.edu.missoesespaciais.service;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.Ods;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.model.Missao;
import br.edu.missoesespaciais.repository.MissaoRepository;

import java.util.List;

/**
 * Gera estatísticas e análises sobre as missões cadastradas.
 * Não realiza nenhuma escrita — apenas lê e processa dados do repositório.
 */
public class RelatorioService {

    private final MissaoRepository repository;

    public RelatorioService(MissaoRepository repository) {
        this.repository = repository;
    }

    // -------------------------------------------------------------------------
    // Totais
    // -------------------------------------------------------------------------

    public int totalMissoes() {
        return repository.quantidadeMissoes();
    }

    // -------------------------------------------------------------------------
    // Distribuições
    // -------------------------------------------------------------------------

    /**
     * Imprime no console a quantidade de missões por status.
     * Percorre todos os valores do enum para garantir que statuses
     * com zero missões também apareçam no relatório.
     */
    public void quantidadePorStatus() {
        System.out.println("  Distribuição por status:");
        for (StatusMissao status : StatusMissao.values()) {
            int quantidade = repository.listarPorStatus(status).size();
            System.out.printf("    %-15s : %d%n", status.getDescricao(), quantidade);
        }
    }

    // -------------------------------------------------------------------------
    // Rankings
    // -------------------------------------------------------------------------

    public Missao missaoMaisPrioritaria() {
        return repository.buscarMaiorPrioridade();
    }

    /** Retorna a área de impacto com mais missões cadastradas. */
    public AreaImpacto areaMaisUtilizada() {
        AreaImpacto maisUsada = null;
        int maiorContagem = 0;

        for (AreaImpacto area : AreaImpacto.values()) {
            int contagem = repository.buscarPorArea(area).size();
            if (contagem > maiorContagem) {
                maiorContagem = contagem;
                maisUsada = area;
            }
        }
        return maisUsada;
    }

    /** Retorna o ODS que aparece mais vezes entre as missões cadastradas. */
    public Ods odsMaisUtilizado() {
        List<Missao> missoes = repository.listarMissoes();
        if (missoes.isEmpty()) return null;

        Ods maisUsado = null;
        int maiorContagem = 0;

        for (Ods ods : Ods.values()) {
            int contagem = 0;
            for (Missao m : missoes) {
                if (m.getOds() == ods) contagem++;
            }
            if (contagem > maiorContagem) {
                maiorContagem = contagem;
                maisUsado = ods;
            }
        }
        return maisUsado;
    }

    // -------------------------------------------------------------------------
    // Resumo geral
    // -------------------------------------------------------------------------

    public void resumoGeral() {
        System.out.println("=".repeat(55));
        System.out.println("           RESUMO GERAL DO SISTEMA");
        System.out.println("=".repeat(55));

        System.out.printf("  Total de missões  : %d%n%n", totalMissoes());

        quantidadePorStatus();

        Missao prioritaria = missaoMaisPrioritaria();
        System.out.println();
        if (prioritaria != null) {
            System.out.printf("  Mais prioritária  : [#%03d] %s (prioridade %d)%n",
                    prioritaria.getId(),
                    prioritaria.getNome(),
                    prioritaria.getPrioridade());
        } else {
            System.out.println("  Mais prioritária  : nenhuma missão cadastrada.");
        }

        AreaImpacto area = areaMaisUtilizada();
        System.out.printf("  Área mais usada   : %s%n",
                area != null ? area.getDescricao() : "-");

        Ods ods = odsMaisUtilizado();
        System.out.printf("  ODS mais usado    : %s%n",
                ods != null ? ods.getDescricao() : "-");

        System.out.println("=".repeat(55));
    }
}
