# 📚 LiterAlura

O **LiterAlura** é um projeto interativo para criar um catálogo de livros, consumindo dados de uma API, armazenando informações em um banco de dados e exibindo resultados no console. Com no mínimo 5 opções de interação, os usuários poderão buscar, filtrar e explorar livros e autores de forma dinâmica.

## 🎯 Funcionalidades

- **Buscar Livros por Título**
- **Listar Livros no Banco de Dados**
- **Listar Autores no Banco de Dados**
- **Buscar Autores Vivos em Um Determinado Ano**
- **Listar Livros por Idioma**

## ✨ Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e ferramentas:

- **Java JDK**  
  Utilizado para compilar e executar o código Java.  
  🔗 [Baixe a versão mais recente do Java LTS](https://www.oracle.com/java/technologies/javase-downloads.html)

- **Maven** (versão 4 ou superior)  
  Gerenciador de dependências e automação de builds.

- **Spring Boot** (versão 3.4.1)  
  Framework para desenvolvimento rápido e eficiente de aplicações Java.  
  🔗 [Explore o Spring Initializr](https://start.spring.io/)

- **PostgreSQL** (versão 17 ou superior)  
  Banco de dados relacional utilizado para armazenar e gerenciar os dados do projeto.  
  🔗 [Baixe o PostgreSQL](https://www.postgresql.org/download/)

- **IDE IntelliJ IDEA** (opcional)  
  Ferramenta recomendada para desenvolvimento, com suporte robusto para Java e Spring Boot.  
  🔗 [Baixe o IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

### Observação

Certifique-se de ter todas as ferramentas e tecnologias acima instaladas e configuradas corretamente antes de executar o projeto. Caso utilize outra IDE ou ferramentas diferentes, ajuste o ambiente de acordo com sua preferência.

## 🛠️ Executando o Projeto no IntelliJ IDEA

### 1. **Clonar o Repositório**
- Faça o clone do repositório para a sua máquina local.

### 2. **Abrir o Projeto no IntelliJ IDEA**
- Abra o IntelliJ IDEA.
- Navegue até **File > Open** e selecione a pasta do projeto clonado.

### 3. **Configurar o PostgreSQL**
1. **Crie um banco de dados no PostgreSQL com o nome desejado**
2. **Certifique-se de que o PostgreSQL está exucutando corretamente ou ajuste conforme necessário**
3. **Configuração de Variáveis de Ambiente:** Este projeto utiliza variáveis de ambiente para configurar a conexão com o banco de dados. Certifique-se de configurá-las!

- `DB_HOST`: Host onde o banco de dados está rodando. Exemplo: `localhost` ou `db.myserver.com`.
- `DB_NAME`: Nome do banco de dados. Exemplo: `meubanco`.
- `DB_USER`: Usuário com permissões de acesso ao banco de dados.
- `DB_PASSWORD`: Senha do usuário configurado no banco de dados.

### 4. **Configurar o Arquivo `application.properties`**
No IntelliJ, configure o arquivo `application.properties` para utilizar as variáveis de ambiente:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

### 5. **Rodar o Projeto**
- No IntelliJ, localize o arquivo principal: `LiteraluraApplication.java`.
- Clique com o botão direito no arquivo e selecione **Run** para iniciar a aplicação.

### 6. **Testar as Funcionalidades**
- Após iniciar a aplicação, use o terminal ou qualquer interface disponível para interagir com o menu e testar as funcionalidades.
- Certifique-se de que o banco de dados está configurado corretamente para persistência dos dados.
