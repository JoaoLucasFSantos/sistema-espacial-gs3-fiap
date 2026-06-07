package br.edu.missoesespaciais.model;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.Ods;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.enums.TipoTecnologia;

/**
 * Especialização de {@link Missao} com dados operacionais espaciais.
 * É o objeto principal com o qual o sistema trabalha.
 *
 * @author 200%Java
 * @version 1.0
 */
public class MissaoEspacial extends Missao {

    private String agenciaResponsavel;
    private String localOperacao;
    private int duracaoMeses;
    private double custoEstimadoMilhoes;

    /**
     * Construtor completo.
     *
     * @param nome                 nome da missão
     * @param objetivo             objetivo principal
     * @param areaImpacto          área de impacto
     * @param ods                  ODS relacionado
     * @param tipoTecnologia       tipo de tecnologia utilizada
     * @param prioridade           grau de prioridade (1 a 5)
     * @param status               status inicial
     * @param agenciaResponsavel   agência ou organização responsável
     * @param localOperacao        local de operação
     * @param duracaoMeses         duração prevista em meses
     * @param custoEstimadoMilhoes custo estimado em milhões de reais
     */
    public MissaoEspacial(String nome, String objetivo,
                          AreaImpacto areaImpacto, Ods ods,
                          TipoTecnologia tipoTecnologia,
                          int prioridade, StatusMissao status,
                          String agenciaResponsavel, String localOperacao,
                          int duracaoMeses, double custoEstimadoMilhoes) {
        super(nome, objetivo, areaImpacto, ods, tipoTecnologia, prioridade, status);
        this.agenciaResponsavel   = agenciaResponsavel;
        this.localOperacao        = localOperacao;
        this.duracaoMeses         = duracaoMeses;
        this.custoEstimadoMilhoes = custoEstimadoMilhoes;
    }

    /**
     * Construtor simplificado — duração padrão 12 meses, custo 0.
     *
     * @param nome               nome da missão
     * @param objetivo           objetivo principal
     * @param areaImpacto        área de impacto
     * @param ods                ODS relacionado
     * @param tipoTecnologia     tipo de tecnologia utilizada
     * @param agenciaResponsavel agência ou organização responsável
     * @param localOperacao      local de operação
     */
    public MissaoEspacial(String nome, String objetivo,
                          AreaImpacto areaImpacto, Ods ods,
                          TipoTecnologia tipoTecnologia,
                          String agenciaResponsavel, String localOperacao) {
        super(nome, objetivo, areaImpacto, ods, tipoTecnologia);
        this.agenciaResponsavel   = agenciaResponsavel;
        this.localOperacao        = localOperacao;
        this.duracaoMeses         = 12;
        this.custoEstimadoMilhoes = 0.0;
    }

    // -------------------------------------------------------------------------
    // Sobrescrita
    // -------------------------------------------------------------------------

    @Override
    public String gerarResumo() {
        return super.gerarResumo()
                + String.format(" | Agência: %s | Local: %s", agenciaResponsavel, localOperacao);
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("  --- Dados Operacionais ---");
        System.out.printf("  Agência    : %s%n", agenciaResponsavel);
        System.out.printf("  Local      : %s%n", localOperacao);
        System.out.printf("  Duração    : %d meses%n", duracaoMeses);
        System.out.printf("  Custo est. : R$ %.2f milhões%n", custoEstimadoMilhoes);
        System.out.printf("  Inv./mês   : R$ %.2f milhões%n", calcularInvestimentoMensal());
        System.out.println("=".repeat(55));
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %s", agenciaResponsavel);
    }

    // -------------------------------------------------------------------------
    // Específicos
    // -------------------------------------------------------------------------

    /**
     * @return custo estimado dividido pela duração, ou 0.0 se duração for zero
     */
    public double calcularInvestimentoMensal() {
        if (duracaoMeses == 0) return 0.0;
        return custoEstimadoMilhoes / duracaoMeses;
    }

    public void exibirInformacoesOperacionais() {
        System.out.println("-".repeat(55));
        System.out.printf("  Missão     : %s%n", getNome());
        System.out.printf("  Agência    : %s%n", agenciaResponsavel);
        System.out.printf("  Local      : %s%n", localOperacao);
        System.out.printf("  Duração    : %d meses%n", duracaoMeses);
        System.out.printf("  Custo est. : R$ %.2f milhões%n", custoEstimadoMilhoes);
        System.out.printf("  Inv./mês   : R$ %.2f milhões%n", calcularInvestimentoMensal());
        System.out.println("-".repeat(55));
    }

    // -------------------------------------------------------------------------
    // Getters e Setters
    // -------------------------------------------------------------------------

    public String getAgenciaResponsavel()   { return agenciaResponsavel; }
    public void setAgenciaResponsavel(String agenciaResponsavel) { this.agenciaResponsavel = agenciaResponsavel; }

    public String getLocalOperacao()        { return localOperacao; }
    public void setLocalOperacao(String localOperacao) { this.localOperacao = localOperacao; }

    public int getDuracaoMeses()            { return duracaoMeses; }
    public void setDuracaoMeses(int duracaoMeses) { this.duracaoMeses = duracaoMeses; }

    public double getCustoEstimadoMilhoes() { return custoEstimadoMilhoes; }
    public void setCustoEstimadoMilhoes(double custoEstimadoMilhoes) { this.custoEstimadoMilhoes = custoEstimadoMilhoes; }
}