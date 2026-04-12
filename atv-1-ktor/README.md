# Projeto Ktor - Gerenciamento de Livros 📚

**Nome Completo:** Ana Flávia Vieira Kunzendorff  
**Instituição:** Fatec Registro  
**Disciplina:** Linguagem de Dispositivos Móveis (LDDM)

Este projeto é uma API REST desenvolvida em Kotlin com Ktor para gerenciamento de livros e autores, cumprindo integralmente os requisitos de arquitetura e persistência solicitados.

## 🛠️ Tecnologias e Requisitos Cumpridos
- **Kotlin & Ktor Framework**
- **Gradle JVM:** jbr-21 (JetBrains Runtime)
- **Banco de Dados:** PostgreSQL via Docker Compose
- **ORM:** Exposed (implementando Repository Pattern)
- **Shared Models:** Data classes serializáveis compartilhadas entre camadas.
- **Migrações & Seed Data:** Tabelas criadas automaticamente via `SchemaUtils` com carga inicial de dados.
- **Documentação:** Swagger UI integrada e funcional.
- **Arquitetura:** 2 arquivos de Routes independentes (`BookRoutes.kt` e `AuthorRoutes.kt`) com CRUD completo (GET, POST, PUT, DELETE).

## 🚀 Como Rodar o Projeto

### 1. Variáveis de Ambiente
Certifique-se de que o arquivo `.env.example` está na raiz. Para rodar localmente, você pode criar um arquivo `.env` baseado nele ou usar as credenciais padrão já configuradas no `Databases.kt`.

### 2. Subir o Banco de Dados (Docker)
No terminal, execute o comando abaixo para iniciar o container do PostgreSQL:
```bash
docker-compose up -d
```
### 3. Executar a Aplicação
Certifique-se de que o JDK 21 está selecionado e execute:

```bash
./gradlew run
```

### 4. Acessar a Documentação
Com o servidor rodando, a documentação Swagger estará disponível em:
👉 [http://localhost:8080/swagger](http://localhost:8080/swagger)

---

### 📂 Estrutura de Pastas
* **src/main/kotlin/models/**: Definição de tabelas e DTOs.
* **src/main/kotlin/repositories/**: Classes de acesso a dados.
* **src/main/kotlin/routes/**: Endpoints da API segregados por domínio.
* **src/main/kotlin/plugins/**: Configurações de infraestrutura (DB, Swagger, Serialization).

