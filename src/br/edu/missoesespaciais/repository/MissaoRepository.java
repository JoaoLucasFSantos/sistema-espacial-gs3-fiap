package br.edu.missoesespaciais.repository;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.model.Missao;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia a coleção de missões cadastradas no sistema.
 * Única classe responsável por acessar e manipular a lista interna.
 */
public class MissaoRepository {

    private final List<Missao> missoes = new ArrayList<>();

    // -------------------------------------------------------------------------
    // Escrita
    // -------------------------------------------------------------------------

    public void adicionarMissao(Missao missao) {
        missoes.add(missao);
    }

    // -------------------------------------------------------------------------
    // Leitura
    // -------------------------------------------------------------------------

    public List<Missao> listarMissoes() {
        return missoes;
    }

    public Missao buscarPorId(int id) {
        for (Missao m : missoes) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    /** Busca parcial, sem distinção de maiúsculas/minúsculas. */
    public List<Missao> buscarPorNome(String termo) {
        List<Missao> resultado = new ArrayList<>();
        for (Missao m : missoes) {
            if (m.getNome().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public List<Missao> buscarPorArea(AreaImpacto area) {
        List<Missao> resultado = new ArrayList<>();
        for (Missao m : missoes) {
            if (m.getAreaImpactoEnum() == area) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public List<Missao> listarPorStatus(StatusMissao status) {
        List<Missao> resultado = new ArrayList<>();
        for (Missao m : missoes) {
            if (m.getStatus() == status) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    // -------------------------------------------------------------------------
    // Verificação e contagem
    // -------------------------------------------------------------------------

    /** Verifica se já existe missão com o mesmo nome (sem distinção de maiúsculas). */
    public boolean existeMissao(String nome) {
        for (Missao m : missoes) {
            if (m.getNome().equalsIgnoreCase(nome)) return true;
        }
        return false;
    }

    public int quantidadeMissoes() {
        return missoes.size();
    }

    // -------------------------------------------------------------------------
    // Consultas para relatório
    // -------------------------------------------------------------------------

    /** Retorna a missão com menor valor de prioridade (1 = mais alta). */
    public Missao buscarMaiorPrioridade() {
        if (missoes.isEmpty()) return null;

        Missao maior = missoes.get(0);
        for (Missao m : missoes) {
            if (m.getPrioridade() < maior.getPrioridade()) {
                maior = m;
            }
        }
        return maior;
    }
}
