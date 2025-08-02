# 📖 Diário de Leituras - API Backend (Quarkus)

Este é o repositório do backend para a aplicação "Diário de Leituras". Desenvolvido com Quarkus e Java, este serviço é responsável por toda a lógica de negócio, persistência de dados e por servir uma API RESTful completa para o frontend.

O projeto utiliza uma arquitetura em camadas (Resource -> Service -> Entity) para uma clara separação de responsabilidades e fácil manutenção.

---

## 🚀 Principais Features

* **API RESTful Completa:** Endpoints para todas as operações CRUD (Create, Read, Update, Delete) de livros e suas reflexões.
* **Persistência de Dados Simplificada:** Utiliza o Hibernate ORM com o padrão Panache para tornar as operações de banco de dados extremamente simples e produtivas.
* **Separação de Responsabilidades:** A lógica de negócio é isolada em uma camada de Serviço, deixando a camada de Recurso (API) focada em lidar com requisições e respostas HTTP.
* **Consultas Personalizadas:** Implementação de endpoints específicos, como um ranking de livros baseado na nota.
* **Configuração Mínima:** Demonstra a facilidade de configurar banco de dados, CORS e outras propriedades no Quarkus.

---

## Endpoints da API

| Método | Endpoint                                    | Descrição                                         | Exemplo de Body (JSON)                                |
| :----- | :------------------------------------------ | :-------------------------------------------------- | :---------------------------------------------------- |
| `GET`  | `/livros`                                   | Lista todos os livros.                              | N/A                                                   |
| `GET`  | `/livros/{id}`                              | Busca um livro específico pelo ID.                  | N/A                                                   |
| `POST` | `/livros`                                   | Cria um novo livro.                                 | `{ "titulo": "...", "autor": "...", "status": "LENDO" }` |
| `PUT`  | `/livros/{id}`                              | Atualiza um livro existente.                        | `{ "titulo": "...", "autor": "...", "nota": 5 }`        |
| `DELETE`| `/livros/{id}`                              | Deleta um livro.                                    | N/A                                                   |
| `GET`  | `/livros/ranking`                           | Retorna os livros lidos, ordenados pela maior nota. | N/A                                                   |
| `GET`  | `/livros/{livroId}/reflexoes`               | Lista todas as reflexões de um livro específico.    | N/A                                                   |
| `POST` | `/livros/{livroId}/reflexoes`               | Adiciona uma nova reflexão a um livro.              | `{ "conteudo": "Ótima reflexão..." }`                  |

---

## 🛠️ Tech Stack

