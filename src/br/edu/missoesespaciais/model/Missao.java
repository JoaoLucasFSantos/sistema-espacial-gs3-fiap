package br.edu.missoesespaciais.model;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.Ods;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.enums.TipoTecnologia;

/**
 * Entidade base de uma missão sustentável cadastrada no sistema.
 * Superclasse de {@link MissaoEspacial}.
 *
 * @author Equipe 200%Java
 * @version 1.0
 */
public class Missao implements IMissao {

    private static int contadorId = 1;

    private final int id;
    private String nome;
    private String objetivo;
    private AreaImpacto areaImpacto;
    private Ods ods;
    private TipoTecnologia tipoTecnologia;
    private int prioridade;
    private StatusMissao status;

    /**
     * Construtor completo.
     *
     * @param nome           nome da missão
     * @param objetivo       objetivo principal
     * @param areaImpacto    área de impacto
     * @param ods            ODS relacionado
     * @param tipoTecnologia tipo de tecnologia utilizada
     * @param prioridade     grau de prioridade (1 a 5)
     * @param status         status inicial
     */
    public Missao(String nome, String objetivo, AreaImpacto areaImpacto,
                  Ods ods, TipoTecnologia tipoTecnologia,
                  int prioridade, StatusMissao status) {
        this.id             = contadorId++;
        this.nome           = nome;
        this.objetivo       = objetivo;
        this.areaImpacto    = areaImpacto;
        this.ods            = ods;
        this.tipoTecnologia = tipoTecnologia;
        this.prioridade     = prioridade;
        this.status         = status;
    }

    /**
     * Construtor simplificado — prioridade padrão 3, status PLANEJADA.
     *
     * @param nome           nome da missão
     * @param objetivo       objetivo principal
     * @param areaImpacto    área de impacto
     * @param ods            ODS relacionado
     * @param tipoTecnologia tipo de tecnologia utilizada
     */
    public Missao(String nome, String objetivo, AreaImpacto areaImpacto,
                  Ods ods, TipoTecnologia tipoTecnologia) {
        this(nome, objetivo, areaImpacto, ods, tipoTecnologia, 3, StatusMissao.PLANEJADA);
    }

    // -------------------------------------------------------------------------
    // IMissao
    // -------------------------------------------------------------------------

    @Override
    public String getNome() { return nome; }

    @Override
    public String getAreaImpacto() { return areaImpacto.getDescricao(); }

    @Override
    public StatusMissao getStatus() { return status; }

    @Override
    public void atualizarStatus(StatusMissao novoStatus) { this.status = novoStatus; }

    @Override
    public String gerarResumo() {
        return String.format("[#%03d] %s | %s | Prioridade: %d | Status: %s",
                id, nome, areaImpacto.getDescricao(), prioridade, status.getDescricao());
    }

    // -------------------------------------------------------------------------
    // Manipulação
    // -------------------------------------------------------------------------

    /**
     * @param novoStatus novo status da missão
     */
    public void alterarStatus(StatusMissao novoStatus) { this.status = novoStatus; }

    /**
     * @param novaPrioridade novo grau de prioridade (1 a 5)
     */
    public void alterarPrioridade(int novaPrioridade) { this.prioridade = novaPrioridade; }

    /**
     * Atualiza nome e objetivo simultaneamente.
     *
     * @param novoNome     novo nome da missão
     * @param novoObjetivo novo objetivo da missão
     */
    public void atualizarInformacoes(String novoNome, String novoObjetivo) {
        this.nome     = novoNome;
        this.objetivo = novoObjetivo;
    }

    /**
     * Atualiza apenas o objetivo. Sobrecarga de {@link #atualizarInformacoes(String, String)}.
     *
     * @param novoObjetivo novo objetivo da missão
     */
    public void atualizarInformacoes(String novoObjetivo) {
        this.objetivo = novoObjetivo;
    }

    // -------------------------------------------------------------------------
    // Consulta
    // -------------------------------------------------------------------------

    /**
     * @return {@code true} se a prioridade for 1 ou 2
     */
    public boolean isPrioritaria() { return prioridade <= 2; }

    public void exibirDetalhes() {
        System.out.println("=".repeat(55));
        System.out.printf("  MISSÃO #%03d%n", id);
        System.out.println("=".repeat(55));
        System.out.printf("  Nome       : %s%n", nome);
        System.out.printf("  Objetivo   : %s%n", objetivo);
        System.out.printf("  Área       : %s%n", areaImpacto.getDescricao());
        System.out.printf("  ODS        : %s%n", ods.getDescricao());
        System.out.printf("  Tecnologia : %s%n", tipoTecnologia.getDescricao());
        System.out.printf("  Prioridade : %d %s%n", prioridade, isPrioritaria() ? "[ALTA]" : "");
        System.out.printf("  Status     : %s%n", status.getDescricao());
        System.out.println("=".repeat(55));
    }

    @Override
    public String toString() {
        return String.format("#%03d | %-35s | %-25s | %s",
                id, nome, areaImpacto.getDescricao(), status.getDescricao());
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    public int getId()                      { return id; }
    public String getObjetivo()             { return objetivo; }
    public Ods getOds()                     { return ods; }
    public TipoTecnologia getTipoTecnologia() { return tipoTecnologia; }
    public int getPrioridade()              { return prioridade; }
    public AreaImpacto getAreaImpactoEnum() { return areaImpacto; }
}