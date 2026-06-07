package br.edu.missoesespaciais.model;

import br.edu.missoesespaciais.enums.StatusMissao;

/**
 * Contrato que toda missão do sistema deve seguir.
 */
public interface IMissao {

    String getNome();
    String getAreaImpacto();
    StatusMissao getStatus();
    void atualizarStatus(StatusMissao novoStatus);
    String gerarResumo();
}