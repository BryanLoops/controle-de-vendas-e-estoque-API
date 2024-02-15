# API de Controle de Vendas e Estoque
# Esta é uma API RESTful desenvolvida para controle de vendas e estoque de uma empresa.

## Funcionalidades Principais
- Cadastro, atualização, listagem e exclusão de produtos.
- Cadastro, listagem e exclusão de usuários.
- Cadastro e listagem de vendas.
- Cadastro e listagem de estoque.
- Geração de relatórios de vendas por CPF de usuário.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Security
- Hibernate
- MySQL (ou outro banco de dados relacional)
- Swagger (para documentação da API)

## Configuração do Ambiente de Desenvolvimento
- Certifique-se de ter o JDK 17 (Java Development Kit) instalado em sua máquina.
- Clone este repositório em seu ambiente local.
- Configure o banco de dados MySQL (ou outro banco relacional) e atualize as informações de conexão no arquivo application.properties(crie o banco antes de tudo para que o flyway funcione).
- Importe o projeto em sua IDE preferida (Eclipse, IntelliJ, etc.).
- Execute a aplicação. Acesse http://localhost:8080/swagger-ui.html para visualizar a documentação da API.

## Guia Rápido de Endpoints
- /usuarios - GET, POST, PUT, DELETE - CRUD para administração de usuários.
- /usuarios/relatorio/{cpf} - GET - Endpoint para o relatório de compras e informações individuais detalhadas com base no cpf. 
- /produtos - GET, POST, PUT, DELETE - CRUD para administração de produtos.
- /estoque  - POST - Endpoint para cadastro de entradas no estoque.
- /vendas   - GET, POST - Endpoint para cadastro individual de vendas e consulta dos totais vendidos por mês, apresentando o status com base no resultado das vendas.
- /vendas/upload - POST - Endpoint para cadastro de vendas a partir de um arquivo .xlsx contendo código do produto, cpf do usuário, quantidade e data.

## Documentação da API
A documentação da API estará disponível em http://localhost:8080/swagger-ui.html. Você pode usar essa interface para explorar os endpoints disponíveis, seus parâmetros e respostas.
