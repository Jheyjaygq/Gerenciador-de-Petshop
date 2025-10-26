
# 🐾 CRUD de Animais (Pet Shop)

Esta é uma aplicação de console, construída com **Spring Boot**, que permite o gerenciamento (CRUD - Criar, Ler, Atualizar, Deletar) de animais. A aplicação utiliza um banco de dados **SQLite** para persistência de dados.

## Recurso Gerenciado: Animal

A aplicação gerencia um único recurso principal: o **Animal**. Este recurso é representado no banco de dados pela tabela `animais` e no código pela `AnimalEntity`.

Abaixo estão as propriedades do recurso `Animal`:
---

| Propriedade | Tipo de Dado | Obrigatório |
| :--- | :--- | :--- |
| `id` | `Long` | **Sim** |
| `nome` | `String` | **Sim** |
| `especie` | `String` | **Sim** |
| `raca` | `String` | Não |
| `idade` | `int` | **Sim** |
| `dataCadastro` | `LocalDate` | Não |

---

## Tecnologias Utilizadas

  * **Linguagem:** Java (Versão 21)
  * **Framework Principal:** Spring Boot 3.5.7
  * **Persistência:** Spring Data JPA (Hibernate)
  * **Banco de Dados:** SQLite
  * **Build:** Apache Maven

-----

## Pré-requisitos e Instalação

Para compilar e executar este projeto, você precisará ter os seguintes softwares instalados em seu sistema:

1.  **Java Development Kit (JDK) - Versão 21 ou superior**
    * Você pode baixar o JDK (por exemplo, OpenJDK) no site oficial da [Oracle](https://www.oracle.com/java/technologies/downloads/).

2.  **Apache Maven**
    * O Maven é usado para gerenciar as dependências e compilar o projeto. Você pode baixá-lo em [maven.apache.org](https://maven.apache.org/download.cgi).
    * *Nota: Muitas IDEs (como IntelliJ IDEA e Eclipse) já vêm com o Maven embutido.*

3.  **Git**
    * O Git é necessário para baixar (clonar) o código-fonte do repositório.
    * Você pode baixá-lo em [git-scm.com](https://git-scm.com/downloads).
      
### Sobre o Banco de Dados (SQLite)

**Nenhuma instalação manual do SQLite é necessária.**

O projeto está configurado no arquivo `pom.xml` para usar o driver `sqlite-jdbc`. Quando você compilar o projeto com o Maven, ele baixará automaticamente esse driver. A aplicação Java usará esse driver para criar e gerenciar um arquivo chamado `animais.db` no diretório raiz do projeto. Este arquivo é o seu banco de dados.


### Passos para Instalação

1.  **Clonar o repositório:**
    Abra seu terminal ou prompt de comando e execute o comando abaixo para baixar o código-fonte:

    ```bash
    git clone https://github.com/Jheyjaygq/Gerenciador-de-Petshop
    ```

2.  **Navegar para o diretório do projeto:**

    ```bash
    cd Gerenciador-de-Petshop
    ```

3.  **Baixar dependências e compilar:**
    Execute o comando do Maven para baixar todas as dependências (incluindo o driver SQLite) e compilar o projeto:

    ```bash
    mvn clean install
    ```

-----



## Como Usar a Aplicação


Para iniciar a aplicação, certifique-se de que você está no diretório raiz do projeto (o mesmo local onde está o arquivo `pom.xml`) e execute o seguinte comando no seu terminal:

```bash
mvn spring-boot:run

```

Ao iniciar, a aplicação criará automaticamente o arquivo `animais.db` (caso não exista) e exibirá o menu principal no console:

=== Gerenciamento do Pet Shop ===
1. Listar animais
2. Cadastrar animal
3. Buscar por nome
4. Deletar animal
5. Atualizar animal
0. Sair
Escolha uma opção:
```

Basta digitar o número da opção desejada e pressionar ENTER.

### 1\. Listar animais

  * **Descrição:** Exibe uma lista de todos os animais atualmente cadastrados no banco de dados.
  * **Exemplo de Saída:**
    ```
    Animais cadastrados:
    Id:1 | Nome: Max | Especie: Cachorro | Raça: Labrador | Idade:5 | Data de Cadastro: 2025-10-25
    Id:2 | Nome: Angelina | Especie: Gato | Raça: Siamês | Idade:2 | Data de Cadastro: 2025-10-25
    ```

### 2\. Cadastrar animal

  * **Descrição:** Inicia um formulário interativo para adicionar um novo animal. Campos obrigatórios não podem ser deixados em branco.
  * **Exemplo de Interação:**
    ```
    Cadastro de Animal:
    Nome (obrigatório): Floco
    Espécie (obrigatório): Gato
    Raça (opcional - ENTER para pular): Siamês
    Idade (0-100): 2
    Animal cadastrado! ID = 2
    ```

### 3\. Buscar por nome

  * **Descrição:** Permite pesquisar animais. A busca **não** diferencia maiúsculas de minúsculas e encontrará animais cujo nome *contenha* o texto digitado.
  * **Exemplo de Interação:**
    ```
    Digite o nome do animal que deseja buscar:
    rex
    Animais encontrados com o nome: rex
    Id:1 | Nome: Rex | Especie: Cachorro | Raça: Labrador | Idade:5 | Data de Cadastro: 2025-10-26
    ```

### 4\. Deletar animal

  * **Descrição:** Remove um animal do banco de dados com base no `ID` fornecido.
  * **Exemplo de Interação:**
    ```
    Deletar animal
    ID para deletar (digite 0 para cancelar): 1
    Animal com ID 1 deletado.
    ```
    *Se o ID não existir, a aplicação informará que não o encontrou.*

### 5\. Atualizar animal

  * **Descrição:** Permite modificar os dados de um animal existente. Você primeiro informa o `ID` do animal que deseja alterar. Em seguida, a aplicação mostra o valor atual de cada campo e solicita o novo valor.
  * **Para manter o valor atual de um campo, basta pressionar ENTER sem digitar nada.**
  * **Exemplo de Interação:**
    ```
    Atualizar animal
    Digite o ID do animal que deseja atualizar (ou 0 para cancelar): 2
    Animal encontrado:
    Nome atual: Floco
    Novo nome (ENTER para manter): [ENTER]
    Espécie atual: Gato
    Nova espécie (ENTER para manter): [ENTER]
    Raça atual: Siamês
    Nova raça (ENTER para manter): Persa
    Idade atual: 2
    Nova idade (ou ENTER para manter): 3
    Animal atualizado com sucesso!
    ID: 2 | Nome: Floco | Espécie: Gato | Raça: Persa | Idade: 3 | Data de Cadastro: 2025-10-26
    ```

### 0\. Sair

  * **Descrição:** Encerra a aplicação.
