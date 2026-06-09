# 🚀 SIGMA
### Sistema Integrado de Gerenciamento de Missões Aeroespaciais

---

## 📌 Sobre o Projeto

O SIGMA é uma aplicação Java de console desenvolvida para simular o cadastro, organização e consulta de missões espaciais voltadas à solução de problemas reais da sociedade.

O sistema permite registrar missões com informações como área de impacto, tipo de tecnologia, ODS relacionado, prioridade e status, além de gerar relatórios e estatísticas sobre o conjunto de missões cadastradas.

---

## 🌍 Problema Escolhido

O projeto aborda o uso de tecnologia espacial como ferramenta para enfrentar desafios globais, como mudanças climáticas, insegurança alimentar, desastres naturais e desigualdade no acesso à conectividade.

Missões espaciais modernas não se limitam à exploração — elas podem monitorar safras agrícolas, prever eventos climáticos extremos, mapear desmatamento e levar conectividade a regiões remotas. O SIGMA simula o gerenciamento dessas iniciativas.

---

## 🎯 ODS Relacionados

O sistema contempla os seguintes Objetivos de Desenvolvimento Sustentável da ONU:

| ODS | Descrição |
|-----|-----------|
| ODS 2  | Fome Zero e Agricultura Sustentável |
| ODS 3  | Saúde e Bem-Estar |
| ODS 9  | Indústria, Inovação e Infraestrutura |
| ODS 11 | Cidades e Comunidades Sustentáveis |
| ODS 13 | Ação Contra a Mudança Global do Clima |
| ODS 15 | Vida Terrestre |

---

## 🗂️ Estrutura do Projeto

```
br.edu.missoesespaciais
│
├── Main.java
├── enums/
│   ├── AreaImpacto.java
│   ├── Ods.java
│   ├── StatusMissao.java
│   └── TipoTecnologia.java
├── model/
│   ├── IMissao.java
│   ├── Missao.java
│   └── MissaoEspacial.java
├── repository/
│   └── MissaoRepository.java
├── service/
│   ├── MissaoService.java
│   └── RelatorioService.java
└── ui/
    ├── MenuPrincipal.java
    ├── CadastroMenu.java
    ├── BuscaMenu.java
    └── RelatorioMenu.java
```

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java JDK 11 ou superior instalado
- Terminal ou prompt de comando

### Passo a passo

**1. Clone ou baixe o repositório**
```bash
git clone https://github.com/seu-usuario/sigma-missoes-espaciais.git
cd sigma-missoes-espaciais
```

**2. Compile o projeto**

Na pasta raiz do projeto, execute:
```bash
javac -d out -sourcepath src src/br/edu/missoesespaciais/Main.java
```

**3. Execute a aplicação**
```bash
java -cp out br.edu.missoesespaciais.Main
```

### Exemplo de uso

Ao iniciar, o sistema exibe o menu principal:

```
=============================================
     SISTEMA DE MISSÕES ESPACIAIS
=============================================
  1 - Cadastrar missão
  2 - Listar missões
  3 - Buscar missão
  4 - Alterar status de missão
  5 - Relatórios
  0 - Sair
=============================================
  Opção:
```

---

## 👥 Integrantes do Grupo

| Nome | RM |
|------|----|
| Cristian Belasco Arancibia | RM 565710 |
| Samuel de Oliveira da Silva | RM 566244 |
| João Lucas Ferreira dos Santos | RM 562608 |
| Lucas Oliveira de Mendonça Almeida | RM 562613 |
| Victor Antonio Teixeira da Silva | RM 562573 |

---

## 🛠️ Tecnologias Utilizadas

- Java (JDK 11+)
- Paradigma orientado a objetos
- Aplicação console (sem frameworks externos)
