package br.edu.missoesespaciais.service;

import br.edu.missoesespaciais.enums.AreaImpacto;
import br.edu.missoesespaciais.enums.Ods;
import br.edu.missoesespaciais.enums.StatusMissao;
import br.edu.missoesespaciais.enums.TipoTecnologia;
import br.edu.missoesespaciais.model.Missao;
import br.edu.missoesespaciais.model.MissaoEspacial;
import br.edu.missoesespaciais.repository.MissaoRepository;

import java.util.List;

/**
 * Contém as regras de negócio do sistema.
 * Valida dados e intermedia a UI com o repositório.
 *
 * @author 200%Java
 * @version 1.0
 */
public class MissaoService {

    private final MissaoRepository repository;

    /**
     * @param repository repositório de missões a ser utilizado
     */
    public MissaoService(MissaoRepository repository) {
        this.repository = repository;
    }

    // -------------------------------------------------------------------------
    // Cadastro
    // -------------------------------------------------------------------------

    /**
     * Valida e persiste uma missão espacial completa.
     *
     * @param nome         nome da missão
     * @param objetivo     objetivo principal
     * @param area         área de impacto
     * @param ods          ODS relacionado
     * @param tecnologia   tipo de tecnologia
     * @param prioridade   grau de prioridade (1 a 5)
     * @param status       status inicial
     * @param agencia      agência responsável
     * @param local        local de operação
     * @param duracaoMeses duração prevista em meses
     * @param custoMilhoes custo estimado em milhões de reais
     * @return mensagem de sucesso ou descrição do erro encontrado
     */
    public String cadastrarMissao(String nome, String objetivo,
                                  AreaImpacto area, Ods ods,
                                  TipoTecnologia tecnologia,
                                  int prioridade, StatusMissao status,
                                  String agencia, String local,
                                  int duracaoMeses, double custoMilhoes) {
        String validacao = validarMissao(nome, prioridade);
        if (validacao != null) return validacao;

        MissaoEspacial missao = new MissaoEspacial(
                nome, objetivo, area, ods, tecnologia,
                prioridade, status, agencia, local, duracaoMeses, custoMilhoes
        );
        repository.adicionarMissao(missao);
        return "Missão \"" + nome + "\" cadastrada com sucesso!";
    }

    /**
     * Valida e persiste uma missão com dados mínimos.
     * Prioridade padrão 3, status PLANEJADA, duração 12 meses, custo 0.
     *
     * @param nome       nome da missão
     * @param objetivo   objetivo principal
     * @param area       área de impacto
     * @param ods        ODS relacionado
     * @param tecnologia tipo de tecnologia
     * @param agencia    agência responsável
     * @param local      local de operação
     * @return mensagem de sucesso ou descrição do erro encontrado
     */
    public String cadastrarMissao(String nome, String objetivo,
                                  AreaImpacto area, Ods ods,
                                  TipoTecnologia tecnologia,
                                  String agencia, String local) {
        String validacao = validarMissao(nome, 3);
        if (validacao != null) return validacao;

        MissaoEspacial missao = new MissaoEspacial(
                nome, objetivo, area, ods, tecnologia, agencia, local
        );
        repository.adicionarMissao(missao);
        return "Missão \"" + nome + "\" cadastrada com sucesso!";
    }

    // -------------------------------------------------------------------------
    // Consultas
    // -------------------------------------------------------------------------

    /** @return lista com todas as missões cadastradas */
    public List<Missao> listarMissoes() {
        return repository.listarMissoes();
    }

    /**
     * @param termo texto parcial ou completo do nome
     * @return lista de missões cujo nome contém o termo
     */
    public List<Missao> buscarPorNome(String termo) {
        return repository.buscarPorNome(termo);
    }

    /**
     * @param area área de impacto a filtrar
     * @return lista de missões da área informada
     */
    public List<Missao> buscarPorArea(AreaImpacto area) {
        return repository.buscarPorArea(area);
    }

    /**
     * @param id identificador da missão
     * @return missão encontrada, ou {@code null} se não existir
     */
    public Missao buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    /** @return total de missões cadastradas */
    public int quantidadeMissoes() {
        return repository.quantidadeMissoes();
    }

    /** @return missão com maior prioridade, ou {@code null} se a lista estiver vazia */
    public Missao obterMissaoMaisPrioritaria() {
        return repository.buscarMaiorPrioridade();
    }

    // -------------------------------------------------------------------------
    // Alterações
    // -------------------------------------------------------------------------

    /**
     * Localiza a missão pelo ID e altera o status.
     *
     * @param id         identificador da missão
     * @param novoStatus novo status a aplicar
     * @return mensagem de sucesso ou descrição do erro encontrado
     */
    public String alterarStatus(int id, StatusMissao novoStatus) {
        Missao missao = repository.buscarPorId(id);
        if (missao == null) return "Missão #" + id + " não encontrada.";

        StatusMissao anterior = missao.getStatus();
        missao.alterarStatus(novoStatus);
        return String.format("Status atualizado: %s → %s",
                anterior.getDescricao(), novoStatus.getDescricao());
    }

    /**
     * Localiza a missão pelo ID e altera a prioridade.
     *
     * @param id             identificador da missão
     * @param novaPrioridade nova prioridade (1 a 5)
     * @return mensagem de sucesso ou descrição do erro encontrado
     */
    public String alterarPrioridade(int id, int novaPrioridade) {
        String validacao = validarPrioridade(novaPrioridade);
        if (validacao != null) return validacao;

        Missao missao = repository.buscarPorId(id);
        if (missao == null) return "Missão #" + id + " não encontrada.";

        missao.alterarPrioridade(novaPrioridade);
        return "Prioridade atualizada para " + novaPrioridade + ".";
    }

    // -------------------------------------------------------------------------
    // Validação
    // -------------------------------------------------------------------------

    /**
     * Valida nome e prioridade antes de cadastrar.
     *
     * @param nome       nome da missão
     * @param prioridade grau de prioridade
     * @return mensagem de erro, ou {@code null} se os dados forem válidos
     */
    public String validarMissao(String nome, int prioridade) {
        if (nome == null || nome.trim().isEmpty()) {
            return "O nome da missão não pode ser vazio.";
        }
        if (repository.existeMissao(nome.trim())) {
            return "Já existe uma missão com o nome \"" + nome + "\".";
        }
        return validarPrioridade(prioridade);
    }

    private String validarPrioridade(int prioridade) {
        if (prioridade < 1 || prioridade > 5) {
            return "Prioridade inválida. Informe um valor entre 1 e 5.";
        }
        return null;
    }
}
