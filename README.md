# Sistema de gerenciamento de Zoológico - Desafio técnico

## Sobre

Este projeto foi desenvolvido para um desafio técnico que consiste em um sistema para o gerenciamento de um zoológico. O sistema permite o cadastro de animais e de cuidados, onde cada cuidado é relacionado a um determinado animal, permtindo controle de rotina dos animais e seu bem estar.

 
## Tecnologias utilizadas

Linguagem Java 21

Framework Spring Boot

Angular 17

Banco relacional H2 em memória


## Requisitos para execução

Java 21+

Node.js 

Angular CLI 17+

*Opcional:* Maven


## Endpoints REST

Tanto as entidades Animal quanto Cuidado possuem as 4 operações CRUD distintas (GET, POST, PUT, DELETE) e busca por ID. Além disso, a entidade Animal possui rotas GET com filtros de habitat e espécie.


## Passos para execução:

1. Clone o repositório com Git ou baixe o arquivo ZIP e extraia
2. Navegue até a pasta `server` e execute `java -jar target/server-0.0.1-SNAPSHOT.jar` (se estiver usando Maven, execute `mvn spring-boot:run` na pasta raíz `server`)
3. Navegue até a pasta `front` e execute `ng serve`
4. Acesse `http://localhost:4200` no navegador


## Extra:

→ Após iniciar a aplicação, você pode também acessar o Swagger UI para visualização dos endpoints e executar requisições diretamente para o servidor:

Swagger UI: `http://localhost:8080/swagger-ui/index.html`
