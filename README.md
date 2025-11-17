[![Github Actions Status for
EduardoHMDeeke/A3_2025_2_GQS](https://github.com/EduardoHMDeeke/A3_2025_2_GQS/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/EduardoHMDeeke/A3_2025_2_GQS/actions)
[![Quality Gate
Status](https://sonarcloud.io/api/project_badges/measure?project=EduardoHMDeeke_A3_2025_2_GQS&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=EduardoHMDeeke_A3_2025_2_GQS)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=EduardoHMDeeke_A3_2025_2_GQS&metric=coverage)](https://sonarcloud.io/component_measures?id=EduardoHMDeeke_A3_2025_2_GQS&metric=coverage)


<h1>📦 Sistema de Registro de Ferramentas Emprestadas</h1>
-----A3 de Gestão de Qualidade de Software-----



<h2>🛠️ Tecnologias</h2>
<ul>
  <li><b>Java 22</b></li>
  <li><b>Maven</b></li>
  <li><b>JUnit</b> para testes</li>
  <li><b>GitHub Actions</b> para CI/CD</li>
</ul>



<h2>👨‍🎓 Alunos</h2>
<table border="1" cellpadding="5" cellspacing="0">
  <tr>
    <td>Alexandre Ciro Andriani Júnior - 1072323107 - alexandre-jr-94</td>
  </tr>
  <tr>
    <td>Eduardo Henrique de Melo Deeke - 1072311890 - EduardoHMDeeke</td>
  </tr>
  <tr>
    <td>João Vitor de Siqueira - 10723113301 - Joao05Vitor</td>
  </tr>
  <tr>
    <td>João da Silva Meurer - 1072223534 - Bolguma</td>
  </tr>
    <tr>
    <td>Ryan Pereira Lima - 1072310978 - ryanpzr</td>
  </tr>
</table>
<<<<<<< Updated upstream
=======



---

## ✅ Requisitos do Sistema </br>

### Requisitos Funcionais </br>
RF001 **Cadastro de Ferramentas**  </br>
   O sistema deve permitir registrar ferramentas com nome, código e estado. </br>

RF002 **Registro de Empréstimo e Devolução**  </br>
   O sistema deve possibilitar registrar quando uma ferramenta é emprestada ou devolvida, atualizando seu status. </br>

RF003 **Consulta de Ferramentas**  </br>
   O sistema deve permitir buscar ferramentas por nome, código ou status (disponível/emprestada). </br>

### Requisito Não Funcional </br>
RNF001 **Usabilidade**  </br>
  A interface deve ser simples e intuitiva para facilitar o uso por qualquer colaborador. </br>

---

## 📋 Funcionalidades do Sistema

### 🏠 **Tela Principal (TelaPrincipal)**
A tela principal é o ponto central do sistema, oferecendo navegação entre diferentes módulos e visualização de informações.

**Funcionalidades:**
- **Navegação por Cards**: Sistema de navegação usando CardLayout entre Home, Lista de Amigos, Lista de Ferramentas e Relatórios
- **Visualização de Empréstimos**: Tabela exibindo todos os empréstimos ativos com informações de amigo, ferramenta e datas
- **Refresh de Dados**: Botão para atualizar a lista de empréstimos em tempo real
- **Menu Popup**: Menu de contexto para acesso rápido às funcionalidades principais
- **Botões de Ação Rápida**: Acesso direto a cadastros, edições e operações de empréstimo

**Funções Principais:**
- `listarEmprestimos()`: Carrega e exibe todos os empréstimos ativos na tabela
- `listarAmigos()`: Atualiza a tabela de amigos
- `listarFerramentas()`: Atualiza a tabela de ferramentas
- Navegação entre cards (Home, Amigos, Ferramentas, Relatório)

---

### 👥 **Gestão de Amigos (RegistrosAmigos)**

#### **Funcionalidades de Cadastro:**
- **Cadastrar Novo Amigo**: Registra amigos com nome, email e telefone
  - Validação de campos obrigatórios
  - Validação para impedir números no campo nome
  - Limpeza automática dos campos após cadastro bem-sucedido

#### **Funcionalidades de Edição:**
- **Atualizar Amigo**: Modifica informações de um amigo existente
  - Requer ID do amigo para identificação
  - Validação de campos antes da atualização
  - Validação de nome sem números

#### **Funcionalidades de Exclusão:**
- **Deletar Amigo**: Remove um amigo do sistema
  - Requer ID do amigo
  - Confirmação de exclusão

**Funções do Controller (RegistrosAmigosController):**
- `registrarAmigo()`: Valida e insere novo amigo no banco de dados
- `updateAmigo()`: Atualiza informações de um amigo existente
- `deleteAmigo()`: Remove um amigo do banco de dados

**Funções da View:**
- `validarCampos()`: Valida se o campo nome contém números e habilita/desabilita botões
- Validação em tempo real ao perder foco do campo nome

---

### 🔧 **Gestão de Ferramentas (RegistroFerramentas)**

#### **Funcionalidades de Cadastro:**
- **Cadastrar Nova Ferramenta**: Registra ferramentas com nome, marca e preço
  - Validação de campos obrigatórios
  - Formatação automática de preço em formato monetário (R$)
  - Validação para impedir números no campo nome
  - Status inicial: não emprestada (0)

#### **Funcionalidades de Edição:**
- **Atualizar Ferramenta**: Modifica informações de uma ferramenta existente
  - Requer ID da ferramenta
  - Validação de todos os campos
  - Validação de ID numérico válido
  - Validação de nome sem números

#### **Funcionalidades de Exclusão:**
- **Deletar Ferramenta**: Remove uma ferramenta do sistema
  - Requer ID da ferramenta
  - Validação de ID antes da exclusão

**Funções do Controller (RegistroFerramentasController):**
- `registrarFerramenta()`: Valida e insere nova ferramenta no banco
- `updateFerramenta()`: Atualiza informações de uma ferramenta existente
- `deleteFerramenta()`: Remove uma ferramenta do banco

**Funções da View:**
- `validarCampos()`: Valida campo nome e habilita/desabilita botões
- `txtPrecoKeyReleased()`: Formata preço automaticamente enquanto digita

---

### 📦 **Gestão de Empréstimos (ViewEmprestimos)**

#### **Funcionalidades:**
- **Realizar Empréstimo**: Cria um novo registro de empréstimo
  - Seleção de amigo através de ComboBox
  - Seleção de ferramenta disponível (apenas não emprestadas)
  - Inserção de data de empréstimo e data prevista de devolução
  - Formatação de datas (dd/MM/yyyy)
  - Atualização automática do status da ferramenta para "emprestada" (1)
  - Validação de todos os campos obrigatórios

**Funções Principais:**
- `jRealizarEmprestimoActionPerformed()`: Processa o empréstimo
  - Extrai IDs de amigo e ferramenta dos ComboBoxes
  - Converte datas de String para LocalDate
  - Insere empréstimo no banco
  - Atualiza status da ferramenta
- `atualizaStatusFerramenta()`: Atualiza o status da ferramenta para emprestada
- Inicialização de ComboBoxes com dados do banco

---

### 🔄 **Devolução de Ferramentas (DevolverFerramenta)**

#### **Funcionalidades:**
- **Devolver Ferramenta**: Registra a devolução de uma ferramenta emprestada
  - Requer ID do empréstimo
  - Atualiza status do empréstimo para devolvido (0)
  - Atualiza status da ferramenta para disponível (0)
  - Busca informações do empréstimo antes de atualizar

**Funções do Controller (EmprestimosController):**
- `devolveFerramenta()`: Processa a devolução
  - Atualiza status do empréstimo
  - Busca informações do empréstimo
  - Atualiza status da ferramenta para disponível

---

### 📊 **Listagem e Consultas**

#### **Lista de Amigos (ListaAmigosController / ListaAmigosFerramentasController):**
- `listarAmigos()`: Retorna lista completa de amigos
  - Exibe em tabela: ID, Nome, Email, Telefone
  - Atualização dinâmica da tabela

#### **Lista de Ferramentas (ListaAmigosFerramentasController):**
- `listarFerramentas()`: Retorna lista completa de ferramentas
  - Exibe em tabela: ID, Nome, Marca, Preço
  - Atualização dinâmica da tabela

#### **Lista de Empréstimos (ListaAmigosFerramentasController):**
- `listarEmprestimos()`: Retorna lista de empréstimos ativos
  - Exibe apenas empréstimos com status "emprestada" (1)
  - Combina dados de amigos e ferramentas através de DTO
  - Exibe: ID, Nome do Amigo, Nome da Ferramenta, Data Empréstimo, Data Devolução
  - Converte datas para formato legível (dd/MM/yyyy)
  - Funções auxiliares:
    - `busqueAmigo()`: Busca amigo por ID na lista
    - `buscarFerramentas()`: Busca ferramenta por ID na lista

---

## 🗄️ **Camada de Acesso a Dados (DAO)**

### **AmigosDAO**
**Funções:**
- `insertBD(Amigos amigos)`: Insere novo amigo no banco
  - Campos: nome, idade, telefone, email
- `UpdateAmigos(Amigos amigos, int id)`: Atualiza amigo existente
- `listarAmigos()`: Retorna ArrayList com todos os amigos
- `deleteAmigos(int id)`: Remove amigo por ID
- `buscarAmigo(int id)`: Busca amigo específico por ID

### **FerramentaDAO**
**Funções:**
- `insertBD(Ferramentas ferramenta)`: Insere nova ferramenta
  - Campos: nome, marca, preco, estaEmprestada
- `updateFerramenta(Ferramentas ferramenta, int id)`: Atualiza ferramenta
- `listarFerramentas()`: Retorna todas as ferramentas
- `listarFerramentasNaoEmprestadas()`: Retorna apenas ferramentas disponíveis (estaEmprestada = 1)
- `buscarFerramenta(int id)`: Busca ferramenta por ID
- `deleteFerramentas(int id)`: Remove ferramenta por ID
- `updateStatus(int estaEmprestada, int id)`: Atualiza apenas o status de empréstimo

### **EmprestimosDAO**
**Funções:**
- `insertBD(Emprestimos emprestimos)`: Insere novo empréstimo
  - Suporta múltiplos tipos de data (LocalDate, LocalDateTime, Date, Timestamp)
  - Campos: idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, estaEmprestada
- `listarEmprestimos()`: Retorna todos os empréstimos
  - Converte datas do banco para LocalDate
- `updateEmprestimos(int estaEmprestada, int id)`: Atualiza status do empréstimo
- `updateEmprestimos(int estaEmprestada, Date dataDevolvida, int id)`: Atualiza status e data de devolução
- `buscarEmprestimo(int id)`: Busca empréstimo específico por ID
- `getAndConvertDate()`: Método auxiliar para conversão de datas

---

## 📦 **Modelos de Dados (Model)**

### **Amigos**
**Atributos:**
- `id`: Identificador único
- `nome`: Nome do amigo
- `email`: Email de contato
- `telefone`: Telefone de contato
- `idade`: Idade do amigo
- `diaDoEmprestimo`: Dia do empréstimo (campo auxiliar)

**Construtores:**
- `Amigos()`: Construtor vazio
- `Amigos(String nome, String email, String telefone, int idade)`: Construtor completo
- `Amigos(String nome, String email, String telefone)`: Construtor sem idade

**Métodos:**
- Getters e Setters para todos os atributos
- `toString()`: Retorna representação em string

### **Ferramentas**
**Atributos:**
- `id`: Identificador único
- `nome`: Nome da ferramenta
- `marca`: Marca da ferramenta
- `valor`: Valor/preço (armazenado como String)
- `estaEmprestada`: Status (0 = disponível, 1 = emprestada)

**Construtores:**
- `Ferramentas()`: Construtor vazio
- `Ferramentas(String nome, String marca, String valor)`: Construtor básico
- `Ferramentas(int id, String nome, String marca, String valor)`: Construtor completo

**Métodos:**
- Getters e Setters para todos os atributos
- `getPreco()`: Retorna o valor (alias para getValor)

### **Emprestimos**
**Atributos:**
- `id`: Identificador único
- `idAmigos`: ID do amigo que pegou emprestado
- `idFerramentas`: ID da ferramenta emprestada
- `dataEmprestimo`: Data do empréstimo (LocalDate)
- `dataDevolucao`: Data prevista de devolução (LocalDate)
- `dataDevolvida`: Data real de devolução (LocalDate)
- `estaEmprestada`: Status (0 = devolvido, 1 = emprestado)

**Construtores:**
- `Emprestimos()`: Construtor vazio
- `Emprestimos(int id, int idAmigos, int idFerramentas, LocalDate dataEmprestimo, LocalDate dataDevolucao)`: Construtor com ID
- `Emprestimos(int idAmigos, int idFerramentas, LocalDate dataEmprestimo, LocalDate dataDevolucao, int estaEmprestada)`: Construtor sem ID

**Métodos:**
- Getters e Setters para todos os atributos

---

## 🛠️ **Utilitários (Util)**

### **Classe Util**
Funções auxiliares para formatação e conversão:

**Funções:**
- `obtemNum(String texto)`: Extrai todos os números de uma string
  - Usa regex para encontrar dígitos
  - Retorna Integer (0 se não encontrar números)

- `converterData(String data)`: Converte String para LocalDate
  - Formato esperado: "dd/MM/yyyy"
  - Usa DateTimeFormatter

- `converterData(LocalDate localDate)`: Converte LocalDate para String
  - Formato de saída: "dd/MM/yyyy"

- `converterData(Date date)`: Converte java.util.Date para LocalDate
  - Usa Calendar para extrair dia, mês e ano

- `verficarNumnoTexto(String texto)`: Verifica se texto contém números
  - Retorna `true` se NÃO contém números
  - Retorna `false` se contém números
  - Usa `obtemNum()` internamente

- `converterPreco(BigDecimal preco)`: Formata preço em formato monetário brasileiro
  - Formato: "R$ ###,###.##"
  - Separador decimal: vírgula
  - Separador de milhar: ponto

---

## 🔌 **Conexão com Banco de Dados**

### **Conexao**
**Função:**
- `getConnection()`: Estabelece conexão com banco MySQL
  - URL: `jdbc:mysql://localhost:3306/dbtooltracker`
  - Usuário e senha configuráveis
  - Retorna objeto Connection

---

## 📝 **DTO (Data Transfer Object)**

### **EmprestimosDTO**
Objeto de transferência para exibição de empréstimos na interface.

**Atributos:**
- `id`: ID do empréstimo
- `amigo`: Nome do amigo (String)
- `ferramenta`: Nome da ferramenta (String)
- `dataDevolucao`: Data de devolução formatada (String)
- `dataEmprestimo`: Data de empréstimo formatada (String)

**Construtor:**
- `EmprestimosDTO(int id, String amigo, String ferramenta, String dataDevolucao, String dataEmprestimo)`

**Métodos:**
- Getters e Setters para todos os atributos

---

## 🎯 **Fluxos Principais do Sistema**

### **Fluxo de Cadastro de Amigo:**
1. Usuário acessa tela de cadastro de amigos
2. Preenche nome, email e telefone
3. Sistema valida campos (nome sem números)
4. Controller chama `registrarAmigo()`
5. DAO insere no banco via `insertBD()`
6. Mensagem de sucesso e limpeza de campos

### **Fluxo de Cadastro de Ferramenta:**
1. Usuário acessa tela de cadastro de ferramentas
2. Preenche nome, marca e preço
3. Sistema valida campos e formata preço
4. Controller chama `registrarFerramenta()`
5. DAO insere no banco via `insertBD()`
6. Status inicial: não emprestada (0)

### **Fluxo de Empréstimo:**
1. Usuário acessa tela de empréstimos
2. Seleciona amigo e ferramenta disponível
3. Informa datas de empréstimo e devolução
4. Sistema valida campos
5. Controller cria objeto Emprestimos
6. DAO insere empréstimo via `insertBD()`
7. Sistema atualiza status da ferramenta para emprestada (1)

### **Fluxo de Devolução:**
1. Usuário seleciona empréstimo na tabela
2. Acessa tela de devolução
3. Informa ID do empréstimo
4. Controller chama `devolveFerramenta()`
5. DAO atualiza status do empréstimo para 0
6. Sistema busca informações do empréstimo
7. DAO atualiza status da ferramenta para disponível (0)

---

## 🔍 **Validações e Regras de Negócio**

### **Validações de Amigos:**
- Nome não pode conter números
- Todos os campos (nome, email, telefone) são obrigatórios
- ID deve ser numérico para edição/exclusão

### **Validações de Ferramentas:**
- Nome não pode conter números
- Todos os campos (nome, marca, preço) são obrigatórios
- ID deve ser numérico para edição/exclusão
- Preço é formatado automaticamente

### **Validações de Empréstimos:**
- Amigo e ferramenta devem ser selecionados
- Apenas ferramentas disponíveis podem ser emprestadas
- Datas devem estar no formato dd/MM/yyyy
- Todos os campos são obrigatórios

### **Regras de Status:**
- Ferramenta: 0 = disponível, 1 = emprestada
- Empréstimo: 0 = devolvido, 1 = ativo/emprestado

---

## 🎨 **Interface do Usuário**

### **Características:**
- Design moderno com cores corporativas (azul #0877C5, laranja #EB7803)
- Navegação intuitiva por cards
- Tabelas interativas para visualização de dados
- Validação em tempo real de campos
- Mensagens de feedback para todas as operações
- Formatação automática de valores monetários
- Máscaras de entrada para datas

### **Componentes Principais:**
- JFrame para janelas principais
- JTable para exibição de dados
- JComboBox para seleção de itens
- JFormattedTextField para entrada de datas
- JOptionPane para mensagens de diálogo
- CardLayout para navegação entre telas

---

## 🚀 **Como Executar**

1. **Pré-requisitos:**
   - Java 22 instalado
   - Maven configurado
   - MySQL rodando (porta 3306)
   - Banco de dados `dbtooltracker` criado

2. **Configuração do Banco:**
   - Execute o script de criação do banco (se disponível)
   - Configure credenciais em `Conexao.java`

3. **Execução:**
   ```bash
   mvn clean install
   java -jar target/A3_2025_2_GQS-1.0-SNAPSHOT.jar
   ```

4. **Ou execute a classe Principal:**
   ```bash
   mvn exec:java -Dexec.mainClass="com.mycompany.a3_2025_2_gqs.Principal"
   ```

---

## 📚 **Estrutura do Projeto**

```
src/
├── main/java/com/mycompany/a3_2025_2_gqs/
│   ├── Controller/          # Controladores (lógica de negócio)
│   ├── DAO/                # Acesso a dados
│   ├── DTO/                # Objetos de transferência
│   ├── Model/              # Modelos de dados
│   ├── Util/               # Utilitários
│   ├── View/               # Interfaces gráficas
│   └── Principal.java      # Classe principal
└── test/java/              # Testes unitários e de integração
```

---

## ✅ **Testes**

O sistema possui cobertura de testes para:
- Modelos (Amigos, Ferramentas, Emprestimos)
- DAOs (AmigosDAO, FerramentaDAO, EmprestimosDAO)
- Controllers
- Utilitários (Util)
- Views (com suporte a Xvfb para ambientes headless)

Execute os testes com:
```bash
mvn test
```

Para gerar relatório de cobertura:
```bash
mvn verify
```

---

## 📄 **Licença**

Este projeto foi desenvolvido como trabalho acadêmico para a disciplina de Gestão de Qualidade de Software.

---

**Desenvolvido com ❤️ pela equipe ToolTracker**

>>>>>>> Stashed changes
