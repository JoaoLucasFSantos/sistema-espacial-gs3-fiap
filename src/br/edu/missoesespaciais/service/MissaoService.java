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
 * Cérebro do sistema. Contém todas as regras de negócio,
 * valida dados e intermedia a UI com o repositório.
 */
public class MissaoService {

    private final MissaoRepository repository;

    public MissaoService(MissaoRepository repository) {
        this.repository = repository;
    }

    // -------------------------------------------------------------------------
    // Cadastro
    // -------------------------------------------------------------------------

    /**
     * Cria e persiste uma missão espacial completa.
     * Retorna mensagem de resultado para a UI exibir.
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
     * Versão simplificada: cria missão com dados mínimos.
     * Prioridade padrão 3, status PLANEJADA, duração 12 meses, custo 0.
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

    public List<Missao> listarMissoes() {
        return repository.listarMissoes();
    }

    public List<Missao> buscarPorNome(String termo) {
        return repository.buscarPorNome(termo);
    }

    public List<Missao> buscarPorArea(AreaImpacto area) {
        return repository.buscarPorArea(area);
    }

    public Missao buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public int quantidadeMissoes() {
        return repository.quantidadeMissoes();
    }

    public Missao obterMissaoMaisPrioritaria() {
        return repository.buscarMaiorPrioridade();
    }

    // -------------------------------------------------------------------------
    // Alterações
    // -------------------------------------------------------------------------

    /**
     * Localiza a missão pelo ID e altera o status.
     * Retorna mensagem de resultado para a UI exibir.
     */
    public String alterarStatus(int id, StatusMissao novoStatus) {
        Missao missao = repository.buscarPorId(id);
        if (missao == null) {
            return "Missão #" + id + " não encontrada.";
        }
        StatusMissao anterior = missao.getStatus();
        missao.alterarStatus(novoStatus);
        return String.format("Status atualizado: %s → %s",
                anterior.getDescricao(), novoStatus.getDescricao());
    }

    /**
     * Localiza a missão pelo ID e altera a prioridade.
     * Retorna mensagem de resultado para a UI exibir.
     */
    public String alterarPrioridade(int id, int novaPrioridade) {
        String validacao = validarPrioridade(novaPrioridade);
        if (validacao != null) return validacao;

        Missao missao = repository.buscarPorId(id);
        if (missao == null) {
            return "Missão #" + id + " não encontrada.";
        }
        missao.alterarPrioridade(novaPrioridade);
        return "Prioridade atualizada para " + novaPrioridade + ".";
    }

    // -------------------------------------------------------------------------
    // Validação
    // -------------------------------------------------------------------------

    /**
     * Valida nome e prioridade antes de cadastrar.
     * Retorna a mensagem de erro encontrada, ou null se tudo estiver ok.
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
