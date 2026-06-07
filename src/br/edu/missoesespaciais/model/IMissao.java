package br.edu.missoesespaciais.model;

import br.edu.missoesespaciais.enums.StatusMissao;

/**
 * Contrato que toda missão do sistema deve seguir.
 *
 * @author Equipe 200%Java
 * @version 1.0
 */
public interface IMissao {

    String getNome();
    String getAreaImpacto();
    StatusMissao getStatus();

    /**
     * @param novoStatus novo status a ser aplicado
     */
    void atualizarStatus(StatusMissao novoStatus);

    /**
     * @return resumo textual com as informações principais da missão
     */
    String gerarResumo();
}