* **Framework:** [Quarkus](https://quarkus.io/)
* **Linguagem:** Java 17+
* **API:** REST com JAX-RS (RESTEasy Reactive)
* **Persistência:** Hibernate ORM com Panache
* **Banco de Dados (Dev):** H2 (Em memória)
* **Injeção de Dependência:** Quarkus CDI (Contexts and Dependency Injection)
* **Build Tool:** Maven

---

## 💡 Conceitos-Chave do Quarkus Aplicados

Este projeto é um excelente exemplo prático de diversos recursos poderosos do Quarkus.

### 1. **RESTEasy Reactive e JAX-RS**
É o coração da nossa API. Usamos anotações padrão do JAX-RS para definir nossos endpoints de forma declarativa e simples.
* `@Path("/livros")`: Define a URL base para uma classe de Resource.
* `@GET`, `@POST`, `@PUT`, `@DELETE`: Mapeiam os métodos HTTP para os métodos Java.
* `@Produces(MediaType.APPLICATION_JSON)`: Especifica que o método retornará dados no formato JSON.
* `@Consumes(MediaType.APPLICATION_JSON)`: Especifica que o método espera receber dados em JSON.
* `@PathParam("id")`: Extrai um valor da URL (ex: o `123` de `/livros/123`).

### 2. **CDI - Injeção de Dependência**
O Quarkus gerencia o ciclo de vida dos nossos objetos. Em vez de criarmos instâncias manualmente (ex: `new LivroService()`), nós delegamos essa tarefa ao framework.
* `@ApplicationScoped`: Transforma uma classe (como `LivroService`) em um "bean" gerenciado, ou seja, um componente reutilizável que o Quarkus conhece.
* `@Inject`: "Pede" ao Quarkus para fornecer uma instância de um bean. Em `LivroResource`, usamos `@Inject LivroService` para que a classe de API pudesse usar a lógica de negócio sem se preocupar em como criá-la.

### 3. **Hibernate ORM com Panache**
Esta é a feature que mais acelera o desenvolvimento com banco de dados no Quarkus. Panache simplifica a persistência de dados através do padrão *Active Record*.
* **Herança de `PanacheEntity` / `PanacheEntityBase`:** Ao estender essas classes, nossas entidades (como `Livro`) ganham superpoderes, como métodos estáticos para operações comuns.
* **Simplicidade nas Operações:** Em vez de escrever código complexo de acesso a dados (DAOs), nós simplesmente chamamos:
  * `Livro.listAll()` para buscar todos os registros.
  * `Livro.findById(id)` para buscar um por ID.
  * `livro.persist()` para salvar ou atualizar.
  * `livro.delete()` para remover.
* **Consultas Simplificadas:** Permite escrever consultas complexas de forma muito mais legível, como `Livro.list("status = ?1 ORDER BY nota DESC", "LIDO")`.

### 4. **Configuração Simplificada**
Toda a configuração da aplicação (conexão com o banco de dados, CORS, etc.) é centralizada no arquivo `src/main/resources/application.properties`. Isso torna o gerenciamento de diferentes ambientes (desenvolvimento, produção) muito mais fácil.

### 5. **Modo de Desenvolvimento (Live Reload)**
Ao rodar o projeto com `./mvnw quarkus:dev`, ativamos o modo de desenvolvimento do Quarkus. Qualquer alteração feita no código-fonte é compilada e recarregada automaticamente em milissegundos, sem a necessidade de reiniciar o servidor. Isso proporciona um ciclo de desenvolvimento extremamente rápido e produtivo.

---

## 🚀 Como Executar o Backend

1.  **Pré-requisitos:**
  * JDK 17 ou superior
  * Apache Maven 3.8+

2.  **Execução:**
    ```bash
    # Navegue até a pasta da API
    cd diario-de-leituras-api

    # Execute o Quarkus em modo de desenvolvimento
    ./mvnw quarkus:dev
    ```

3.  O backend estará rodando em `http://localhost:8080`.
  * Acesse a **Dev UI** em `http://localhost:8080/q/dev-ui` para explorar os beans, endpoints e outras funcionalidades.

# Manual Introdutório ao Quarkus: O Java Supereônico e Subatômico

Este documento serve como uma introdução prática ao [Quarkus](https://quarkus.io/), o framework que impulsiona o backend desta aplicação. O objetivo é explicar seus conceitos fundamentais, como ele foi utilizado neste projeto e quais são suas principais diferenças em relação a um framework consolidado como o Spring Boot.

## 1. O que é Quarkus? A Filosofia por Trás da Velocidade

**Quarkus é um framework Java moderno, open-source e otimizado para a nuvem, contêineres (Docker, Kubernetes) e ambientes serverless.** Ele foi criado para tornar o Java uma plataforma de altíssima performance nesses novos cenários.

Sua principal inovação é a filosofia de **"compilação à frente" (Ahead-of-Time Compilation - AOT)**.

Pense nisso da seguinte forma:
* **Frameworks Tradicionais (como o Spring Boot):** Fazem muita "mágica" durante a inicialização da aplicação (runtime). Eles escaneiam o código, configuram a injeção de dependência, criam proxies, etc. Isso torna o framework muito flexível, mas cobra um preço: tempo de startup mais longo e maior consumo de memória. É como arrumar a mochila na hora de sair para a viagem.
* **Quarkus:** Ele faz o máximo de trabalho possível durante o **tempo de compilação** (build time). Ele analisa seu código, resolve as dependências e gera o bytecode otimizado. Quando a aplicação finalmente inicia, ela já está pronta para rodar, sem precisar de configurações demoradas. A mochila já está pronta e ao lado da porta.

O resultado dessa abordagem são:
* **Startup Quase Instantâneo:** Aplicações iniciam em milissegundos.
* **Baixo Consumo de Memória:** Ideal para contêineres com recursos limitados.
* **Tamanho de Pacote Menor:** Otimizado para implantações rápidas.
* **Excelente Experiência de Desenvolvimento:** Graças ao seu incrível Live Reloading.

## 2. Os Pilares do Quarkus: Principais Conceitos e Métodos

Neste projeto, utilizamos os principais recursos que tornam o Quarkus produtivo e poderoso.

### 2.1. API REST com JAX-RS (RESTEasy Reactive)

Quarkus utiliza o padrão **JAX-RS** para construir APIs REST. Isso significa que usamos anotações padrão do Java para definir nossos endpoints de forma declarativa e simples.

* `@Path("/livros")`: Define a URL base da classe.
* `@GET`, `@POST`, `@PUT`, `@DELETE`: Mapeiam os métodos HTTP.
* `@Produces` e `@Consumes`: Definem o tipo de mídia (ex: `application/json`).
* `@PathParam("id")`: Extrai um parâmetro da URL.

**Exemplo do nosso projeto:**
```java
@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
public class LivroResource {

    @GET
    @Path("/{id}")
    public Response encontrarPorId(@PathParam("id") Long id) {
        // ... lógica para buscar um livro ...
    }
}
```

### 2.2. Injeção de Dependências (CDI - Contexts and Dependency Injection)

O Quarkus usa o padrão CDI para gerenciar o ciclo de vida dos nossos componentes. Em vez de criarmos objetos manualmente (`new LivroService()`), nós delegamos essa tarefa ao framework.

* `@ApplicationScoped`: Transforma uma classe em um componente gerenciado (um "bean") que existe durante toda a vida da aplicação.
* `@Inject`: Pede ao Quarkus para "injetar" uma instância de um bean em outra classe.

**Exemplo do nosso projeto:**
```java
@Path("/livros")
public class LivroResource {

    @Inject // O Quarkus nos fornece a instância de LivroService
    LivroService livroService;

    @GET
    public Response listarTodos() {
        return Response.ok(livroService.listarTodos()).build();
    }
}
```

### 2.3. Persistência de Dados com Panache

Para simplificar a interação com o banco de dados via Hibernate ORM, o Quarkus oferece o **Panache**. Ele implementa o padrão *Active Record*, onde a própria entidade (ex: `Livro`) contém os métodos para manipular seus dados.

Isso elimina a necessidade de criar classes de Repositório (DAOs) para operações simples.

**Exemplos do nosso projeto:**
```java
// Buscar todos os livros
List<Livro> todos = Livro.listAll();

// Buscar um livro por ID
Livro livro = Livro.findById(id);

// Salvar ou atualizar
livro.persist();

// Query customizada
List<Livro> ranking = Livro.list("status = 'LIDO' ORDER BY nota DESC");
```

## 3. Quarkus vs. Spring Boot: As Principais Diferenças

Ambos são frameworks fantásticos, mas com filosofias e casos de uso ideais diferentes.

| Característica          | Quarkus                                                | Spring Boot                                                          |
| :---------------------- | :----------------------------------------------------- | :------------------------------------------------------------------- |
| **Filosofia Principal** | Compilação à Frente (AOT) para performance             | Flexibilidade e configuração em tempo de execução (Runtime)          |
| **Tempo de Startup** | Extremamente rápido (milissegundos)                    | Rápido, mas mais lento que o Quarkus (segundos)                      |
| **Uso de Memória** | Muito baixo                                            | Menor que Java EE tradicional, mas maior que o Quarkus               |
| **Build Nativo (GraalVM)** | Foco principal, integrado desde o início               | Suportado, mas adicionado posteriormente ao ecossistema              |
| **Padrões Java** | Baseado em padrões (JAX-RS, CDI, JPA)                  | Ecossistema próprio muito rico (`@RestController`, `@Autowired`, etc.) |
| **Ecossistema** | Crescendo rapidamente, focado em extensões             | Gigantesco, maduro e com integração para quase tudo                  |

### Análise Detalhada das Diferenças:

1.  **Performance (Startup e Memória):** Esta é a maior vantagem do Quarkus. Graças à sua compilação AOT, ele inicia muito mais rápido e consome muito menos memória. Isso é crucial em ambientes serverless (onde o tempo de inicialização é cobrado) e em contêineres (onde você pode colocar mais instâncias em uma mesma máquina).

2.  **Padrões vs. Ecossistema Próprio:** O Quarkus se apoia fortemente em especificações padrão do Java (agora Jakarta EE). Se você já conhece JAX-RS e CDI, se sentirá em casa. O Spring, por outro lado, criou seu próprio ecossistema de anotações e abstrações (`@RestController` vs. `@Path`, `@Autowired` vs. `@Inject`, `Spring Data` vs. `Panache`). Ambas as abordagens têm méritos; a do Quarkus é mais "padrão Java", enquanto a do Spring é extremamente poderosa dentro do seu próprio universo.

3.  **Build Nativo com GraalVM:** O Quarkus foi projetado desde o início com a compilação nativa em mente. Compilar uma aplicação Quarkus para um executável nativo com GraalVM resulta em um startup *instantâneo* e um uso de memória ainda menor. Embora o Spring também suporte compilação nativa, a experiência com o Quarkus é frequentemente vista como mais fluida e integrada.

4.  **Experiência de Desenvolvimento:** Ambos são excelentes. O Live Reloading do Quarkus é considerado um dos melhores do mercado, fornecendo feedback quase instantâneo. A Dev UI (`/q/dev-ui`) também é uma ferramenta fantástica. O Spring, por sua vez, se beneficia de uma comunidade gigantesca, documentação vasta e décadas de soluções para problemas comuns.

## 4. Conclusão: Quando Escolher Quarkus?

Quarkus não veio para substituir o Spring, mas para oferecer uma alternativa de alta performance para cenários específicos.

**Escolha Quarkus quando:**
* Você está construindo **microsserviços** ou funções **serverless** (FaaS).
* Sua aplicação será implantada em **contêineres (Docker/Kubernetes)** e a eficiência de recursos (memória, CPU) e a velocidade de escalonamento (startup rápido) são cruciais.
* Você valoriza a conformidade com os padrões Jakarta EE.
* Você quer a melhor performance possível para uma aplicação Java, especialmente com compilação nativa.

O Spring Boot ainda é uma escolha fantástica e segura para uma vasta gama de aplicações, especialmente monolitos complexos e sistemas onde seu ecossistema maduro é uma grande vantagem.

O Quarkus chegou para provar que o Java pode ser incrivelmente rápido, leve e perfeitamente adaptado para a nuvem.