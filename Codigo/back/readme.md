# Backend 
A API foi desenvolvida utilizando **Spring Boot**, com suporte à autenticação JWT, documentação via Swagger/OpenAPI, e integração com banco de dados **PostgreSQL**.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI
- Maven

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

- Java 17+
- PostgreSQL
- Maven

### Passos para execução local

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/s1mulado-backend.git
cd s1mulado/s1mulado
```

2. Configure seu banco de dados PostgreSQL:

Crie um banco chamado `s1mulado` e adicione as credenciais no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/s1mulado
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Compile e execute a aplicação:

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

## 🔐 Segurança

O projeto utiliza autenticação via JWT, com filtros de segurança configurados por meio da classe `SecurityFilter`. O token é necessário para acessar rotas protegidas.

---