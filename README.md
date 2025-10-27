# Agendador de Tarefas BFF

**Agendador de Tarefas BFF** é o backend principal responsável por orquestrar e centralizar a comunicação entre os microserviços do sistema de agendamento de tarefas.  
O projeto permite criar, gerenciar e automatizar tarefas com envio de notificações por e-mail, utilizando tecnologias modernas e arquitetura baseada em microserviços.

---

## Tecnologias Utilizadas

### **Back-end**
-  **Java 17**
-  **Spring Boot**
-  **Spring Security** — autenticação e controle de acesso
-  **Spring Data JPA** — persistência de dados no PostgreSQL
-  **Spring Data MongoDB** — persistência de logs e histórico no MongoDB
-  **Spring Cloud OpenFeign** — consumo de APIs dos microserviços
-  **MapStruct** — conversão de DTOs e entidades
-  **Cron Scheduler** — agendamento de tarefas automáticas
-  **Google Mail API** — envio de notificações por e-mail

### **Infraestrutura**
-  **PostgreSQL**
-  **MongoDB**
-  **Docker & Docker Compose**
-  **Swagger (OpenAPI)** — documentação da API
-  **Maven** — gerenciamento de dependências e build

---

## Arquitetura

O projeto segue uma arquitetura de **microserviços**, onde o **BFF (Backend For Frontend)** atua como ponto central de integração.  
Cada microserviço é responsável por uma funcionalidade específica e se comunica com o BFF via **OpenFeign**.


---

## Como Executar o Projeto

### Opção 1: Usando Docker (recomendado)

>  Esta é a forma mais simples e rápida de rodar o projeto.  
> Basta ter **Docker** e **Docker Compose** instalados.

#### Clone o repositório
```
git clone https://github.com/seu-usuario/task-scheduler-bff.git
cd task-scheduler-bff
```
O Docker irá subir:
- O BFF
- Os microserviços necessários
- PostgreSQL
- MongoDB
  
Acesse a documentação Swagger
Após o build, acesse no navegador:
```
http://localhost:8083/swagger-ui/index.html
```

### Opção 2: Executando sem Docker

Essa opção é útil para desenvolvimento local e debugging.

### Faça o fork e clone dos repositórios

Clone este repositório e os microserviços associados (Usuario, Agendador de Tarefas e Notificacao):

```
git clone https://github.com/DLNascimento/usuario.git
git clone https://github.com/DLNascimento/agendador-tarefas.git
git clone https://github.com/DLNascimento/notificacao.git
```

## Configure as variáveis de ambiente
Crie um arquivo .env ou configure manualmente as variáveis no application.yml:
```
POSTGRES_URL=jdbc:postgresql://localhost:5432/seudb
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
MONGO_URL=mongodb://localhost:27017/logs
GOOGLE_MAIL_API_KEY=sua_chave_aqui
```

## Inicie os serviços
Start cada microserviço antes do BFF
# Em terminais separados
```
cd usuario && mvn spring-boot:run
cd agendador-de-tarefas && mvn spring-boot:run
cd notificacao && mvn spring-boot:run
```

## Acesse o Swagger:
```
http://localhost:8083/swagger-ui/index.html
```

### Autenticação e Segurança

O projeto utiliza Spring Security com autenticação baseada em JWT.
Para acessar endpoints protegidos, primeiro obtenha um token via /auth/login e use-o no Authorization Header:

Authorization: Bearer <seu_token_jwt>


### Envio de E-mails

O agendador usa a Google Mail API para enviar notificações automáticas conforme os cron jobs definidos.
Cada tarefa criada pode ter uma configuração de agendamento personalizada.


### Documentação da API

O projeto conta com documentação interativa via Swagger/OpenAPI:
 http://localhost:8083/swagger-ui/index.html

### Aprendizados Técnicos

Durante o desenvolvimento deste projeto, foram aplicados conceitos avançados de:
- Arquitetura de microserviços
- Integração entre múltiplos bancos (SQL e NoSQL)
- Comunicação síncrona com OpenFeign
- Segurança com JWT
- Agendamento com Spring Scheduler
- Containerização com Docker
- Documentação interativa de APIs com Swagger

### Autor
Diego Lopes do Nascimento - [Linkedin](www.linkedin.com/in/diego-nascimento-b33311221)
