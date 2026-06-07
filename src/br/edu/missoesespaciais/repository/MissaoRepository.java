package br.edu.missoesespaciais.repository;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.model.Missao;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia a coleção de missões cadastradas no sistema.
 * Única classe responsável por acessar e manipular a lista interna.
 *
 * @author 200%Java
 * @version 1.0
 */
public class MissaoRepository {

    private final List<Missao> missoes = new ArrayList<>();

    // -------------------------------------------------------------------------
    // Escrita
    // -------------------------------------------------------------------------

    /**
     * @param missao missão a ser adicionada
     */
    public void adicionarMissao(Missao missao) {
        missoes.add(missao);
    }

    // -------------------------------------------------------------------------
    // Leitura
    // -------------------------------------------------------------------------

    /**
     * @return lista com todas as missões cadastradas
     */
    public List<Missao> listarMissoes() {
        return missoes;
    }

    /**
     * @param id identificador da missão
     * @return missão encontrada, ou {@code null} se não existir
     */
    public Missao buscarPorId(int id) {
        for (Missao m : missoes) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    /**
     * Busca parcial, sem distinção de maiúsculas/minúsculas.
     *
     * @param termo texto parcial ou completo do nome
     * @return lista de missões cujo nome contém o termo
     */
    public List<Missao> buscarPorNome(String termo) {
        List<Missao> resultado = new ArrayList<>();
        for (Missao m : missoes) {
            if (m.getNome().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    /**
     * @param area área de impacto a filtrar
     * @return lista de missões da área informada
     */
    public List<Missao> buscarPorArea(AreaImpacto area) {
        List<Missao> resultado = new ArrayList<>();
        for (Missao m : missoes) {
            if (m.getAreaImpactoEnum() == area) resultado.add(m);
        }
        return resultado;
    }

    /**
     * @param status status a filtrar
     * @return lista de missões com o status informado
     */
    public List<Missao> listarPorStatus(StatusMissao status) {
        List<Missao> resultado = new ArrayList<>();
        for (Missao m : missoes) {
            if (m.getStatus() == status) resultado.add(m);
        }
        return resultado;
    }

    // -------------------------------------------------------------------------
    // Verificação e contagem
    // -------------------------------------------------------------------------

    /**
     * Verifica se já existe missão com o mesmo nome (sem distinção de maiúsculas).
     *
     * @param nome nome a verificar
     * @return {@code true} se o nome já estiver cadastrado
     */
    public boolean existeMissao(String nome) {
        for (Missao m : missoes) {
            if (m.getNome().equalsIgnoreCase(nome)) return true;
        }
        return false;
    }

    /**
     * @return total de missões cadastradas
     */
    public int quantidadeMissoes() {
        return missoes.size();
    }

    // -------------------------------------------------------------------------
    // Consultas para relatório
    // -------------------------------------------------------------------------

    /**
     * @return missão com menor valor de prioridade (1 = mais alta),
     *         ou {@code null} se a lista estiver vazia
     */
    public Missao buscarMaiorPrioridade() {
        if (missoes.isEmpty()) return null;
        Missao maior = missoes.get(0);
        for (Missao m : missoes) {
            if (m.getPrioridade() < maior.getPrioridade()) maior = m;
        }
        return maior;
    }
}
